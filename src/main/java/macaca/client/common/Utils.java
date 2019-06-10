package macaca.client.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import javafx.util.Callback;
import macaca.client.model.JsonWireStatus;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
public class Utils {
//    private final Log log = LogFactory.getLog(getClass());

    private HttpGet httpget = null;
    //Use static variables to ensure that the same connection pool is used when
    // multiple macacaClient instances are initialized in the application.
    private static CloseableHttpClient httpclient;
    static {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        //max size conect per host route
        connectionManager.setDefaultMaxPerRoute(500);
        //max size connect
        connectionManager.setMaxTotal(1000);
        HttpClientBuilder builder = HttpClientBuilder.create();

        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom().setCookieSpec(
                CookieSpecs.STANDARD);
        //set timeout config
        RequestConfig requestConfig = requestConfigBuilder.setConnectTimeout(50000)
                .setSocketTimeout(50000).build();

        builder.setConnectionManager(connectionManager).setDefaultHeaders(Arrays.asList(
                new BasicHeader("Accept", "*/*"),
                new BasicHeader("Connection", "keep-alive")));
        builder.setDefaultRequestConfig(requestConfig);
        httpclient = builder.build();
    }

    private CloseableHttpResponse response = null;
    private HttpEntity entity = null;
    private JSONObject jsonResponse = null;
    private String stringResponse = "";
    private MacacaDriver driver;

    private static Callback<Exception, Boolean> exceptionCallback;

    public Utils(MacacaDriver driver) {
        this.driver = driver;
    }

    // you can set Exception Callback for request macaca server error process.
    public static void setExceptionCallback(Callback<Exception, Boolean> callback) {
        exceptionCallback = callback;
    }

    private static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置日期格式

    private void printResponse(String stringResponse) throws Exception {
        if (stringResponse.length() > 800) {
            System.out.println(sDateFormat.format(new java.util.Date()) + " Response:" + stringResponse.substring(0, 800) + "...more response is ignored..");
        } else {
            System.out.println(sDateFormat.format(new java.util.Date()) + " Response:" + stringResponse);
        }
    }

    private void printRequest(String stringRequest) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置日期格式
        System.out.println(df.format(new java.util.Date()) + " Request:" + stringRequest);
    }

    private void executeRequest(HttpRequestBase request) throws Exception {
        // avoid entity is null
        jsonResponse = null;
        try {
            response = httpclient.execute(request);
            entity = response.getEntity();
            //System.out.println(response.getStatusLine().getStatusCode());
            if (entity != null) {
                stringResponse = EntityUtils.toString(entity);
                printResponse(stringResponse);
                jsonResponse = JSON.parseObject(stringResponse);
                handleStatus(jsonResponse.getInteger("status"));
            }
        } finally {
            if (response != null) {
                //Release the resources occupied by the connection after use
                response.close();
            }
        }
        
    }

    private Object getRequest(String method, JSONObject jsonBody) throws Exception {
        for (String key : jsonBody.keySet()) {
            String value = jsonBody.get(key).toString();
            method = method.replace(":" + key, value);
        }

        String url = Constants.SUFFIX.replace("${host}", driver.getHost()).replace("${port}", driver.getPort()) + method;
        printRequest(url);
        httpget = new HttpGet(url);
        executeRequest(httpget);
        if (jsonResponse != null) {
            return jsonResponse.get("value");
        }
        return null;
    }

    private Object postRequest(String method, JSONObject jsonBody) throws Exception {
        JSONObject tempObj = new JSONObject();
        for (String key : jsonBody.keySet()) {
            String value;
            if (jsonBody.get(key) == null) {
                value = null;
            } else {
                value = jsonBody.get(key).toString();
            }
            if (method.contains(":" + key)) {
                if (value != null) {
                    method = method.replace(":" + key, value);
                }
            } else {
                tempObj.put(key, jsonBody.get(key));
            }
        }

        String url = Constants.SUFFIX.replace("${host}", driver.getHost()).replace("${port}", driver.getPort()) + method;

        HttpPost httppost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(JSONObject.toJSONString(tempObj, SerializerFeature.WriteMapNullValue), "utf-8");
        stringEntity.setContentEncoding("utf-8");
        stringEntity.setContentType("application/json");
        httppost.setEntity(stringEntity);

        printRequest(url + ":" + JSONObject.toJSONString(tempObj,
                SerializerFeature.WriteMapNullValue));
        executeRequest(httppost);
        return jsonResponse;
    }

    public Object deleteRequest(String method, JSONObject jsonBody) throws Exception {
        for (String key : jsonBody.keySet()) {
            String value = jsonBody.get(key).toString();
            method = method.replace(":" + key, value);
        }

        String url = Constants.SUFFIX.replace("${host}", driver.getHost()).replace("${port}", driver.getPort()) + method;
        HttpDelete httpdelete = new HttpDelete(url);
        executeRequest(httpdelete);
        return jsonResponse;
    }

    public Object request(String method, String url, JSONObject jsonObj) throws Exception {
        try {
            if ("GET".equals(method.toUpperCase())) {
                return getRequest(url, jsonObj);
            } else if ("POST".equals(method.toUpperCase())) {
                return postRequest(url, jsonObj);
            } else if ("DELETE".equals(method.toUpperCase())) {
                return deleteRequest(url, jsonObj);
            }
        } catch (Exception e) {
            // if exceptionCallback return Boolean.TRUE, throw exception again
            if (exceptionCallback != null) {
                Boolean needThrow = exceptionCallback.call(e);
                if (needThrow != null && needThrow.booleanValue()) {
                    throw e;
                }
            } else {
                throw e;
            }
        }

        return null;
    }

    void handleStatus(int statusCode) throws Exception {
        JsonWireStatus status = JsonWireStatus.findByStatus(statusCode);
        if (status != JsonWireStatus.Success && status != JsonWireStatus.Default && status != JsonWireStatus.NoAlertOpenError) {
            throw new Exception(status.message());
        }
    }

    public String getStatus(String method) throws Exception {
        try {
            String url = Constants.SUFFIX.replace("${host}", driver.getHost()).replace("${port}", driver.getPort()) + method;
            httpget = new HttpGet(url);
            response = httpclient.execute(httpget);
            entity = response.getEntity();
            return String.valueOf(response.getStatusLine().getStatusCode());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        return "get server status error";
    }
}

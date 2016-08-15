package macaca.client.common;

import java.io.IOException;
import java.util.Map;

import macaca.client.model.JsonwireErrors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Utils {

	private final Log log = LogFactory.getLog(getClass());
	
	private HttpGet httpget = null;
	private HttpPost httppost = null;
	private HttpDelete httpdelete = null;
	private CloseableHttpClient httpclient = HttpClients.createDefault();;
	private CloseableHttpResponse response = null;
	private HttpEntity entity = null;
	private StringEntity stringEntity = null;
	private JSONObject jsonResponse = null;
	private String stringResponse = "";

	public Object getRequest(String method, JSONObject jsonBody) throws Exception {

		for (String key : jsonBody.keySet()) {
			String value = jsonBody.get(key).toString();
			method = method.replace(":" + key, value);
		}

		try {
			String url = Constants.SUFFIX + method;
			httpget = new HttpGet(url);
			response = httpclient.execute(httpget);
			entity = response.getEntity();
			//System.out.println(response.getStatusLine().getStatusCode());
			if (entity != null) {
				stringResponse = EntityUtils.toString(entity);
				System.out.println("Response content:" + stringResponse);
				jsonResponse = JSON.parseObject(stringResponse);
				handleStatus(jsonResponse.getInteger("status"));
				return jsonResponse.get("value");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object postRequest(String method, JSONObject jsonBody) throws Exception {
		JSONObject tempObj = new JSONObject();
		for (String key : jsonBody.keySet()) {
			String value = jsonBody.get(key).toString();
			if (method.contains(":" + key)) {
				method = method.replace(":" + key, value);
			} else {
				tempObj.put(key, jsonBody.get(key));
			}
		}

		try {
			String url = Constants.SUFFIX + method;
			httppost = new HttpPost(url);
			if (jsonBody != null) {
				stringEntity = new StringEntity(tempObj.toString(), "utf-8");
				stringEntity.setContentEncoding("utf-8");
				stringEntity.setContentType("application/json");
				httppost.setEntity(stringEntity);
			}
			response = httpclient.execute(httppost);
			entity = response.getEntity();
			System.out.println(response.getStatusLine().getStatusCode());
			if (entity != null) {
				stringResponse = EntityUtils.toString(entity);
				System.out.println("Response content:" + stringResponse);
				jsonResponse = JSON.parseObject(stringResponse);
				handleStatus(jsonResponse.getInteger("status"));
				return jsonResponse;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object deleteRequest(String method, JSONObject jsonBody) throws Exception {

		for (String key : jsonBody.keySet()) {
			String value = jsonBody.get(key).toString();
			method = method.replace(":" + key, value);
		}

		String url = Constants.SUFFIX + method;
		httpdelete = new HttpDelete(url);
		response = httpclient.execute(httpdelete);
		entity = response.getEntity();
		System.out.println(response.getStatusLine().getStatusCode());
		if (entity != null) {
			String stringResponse = EntityUtils.toString(entity);
			System.out.println("Response content:" + stringResponse);
			jsonResponse = JSON.parseObject(stringResponse);
			handleStatus(jsonResponse.getInteger("status"));
			return jsonResponse;
		}
		return null;
	}

	public Object request(String method, String url, JSONObject jsonObj) throws Exception {

		if (method.toUpperCase() == "GET") {
			return getRequest(url, jsonObj);
		} else if (method.toUpperCase() == "POST") {
			return postRequest(url, jsonObj);
		} else if (method.toUpperCase() == "DELETE") {
			return deleteRequest(url, jsonObj);
		}

		return null;
	}

	public void handleStatus(int statusCode) {
		JsonwireErrors jsonwireErrors = new JsonwireErrors();
		Map<Integer, String> map = jsonwireErrors.getStatusMap();
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			if (entry.getKey() == statusCode) {
				System.out.println(entry.getValue());
				break;
			}
		}
	}
}

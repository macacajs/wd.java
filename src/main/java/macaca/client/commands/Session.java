package macaca.client.commands;

import com.alibaba.fastjson.JSONObject;

import macaca.client.common.DriverCommand;
import macaca.client.common.MacacaDriver;
import macaca.client.common.Utils;

public class Session {

	private MacacaDriver driver;
	private Utils utils;

	public Session(MacacaDriver driver) {
		this.driver = driver;
		this.utils = new Utils(driver);
	}

	public void createSession(JSONObject jsonObj) throws Exception {
		if (jsonObj.get("host") != null && jsonObj.get("port") != null) {
			String host = (String) jsonObj.get("host");
			int port = (Integer) jsonObj.get("port");
			this.driver.setRemote(host, port);
		}
		
		if (System.getenv("MACACA_UDID") != null) {
			System.out.println("环境变量MACACA_UDID传入："+System.getenv("MACACA_UDID") );
			jsonObj.put("udid", System.getenv("MACACA_UDID"));
		} else {
			System.out.println("环境变量MACACA_UDID为null");
		}
		
		if (System.getenv("MACACA_APP_NAME") != null) {
			System.out.println("环境变量MACACA_APP_NAME传入："+System.getenv("MACACA_APP_NAME") );
			jsonObj.put("package", System.getenv("MACACA_APP_NAME"));
		} else {
			System.out.println("环境变量MACACA_APP_NAME为null");
		}
		
		System.out.print(jsonObj.toString());
		JSONObject response = (JSONObject) utils.request("POST", DriverCommand.CREATE_SESSION, jsonObj);
		String sessionId = (String) response.get("sessionId");
		this.driver.setSessionId(sessionId);

		this.driver.setCapabilities(response);
	}

	public void delSession() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		utils.request("DELETE", DriverCommand.SESSION, jsonObject);
	}

	public JSONObject sessionAvailable() throws Exception {
		return this.driver.getCapabilities();
	}
}

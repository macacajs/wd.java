package macaca.client.commands;

import macaca.client.MacacaDriver;
import macaca.client.common.DriverCommand;
import macaca.client.common.Utils;

import com.alibaba.fastjson.JSONObject;

public class Session {

	private MacacaDriver driver;
	private Utils utils = new Utils();

	public Session(MacacaDriver driver) {
		this.driver = driver;
	}

	public void createSession(JSONObject jsonObj) throws Exception {
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
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
		JSONObject response = (JSONObject) utils.postRequest(DriverCommand.CREATE_SESSION, jsonObj);
		String sessionId = (String) response.get("sessionId");
		this.driver.setSessionId(sessionId);
	}

	public void delSession() throws Exception {
		String sessionId = this.driver.getSessionId();
		utils.deleteRequest(DriverCommand.SESSION.replace(":sessionId", sessionId));
	}
}
package macaca.client.commands;

import macaca.client.MacacaDriver;
import macaca.client.common.DriverCommand;
import macaca.client.common.Utils;

import com.alibaba.fastjson.JSONObject;

public class Alert {

	private MacacaDriver driver;
	private Utils utils = new Utils();
	
	public Alert(MacacaDriver driver) {
		this.driver = driver;
	}
	
	public void acceptAlert() throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.ACCEPT_ALERT, jsonObj);
	}
	
	public void dismissAlert() throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.DISMISS_ALERT, jsonObj);
	}
	
	public String alertText() throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		String alert = (String) utils.request("GET", DriverCommand.ALERT_TEXT, jsonObj);
		return alert;
	}
	
	public void alertKeys(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.ALERT_TEXT, jsonObj);
	}
	
}

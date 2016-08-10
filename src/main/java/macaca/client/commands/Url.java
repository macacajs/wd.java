package macaca.client.commands;

import com.alibaba.fastjson.JSONObject;

import macaca.client.MacacaDriver;
import macaca.client.common.DriverCommand;
import macaca.client.common.Utils;

public class Url {

	private MacacaDriver driver;
	private Utils utils = new Utils();

	public Url(MacacaDriver driver) {
		this.driver = driver;
	}

	public void url(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.URL, jsonObj);
	}

	public void getUrl(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.URL, jsonObj);
	}

	public void forward(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.URL, jsonObj);
	}

	public void back(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.URL, jsonObj);
	}

	public void refresh(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.URL, jsonObj);
	}
}
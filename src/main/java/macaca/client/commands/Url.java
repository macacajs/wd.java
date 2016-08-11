package macaca.client.commands;

import com.alibaba.fastjson.JSONObject;

import macaca.client.common.DriverCommand;
import macaca.client.common.MacacaDriver;
import macaca.client.common.Utils;

public class Url {

	private MacacaDriver driver;
	private Utils utils = new Utils();

	public Url(MacacaDriver driver) {
		this.driver = driver;
	}

	public void url() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		utils.request("GET", DriverCommand.URL, jsonObject);
	}

	public void getUrl(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.URL, jsonObject);
	}

	public void forward(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.URL, jsonObject);
	}

	public void back(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.URL, jsonObject);
	}

	public void refresh(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.URL, jsonObject);
	}
}
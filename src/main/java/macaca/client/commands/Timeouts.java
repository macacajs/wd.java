package macaca.client.commands;

import com.alibaba.fastjson.JSONObject;

import macaca.client.common.DriverCommand;
import macaca.client.common.MacacaDriver;
import macaca.client.common.Utils;

public class Timeouts {

	private MacacaDriver driver;
	private Utils utils;

	public Timeouts(MacacaDriver driver) {
		this.driver = driver;
		this.utils = new Utils(driver);
	}

	public void implicitWait(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.IMPLICITLY_WAIT, jsonObject);
	}
}

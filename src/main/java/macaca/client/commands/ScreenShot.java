package macaca.client.commands;

import com.alibaba.fastjson.JSONObject;

import macaca.client.MacacaDriver;
import macaca.client.common.DriverCommand;
import macaca.client.common.Utils;

public class ScreenShot {

	private MacacaDriver driver;
	private Utils utils = new Utils();

	public ScreenShot(MacacaDriver driver) {
		this.driver = driver;
	}

	public void takeScreenshot() throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		utils.request("GET", DriverCommand.SCREENSHOT, jsonObj);
	}
}

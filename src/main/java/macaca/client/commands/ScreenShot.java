package macaca.client.commands;

import com.alibaba.fastjson.JSONObject;

import macaca.client.common.DriverCommand;
import macaca.client.common.MacacaDriver;
import macaca.client.common.Utils;

public class ScreenShot {

	private MacacaDriver driver;
	private Utils utils = new Utils();

	public ScreenShot(MacacaDriver driver) {
		this.driver = driver;
	}

	public void takeScreenshot() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		utils.request("GET", DriverCommand.SCREENSHOT, jsonObject);
	}
}

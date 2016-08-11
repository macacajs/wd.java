package macaca.client.commands;

import macaca.client.MacacaDriver;
import macaca.client.common.DriverCommand;
import macaca.client.common.Utils;

import com.alibaba.fastjson.JSONObject;

public class Timeouts {

	private MacacaDriver driver;
	private Utils utils = new Utils();
	
	public Timeouts(MacacaDriver driver) {
		this.driver = driver;
	}
	
	public void implicitWait(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.IMPLICITLY_WAIT, jsonObject);
	}
}

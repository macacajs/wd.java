package macaca.client.commands;

import com.alibaba.fastjson.JSONObject;

import macaca.client.MacacaDriver;
import macaca.client.common.DriverCommand;
import macaca.client.common.Utils;

public class Keys {

	private MacacaDriver driver;
	private Utils utils = new Utils();

	public Keys(MacacaDriver driver) {
		this.driver = driver;
	}
	
	public void keys(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.KEYS, jsonObject);
	}
}

package macaca.client.commands;

import com.alibaba.fastjson.JSONObject;

import macaca.client.MacacaDriver;
import macaca.client.common.DriverCommand;
import macaca.client.common.Utils;

public class Execute {

	private MacacaDriver driver;
	private Utils utils = new Utils();

	public Execute(MacacaDriver driver) {
		this.driver = driver;
	}
	
	public String execute(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		String result = (String) utils.request("POST", DriverCommand.EXECUTE_SCRIPT, jsonObj);
		return result;
	}
}

package macaca.client.commands;

import macaca.client.MacacaDriver;
import macaca.client.common.DriverCommand;
import macaca.client.common.Utils;

import com.alibaba.fastjson.JSONObject;

public class Title {

	private MacacaDriver driver;
	private Utils utils = new Utils();
	
	public Title(MacacaDriver driver) {
		this.driver = driver;
	}
	
	public String title() throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		String title = (String) utils.request("GET", DriverCommand.GET_TITLE, jsonObj);
		return title;
	}
}

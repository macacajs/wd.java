package macaca.client.commands;

import macaca.client.MacacaDriver;
import macaca.client.common.DriverCommand;
import macaca.client.common.Utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Context {

	private MacacaDriver driver;
	private Utils utils = new Utils();

	public Context(MacacaDriver driver) {
		this.driver = driver;
	}

	public String getContext() {
		return this.driver.getContext();
	}

	public JSONArray getContexts() throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		JSONArray contexts = (JSONArray) utils.request("GET", DriverCommand.CONTEXTS, jsonObj);
		return contexts;
	}

	public void setContext(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		driver.setContext(jsonObj.getString("name"));
		utils.request("POST", DriverCommand.CONTEXT, jsonObj);
	}

}

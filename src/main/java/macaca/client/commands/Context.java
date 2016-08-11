package macaca.client.commands;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import macaca.client.common.DriverCommand;
import macaca.client.common.MacacaDriver;
import macaca.client.common.Utils;

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
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		JSONArray contexts = (JSONArray) utils.request("GET", DriverCommand.CONTEXTS, jsonObject);
		return contexts;
	}

	public void setContext(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		driver.setContext(jsonObject.getString("name"));
		utils.request("POST", DriverCommand.CONTEXT, jsonObject);
	}

}

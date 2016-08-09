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

	public void getContext() {

	}

	public JSONArray getContexts() throws Exception {
		JSONArray contexts = (JSONArray) utils
				.getRequest(DriverCommand.CONTEXTS.replace(":sessionId", driver.getSessionId()));
		return contexts;
	}

	public void setContext(JSONObject jsonObj) throws Exception {
		driver.setContext(jsonObj.getString("name"));
		utils.postRequest(DriverCommand.CONTEXT.replace(":sessionId", driver.getSessionId()), jsonObj);
	}

}

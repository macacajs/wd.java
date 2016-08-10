package macaca.client.commands;

import macaca.client.MacacaDriver;
import macaca.client.common.DriverCommand;
import macaca.client.common.Utils;

import com.alibaba.fastjson.JSONObject;

public class Window {

	private MacacaDriver driver;
	private Utils utils = new Utils();

	public Window(MacacaDriver driver) {
		this.driver = driver;
	}

	public void getWindow() throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		String windowHandle = (String) utils
				.request("GET", DriverCommand.WINDOW_HANDLE, jsonObj);
		driver.setWindowHandle(windowHandle);
	}

	public void getWindows() throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		utils.request("GET", DriverCommand.WINDOW_HANDLES, jsonObj);
	}

	public void getWindowSize() throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		jsonObj.put("windowHandle", "current");
		utils.request("GET", DriverCommand.WINDOW_SIZE, jsonObj);
	}

	public void setWindowSize(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		jsonObj.put("windowHandle", "current");
		utils.request("POST", DriverCommand.WINDOW_SIZE, jsonObj);
	}

	public void maximize() throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		jsonObj.put("windowHandle", "current");
		utils.request("GET", DriverCommand.MAXIMIZE_WINDOW, jsonObj);
	}

	public String setWindow(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		String name = (String) utils.request("POST", DriverCommand.WINDOW, jsonObj);
		return name;
	}

	public void deleteWindow() throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		utils.request("DELETE", DriverCommand.WINDOW, jsonObj);
	}

	public void setFrame(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		String name = (String) utils.request("POST", DriverCommand.FRAME, jsonObj);
	}
}

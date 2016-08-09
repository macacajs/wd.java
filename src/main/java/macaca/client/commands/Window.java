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
		String windowHandle = (String) utils
				.getRequest(DriverCommand.WINDOW_HANDLE.replace(":sessionId", driver.getSessionId()));
		driver.setWindowHandle(windowHandle);
	}

	public void getWindows() throws Exception {

	}

	public void getWindowSize() throws Exception {
		utils.getRequest(DriverCommand.WINDOW_SIZE.replace(":sessionId", driver.getSessionId()));
	}

	public void setWindowSize(JSONObject jsonObj) throws Exception {
		utils.postRequest(DriverCommand.WINDOW_SIZE.replace(":sessionId", driver.getSessionId()), jsonObj);
	}

	public String maximize() {
		return "";
	}

	public String setWindow() {
		return "";
	}

	public String deleteWindow() {
		return "";
	}

	public String setFrame() {
		return "";
	}
}

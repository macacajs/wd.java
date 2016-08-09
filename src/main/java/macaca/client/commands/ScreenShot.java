package macaca.client.commands;

import macaca.client.MacacaDriver;
import macaca.client.common.DriverCommand;
import macaca.client.common.Utils;

public class ScreenShot {

	private MacacaDriver driver;
	private Utils utils = new Utils();

	public ScreenShot(MacacaDriver driver) {
		this.driver = driver;
	}

	public void takeScreenshot() throws Exception {
		utils.getRequest(DriverCommand.SCREENSHOT.replace(":sessionId", driver.getSessionId()));
	}
}

package macaca.client.commands;

import macaca.client.MacacaDriver;
import macaca.client.common.DriverCommand;
import macaca.client.common.Utils;

public class Source {

	private MacacaDriver driver;
	private Utils utils = new Utils();

	public Source(MacacaDriver driver) {
		this.driver = driver;
	}

	public String getSource() throws Exception {
		return utils.getRequest(DriverCommand.GET_SOURCE.replace(":sessionId", driver.getSessionId())).toString();
	}
}

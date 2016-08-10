package macaca.client.commands;

import macaca.client.MacacaDriver;
import macaca.client.common.DriverCommand;
import macaca.client.common.Utils;

import com.alibaba.fastjson.JSONObject;

public class Status {

	private MacacaDriver driver;
	private Utils utils = new Utils();

	public Status(MacacaDriver driver) {
		this.driver = driver;
	}
	
	public void getStatus(JSONObject jsonObj) throws Exception {
		
	}
}

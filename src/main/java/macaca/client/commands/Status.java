package macaca.client.commands;

import com.alibaba.fastjson.JSONObject;

import macaca.client.common.MacacaDriver;
import macaca.client.common.Utils;

public class Status {

	private MacacaDriver driver;
	private Utils utils = new Utils();

	public Status(MacacaDriver driver) {
		this.driver = driver;
	}
	
	public void getStatus(JSONObject jsonObj) throws Exception {
		
	}
}

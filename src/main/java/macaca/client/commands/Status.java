package macaca.client.commands;

import com.alibaba.fastjson.JSONObject;

import macaca.client.common.MacacaDriver;
import macaca.client.common.Utils;

public class Status {

	private MacacaDriver driver;
	private Utils utils;

	public Status(MacacaDriver driver) {
		this.driver = driver;
		this.utils = new Utils(driver);
	}
	
	public void getStatus(JSONObject jsonObj) throws Exception {
		
	}
}

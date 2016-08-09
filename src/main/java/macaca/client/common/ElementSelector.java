package macaca.client.common;

import macaca.client.MacacaClient;
import macaca.client.MacacaDriver;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ElementSelector {

	private JSONArray jsonArray;
	private MacacaClient client;
	private MacacaDriver driver;

	public ElementSelector(MacacaDriver driver, MacacaClient client, JSONArray jsonArray) {
		this.client = client;
		this.driver = driver;
		this.jsonArray = jsonArray;
	}

	public MacacaClient getIndex(int index) {
		setElementByIndex(index);
		return this.client;
	}

	private void setElementByIndex(int index) {
		this.driver.setElementId(((JSONObject) this.jsonArray.get(index)).getString("ELEMENT"));
	}

}

package macaca.client.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import macaca.client.MacacaClient;
import macaca.client.commands.Element;

public class ElementSelector {

	private JSONArray jsonArray;
	private MacacaClient client;
	private MacacaDriver driver;

	public ElementSelector(MacacaDriver driver, MacacaClient client, JSONArray jsonArray) {
		this.client = client;
		this.driver = driver;
		this.jsonArray = jsonArray;
	}

	/**
	 * get size for ElementSelector
	 * @return size
	 */
	public int size() {
		return this.jsonArray.size();
	}

	/**
	 * get element by index
	 * @param index element index
	 * @return instance for target Element
	 */
	public Element getIndex(int index) {
		setElementByIndex(index);
		return this.client.element;
	}

	/**
	 * change active element for current macacaClient
	 * @param index active element index
	 */
	private void setElementByIndex(int index) {
		this.driver.setElementId(((JSONObject) this.jsonArray.get(index)).getString("ELEMENT"));
	}

}

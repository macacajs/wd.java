package macaca.client.commands;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import macaca.client.common.DriverCommand;
import macaca.client.common.MacacaDriver;
import macaca.client.common.Utils;

public class Element {

	private MacacaDriver driver;
	private Utils utils;
	private Boolean globalTap;

	public Element(MacacaDriver driver) {
		this.driver = driver;
		this.globalTap = false;
		this.utils = new Utils(driver);
	}

	public void setValue(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("elementId", driver.getElementId());
		utils.request("POST", DriverCommand.ELEMENT_VALUE, jsonObject);
	}

	public void click() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		if (!globalTap) {
			jsonObject.put("elementId", driver.getElementId());
			utils.request("POST", DriverCommand.CLICK_ELEMENT, jsonObject);
		} else {
			utils.request("POST", DriverCommand.CLICK, jsonObject);
			globalTap = false;
		}
	}

	public boolean hasElement(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		JSONObject response = (JSONObject) utils.request("POST", DriverCommand.FIND_ELEMENT, jsonObject);
		JSONObject element = (JSONObject) response.get("value");
		Object elementId = (Object) element.get("ELEMENT");
		return elementId != null;
	}


	/**
	 * find an element
	 * @param jsonObject
	 * @return
	 * 		true - the element exists
	 * 		false - the element does not exist
	 *
	 * @throws Exception
	 */
	public boolean findElement(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		JSONObject response = (JSONObject) utils.request("POST", DriverCommand.FIND_ELEMENT, jsonObject);
		JSONObject element = (JSONObject) response.get("value");
		Object elementId = (Object) element.get("ELEMENT");
		if (elementId !=  null) {
			// the element exists
			driver.setElementId(elementId);
			return true;
		} else {
			// the element does not exist
			return false;
		}

	}

	public JSONArray findElements(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		JSONObject response = (JSONObject) utils.request("POST", DriverCommand.FIND_ELEMENTS, jsonObject);
		JSONArray elements = (JSONArray) response.get("value");
		return elements;
	}

	public String getText() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("elementId", driver.getElementId());
		String text = (String) utils.request("GET", DriverCommand.GET_ELEMENT_TEXT, jsonObject);
		return text;
	}

	public void clearText() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("elementId", driver.getElementId());
		utils.request("POST", DriverCommand.CLEAR_ELEMENT, jsonObject);
	}

	public void back() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.BACK, jsonObject);
	}

	public Object getProperty(String name) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("elementId", driver.getElementId());
		jsonObject.put("name", name);
		Object response = (JSONObject) utils.request("GET", DriverCommand.GET_ELEMENT_PROPERTY, jsonObject);
		return response;
	}

	public Object getRect() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("elementId", driver.getElementId());
		Object response = (JSONObject) utils.request("GET", DriverCommand.GET_ELEMENT_RECT, jsonObject);
		return response;
	}

	public String getComputedCss(String propertyName) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("elementId", driver.getElementId());
		jsonObject.put("propertyName", propertyName);
		String computedCss = (String) utils.request("GET", DriverCommand.GET_ELEMENT_VALUE_OF_CSS_PROPERTY, jsonObject);
		return computedCss;
	}

	public boolean isDisplayed() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("elementId", driver.getElementId());
		boolean displayed = (Boolean) utils.request("GET", DriverCommand.IS_ELEMENT_DISPLAYED, jsonObject);
		return displayed;
	}

	public void touch(String action, JSONObject args) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		JSONArray array = new JSONArray();
		JSONObject actionObject = new JSONObject();
		actionObject.put("element", driver.getElementId());
		actionObject.put("type", action);
		for (String key : args.keySet()) {
			String value = args.getString(key);
			actionObject.put(key , value);
		}
		array.add(actionObject);
		jsonObject.put("actions", array);
		utils.request("POST", DriverCommand.ACTIONS, jsonObject);
	}

}

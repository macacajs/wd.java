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

	public void findElement(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		JSONObject response = (JSONObject) utils.request("POST", DriverCommand.FIND_ELEMENT, jsonObject);
		JSONObject element = (JSONObject) response.get("value");
		Object elementId = (Object) element.get("ELEMENT");
		driver.setElementId(elementId);
	}

	public JSONArray findElements(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		JSONObject response = (JSONObject) utils.request("POST", DriverCommand.FIND_ELEMENTS, jsonObject);
		JSONArray elements = (JSONArray) response.get("value");
		return elements;
	}

	public void swipe(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("elementId", driver.getElementId());
		utils.request("POST", DriverCommand.SWIPE, jsonObject);
	}

	public void flick(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.TOUCH_FLICK, jsonObject);
	}

	public void tap() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("element", driver.getElementId());
		utils.request("POST", DriverCommand.TOUCH_CLICK, jsonObject);
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
		jsonObject.put("elementId", driver.getElementId());
		utils.request("POST", DriverCommand.BACK, jsonObject);
	}

	public JSONObject getProperty(String name) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("elementId", driver.getElementId());
		jsonObject.put("name", name);
		JSONObject response = (JSONObject) utils.request("GET", DriverCommand.GET_ELEMENT_PROPERTY, jsonObject);
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

	public void moveTo(int xoffset, int yoffset) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("xoffset", xoffset);
		jsonObject.put("yoffset", yoffset);
		utils.request("POST", DriverCommand.MOVE_TO, jsonObject);
		globalTap = true;
	}
	
	public void touch(String action, JSONObject args) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		JSONArray array = new JSONArray();
		JSONObject actionObject = new JSONObject();
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

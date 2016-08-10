package macaca.client.commands;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import macaca.client.MacacaDriver;
import macaca.client.common.DriverCommand;
import macaca.client.common.Utils;

public class Element {

	private MacacaDriver driver;
	private Utils utils = new Utils();

	public Element(MacacaDriver driver) {
		this.driver = driver;
	}

	public void setValue(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		jsonObj.put("elementId", driver.getElementId());
		utils.postRequest(DriverCommand.ELEMENT_VALUE, jsonObj);
	}

	public void click() throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		jsonObj.put("elementId", driver.getElementId());
		utils.postRequest(DriverCommand.CLICK_ELEMENT, jsonObj);
	}

	public void findElement(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		JSONObject response = (JSONObject) utils.postRequest(DriverCommand.FIND_ELEMENT, jsonObj);
		JSONObject element = (JSONObject) response.get("value");
		Object elementId = (Object) element.get("ELEMENT");
		driver.setElementId(elementId);
	}

	public JSONArray findElements(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		JSONArray elements = new JSONArray();
		elements = (JSONArray) utils.postRequest(DriverCommand.FIND_ELEMENTS, jsonObj);
		return elements;
	}

	public void swipe(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		utils.postRequest(DriverCommand.SWIPE, jsonObj);
	}

	public void flick(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		utils.postRequest(DriverCommand.TOUCH_FLICK, jsonObj);
	}

	public void tap(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		utils.postRequest(DriverCommand.TOUCH_CLICK, jsonObj);
	}

	public String getText() throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		jsonObj.put("elementId", driver.getElementId());
		String text = (String) utils.getRequest(DriverCommand.GET_ELEMENT_TEXT, jsonObj);
		return text;
	}

	public void clearText() throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		jsonObj.put("elementId", driver.getElementId());
		utils.postRequest(DriverCommand.CLEAR_ELEMENT, jsonObj);
	}

	public String getAttribute(String name) throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		jsonObj.put("elementId", driver.getElementId());
		jsonObj.put(":name", name);
		String attribute = (String) utils.request("GET", DriverCommand.GET_ELEMENT_ATTRIBUTE, jsonObj);
		return attribute;
	}

	public String getProperty(String name) throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		jsonObj.put("elementId", driver.getElementId());
		jsonObj.put(":name", name);
		String property = (String) utils.request("GET", DriverCommand.GET_ELEMENT_ATTRIBUTE, jsonObj);
		return property;
	}

	public String getComputedCss(String propertyName) throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		jsonObj.put("elementId", driver.getElementId());
		jsonObj.put(":propertyName", propertyName);
		String computedCss = (String) utils.request("GET", DriverCommand.GET_ELEMENT_VALUE_OF_CSS_PROPERTY, jsonObj);
		return computedCss;
	}

	public boolean isDisplayed() throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sessionId", driver.getSessionId());
		jsonObj.put("elementId", driver.getElementId());
		boolean displayed = Boolean
				.parseBoolean((String) utils.request("GET", DriverCommand.IS_ELEMENT_DISPLAYED, jsonObj));
		return displayed;
	}

	public void moveTo(JSONObject jsonObj) throws Exception {
		jsonObj.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.MOVE_TO, jsonObj);
	}

}

package macaca.client.commands;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import macaca.client.common.DriverCommand;
import macaca.client.common.MacacaDriver;
import macaca.client.common.Utils;

public class Element {

	private MacacaDriver driver;
	private Utils utils = new Utils();

	public Element(MacacaDriver driver) {
		this.driver = driver;
	}

	public void setValue(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("elementId", driver.getElementId());
		utils.request("POST", DriverCommand.ELEMENT_VALUE, jsonObject);
	}

	public void click() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("elementId", driver.getElementId());
		utils.request("POST", DriverCommand.CLICK_ELEMENT, jsonObject);
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
		JSONArray elements = new JSONArray();
		elements = (JSONArray) utils.request("POST", DriverCommand.FIND_ELEMENTS, jsonObject);
		return elements;
	}

	public void swipe(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.SWIPE, jsonObject);
	}

	public void flick(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.TOUCH_FLICK, jsonObject);
	}

	public void tap(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
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

	public String getAttribute(String name) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("elementId", driver.getElementId());
		jsonObject.put("name", name);
		String attribute = (String) utils.request("GET", DriverCommand.GET_ELEMENT_ATTRIBUTE, jsonObject);
		return attribute;
	}

	public String getProperty(String name) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("elementId", driver.getElementId());
		jsonObject.put("name", name);
		String property = (String) utils.request("GET", DriverCommand.GET_ELEMENT_ATTRIBUTE, jsonObject);
		return property;
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
		boolean displayed = Boolean
				.parseBoolean((String) utils.request("GET", DriverCommand.IS_ELEMENT_DISPLAYED, jsonObject));
		return displayed;
	}

	public void moveTo(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		utils.request("POST", DriverCommand.MOVE_TO, jsonObject);
	}

}

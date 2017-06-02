package macaca.client.commands;

import java.util.ArrayList;

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

	/**
	 * <p>
	 * Send a sequence of key strokes to the active element.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param keys
	 *            The keys sequence to be sent.
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public void sendKeys(String keys) throws Exception {
		JSONObject jsonObject = new JSONObject();
		ArrayList<String> values = new ArrayList<String>();
		values.add(keys);
		jsonObject.put("value", values);
		setValue(jsonObject);
	}


	/**
	 *
	 * @param jsonObject
	 * @throws Exception
	 */
	public void setValue(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("elementId", driver.getElementId());
		utils.request("POST", DriverCommand.ELEMENT_VALUE, jsonObject);
	}

	/**
	 * click this element
	 * Support: Android iOS Web(WebView)
	 * @throws Exception
	 */
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


//	/**
//	 * find an element
//	 * @param jsonObject
//	 * @return
//	 * 		true - the element exists
//	 * 		false - the element does not exist
//	 *
//	 * @throws Exception
//	 */
//	public boolean findElement(JSONObject jsonObject) throws Exception {
//		jsonObject.put("sessionId", driver.getSessionId());
//		JSONObject response = (JSONObject) utils.request("POST", DriverCommand.FIND_ELEMENT, jsonObject);
//		JSONObject element = (JSONObject) response.get("value");
//		Object elementId = (Object) element.get("ELEMENT");
//		if (elementId !=  null) {
//			// the element exists
//			driver.setElementId(elementId);
//			return true;
//		} else {
//			// the element does not exist
//			return false;
//		}
//
//	}
//
//	public JSONArray findElements(JSONObject jsonObject) throws Exception {
//		jsonObject.put("sessionId", driver.getSessionId());
//		JSONObject response = (JSONObject) utils.request("POST", DriverCommand.FIND_ELEMENTS, jsonObject);
//		JSONArray elements = (JSONArray) response.get("value");
//		return elements;
//	}

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


	/**
	 * <p>
	 * Get the result of a property of a element.<br>
	 * Support: Android iOS Web(WebView). iOS: 'isVisible', 'isAccessible', 'isEnabled', 'type', 'label', 'name', 'value', Android: 'selected', 'description', 'text'
	 *
	 * @param name
	 *            The property name of element
	 * @return The property
	 * @throws Exception
	 */
	public Object getProperty(String name) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("elementId", driver.getElementId());
		jsonObject.put("name", name);
		Object response =  utils.request("GET", DriverCommand.GET_ELEMENT_PROPERTY, jsonObject);
		return response;
	}

	public Object getRect() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		jsonObject.put("elementId", driver.getElementId());
		Object response = (Object) utils.request("GET", DriverCommand.GET_ELEMENT_RECT, jsonObject);
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

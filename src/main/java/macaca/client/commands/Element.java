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
		utils.postRequest(DriverCommand.ELEMENT_VALUE.replace(":sessionId", driver.getSessionId()).replace(":elementId",
				driver.getElementId()), jsonObj);
	}

	public void click() throws Exception {
		utils.postRequest(DriverCommand.CLICK_ELEMENT.replace(":sessionId", driver.getSessionId()).replace(":elementId",
				driver.getElementId()), null);
	}

	public void findElement(JSONObject jsonObj) throws Exception {
		JSONObject response = (JSONObject) utils
				.postRequest(DriverCommand.FIND_ELEMENT.replace(":sessionId", driver.getSessionId()), jsonObj);
		JSONObject element = (JSONObject) response.get("value");
		Object elementId = (Object) element.get("ELEMENT");
		driver.setElementId(elementId);
	}

	public JSONArray findElements(JSONObject jsonObj) throws Exception {
		JSONArray elements = new JSONArray();
		elements = (JSONArray) utils
				.postRequest(DriverCommand.FIND_ELEMENTS.replace(":sessionId", driver.getSessionId()), jsonObj);
		return elements;
	}

	public void swipe(JSONObject jsonObj) throws Exception {
		utils.postRequest(DriverCommand.SWIPE.replace(":sessionId", driver.getSessionId()), jsonObj);
	}

	public void flick(JSONObject jsonObj) throws Exception {
		utils.postRequest(DriverCommand.TOUCH_FLICK.replace(":sessionId", driver.getSessionId()), jsonObj);
	}

	public void tap(JSONObject jsonObj) throws Exception {
		utils.postRequest(DriverCommand.TOUCH_CLICK.replace(":sessionId", driver.getSessionId()), jsonObj);
	}

	public String getText() throws Exception {
		String text = (String) utils.getRequest(DriverCommand.GET_ELEMENT_TEXT
				.replace(":sessionId", driver.getSessionId()).replace(":elementId", driver.getElementId()));
		return text;
	}

	public void clearText() throws Exception {
		utils.postRequest(DriverCommand.CLEAR_ELEMENT.replace(":sessionId", driver.getSessionId()).replace(":elementId",
				driver.getElementId()), null);
	}
	/*
	 * TODO flick, tap, getText, clearText, getAttribute, getProperty,
	 * getComputedCss, isDisplayed, moveTo
	 */
}

package macaca.client.model;

import java.util.HashMap;
import java.util.Map;

public class JsonwireErrors {

	private Map<Integer, String> map = null;

	private int Success = 0;
	private int NoSuchElement = 7;
	private int NoSuchFrame = 8;
	private int UnknownCommand = 9;
	private int StaleElementReference = 10;
	private int ElementNotVisible = 11;
	private int InvalidElementState = 12;
	private int UnknownError = 13;
	private int ElementIsNotSelectable = 15;
	private int JavaScriptError = 17;
	private int XPathLookupError = 19;
	private int Timeout = 21;
	private int NoSuchWindow = 23;
	private int InvalidCookieDomain = 24;
	private int UnableToSetCookie = 25;
	private int UnexpectedAlertOpen = 26;
	private int NoAlertOpenError = 27;
	private int ScriptTimeout = 28;
	private int InvalidElementCoordinates = 29;
	private int IMENotAvailable = 30;
	private int IMEEngineActivationFailed = 31;
	private int InvalidSelector = 32;
	private int SessionNotCreatedException = 33;
	private int MoveTargetOutOfBounds = 34;

	public JsonwireErrors() {
		map = new HashMap<Integer, String>();
		map.put(Success, "The command executed successfully.");
		map.put(NoSuchElement, "An element could not be located on the page using the given search parameters.");
		map.put(NoSuchFrame,
				"A request to switch to a frame could not be satisfied because the frame could not be found.");
		map.put(UnknownCommand,
				"The requested resource could not be found, or a request was received using an HTTP method that is not supported by the mapped resource.");
		map.put(StaleElementReference,
				"An element command failed because the referenced element is no longer attached to the DOM.");
		map.put(ElementNotVisible,
				"An element command could not be completed because the element is not visible on the page.");
		map.put(InvalidElementState,
				"An element command could not be completed because the element is in an invalid state (e.g. attempting to click a disabled element).");
		map.put(UnknownError, "An unknown server-side error occurred while processing the command.");
		map.put(ElementIsNotSelectable, "An attempt was made to select an element that cannot be selected.");
		map.put(JavaScriptError, "An error occurred while executing user supplied JavaScript.");
		map.put(XPathLookupError, "An error occurred while searching for an element by XPath.");
		map.put(Timeout, "An operation did not complete before its timeout expired.");
		map.put(NoSuchWindow,
				"A request to switch to a different window could not be satisfied because the window could not be found.");
		map.put(InvalidCookieDomain,
				"An illegal attempt was made to set a cookie under a different domain than the current page.");
		map.put(UnableToSetCookie, "A request to set a cookie\'s value could not be satisfied.");
		map.put(UnexpectedAlertOpen, "A modal dialog was open, blocking this operation.");
		map.put(NoAlertOpenError, "An attempt was made to operate on a modal dialog when one was not open.");
		map.put(ScriptTimeout, "A script did not complete before its timeout expired.");
		map.put(InvalidElementCoordinates, "The coordinates provided to an interactions operation are invalid.");
		map.put(IMENotAvailable, "IME was not available.");
		map.put(IMEEngineActivationFailed, "An IME engine could not be started.");
		map.put(InvalidSelector, "Argument was an invalid selector (e.g. XPath/CSS).");
	}

	public Map<Integer, String> getStatusMap() {
		return this.map;
	}

}

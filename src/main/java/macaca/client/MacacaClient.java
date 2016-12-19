package macaca.client;

import java.lang.annotation.Retention;
import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import macaca.client.commands.*;
import macaca.client.common.ElementSelector;
import macaca.client.common.GetElementWay;
import macaca.client.common.MacacaDriver;

public class MacacaClient {

	private MacacaDriver driver = new MacacaDriver();

	private Alert alert = new Alert(driver);
	private Context context = new Context(driver);
	private Element element = new Element(driver); // TODO
	private Execute execute = new Execute(driver);
	private Keys _keys = new Keys(driver);
	private ScreenShot screenshot = new ScreenShot(driver);
	private Session session = new Session(driver);
	private Source source = new Source(driver);
	private Status status = new Status(driver);
	private Title title = new Title(driver);
	private Url _url = new Url(driver);

	private Window window = new Window(driver);

	/**
	 * timeout for waitForElement
	 */
	public int waitElementTimeout = 1000;

	/**
	 * interval for waitForElement
	 */
	public int waitElementTimeInterval = 200;

	// Alert

	public int getWaitElementTimeout() {
		return waitElementTimeout;
	}

	public void setWaitElementTimeout(int waitElementTimeout) {
		this.waitElementTimeout = waitElementTimeout;
	}

	public int getWaitElementTimeInterval() {
		return waitElementTimeInterval;
	}

	public void setWaitElementTimeInterval(int waitElementTimeInterval) {
		this.waitElementTimeInterval = waitElementTimeInterval;
	}

	// Alert

	/**
	 * <p>
	 * Accepts the currently displayed alert dialog.<br>
	 * Support: iOS
	 *
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient acceptAlert() throws Exception {
		alert.acceptAlert();
		return this;
	}

	/**
	 * <p>
	 * Dismisses the currently displayed alert dialog.<br>
	 * Support: iOS
	 *
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient dismissAlert() throws Exception {
		alert.dismissAlert();
		return this;
	}

	/**
	 * <p>
	 * Gets the text of the currently displayed JavaScript alert(), confirm(),
	 * or prompt() dialog.<br>
	 * Support: iOS
	 *
	 * @return The text of the currently displayed alert.
	 * @throws Exception
	 */
	public String alertText() throws Exception {
		return alert.alertText();
	}

	/**
	 * <p>
	 * Sends keystrokes to a JavaScript prompt() dialog.<br>
	 * Support: iOS
	 *
	 * @param keys
	 *            Keystrokes to send to the prompt() dialog.
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient alertKeys(String keys) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("text", keys);
		alert.alertKeys(jsonObject);
		return this;
	}

	// Context

	/**
	 * <p>
	 * Get a list of the available contexts.<br>
	 * Support: Android iOS
	 *
	 * @return The currently available contexts
	 * @throws Exception
	 */
	public JSONArray contexts() throws Exception {
		return context.getContexts();
	}

	/**
	 * <p>
	 * Set the current context.<br>
	 * Support: Android iOS
	 *
	 * @param contextRef
	 *            context reference from contexts
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient context(String contextRef) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", contextRef);
		context.setContext(jsonObject);
		return this;
	}

	/**
	 * <p>
	 * Get the current context.<br>
	 * Support: Android iOS
	 *
	 * @return The currently context
	 * @throws Exception
	 */
	public String currentContext() throws Exception {
		return context.getContext();
	}

	// Element

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param elementId
	 *            The ID attribute of element
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient elementById(String elementId) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", elementId);
		jsonObject.put("using", "id");
		element.findElement(jsonObject);
		return this;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Web(WebView)
	 *
	 * @param selector
	 *            The css selector of element
	 * @returnThe currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient elementByCss(String selector) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", selector);
		jsonObject.put("using", "css");
		element.findElement(jsonObject);
		return this;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param xpath
	 *            The XPath expression of element
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient elementByXPath(String xpath) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", xpath);
		jsonObject.put("using", "xpath");
		element.findElement(jsonObject);
		return this;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param name
	 *            The name attribute of element
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient elementByName(String name) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", name);
		jsonObject.put("using", "name");
		element.findElement(jsonObject);
		return this;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param wayToFind
	 *            way to find an element
	 * @param value
	 *            target value for element,paired with wayToFind
	 * @param index
	 *            index for target element
	 */
	public MacacaClient getElement(GetElementWay wayToFind, String value, int index) throws Exception {
		ElementSelector elementSelector;
		switch (wayToFind) {
		case ID:
			elementSelector = elementsById(value);
			break;
		case CSS:
			elementSelector = elementsByCss(value);
			break;
		case NAME:
			elementSelector = elementsByName(value);
			break;
		case XPATH:
			elementSelector = elementsByXPath(value);
			break;
		case CLASS_NAME:
			elementSelector = elementsByClassName(value);
			break;
		case LINK_TEXT:
			elementSelector = elementsByLinkText(value);
			break;
		case PARTIAL_LINK_TEXT:
			elementSelector = elementsByPartialLinkText(value);
			break;
		case TAG_NAME:
			elementSelector = elementsByTagName(value);
			break;

		default:
			elementSelector = null;
			break;
		}

		if (elementSelector != null) {
			elementSelector.getIndex(index);
		} else {
			System.out.println("can't find the element:" + value + "[" + index + "]");
			throw new Exception();
		}

		return this;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 * 
	 * @param wayToFind
	 *            the way to find an element,for example:ID,CSS,XPATH...
	 * @param value
	 *            the value for target element,paired with wayToFind
	 * @throws Exception
	 */
	public MacacaClient getElement(GetElementWay wayToFind, String value) throws Exception {
		switch (wayToFind) {
		case ID:
			elementById(value);
			break;
		case CSS:
			elementByCss(value);
			break;
		case NAME:
			elementByName(value);
			break;
		case XPATH:
			elementByXPath(value);
			break;
		case CLASS_NAME:
			elementByClassName(value);
			break;
		case LINK_TEXT:
			elementByLinkText(value);
			break;
		case PARTIAL_LINK_TEXT:
			elementByPartialLinkText(value);
			break;
		case TAG_NAME:
			elementByTagName(value);
			break;
		default:
			throw new Exception();
		}

		return this;
	}

	/**
	 * <p>
	 * get count of elements when there exist multiple elements<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param wayToFind
	 *            the way to find an element,for example:ID,CSS,XPATH...
	 * @param value
	 *            the value for target element,paired with wayToFind
	 * @return count of target element
	 * @throws Exception
	 */
	public int countOfElements(GetElementWay wayToFind, String value) throws Exception {

		ElementSelector elementSelector = getElementSelector(wayToFind, value);
		if (elementSelector != null) {
			return elementSelector.size();
		}

		return 0;
	}

	private ElementSelector getElementSelector(GetElementWay wayToFind, String value) throws Exception {
		ElementSelector elementSelector;
		switch (wayToFind) {
		case ID:
			elementSelector = elementsById(value);
			break;
		case CSS:
			elementSelector = elementsByCss(value);
			break;
		case NAME:
			elementSelector = elementsByName(value);
			break;
		case XPATH:
			elementSelector = elementsByXPath(value);
			break;
		case CLASS_NAME:
			elementSelector = elementsByClassName(value);
			break;
		case LINK_TEXT:
			elementSelector = elementsByLinkText(value);
			break;
		case PARTIAL_LINK_TEXT:
			elementSelector = elementsByPartialLinkText(value);
			break;
		case TAG_NAME:
			elementSelector = elementsByTagName(value);
			break;

		default:
			elementSelector = null;
			break;
		}

		return elementSelector;
	}

	/**
	 * <p>
	 * find target element,if it doesn't exist,keep finding during given time
	 * (property:waitElementTimeout)<br>
	 * Support: Android iOS Web(WebView)
	 * 
	 * @param wayToFind
	 *            the way to find an element,for example:ID,CSS,XPATH...
	 * @param value
	 *            the value for target element,paired with wayToFind
	 * @param index
	 *            the index for target element
	 * @throws Exception
	 */
	public MacacaClient waitForElement(GetElementWay wayToFind, String value, int index) throws Exception {
		int count = 0;
		int timeLeft = waitElementTimeout;
		boolean satisfied = false;
		while (timeLeft > 0) {
			boolean elementExist = false;
			System.out.println(String.format("attempt to search the element for %d times", count++));
			elementExist = isElementExist(wayToFind, value, index);
			if (!elementExist) {
				// not find element ,keep searching
				this.sleep(waitElementTimeInterval);
				timeLeft -= waitElementTimeInterval;
			} else {
				// finded , break
				satisfied = true;
				getElement(wayToFind, value, index);
				break;
			}
		}
		if (satisfied == false) {
			System.out.println("can't find the element:" + value);
		}
		return this;
	}

	/**
	 * <p>
	 * find target element,if it doesn't exist,keep finding during given time
	 * (property:waitElementTimeout)<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param wayToFind
	 *            the way to find an element,for example:ID,CSS,XPATH...
	 * @param value
	 *            the value for target element,paired with wayToFind
	 * @throws Exception
	 */
	public MacacaClient waitForElement(GetElementWay wayToFind, String value) throws Exception {
		int count = 0;
		int timeLeft = waitElementTimeout;
		boolean satisfied = false;
		while (timeLeft > 0) {
			boolean elementExist = false;
			System.out.println(String.format("attempt to search the element for %d times", count++));
			elementExist = isElementExist(wayToFind, value);
			if (!elementExist) {
				// not find element ,keep searching
				this.sleep(waitElementTimeInterval);
				timeLeft -= waitElementTimeInterval;
			} else {
				// finded , break
				satisfied = true;
				getElement(wayToFind, value);
				break;
			}
		}
		if (satisfied == false) {
			System.out.println("can't find the element:" + value);
		}
		return this;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param className
	 *            The className attribute of element
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient elementByClassName(String className) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", className);
		jsonObject.put("using", "class name");
		element.findElement(jsonObject);
		return this;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param linkText
	 *            The linkText attribute of element
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient elementByLinkText(String linkText) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", linkText);
		jsonObject.put("using", "link text");
		element.findElement(jsonObject);
		return this;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param tagName
	 *            The tag name attribute of element
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient elementByTagName(String tagName) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", tagName);
		jsonObject.put("using", "tag name");
		element.findElement(jsonObject);
		return this;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param partialLinkText
	 *            The partial link text attribute of element
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient elementByPartialLinkText(String partialLinkText) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", partialLinkText);
		jsonObject.put("using", "partial link text");
		element.findElement(jsonObject);
		return this;
	}

	/**
	 * <p>
	 * Search for multiple elements on the page, starting from the document
	 * root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param xpath
	 *            The XPath expression of elements
	 * @return The instance of ElementSelector for index
	 * @throws Exception
	 */
	public ElementSelector elementsByXPath(String xpath) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", xpath);
		jsonObject.put("using", "xpath");
		JSONArray jsonArray = element.findElements(jsonObject);
		return new ElementSelector(driver, this, jsonArray);
	}

	/**
	 * <p>
	 * Search for multiple elements on the page, starting from the document
	 * root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param name
	 *            The name attribute of elements
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public ElementSelector elementsByName(String name) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", name);
		jsonObject.put("using", "name");
		JSONArray jsonArray = element.findElements(jsonObject);
		return new ElementSelector(driver, this, jsonArray);
	}

	/**
	 * <p>
	 * Search for multiple elements on the page, starting from the document
	 * root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param elementId
	 *            The elementId attribute of elements
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public ElementSelector elementsById(String elementId) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", elementId);
		jsonObject.put("using", "id");
		JSONArray jsonArray = element.findElements(jsonObject);
		return new ElementSelector(driver, this, jsonArray);
	}

	/**
	 * <p>
	 * Search for multiple elements on the page, starting from the document
	 * root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param className
	 *            The className attribute of elements
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public ElementSelector elementsByClassName(String className) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", className);
		jsonObject.put("using", "class name");
		JSONArray jsonArray = element.findElements(jsonObject);
		return new ElementSelector(driver, this, jsonArray);
	}

	/**
	 * <p>
	 * Search for multiple elements on the page, starting from the document
	 * root.<br>
	 * Support: Web(WebView)
	 *
	 * @param css
	 *            The selector selector of elements
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public ElementSelector elementsByCss(String css) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", css);
		jsonObject.put("using", "selector");
		JSONArray jsonArray = element.findElements(jsonObject);
		return new ElementSelector(driver, this, jsonArray);
	}

	/**
	 * <p>
	 * Search for multiple elements on the page, starting from the document
	 * root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param linkText
	 *            The link text attribute of elements
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public ElementSelector elementsByLinkText(String linkText) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", linkText);
		jsonObject.put("using", "link text");
		JSONArray jsonArray = element.findElements(jsonObject);
		return new ElementSelector(driver, this, jsonArray);
	}

	/**
	 * <p>
	 * Search for multiple elements on the page, starting from the document
	 * root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param linkText
	 *            The partial link text attribute of elements
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public ElementSelector elementsByPartialLinkText(String partialLinkText) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", partialLinkText);
		jsonObject.put("using", "partial link text");
		JSONArray jsonArray = element.findElements(jsonObject);
		return new ElementSelector(driver, this, jsonArray);
	}

	/**
	 * <p>
	 * Search for multiple elements on the page, starting from the document
	 * root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param tagName
	 *            The tag name attribute of elements
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public ElementSelector elementsByTagName(String tagName) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", tagName);
		jsonObject.put("using", "tag name");
		JSONArray jsonArray = element.findElements(jsonObject);
		return new ElementSelector(driver, this, jsonArray);
	}

	/**
	 * <p>
	 * Search for element at specific interval during given time<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param using
	 *            The way for find an element,eg:"name","xpath","css","id"
	 * @param value
	 *            The value for the specific way
	 * @param timeout
	 *            Total time for searching
	 * @param interval
	 *            Time interval between searching
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient waitForElement(String using, String value, int timeout, int interval) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", value);
		jsonObject.put("using", using);
		int count = 1;
		int timeLeft = timeout;
		boolean satisfied = false;
		while (timeLeft > 0) {
			boolean elementExist = false;
			System.out.println(String.format("attempt to search the element for %d times", count++));
			elementExist = this.isElementExist(using, value);
			if (!elementExist) {
				// not find element ,keep searching
				this.sleep(interval);
				timeLeft -= interval;
			} else {
				// finded , break
				satisfied = true;
				element.findElement(jsonObject);
				break;
			}
		}
		if (satisfied == false) {
			System.out.println("can't find the element: " + using + ":" + value);
		}
		return this;
	}

	/**
	 * <p>
	 * Search for element at specific interval during given time<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param using
	 *            The way for find an element,eg:"name","xpath","css","id"
	 * @param value
	 *            The value for the specific way
	 * @return
	 * @throws Exception
	 */
	public MacacaClient waitForElement(String using, String value) throws Exception {
		// default timeout:2000, default interval:200
		waitForElement(using, value, waitElementTimeout, waitElementTimeInterval);
		return this;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param elementId
	 *            The ID attribute of element
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient waitForElementById(String elementId) throws Exception {
		waitForElement("id", elementId);
		return this;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Web(WebView)
	 *
	 * @param selector
	 *            The css selector of element
	 * @returnThe currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient waitForElementByCss(String selector) throws Exception {
		waitForElement("css", selector);
		return this;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param xpath
	 *            The XPath expression of element
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient waitForElementByXPath(String xpath) throws Exception {
		waitForElement("xpath", xpath);
		return this;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param name
	 *            The name attribute of element
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient waitForElementByName(String name) throws Exception {
		waitForElement("name", name);
		return this;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param text
	 *            The visible text of element
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient waitForElementByLinkText(String text) throws Exception {
		waitForElement("link text", text);
		return this;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param text
	 *            The visible text of element
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient waitForElementByPartialLinkText(String text) throws Exception {
		waitForElement("partial link text", text);
		return this;
	}

	/**
	 * <p>
	 * check if target element exist<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param using
	 *            The way for find an element,eg:"name","xpath","css","id"
	 * @param value
	 *            The value for the specific way
	 * @return true-exist ; false-do not exist
	 * @throws Exception
	 */
	public boolean isElementExist(String using, String value) throws Exception {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", value);
		jsonObject.put("using", using);
		try {
			element.findElement(jsonObject);
		} catch (Exception e) {
			return false;
		}

		return element.isDisplayed();
	}

	/**
	 * <p>
	 * check if target element exist<br>
	 * Support: Android iOS Web(WebView)
	 * 
	 * @param wayToFind
	 *            The way to find an element
	 * @param value
	 *            The value for the specific way
	 * @param index
	 *            The index of the target element
	 * @return
	 * @throws Exception
	 */
	public boolean isElementExist(GetElementWay wayToFind, String value, int index) throws Exception {
		try {
			getElement(wayToFind, value, index);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

		return element.isDisplayed();
	}

	/**
	 * <p>
	 * check if target element exist<br>
	 * Support: Android iOS Web(WebView)
	 * 
	 * @param wayToFind
	 *            The way to find an element
	 * @param value
	 *            The value for the specific way
	 * @return boolean exist-true ; not exist-false
	 * @throws Exception
	 */
	public boolean isElementExist(GetElementWay wayToFind, String value) throws Exception {
		try {
			getElement(wayToFind, value);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

		return element.isDisplayed();
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
	public MacacaClient sendKeys(String keys) throws Exception {
		JSONObject jsonObject = new JSONObject();
		ArrayList<String> values = new ArrayList<String>();
		values.add(keys);
		jsonObject.put("value", values);
		element.setValue(jsonObject);
		return this;
	}

	/**
	 * <p>
	 * Move the mouse by an offset of the specificed element.<br>
	 * Support: Android
	 * 
	 * @param xoffset
	 *            X offset to move to, relative to the top-left corner of the
	 *            element. If not specified, the mouse will move to the middle
	 *            of the element.
	 * @param yoffset
	 *            Y offset to move to, relative to the top-left corner of the
	 *            element. If not specified, the mouse will move to the middle
	 *            of the element.
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */

	public MacacaClient moveTo(int xoffset, int yoffset) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("xoffset", xoffset);
		jsonObject.put("yoffset", yoffset);
		element.moveTo(xoffset, yoffset);
		return this;
	}

	/**
	 * <p>
	 * Click on an element.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient click() throws Exception {
		element.click();
		return this;
	}

	/**
	 * <p>
	 * click at specific point ,absolutely coordinate<br>
	 * Support: Android
	 *
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 * @return
	 * @throws Exception
	 */
	public MacacaClient clickPoint(int x, int y) throws Exception {
		moveTo(x, y);
		click();
		return this;
	}

	/**
	 * <p>
	 * Touch click on an element.<br>
	 * Support: Android iOS
	 *
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient tap() throws Exception {
		element.tap();
		return this;
	}

	/**
	 * <p>
	 * clear on an element.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient clear() throws Exception {
		element.clearText();
		return this;
	}

	/**
	 * <p>
	 * press back.<br>
	 * Support: Web(WebView)
	 *
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient back() throws Exception {
		element.back();
		return this;
	}

	/**
	 * <p>
	 * Swipe on the touch screen using finger motion events.<br>
	 * Support: Android iOS
	 *
	 * @param startX
	 *            The X coordinate to position the window at, relative to the
	 *            upper left corner of the screen
	 * @param startY
	 *            The Y coordinate to position the window at, relative to the
	 *            upper left corner of the screen
	 * @param endX
	 *            The X coordinate to position the window at, relative to the
	 *            upper left corner of the screen
	 * @param endY
	 *            The Y coordinate to position the window at, relative to the
	 *            upper left corner of the screen
	 * @param duration
	 *            The duration of the swipe operation
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient swipe(int startX, int startY, int endX, int endY, int duration) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("startX", startX);
		jsonObject.put("startY", startY);
		jsonObject.put("endX", endX);
		jsonObject.put("endY", endY);
		jsonObject.put("duration", duration);
		element.swipe(jsonObject);
		return this;
	}

	/**
	 * <p>
	 * Get the result of a property of a element.<br>
	 * Support: Android iOS Web(WebView), iOS: `origin`, `size`
	 * Android: `origin`, `size`, `description`, `text`, `enabled`,
	 * `checkable`, `checked`, `clickable`, `focusable`, `focused`,
	 * `longClickable`, `scrollable`, `selected`
	 *
	 * @param name
	 *            The property name of element
	 * @return The property
	 * @throws Exception
	 */
	public JSONObject getProperty(String name) throws Exception {
		return element.getProperty(name);
	}

	// Execute

	/**
	 * <p>
	 * Inject a snippet of JavaScript into the page for execution in the context
	 * of the currently selected frame.<br>
	 * Support: Web(WebView)
	 *
	 * @param code
	 *            The script to execute
	 * @return The results of execution
	 * @throws Exception
	 */
	public String execute(String code) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", code);
		return execute.execute(jsonObject);
	}

	// Keys

	/**
	 * <p>
	 * Send a sequence of key strokes to the active window.<br>
	 * Support: Android Web(WebView)
	 *
	 * @param keys
	 *            The keys sequence to be sent.
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient keys(String keys) throws Exception {
		JSONObject jsonObject = new JSONObject();
		ArrayList<String> values = new ArrayList<String>();
		values.add(keys);
		jsonObject.put("value", values);
		_keys.keys(jsonObject);
		return this;
	}

	// ScreenShot

	/**
	 * <p>
	 * Take a screenshot of the current page.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * 
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient takeScreenshot() throws Exception {
		screenshot.takeScreenshot();
		return this;
	}

	// saveScreenShot

	/**
	 * <p>
	 * Save screenshot of the current page.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param fileName
	 *            The absolute path of the image filename
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient saveScreenshot(String fileName) throws Exception {
		screenshot.saveScreenshot(fileName);
		return this;
	}

	// Session

	/**
	 * <p>
	 * Initial webdriver client and create a session.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param jsonObject
	 *            The capabilities of session
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient initDriver(JSONObject jsonObject) throws Exception {
		session.createSession(jsonObject);
		return this;
	}

	/**
	 * <p>
	 * Get the current sessionId.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @return The current sessionId.
	 * @throws Exception
	 */
	public String sessionId() throws Exception {
		return driver.getSessionId();
	}

	/**
	 * <p>
	 * Delete session.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @throws Exception
	 */
	public void quit() throws Exception {
		session.delSession();
	}

	// Source

	/**
	 * <p>
	 * Get the current page source.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @return The current page source.
	 * @throws Exception
	 */
	public String source() throws Exception {
		return source.getSource();
	}

	// Status

	/**
	 * <p>
	 * Query the server's current status.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @throws Exception
	 */
	public String status() throws Exception {
		return status.getStatus();
	}

	/**
	 * <p>
	 * Set the amount of time the driver should wait.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param ms
	 *            The amount of sleep time, in milliseconds. This value has a
	 *            lower bound of 0.
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient sleep(int ms) throws Exception {
		Thread.sleep(ms);
		return this;
	}

	// Title

	/**
	 * <p>
	 * Get the current page title.<br>
	 * Support: Web(WebView)
	 *
	 * @return The current page title.
	 * @throws Exception
	 */
	public String title() throws Exception {
		return title.title();
	}

	// Url

	/**
	 * <p>
	 * Retrieve the URL of the current page.<br>
	 * Support: Web(WebView)
	 *
	 * @return The current URL.
	 * @throws Exception
	 */
	public String url() throws Exception {
		return _url.url();
	}

	/**
	 * <p>
	 * Navigate to a new URL.<br>
	 * Support: Web(WebView)
	 *
	 * @param url
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient get(String url) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("url", url);
		_url.getUrl(jsonObject);
		return this;
	}

	// Window

	/**
	 * <p>
	 * Get the size of the specified window.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @return {width: number, height: number} The size of the window.
	 * @throws Exception
	 */
	public JSONObject getWindowSize() throws Exception {
		return window.getWindowSize();
	}

	/**
	 * <p>
	 * Change the size of the specified window.<br>
	 * Support: Web(WebView)
	 *
	 * @param width
	 *            The new window width.
	 * @param height
	 *            The new window height.
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient setWindowSize(int width, int height) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("width", width);
		jsonObject.put("height", height);
		window.setWindowSize(jsonObject);
		return this;
	}

	/**
	 * <p>
	 * set maximize size of the window.<br>
	 * Support: Web(WebView)
	 *
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient maximize() throws Exception {
		window.maximize();
		return this;
	}

	/**
	 * <p>
	 * Get text of the element<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @return The text of the element
	 * @throws Exception
	 */
	public String text() throws Exception {
		return element.getText();
	}

}

package macaca.client;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import macaca.client.commands.*;
import macaca.client.common.DriverCommand;
import macaca.client.common.ElementSelector;
import macaca.client.common.GetElementWay;
import macaca.client.common.MacacaDriver;
import macaca.client.common.Utils;

public class MacacaClient {


	// GesturePinchType
	public enum GesturePinchType {
		PINCH_IN, //两只手指向内缩小元素
		PINCH_OUT,// 两只手指向外放大元素
	}

	public MacacaDriver contexts = new MacacaDriver();

	private Alert alert = new Alert(contexts);
	private Context context = new Context(contexts);
	public  Element element = new Element(contexts); // TODO
	private Execute execute = new Execute(contexts);
	private Keys _keys = new Keys(contexts);
	private ScreenShot screenshot = new ScreenShot(contexts);
	private Session session = new Session(contexts);
	private Source source = new Source(contexts);
	private Status status = new Status(contexts);
	private Title title = new Title(contexts);
	private Url _url = new Url(contexts);
	private Utils utils = new Utils(contexts);

	private Window window = new Window(contexts);

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
	 * Support: Android iOS
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
	 * Support: Android iOS
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
	 * find an element
	 * @param jsonObject
	 * 		{using:way_to_find,value:element property value for given way}
	 *      way_to_find :name,id,css,xpath,class name,
	 * @return
	 * 		true - the element exists
	 * 		false - the element does not exist
	 *
	 * @throws Exception
	 */
	private  boolean findElement(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", contexts.getSessionId());
		JSONObject response = (JSONObject) utils.request("POST", DriverCommand.FIND_ELEMENT, jsonObject);
		JSONObject element = (JSONObject) response.get("value");
		Object elementId = (Object) element.get("ELEMENT");
		if (elementId !=  null) {
			// the element exists
			contexts.setElementId(elementId);
			return true;
		} else {
			// the element does not exist
			return false;
		}

	}

	private JSONArray findElements(JSONObject jsonObject) throws Exception {
		jsonObject.put("sessionId", contexts.getSessionId());
		JSONObject response = (JSONObject) utils.request("POST", DriverCommand.FIND_ELEMENTS, jsonObject);
		JSONArray elements = (JSONArray) response.get("value");
		return elements;
	}


	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param name
	 *            The name attribute of element
	 * @param value
	 *            the value for target element
	 * @return return the element to find if it exists,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element element(String name, String value) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", name);
		jsonObject.put("using", value);
		boolean isExist = findElement(jsonObject);
		return isExist ? element : null;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param elementId
	 *            The ID attribute of element
	 * @return return the element to find if it exists,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element elementById(String elementId) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", elementId);
		jsonObject.put("using", "id");
		boolean isExist = findElement(jsonObject);
		return isExist ? element : null;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Web(WebView)
	 *
	 * @param selector
	 *            The css selector of element
	 * @return return the element to find if it exists,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element elementByCss(String selector) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", selector);
		jsonObject.put("using", "css");
		boolean isExist = findElement(jsonObject);
		return isExist ? element : null;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param xpath
	 *            The XPath expression of element
	 * @return return the element to find if it exists,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element elementByXPath(String xpath) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", xpath);
		jsonObject.put("using", "xpath");
		boolean isExist = findElement(jsonObject);
		return isExist ? element : null;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param name
	 *            The name attribute of element
	 * @return return the element to find if it exists,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element elementByName(String name) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", name);
		jsonObject.put("using", "name");
		boolean isExist = findElement(jsonObject);
		return isExist ? element : null;
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
	 * @return return the element to find if it exists,if it does not exist ,return null
	 */
	public Element  getElement(GetElementWay wayToFind, String value, int index) throws Exception {
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
			return null;
		}

		return element;
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
	 * @return return the element to find if it exists,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element getElement(GetElementWay wayToFind, String value) throws Exception {
		switch (wayToFind) {
		case ID:
			return elementById(value);
		case CSS:
		    return elementByCss(value);
		case NAME:
			return elementByName(value);
		case XPATH:
			return elementByXPath(value);
		case CLASS_NAME:
			return elementByClassName(value);
		case LINK_TEXT:
			return elementByLinkText(value);
		case PARTIAL_LINK_TEXT:
			return elementByPartialLinkText(value);
		case TAG_NAME:
			return elementByTagName(value);
		default:
			return null;
		}

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
	 * @return return the element to find if it exists,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element waitForElement(GetElementWay wayToFind, String value, int index) throws Exception {
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
			return null;
		}
		return element;
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
	 * @return return the element to find if it exist,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element waitForElement(GetElementWay wayToFind, String value) throws Exception {
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
			return null;
		}
		return element;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param className
	 *            The className attribute of element
	 * @return return the element to find if it exist,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element elementByClassName(String className) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", className);
		jsonObject.put("using", "class name");
		boolean isExist = findElement(jsonObject);
		return isExist ? element : null;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param linkText
	 *            The linkText attribute of element
	 * @return return the element to find if it exist,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element elementByLinkText(String linkText) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", linkText);
		jsonObject.put("using", "link text");
		boolean isExist = findElement(jsonObject);
		return isExist ? element : null;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param tagName
	 *            The tag name attribute of element
	 * @return return the element to find if it exist,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element elementByTagName(String tagName) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", tagName);
		jsonObject.put("using", "tag name");
		boolean isExist = findElement(jsonObject);
		return isExist ? element : null;
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param partialLinkText
	 *            The partial link text attribute of element
	 * @return return the element to find if it exist,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element elementByPartialLinkText(String partialLinkText) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", partialLinkText);
		jsonObject.put("using", "partial link text");
		boolean isExist = findElement(jsonObject);
		return isExist ? element : null;
	}

	/**
	 * <p>
	 * Search for multiple elements on the page, starting from the document
	 * root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param xpath
	 *            The XPath expression of elements
	 * @return ElementSelector object
	 * @throws Exception
	 */
	public ElementSelector elementsByXPath(String xpath) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", xpath);
		jsonObject.put("using", "xpath");
		JSONArray jsonArray = findElements(jsonObject);
		return new ElementSelector(contexts, this, jsonArray);
	}

	/**
	 * <p>
	 * Search for multiple elements on the page, starting from the document
	 * root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param name
	 *            The name attribute of elements
	 * @return ElementSelector object
	 * @throws Exception
	 */
	public ElementSelector elementsByName(String name) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", name);
		jsonObject.put("using", "name");
		JSONArray jsonArray = findElements(jsonObject);
		return new ElementSelector(contexts, this, jsonArray);
	}

	/**
	 * <p>
	 * Search for multiple elements on the page, starting from the document
	 * root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param elementId
	 *            The elementId attribute of elements
	 * @return ElementSelector object
	 * @throws Exception
	 */
	public ElementSelector elementsById(String elementId) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", elementId);
		jsonObject.put("using", "id");
		JSONArray jsonArray = findElements(jsonObject);
		return new ElementSelector(contexts, this, jsonArray);
	}

	/**
	 * <p>
	 * Search for multiple elements on the page, starting from the document
	 * root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param className
	 *            The className attribute of elements
	 * @return ElementSelector object
	 * @throws Exception
	 */
	public ElementSelector elementsByClassName(String className) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", className);
		jsonObject.put("using", "class name");
		JSONArray jsonArray = findElements(jsonObject);
		return new ElementSelector(contexts, this, jsonArray);
	}

	/**
	 * <p>
	 * Search for multiple elements on the page, starting from the document
	 * root.<br>
	 * Support: Web(WebView)
	 *
	 * @param css
	 *            The selector selector of elements
	 * @return ElementSelector object
	 * @throws Exception
	 */
	public ElementSelector elementsByCss(String css) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", css);
		jsonObject.put("using", "selector");
		JSONArray jsonArray = findElements(jsonObject);
		return new ElementSelector(contexts, this, jsonArray);
	}

	/**
	 * <p>
	 * Search for multiple elements on the page, starting from the document
	 * root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param linkText
	 *            The link text attribute of elements
	 * @return ElementSelector object contains elements
	 * @throws Exception
	 */
	public ElementSelector elementsByLinkText(String linkText) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", linkText);
		jsonObject.put("using", "link text");
		JSONArray jsonArray = findElements(jsonObject);
		return new ElementSelector(contexts, this, jsonArray);
	}

	/**
	 * <p>
	 * Search for multiple elements on the page, starting from the document
	 * root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param linkText
	 *            The partial link text attribute of elements
	 * @return ElementSelector object
	 * @throws Exception
	 */
	public ElementSelector elementsByPartialLinkText(String partialLinkText) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", partialLinkText);
		jsonObject.put("using", "partial link text");
		JSONArray jsonArray = findElements(jsonObject);
		return new ElementSelector(contexts, this, jsonArray);
	}

	/**
	 * <p>
	 * Search for multiple elements on the page, starting from the document
	 * root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param tagName
	 *            The tag name attribute of elements
	 * @return  ElementSelector object
	 * @throws Exception
	 */
	public ElementSelector elementsByTagName(String tagName) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", tagName);
		jsonObject.put("using", "tag name");
		JSONArray jsonArray = findElements(jsonObject);
		return new ElementSelector(contexts, this, jsonArray);
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
	 * @return return the element to find if it exist,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element waitForElement(String using, String value, int timeout, int interval) throws Exception {
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
				findElement(jsonObject);
				break;
			}
		}
		if (satisfied == false) {
			System.out.println("can't find the element: " + using + ":" + value);
			return null;
		}
		return element;
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
	 * @return return the element to find if it exist,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element waitForElement(String using, String value) throws Exception {
		// default timeout:2000, default interval:200
		 return waitForElement(using, value, waitElementTimeout, waitElementTimeInterval);
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param elementId
	 *            The ID attribute of element
	 * @return return the element to find if it exist,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element waitForElementById(String elementId) throws Exception {
		return waitForElement(GetElementWay.ID, elementId);
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Web(WebView)
	 *
	 * @param selector
	 *            The css selector of element
	 * @returnThe return the element to find if it exist,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element waitForElementByCss(String selector) throws Exception {
		return waitForElement(GetElementWay.CSS, selector);
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param xpath
	 *            The XPath expression of element
	 * @return return the element to find if it exist,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element waitForElementByXPath(String xpath) throws Exception {
		return waitForElement(GetElementWay.XPATH, xpath);
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param tagname
	 *            The tagname expression of element
	 * @return return the element to find if it exist,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element waitForElementByTagName(String tagname) throws Exception {
		return waitForElement(GetElementWay.TAG_NAME, tagname);
	}
	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param name
	 *            The name attribute of element
	 * @return return the element to find if it exist,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element waitForElementByName(String name) throws Exception {
		return waitForElement(GetElementWay.NAME, name);
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param text
	 *            The visible text of element
	 * @return return the element to find if it exist,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element waitForElementByLinkText(String text) throws Exception {
		return waitForElement(GetElementWay.LINK_TEXT, text);
	}

	/**
	 * <p>
	 * Search for an element on the page, starting from the document root.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @param text
	 *            The visible text of element
	 * @return return the element to find if it exist,if it does not exist ,return null
	 * @throws Exception
	 */
	public Element waitForElementByPartialLinkText(String text) throws Exception {
		return waitForElement("partial link text", text);
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
			findElement(jsonObject);
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
	@Deprecated
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
	 * Click on an element.<br>
	 * Support: Android iOS Web(WebView)
	 *
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	@Deprecated
	public MacacaClient click() throws Exception {
		element.click();
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
	@Deprecated
	public MacacaClient clear() throws Exception {
		element.clearText();
		return this;
	}

	/**
	 * <p>
	 * press back.<br>
	 * Support: Android Web(WebView)
	 *
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient back() throws Exception {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId",contexts.getSessionId());
		utils.request("POST", DriverCommand.BACK, jsonObject);
		return this;
	}

	/**
	 * <p>
	 * Get the result of a property of a element.<br>
	 * Support: Android iOS Web(WebView). iOS: 'isVisible', 'label', 'value', Android: 'selected', 'description', 'text'
	 *
	 * @param name
	 *            The property name of element
	 * @return The property
	 * @throws Exception
	 */
	@Deprecated
	public Object getProperty(String name) throws Exception {
		return element.getProperty(name);
	}

	/**
	 * <p>
	 * Get the dimensions and coordinates of the given element with a object including x/y/height/width.<br>
	 * Support: Android iOS
	 *
	 * @return The rect of element
	 * @throws Exception
	 */
	@Deprecated
	public Object getRect() throws Exception {
		return element.getRect();
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
	 *            The capabilities of session  {@see <a href="https://github.com/alibaba/macaca/issues/366" target="_blank">more params</a>}
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
		return contexts.getSessionId();
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
	@Deprecated
	public String text() throws Exception {
		return element.getText();
	}

	/**
	 * <p>
	 * Apply touch actions on devices. <br>
	 * Support: Support: Android iOS
	 *
	 * @param action The new window width, such as, tap/doubleTap/press/pinch/rotate/drag
	 * @param args Parameters of the action {@see <a href="https://github.com/alibaba/macaca/issues/366" target="_blank">more params</a>}
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient touch(String action, JSONObject args) throws Exception {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", contexts.getSessionId());
		JSONArray array = new JSONArray();
		JSONObject actionObject = new JSONObject();
		actionObject.put("type", action);
		for (String key : args.keySet()) {
			String value = args.getString(key);
			actionObject.put(key , value);
		}
		array.add(actionObject);
		jsonObject.put("actions", array);
		Utils utils = new Utils(contexts);
		utils.request("POST", DriverCommand.ACTIONS, jsonObject);
		return this;
	}

	 /**
     * tap by coordinate
     * Support: Android iOS
     *
     * @param x
     * 			coordinate - x
     * @param y
     * 			coordinate - y
     * @throws Exception
     */
	public void tap(double x,double y) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("x", x);
		jsonObject.put("y", y);
		touch("tap", jsonObject);
	}

	/**
	 * double tap by coordinate
	 * Support: Android iOS
	 *
	 * @param x
	 * 			coordinate - x
	 * @param y
	 * 			coordinate - y
	 * @throws Exception
	 */
	public void doubleTap(double x,double y) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("x", x);
		jsonObject.put("y", y);
		touch("doubleTap", jsonObject);
	}

	/**
	 * long press
	 * Support: Android iOS
	 *
	 * @param x
	 * 			coordinate - x
	 * @param y
	 * 			coordinate - y
	 * @param duration(for iOS,time-unit:s)
	 * 			time to press(valid for iOS,time-unit:s)
	 * @param steps(for android,time-unit:step)
	 * 			time to press（valid for Android,1 step is about 5ms）
	 * @throws Exception
	 */
	public  void press(double x,double y, double duration,int steps) throws Exception{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("x", x);
		jsonObject.put("y", y);
		jsonObject.put("duration", duration);
		jsonObject.put("steps", steps);
		touch("press", jsonObject);
	}

	/**
	 * pinch
	 * Support: Android iOS
	 *
	 * @param x
	 * 			coordinate -x
	 * @param y
	 * 			coordinate -y
	 * @param scale
	 *			valid for iOS, GesturePinchType.PINCH_IN: scale < 1;GesturePinchType.PINCH_OUT:scale > 1
	 * @param velocity
	 * 			valid for iOS
	 * @param pinchType
	 * 			GesturePinchType.PINCH_IN，GesturePinchType.PINCH_OUT
	 * @param percent
	 * 			valid for Android, percent to pinch
	 * @param steps
	 * 			valid for Android, unit for andriod pinch
	 * @throws Exception
	 */
	public void pinch(double x,double y,double scale, double velocity,GesturePinchType direction,double percent,int steps) throws Exception{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("x", x);
		jsonObject.put("y", y);
		jsonObject.put("scale", scale);
		jsonObject.put("velocity", velocity);
		jsonObject.put("percent", percent);
		jsonObject.put("steps", steps);
		if (direction == GesturePinchType.PINCH_IN) {
			jsonObject.put("direction", "in");
		} else {
			jsonObject.put("direction", "out");
		}
		touch("pinch", jsonObject);
	}

	/**
	 * rotate
	 * Support: iOS
	 * @param rotation
	 * 			旋转弧度
	 * @param velocity
	 * 			旋转速率
	 * @throws Exception
	 */
	public void rotate(double rotation, double velocity) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("rotation", rotation);
		jsonObject.put("velocity", velocity);
		touch("rotate", jsonObject);
	}

	/**
	 * drag
	 * Support: Android iOS
	 *
	 * @param fromX
	 * 			drag start x-coordinate
	 * @param fromY
	 * 			drag start y-coordinate
	 * @param toX
	 * 			drag end x-coordinate
	 * @param toY
	 * 			drag end y-coordinate
	 * @param duration
	 * 			drag duration (valid for iOS,time-unit:s)
	 * @param steps
	 * 			drag duration (valid for Android,time-unit:steps)
	 * @throws Exception
	 */
	public void drag(double fromX, double fromY, double toX,double toY,double duration, int steps) throws Exception{

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fromX", fromX);
		jsonObject.put("fromY", fromY);
		jsonObject.put("toX", toX);
		jsonObject.put("toY", toY);
		jsonObject.put("duration", duration);
		jsonObject.put("steps", steps);

		touch("drag", jsonObject);
	}


}

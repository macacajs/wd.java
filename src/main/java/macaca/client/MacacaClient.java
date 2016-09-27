package macaca.client;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import macaca.client.commands.*;
import macaca.client.common.ElementSelector;
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
	private Timeouts timeouts = new Timeouts(driver);
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
	 * Accepts the currently displayed alert dialog.
	 * 
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient acceptAlert() throws Exception {
		alert.acceptAlert();
		return this;
	}

	/**
	 * Dismisses the currently displayed alert dialog.
	 * 
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient dismissAlert() throws Exception {
		alert.dismissAlert();
		return this;
	}

	/**
	 * Gets the text of the currently displayed JavaScript alert(), confirm(),
	 * or prompt() dialog.
	 * 
	 * @return The text of the currently displayed alert.
	 * @throws Exception
	 */
	public String alertText() throws Exception {
		return alert.alertText();
	}

	/**
	 * Sends keystrokes to a JavaScript prompt() dialog.
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
	 * Get a list of the available contexts.
	 * 
	 * @return The currently available contexts
	 * @throws Exception
	 */
	public JSONArray contexts() throws Exception {
		return context.getContexts();
	}

	/**
	 * Set the current context.
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
	 * Get the current context.
	 * 
	 * @return The currently context
	 * @throws Exception
	 */
	public String currentContext() throws Exception {
		return context.getContext();
	}

	// Element

	/**
	 * Search for an element on the page, starting from the document root.
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
	 * Search for an element on the page, starting from the document root.
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
	 * Search for an element on the page, starting from the document root.
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
	 * Search for an element on the page, starting from the document root.
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
	 * Search for an element on the page, starting from the document root.
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
	 * Search for an element on the page, starting from the document root.
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
	 * Search for an element on the page, starting from the document root.
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
	 * Search for an element on the page, starting from the document root.
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
	 * Search for multiple elements on the page, starting from the document
	 * root.
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
	 * Search for multiple elements on the page, starting from the document
	 * root.
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
	 * Search for multiple elements on the page, starting from the document
	 * root.
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
	 * Search for multiple elements on the page, starting from the document
	 * root.
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
	 * Search for multiple elements on the page, starting from the document
	 * root.
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
	 * Search for multiple elements on the page, starting from the document
	 * root.
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
	 * Search for multiple elements on the page, starting from the document
	 * root.
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
	 * Search for multiple elements on the page, starting from the document
	 * root.
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
	 * Search for element at specific interval during given time
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
	public MacacaClient waitForElement(String using,String value,int timeout,int interval) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", value);
		jsonObject.put("using", using);
		int count = 1;
		int timeLeft = timeout;
		boolean satisfied = false;
		while (timeLeft > 0) {
			boolean elementExist = false;
			System.out.println(String.format("第%d次尝试找元素", count++));
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
			System.out.println("没有找到对应的element: " + using + ":" + value);
			throw new Exception();
		}
		return this;
	}

	/**
	 * Search for element at specific interval during given time
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
	 * Search for an element on the page, starting from the document root.
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
	 * Search for an element on the page, starting from the document root.
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
	 * Search for an element on the page, starting from the document root.
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
	 * Search for an element on the page, starting from the document root.
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
	 * check if target element exist
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
		} catch(Exception e) {
			return false;
		}

		return element.isDisplayed();
	}

	/**
	 * Send a sequence of key strokes to the active element.
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
	 * Click on an element.
	 * 
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient click() throws Exception {
		element.click();
		return this;
	}

	/**
	 * clear on an element.
	 * 
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient clear() throws Exception {
		element.clearText();
		return this;
	}

	/**
	 * press back.
	 * 
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient back() throws Exception {
		element.back();
		return this;
	}

	/**
	 * Swipe on the touch screen using finger motion events.
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
	 * Get the value of an web element's attribute.
	 * 
	 * @param name
	 *            The attribute name of element
	 * @return The attribute
	 * @throws Exception
	 */
	public String getAttribute(String name) throws Exception {
		return element.getAttribute(name);
	}

	/**
	 * Get the result of a property of a element.
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
	 * Inject a snippet of JavaScript into the page for execution in the context
	 * of the currently selected frame.
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
	 * Send a sequence of key strokes to the active window.
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
		jsonObject.put("value", keys);
		_keys.keys(jsonObject);
		return this;
	}

	// ScreenShot

	/**
	 * Take a screenshot of the current page.
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
	 * Save screenshot of the current page.
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
	 * Initial webdriver client and create a session.
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
	 * Get the current sessionId.
	 * 
	 * @return The current sessionId.
	 * @throws Exception
	 */
	public String sessionId() throws Exception {
		return driver.getSessionId();
	}

	/**
	 * Delete session.
	 * 
	 * @throws Exception
	 */
	public void quit() throws Exception {
		session.delSession();
	}

	// Source

	/**
	 * Get the current page source.
	 * 
	 * @return The current page source.
	 * @throws Exception
	 */
	public String source() throws Exception {
		return source.getSource();
	}

	// Status

	/**
	 * Query the server's current status.
	 * 
	 * @throws Exception
	 */
	public String status() throws Exception {
		return status.getStatus();
	}

	// Timeouts

	/**
	 * Set the amount of time the driver should wait when searching for
	 * elements.
	 * 
	 * @param ms
	 *            The amount of time to wait, in milliseconds. This value has a
	 *            lower bound of 0.
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient setImplicitWaitTimeout(int ms) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ms", ms);
		timeouts.implicitWait(jsonObject);
		return this;
	}

	/**
	 * Set the amount of time the driver should wait.
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
	 * Get the current page title.
	 * 
	 * @return The current page title.
	 * @throws Exception
	 */
	public String title() throws Exception {
		return title.title();
	}

	// Url

	/**
	 * Retrieve the URL of the current page.
	 * 
	 * @return The current URL.
	 * @throws Exception
	 */
	public String url() throws Exception {
		return _url.url();
	}

	/**
	 * Navigate to a new URL.
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
	 * Get the size of the specified window.
	 * 
	 * @return {width: number, height: number} The size of the window.
	 * @throws Exception
	 */
	public JSONObject getWindowSize() throws Exception {
		return window.getWindowSize();
	}

	/**
	 * Change the size of the specified window.
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
	 * set maximize size of the window.
	 * 
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public MacacaClient maximize() throws Exception {
		window.maximize();
		return this;
	}

	/**
	 * Get text of the element
	 * 
	 * @return The text of the element
	 * @throws Exception
	 */
	public String text() throws Exception {
		return element.getText();
	}

}

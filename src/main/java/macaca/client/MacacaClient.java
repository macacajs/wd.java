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

	// Alert

	/**
	 * Accepts the currently displayed alert dialog.
	 * @throws Exception
	 */
	public void acceptAlert() throws Exception {
		alert.acceptAlert();
	}

	/**
	 * Dismisses the currently displayed alert dialog.
	 * @throws Exception
	 */
	public void dismissAlert() throws Exception {
		alert.dismissAlert();
	}

	/**
	 * Gets the text of the currently displayed JavaScript alert(), confirm(), or prompt() dialog.
	 * @return
	 * @throws Exception
	 */
	public String alertText() throws Exception {
		return alert.alertText();
	}

	/**
	 * Sends keystrokes to a JavaScript prompt() dialog.
	 * @param jsonObj
	 * @throws Exception
	 */
	public void alertKeys(String keys) throws Exception {
		alert.alertKeys(keys);
	}

	// Context

	/**
	 * Get a list of the available contexts.
	 * @return
	 * @throws Exception
	 */
	public JSONArray contexts() throws Exception {
		return context.getContexts();
	}


	/**
	 * Set the current context.
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public MacacaClient context(String name) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		context.setContext(jsonObject);
		return this;
	}

	/**
	 * Get the current context.
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public String currentContext(String name) throws Exception {
		return context.getContext();
	}

	// Element

	/**
	 * Search for an element on the page, starting from the document root.
	 * @param elementId
	 * @return
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
	 * @param selector
	 * @return
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
	 * @param xpath
	 * @return
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
	 * @param name
	 * @return
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
	 * Search for multiple elements on the page, starting from the document root.
	 * @param xpath
	 * @return
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
	 * Search for multiple elements on the page, starting from the document root.
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public MacacaClient elementsByName(String name) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", name);
		jsonObject.put("using", "name");
		element.findElements(jsonObject);
		return this;
	}

	/**
	 * Send a sequence of key strokes to the active element.
	 * @param value
	 * @return
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
	 * @return
	 * @throws Exception
	 */
	public MacacaClient click() throws Exception {
		element.click();
		return this;
	}


	/**
	 * Swipe on the touch screen using finger motion events.
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @param duration
	 * @return
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
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public String getAttribute(String name) throws Exception {
		return element.getAttribute(name);
	}

	// Execute

	/**
	 * Inject a snippet of JavaScript into the page for execution in the context of the currently selected frame.
	 * @param code
	 * @return
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
	 * @param keys
	 * @throws Exception
	 */
	public void keys(String keys) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("keys", keys);
		_keys.keys(jsonObject);
	}

	// ScreenShot

	/**
	 * Take a screenshot of the current page.
	 * @return
	 * @throws Exception
	 */
	public MacacaClient takeScreenshot() throws Exception {
		screenshot.takeScreenshot();
		return this;
	}

	// Session


	/**
	 * Initial webdriver client and create a session.
	 * @param jsonObject
	 * @return
	 * @throws Exception
	 */
	public MacacaClient initDriver(JSONObject jsonObject) throws Exception {
		session.createSession(jsonObject);
		return this;
	}

	/**
	 * Delete session.
	 * @throws Exception
	 */
	public void quit() throws Exception {
		session.delSession();
	}

	// Source

	/**
	 * Get the current page source.
	 * @return
	 * @throws Exception
	 */
	public String source() throws Exception {
		return source.getSource();
	}

	// Status

	/**
	 * Query the server's current status.
	 * @throws Exception
	 */
	public void status() throws Exception {
	}

	// Timeouts

	/**
	 * Set the amount of time the driver should wait when searching for elements.
	 * @param ms
	 * @return
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
	 * @param ms
	 * @return
	 * @throws Exception
	 */
	public MacacaClient sleep(int ms) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ms", ms);
		timeouts.implicitWait(jsonObject);
		return this;
	}

	// Title

	/**
	 * Get the current page title.
	 * @return
	 * @throws Exception
	 */
	public String title() throws Exception {
		return title.title();
	}

	// Url

	/**
	 * Retrieve the URL of the current page.
	 * @throws Exception
	 */
	public void url() throws Exception {
		_url.url();
	}

	/**
	 * Navigate to a new URL.
	 * @param url
	 * @return
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
	 * @return
	 * @throws Exception
	 */
	public String getWindowSize() throws Exception {
		return window.getWindowSize();
	}

	/**
	 * Change the size of the specified window.
	 * @param width
	 * @param height
	 * @return
	 * @throws Exception
	 */
	public MacacaClient setWindowSize(int width, int height) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("width", width);
		jsonObject.put("height", height);
		window.setWindowSize(jsonObject);
		return this;
	}

}

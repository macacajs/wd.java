package macaca.client;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import macaca.client.commands.*;
import macaca.client.common.ElementSelector;

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

	public void acceptAlert() throws Exception {
		alert.acceptAlert();
	}

	public void dismissAlert() throws Exception {
		alert.dismissAlert();
	}

	public String alertText() throws Exception {
		return alert.alertText();
	}

	public void alertKeys(JSONObject jsonObj) throws Exception {
		alert.alertKeys(jsonObj);
		;
	}

	// Context

	public JSONArray contexts() throws Exception {
		return context.getContexts();
	}

	public MacacaClient context(String name) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		context.setContext(jsonObject);
		return this;
	}

	public String currentContext(String name) throws Exception {
		return context.getContext();
	}

	// Element

	public MacacaClient elementById(String elementId) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", elementId);
		jsonObject.put("using", "id");
		element.findElement(jsonObject);
		return this;
	}

	public MacacaClient elementByCss(String selector) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", selector);
		jsonObject.put("using", "css");
		element.findElement(jsonObject);
		return this;
	}

	public MacacaClient elementByXPath(String xpath) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", xpath);
		jsonObject.put("using", "xpath");
		element.findElement(jsonObject);
		return this;
	}

	public MacacaClient elementByName(String name) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", name);
		jsonObject.put("using", "name");
		element.findElement(jsonObject);
		return this;
	}

	public ElementSelector elementsByXPath(String xpath) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", xpath);
		jsonObject.put("using", "xpath");
		JSONArray jsonArray = element.findElements(jsonObject);

		return new ElementSelector(driver, this, jsonArray);
	}

	public MacacaClient elementsByName(String name) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("value", name);
		jsonObject.put("using", "name");
		element.findElements(jsonObject);
		return this;
	}

	public MacacaClient sendKeys(String value) throws Exception {
		JSONObject jsonObject = new JSONObject();
		ArrayList<String> values = new ArrayList<String>();
		values.add(value);
		jsonObject.put("value", values);
		element.setValue(jsonObject);
		return this;
	}

	public MacacaClient click() throws Exception {
		element.click();
		return this;
	}

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

	public String getAttribute(String name) throws Exception {
		return element.getAttribute(name);
	}

	// Execute

	public String execute(String code) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", code);
		return execute.execute(jsonObject);
	}

	// Keys

	public void keys(String keys) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("keys", keys);
		_keys.keys(jsonObject);
	}

	// ScreenShot

	public MacacaClient takeScreenshot() throws Exception {
		screenshot.takeScreenshot();
		return this;
	}

	// Session

	public MacacaClient initDriver(JSONObject jsonObject) throws Exception {
		session.createSession(jsonObject);
		return this;
	}

	public void quit() throws Exception {
		session.delSession();
	}

	// Source

	public String source() throws Exception {
		return source.getSource();
	}

	// Status

	public void status() throws Exception {
	}

	// Timeouts

	public MacacaClient setImplicitWaitTimeout(int ms) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ms", ms);
		timeouts.implicitWait(jsonObject);
		return this;
	}

	public MacacaClient sleep(int ms) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ms", ms);
		timeouts.implicitWait(jsonObject);
		return this;
	}

	// Title

	public String title() throws Exception {
		return title.title();
	}

	// Url

	public void url() throws Exception {
		_url.url();
	}

	public MacacaClient get(String url) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("url", url);
		_url.getUrl(jsonObject);
		return this;
	}

	// Window

	public MacacaClient getWindow() throws Exception {
		window.getWindow();
		return this;
	}

	public MacacaClient setWindowSize(int width, int height) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("width", width);
		jsonObject.put("height", height);
		window.setWindowSize(jsonObject);
		return this;
	}

}

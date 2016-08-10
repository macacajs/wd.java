package macaca.client;

import java.util.ArrayList;

import macaca.client.commands.Alert;
import macaca.client.commands.Context;
import macaca.client.commands.Element;
import macaca.client.commands.ScreenShot;
import macaca.client.commands.Session;
import macaca.client.commands.Source;
import macaca.client.commands.Url;
import macaca.client.commands.Window;
import macaca.client.common.ElementSelector;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MacacaClient {

	private MacacaDriver driver = new MacacaDriver();

	private Alert alert = new Alert(driver);
	private Context context = new Context(driver);
	private Session session = new Session(driver);
	private Window window = new Window(driver);
	private Url url = new Url(driver);
	private Element element = new Element(driver);
	private Source source = new Source(driver);
	private ScreenShot screenshot = new ScreenShot(driver);

	public MacacaClient initDriver(JSONObject jsonObj) throws Exception {
		session.createSession(jsonObj);
		return this;
	}

	public void quit() throws Exception {
		session.delSession();
	}

	public JSONArray contexts() throws Exception {
		return context.getContexts();
	}

	public MacacaClient context(String name) throws Exception {
		JSONObject contextObj = new JSONObject();
		contextObj.put("name", name);
		context.setContext(contextObj);
		return this;
	}

	public MacacaClient getWindow() throws Exception {
		window.getWindow();
		return this;
	}

	public MacacaClient setWindowSize(int width, int height) throws Exception {
		JSONObject sizeObj = new JSONObject();
		sizeObj.put("width", width);
		sizeObj.put("height", height);
		window.setWindowSize(sizeObj);
		return this;
	}

	public MacacaClient get(String distUrl) throws Exception {
		JSONObject urlObj = new JSONObject();
		urlObj.put("url", distUrl);
		url.getUrl(urlObj);
		return this;
	}

	public MacacaClient elementById(String elementId) throws Exception {
		JSONObject elementObj = new JSONObject();
		elementObj.put("value", elementId);
		elementObj.put("using", "id");
		element.findElement(elementObj);
		return this;
	}

	public MacacaClient elementByCss(String selector) throws Exception {
		JSONObject elementObj = new JSONObject();
		elementObj.put("value", selector);
		elementObj.put("using", "css");
		element.findElement(elementObj);
		return this;
	}

	public MacacaClient elementByXPath(String xpath) throws Exception {
		JSONObject elementObj = new JSONObject();
		elementObj.put("value", xpath);
		elementObj.put("using", "xpath");
		element.findElement(elementObj);
		return this;
	}

	public MacacaClient elementByName(String name) throws Exception {
		JSONObject elementObj = new JSONObject();
		elementObj.put("value", name);
		elementObj.put("using", "name");
		element.findElement(elementObj);
		return this;
	}

	public ElementSelector elementsByXPath(String xpath) throws Exception {
		JSONObject elementsObj = new JSONObject();
		elementsObj.put("value", xpath);
		elementsObj.put("using", "xpath");
		JSONArray jsonArray = element.findElements(elementsObj);

		return new ElementSelector(driver, this, jsonArray);
	}

	public MacacaClient elementsByName(String name) throws Exception {
		JSONObject elementsObj = new JSONObject();
		elementsObj.put("value", name);
		elementsObj.put("using", "name");
		element.findElements(elementsObj);
		return this;
	}

	public MacacaClient sendKeys(String value) throws Exception {
		JSONObject valueObj = new JSONObject();
		ArrayList<String> values = new ArrayList<String>();
		values.add(value);
		valueObj.put("value", values);
		element.setValue(valueObj);
		return this;
	}

	public MacacaClient click() throws Exception {
		element.click();
		return this;
	}

	public String source() throws Exception {
		return source.getSource();
	}

	public MacacaClient takeScreenshot() throws Exception {
		screenshot.takeScreenshot();
		return this;
	}

	public MacacaClient swipe(int startX, int startY, int endX, int endY, int duration) throws Exception {
		JSONObject swipeObj = new JSONObject();
		swipeObj.put("startX", startX);
		swipeObj.put("startY", startY);
		swipeObj.put("endX", endX);
		swipeObj.put("endY", endY);
		swipeObj.put("duration", duration);
		element.swipe(swipeObj);
		return this;
	}

	public MacacaClient sleep(int mseconds) throws InterruptedException {
		Thread.sleep(mseconds);
		return this;
	}

	public String getAttribute(String name) throws Exception {
		return element.getAttribute(name);
	}

}

package macaca.client;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class SampleTest {

	MacacaClient driver = new MacacaClient();

	@Before
	public void setUp() throws Exception {
		Logger logger = Logger.getLogger(getClass());
		JSONObject porps = new JSONObject();
		porps.put("autoAcceptAlerts", true);
		porps.put("platformVersion", "9.3");
		porps.put("deviceName", "iPhone 5s");
		porps.put("platformName", "iOS");
		//porps.put("reuse", 2);
		porps.put("app", "/Users/julia/macaca-test-sample/app/ios-app-bootstrap.zip");
		JSONObject desiredCapabilities = new JSONObject();
		desiredCapabilities.put("desiredCapabilities", porps);
		driver.initDriver(desiredCapabilities);
	}

	@Test
	public void test_case_1() throws Exception {
		driver
			.elementByXPath("//XCUIElementTypeTextField[1]")
			.sendKeys("中文+Test+12345678")
			.elementByXPath("//XCUIElementTypeSecureTextField[1]")
			.sendKeys("111111")
			.sleep(1000)
	    	.sendKeys("\n")
	    	.elementByName("Login")
	    	.click()
	    	.source();
	    
	    driver
	    	.elementByName("HOME")
	    	.click()
	    	.elementByName("list")
	    	.click()
	    	.sleep(3000)
	    	.swipe(200, 400, 200, 100, 500)
	    	.click()
	    	.sleep(1000)
			.elementByName("Webview")
			.click()
			.takeScreenshot();
		JSONArray contexts1 = driver.contexts();		
		
		driver
			.context(contexts1.getString(contexts1.size() - 1))
			.elementById("pushView")
			.click()
			.sleep(1000)
			.elementById("popView")
			.click()
			.takeScreenshot()
			.context(contexts1.getString(0))
			.elementByName("Baidu")
			.click()
			.sleep(3000);
		
		JSONArray contexts2 = driver.contexts();	
		
		driver
			.context(contexts2.getString(contexts2.size() - 1))
			.elementById("index-kw")
			.sendKeys("中文+TesterHome")
			.elementById("index-bn")
			.click()
			.source();
		
		driver
		    .takeScreenshot()
			.context(contexts1.getString(0))
			.elementByName("PERSONAL")
			.click()
			.takeScreenshot()
			.elementByName("Logout")
			.click()
			.takeScreenshot()
			.sleep(3000);
	}
	
	
	@After
	public void tearDown() throws Exception {
		//driver.quit();
	}
}

package macaca.client;

import static org.hamcrest.Matchers.containsString;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import macaca.client.MacacaClient;

public class SampleTest2 {

	MacacaClient driver = new MacacaClient();

	@Before
	public void setUp() throws Exception {
		Logger logger = Logger.getLogger(getClass());
		JSONObject porps = new JSONObject();
		porps.put("autoAcceptAlerts", true);
		porps.put("browserName", "electron");
		porps.put("platformName", "desktop");
		porps.put("version", "");
		porps.put("javascriptEnabled", true);
		porps.put("platform", "ANY");
		JSONObject desiredCapabilities = new JSONObject();
		desiredCapabilities.put("desiredCapabilities", porps);
		driver.initDriver(desiredCapabilities);
		driver.setWindowSize(1280, 800);
		driver.get("https://www.baidu.com");
	}

	@Test
	public void test_case_1() throws Exception {
		driver.elementById("kw");
		driver.sendKeys("macaca");
		Thread.sleep(1000);
		driver.elementById("su");
		driver.click();
		String html = driver.source();

		Assert.assertThat(html, containsString("macaca"));

		driver.elementByCss("#head > div.head_wrapper");

		driver.elementByXPath("//*[@id=\"kw\"]");
		driver.sendKeys(" elementByXPath");
		driver.elementById("su");
		driver.click();
		driver.takeScreenshot();
	}

	@Test
	public void test_case_2() throws Exception {
		System.out.println("test case #2");
		driver.get("https://www.baidu.com");
		driver.elementById("kw");
		driver.sendKeys("testerhome");

		driver.elementByXPath("//*[@id=\"kw\"]");
		driver.click();
		Thread.sleep(3000);
		String html = driver.source();
		Assert.assertThat(html, containsString("testerhome"));
		driver.takeScreenshot();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}

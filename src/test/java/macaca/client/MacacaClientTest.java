package macaca.client;

import com.alibaba.fastjson.JSONObject;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MacacaClientTest {

	MacacaClient driver = new MacacaClient();

	// the default mock data of request
	JSONObject defaultMockData = new JSONObject();

	/*
	* http://wiremock.org/docs/
	* */
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(3456);

	@Before
	public void setUp() throws Exception {
		defaultMockData.put("value", "");
		defaultMockData.put("status", 0);
		defaultMockData.put("sessionId", "123456");

		stubFor(any(urlPathMatching(".*/session.*"))
			.willReturn(aResponse()
				.withStatus(200)
				.withHeader("content-type", "application/json")
				.withBody(defaultMockData.toString())));

		JSONObject porps = new JSONObject();
		porps.put("browserName", "electron");
		porps.put("platformName", "desktop");
		JSONObject desiredCapabilities = new JSONObject();
		desiredCapabilities.put("desiredCapabilities", porps);
		driver.initDriver(desiredCapabilities);
	}

	@Test
	public void test_case_1() throws Exception {
		// test url
		String url = "https://macacajs.github.io";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("url", url);

		// set the request router
		stubFor(
			post(urlPathMatching("/wd/hub.*/url"))
				//	mock the response using the default mock data
				.willReturn(
					aResponse()
						.withStatus(200)
						.withHeader("content-type", "application/json")
						.withBody(defaultMockData.toString())
				));

		// begin to test the api
		driver.get(url);

		verify(
			// check the url
			anyRequestedFor(urlPathMatching("/wd/hub.*/url"))
			// check the request body
			.withRequestBody(equalToJson(jsonObj.toString()))
		);
	}


	@After
	public void tearDown() throws Exception {

	}
}

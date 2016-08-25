package macaca.client.commands;

import com.alibaba.fastjson.JSONObject;

import macaca.client.common.DriverCommand;
import macaca.client.common.MacacaDriver;
import macaca.client.common.Utils;
import java.io.FileOutputStream;
import java.io.OutputStream;
import sun.misc.BASE64Decoder;

public class ScreenShot {

	private MacacaDriver driver;
	private Utils utils = new Utils();

	public ScreenShot(MacacaDriver driver) {
		this.driver = driver;
	}

	public Object takeScreenshot() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sessionId", driver.getSessionId());
		return utils.request("GET", DriverCommand.SCREENSHOT, jsonObject);
	}
	
	/**
	 * Save screenshot of the current page from base64 decoder.
	 * @param fileName The absolute path of the image filename
	 * @return The currently instance of MacacaClient
	 * @throws Exception
	 */
	public void saveScreenshot(String filename) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Decode Base64
			byte[] b = decoder.decodeBuffer(takeScreenshot().toString());
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			// generate the image file
			OutputStream out = new FileOutputStream(filename);
			out.write(b);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

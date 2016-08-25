package macaca.client.commands;

import com.alibaba.fastjson.JSONObject;

import macaca.client.common.DriverCommand;
import macaca.client.common.MacacaDriver;
import macaca.client.common.Utils;

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
	 * Save screenshot of the current page.
	 * @param filename
	 * @throws Exception
	 */
	public void saveScreenshot(String filename) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(takeScreenshot().toString());
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成图片
			OutputStream out = new FileOutputStream(filename);
			out.write(b);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

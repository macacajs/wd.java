package macaca.client;

import com.alibaba.fastjson.JSONObject;

public class MacacaDriver {
	private String sessionId;
	private String windowHandle;
	private String elementId;
	private String context;
	private JSONObject capabilities;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setWindowHandle(String windowHandle) {
		this.windowHandle = windowHandle;
	}

	public String getWindowHandle() {
		return this.windowHandle;
	}

	public void setElementId(Object elementId) {
		this.elementId = String.valueOf(elementId);
	}

	public String getElementId() {
		return this.elementId;
	}

	public JSONObject getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(JSONObject capabilities) {
		this.capabilities = capabilities;
	}

}

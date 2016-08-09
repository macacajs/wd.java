package macaca.client;

public class MacacaDriver {
	private String sessionId;
	private String windowHandle;
	private String elementId;
	private String context;

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
}

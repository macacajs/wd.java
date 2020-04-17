package macaca.client.commands;

import com.alibaba.fastjson.JSONObject;

import macaca.client.common.DriverCommand;
import macaca.client.common.MacacaDriver;
import macaca.client.common.Utils;

public class Window {

    private MacacaDriver driver;
    private Utils utils;

    public Window(MacacaDriver driver) {
        this.driver = driver;
        this.utils = new Utils(driver);
    }

    public Object getWindow() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", driver.getSessionId());
        return utils.request("GET", DriverCommand.WINDOW_HANDLE, jsonObject);
    }

    public Object getWindows() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", driver.getSessionId());
        return utils.request("GET", DriverCommand.WINDOW_HANDLES, jsonObject);
    }

    public JSONObject getWindowSize() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", driver.getSessionId());
        jsonObject.put("windowHandle", "current");
        return (JSONObject) utils.request("GET", DriverCommand.WINDOW_SIZE, jsonObject);
    }

    public void setWindowSize(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", driver.getSessionId());
        jsonObject.put("windowHandle", "current");
        utils.request("POST", DriverCommand.WINDOW_SIZE, jsonObject);
    }

    public void maximize() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", driver.getSessionId());
        jsonObject.put("windowHandle", "current");
        utils.request("POST", DriverCommand.MAXIMIZE_WINDOW, jsonObject);
    }

    public void setWindow(String handle) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", driver.getSessionId());
        jsonObject.put("name", handle);
        utils.request("POST", DriverCommand.WINDOW, jsonObject);
    }

    public void deleteWindow() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId", driver.getSessionId());
        utils.request("DELETE", DriverCommand.WINDOW, jsonObject);
    }

    public void setFrame(JSONObject jsonObject) throws Exception {
        jsonObject.put("sessionId", driver.getSessionId());
        String name = utils.request("POST", DriverCommand.FRAME, jsonObject).toString();
    }

}

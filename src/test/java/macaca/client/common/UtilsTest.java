package macaca.client.common;

import org.junit.Test;

public class UtilsTest {

    @Test
    public void handleStatus() throws Exception {
        Utils utils = new Utils(new MacacaDriver());
        try {
            utils.handleStatus(11);
        }catch (Exception e){
            assert e.getMessage().equals("An element command could not be completed because the element is not visible on the page.");
        }

        try {
            utils.handleStatus(0);
        }catch (Exception e){
            throw new RuntimeException("");
        }

        try {
            utils.handleStatus(1);
        }catch (Exception e){
            throw new RuntimeException("");
        }
    }
}
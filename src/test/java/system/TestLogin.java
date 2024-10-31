package system;

import base.Base;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class TestLogin extends Base {
    String username = "Admin";
    @Test
    public void testValidLogin(){
        HomePage homePage = new HomePage();
        logger.info("enter username");
        homePage.enterUserName(username);
        Assert.assertTrue(false);
    }
}

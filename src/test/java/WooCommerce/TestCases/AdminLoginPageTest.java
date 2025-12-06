package WooCommerce.TestCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import WooCommerce.Pages.AdminLoginPage;
import WooCommerce.Pages.DashboardPage;
import config.EnvManager;

public class AdminLoginPageTest extends BaseTest {
    //Check Login page URL
    @Test(priority = 1)
    public void checkLoginPageUrl(){
        AdminLoginPage login = page.goTo(AdminLoginPage.class);
        String loginPageUrl = login.getCurrentPageURL();
        Assert.assertTrue(loginPageUrl.contains(EnvManager.adminPageUrl()));
    }

    @Test(priority = 2)
    public void loginShouldSucceed(){
        DashboardPage dashboardPage = page.goTo(AdminLoginPage.class)
                .enterUserNameOrEmail(EnvManager.userName())
                .enterPassword(EnvManager.password())
                .clickPasswordVisibility()
                .checkRememberMe()
                .clickLoginButton();
    }

}

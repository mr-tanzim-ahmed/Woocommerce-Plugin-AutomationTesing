package java.FlexTablePlugin.TestCases;

import FlexTablePlugin.Pages.*;
import config.EnvManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminLoginPageTest extends BaseTest {
    //Check Login page URL
    @Test(priority = 1)
    public void checkLoginPageUrl(){
        AdminLoginPage login = page.goTo(AdminLoginPage.class);
        String loginPageUrl = login.getCurrentPageURL();
        Assert.assertTrue(loginPageUrl.contains(EnvManager.adminPageUrl()));
    }
    /*1️⃣ Test Case 1: Verify WordPress Login Functionality
        Objective: Ensure the user can successfully log in to the WordPress Admin Panel.
        Steps:
        1. Navigate to the WordPress login page.
        2. Enter valid username and password.
        3. Click the Log In button.
        Expected Result:
        - User is redirected to the WordPress Dashboard without errors.
    */

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

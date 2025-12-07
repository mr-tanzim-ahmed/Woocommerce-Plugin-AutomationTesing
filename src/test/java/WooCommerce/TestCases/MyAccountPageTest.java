package WooCommerce.TestCases;

import WooCommerce.Pages.MyAccountPage;
import WooCommerce.Pages.UserOrderDetailPage;
import config.EnvManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyAccountPageTest extends BaseTest{

    @Test(priority = 1)
    public void checkUserLoginSuccessfully(){
        MyAccountPage accountPage = page.goTo(MyAccountPage.class)
                .doUserLogin(EnvManager.userName(), EnvManager.userPassword());

        Assert.assertEquals(accountPage.checkMyAccountPageTitle(), "My account", "User can Logged in Successfully");
    }

    @Test(priority = 2)
    public void verifyOrderHistoryVisibleForRegisteredUser(){
        UserOrderDetailPage orderDetailPage = page.goTo(MyAccountPage.class)
                .clickOrders()
                .clickViewOrderDetails();

        Assert.assertTrue(orderDetailPage.checkOrderHistoryAvailable());
    }
}
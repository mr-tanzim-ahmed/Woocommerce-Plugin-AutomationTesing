package WooCommerce.TestCases;

import WooCommerce.Pages.*;
import config.EnvManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserAccountOrderHistory extends BaseTest {

    @Test(priority = 1)
    public void verifyOrderHistoryVisibleForRegisteredUser() {

        MyAccountPage accountPage = page.goTo(MyAccountPage.class)
                .doUserLogin(EnvManager.userName(), EnvManager.userPassword());

        String pageTitle = accountPage.checkMyAccountPageTitle();
        Assert.assertEquals(pageTitle, "My account");

        accountPage.clickOrders();

        UserOrderDetailPage orderDetailPage = accountPage.clickViewOrderDetails();

        boolean isHistoryAvailable = orderDetailPage.checkOrderHistoryAvailable();
        Assert.assertTrue(isHistoryAvailable);

        boolean isOrderShowing = orderDetailPage.checkOrderSucceedOrNot();
        Assert.assertTrue(isOrderShowing);

        System.out.println("✓ User can see order history");
    }

    @Test(priority = 2)
    public void validateOrderDetailsConsistencyBetweenFrontendAndBackend() {

        UserOrderDetailPage userOrderPage = page.goTo(UserOrderDetailPage.class);

        String frontendOrderNumber = userOrderPage.getOrderNumber();
        String expectedOrderNumber = EnvManager.orderNumber();

        System.out.println("=======================");
        System.out.println("Frontend Order Number: " + frontendOrderNumber);
        System.out.println("Expected Order Number: " + expectedOrderNumber);
        System.out.println("========================");

        Assert.assertNotNull(frontendOrderNumber);

        boolean orderMatches = frontendOrderNumber.contains(expectedOrderNumber);
        Assert.assertTrue(orderMatches);

        boolean orderExists = userOrderPage.checkOrderSucceedOrNot();
        Assert.assertTrue(orderExists);

        boolean historyComplete = userOrderPage.checkOrderHistoryAvailable();
        Assert.assertTrue(historyComplete);

        DashboardPage dashboardPage = userOrderPage.goTo(AdminLoginPage.class)
                .doAdminLogin(EnvManager.adminUserName(), EnvManager.adminUserPassword());

        WooCommerceOrdersFromWordpressDashboard ordersPage = dashboardPage.goToWooCommerceOrders();

        boolean backendOrderExists = ordersPage.isOrderInBackend(expectedOrderNumber);
        Assert.assertTrue(backendOrderExists);

        System.out.println("✓ Frontend and backend order details match");
    }
}

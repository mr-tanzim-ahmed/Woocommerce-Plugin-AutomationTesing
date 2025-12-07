package WooCommerce.TestCases;

import WooCommerce.Pages.*;
import config.EnvManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndCheckoutFlowTest extends BaseTest {

    @Test(priority = 1)
    public void verifyCartTotalsCalculatedCorrectly() {
        DashboardPage dashboardPage = page.goTo(AdminLoginPage.class)
                .doAdminLogin(EnvManager.adminUserName(), EnvManager.adminUserPassword());

        dashboardPage.goToTargetPage(EnvManager.homePageUrl());

        CartPage cartPage = page.goTo(HomePage.class)
                .selectAprocuct()
                .clickAddToCart()
                .clickViewCart();

        String cartSubtotal = cartPage.getCartSubtotal();
        String cartTaxAmount = cartPage.getCartTaxAmount();

        Assert.assertNotNull(cartSubtotal);
        Assert.assertNotNull(cartTaxAmount);

    }

    @Test(priority = 2)
    public void verifyPaymentProcessedSuccessfully() {
        CartPage cartPage = page.goTo(CartPage.class);

        cartPage.goToTargetPage(EnvManager.homePageUrl());

        OrderReceivedPage orderConfirmPage = page.goTo(HomePage.class)
                .selectAprocuct()
                .clickAddToCart()
                .clickViewCart()
                .clickProceedToCheckout()
                .enterFirstName("Test")
                .enterLastName("Customer")
                .enterCompanyName("WooCommerce Test")
                .selectCountry("Bangladesh")
                .enterStreetAddress("123 Test Street")
                .enterCity("Dhaka")
                .selectDistrict("Dhaka")
                .enterPostcode("1206")
                .enterPhone("01712345678")
                .enterEmail("testorder@example.com")
                .selectCashOnDelivery()
                .clickPlaceOrder();

        boolean isOrderPlaced = orderConfirmPage.checkOrderProcessedSuccessfully();
        Assert.assertTrue(isOrderPlaced);

        String paymentMethod = orderConfirmPage.checkPaymentMethodCOD();
        String orderNumber = EnvManager.orderNumber();

        Assert.assertEquals(paymentMethod, "Cash on delivery");

        WooCommerceOrdersFromWordpressDashboard ordersPage = page.goTo(DashboardPage.class)
                .goToWooCommerceOrders();

    }

    @Test(priority = 3)
    public void testEndToEndCheckoutFlow() {
        OrderReceivedPage previousPage = page.goTo(OrderReceivedPage.class);

        DashboardPage dashboardPage = previousPage.goTo(AdminLoginPage.class)
                .doAdminLogin(EnvManager.adminUserName(), EnvManager.adminUserPassword());

        dashboardPage.goToTargetPage(EnvManager.homePageUrl());

        CartPage cartPage = page.goTo(HomePage.class)
                .selectAprocuct()
                .setOrderQuantity("2")
                .clickAddToCart()
                .clickViewCart();

        String cartSubtotal = cartPage.getCartSubtotal();
        String cartTaxAmount = cartPage.getCartTaxAmount();

        Assert.assertNotNull(cartSubtotal);
        Assert.assertNotNull(cartTaxAmount);

        OrderReceivedPage orderPage = cartPage.clickProceedToCheckout()
                .enterFirstName("A")
                .enterLastName("B")
                .enterCompanyName("WPPOOL")
                .selectCountry("Bangladesh")
                .enterStreetAddress("456")
                .enterCity("Chittagong")
                .selectDistrict("Chittagong")
                .enterPostcode("4000")
                .enterPhone("01234567")
                .enterEmail("wppool@test.com")
                .selectDirectBankTransfer()
                .clickPlaceOrder();

        Assert.assertTrue(orderPage.checkOrderProcessedSuccessfully());
    }

    @Test(priority = 4)
    public void checkoutFlow_EndToEndValidation() {
        OrderReceivedPage previousOrderPage = page.goTo(OrderReceivedPage.class);

        DashboardPage dashboardPage = previousOrderPage.goTo(AdminLoginPage.class)
                .doAdminLogin(EnvManager.adminUserName(), EnvManager.adminUserPassword());

        dashboardPage.goToTargetPage(EnvManager.homePageUrl());

        CartPage cartPage = page.goTo(HomePage.class)
                .selectAprocuct()
                .clickAddToCart()
                .clickViewCart();

        String currentUrl = cartPage.getCurrentPageURL();
        Assert.assertTrue(currentUrl.contains("cart"));

        String subtotal = cartPage.getCartSubtotal();
        String tax = cartPage.getCartTaxAmount();

        OrderReceivedPage orderPage = cartPage.clickProceedToCheckout()
                .enterFirstName("Assignment")
                .enterLastName("Part B")
                .enterCompanyName("WPPOOL")
                .selectCountry("Bangladesh")
                .enterStreetAddress("Dhaka")
                .enterCity("Dhaka")
                .selectDistrict("Dhaka")
                .enterPostcode("1200")
                .enterPhone("0123455667")
                .enterEmail("hi@test.com")
                .selectCashOnDelivery()
                .clickPlaceOrder();

        Assert.assertTrue(orderPage.checkOrderProcessedSuccessfully());

        String paymentMethod = orderPage.checkPaymentMethodCOD();
        Assert.assertEquals(paymentMethod, "Cash on delivery");
    }

    protected double parseAmount(String amountString) {
        try {
            String cleaned = amountString.replaceAll("[^0-9.]", "");
            return Double.parseDouble(cleaned);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}

package WooCommerce.TestCases;

import WooCommerce.Pages.*;

import config.EnvManager;
import org.testng.annotations.Test;

public class EndToEndCheckoutFlowTest extends BaseTest {
    @Test
    public void testEndToEndCheckoutFlow() {
        //Sorry tomorrow Operating System Lab final, cant not complete right now!
    }

    @Test
    public void verifyPaymentProcessedSuccessfully(){
        DashboardPage testPage = page.goTo(AdminLoginPage.class)
                .doAdminLogin(EnvManager.adminUserName(),EnvManager.adminUserPassword())
                .goTo(DashboardPage.class);
                testPage.goToTargetPage(EnvManager.homePageUrl());
                OrderReceivedPage orderConfirmPage = page.goTo(HomePage.class)
                        .selectAprocuct()
                        .clickAddToCart()
                        .clickViewCart()
                        .clickProceedToCheckout()
                        .enterFirstName("Sorry ")
                        .enterLastName("for Late!")
                        .enterCompanyName("Tomorrow Lab Final Exam!")
                        .selectCountry("Bangladesh")
                        .enterCity("Dhaka")
                        .selectDistrict("Dhaka")
                        .enterPostcode("1206")
                        .enterPhone("0123456789")
                        .enterEmail("test@test.com")
                        .selectCashOnDelivery()
                        .clickPlaceOrder();


    }
    @Test
    public void validateCheckoutFlowWithPayment() {

    }
    @Test
    public void checkoutFlow_EndToEndValidation() { }





}

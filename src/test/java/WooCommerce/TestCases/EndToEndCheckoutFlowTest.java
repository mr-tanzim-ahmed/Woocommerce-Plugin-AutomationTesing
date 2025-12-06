package WooCommerce.TestCases;

import WooCommerce.Pages.*;

import config.EnvManager;
import org.testng.annotations.Test;

public class EndToEndCheckoutFlowTest extends BaseTest {

    @Test()
    public void validatePaymentProcessedSuccessfully(){
        DashboardPage testPage = page.goTo(AdminLoginPage.class)
                .doLogin(EnvManager.userName(),EnvManager.password())
                .goTo(DashboardPage.class);
                testPage.goToTargetPage(properties.getProperty("homePageURL"));
                OrderConfirmAndDetailPage orderConfirmPage = page.goTo(HomePage.class)
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



}

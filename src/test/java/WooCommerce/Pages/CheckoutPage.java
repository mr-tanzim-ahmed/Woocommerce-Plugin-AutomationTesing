package WooCommerce.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // =========================
    // BILLING DETAILS INPUT BOXES
    // =========================

    public CheckoutPage enterFirstName(String firstName) {
        setInput(By.id("billing_first_name"), firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        setInput(By.id("billing_last_name"), lastName);
        return this;
    }

    public CheckoutPage enterCompanyName(String company) {
        setInput(By.id("billing_company"), company);
        return this;
    }

    public CheckoutPage selectCountry(String countryName) {
        selectElementFromVisibleText(By.id("billing_country"), countryName);
        return this;
    }

    public CheckoutPage enterStreetAddress(String address) {
        setInput(By.id("billing_address_1"), address);
        return this;
    }

    public CheckoutPage enterAddressOptional(String address2) {
        setInput(By.id("billing_address_2"), address2);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        setInput(By.id("billing_city"), city);
        return this;
    }

    public CheckoutPage selectDistrict(String district) {
        selectElementFromVisibleText(By.id("billing_state"), district);
        return this;
    }

    public CheckoutPage enterPostcode(String postcode) {
        setInput(By.id("billing_postcode"), postcode);
        return this;
    }

    public CheckoutPage enterPhone(String phone) {
        setInput(By.id("billing_phone"), phone);
        return this;
    }

    public CheckoutPage enterEmail(String email) {
        clearInputText(By.id("billing_email"));
        setInput(By.id("billing_email"), email);
        return this;
    }


    public CheckoutPage selectDirectBankTransfer() {
        clickElement(By.id("payment_method_bacs"));
        return this;
    }

    public CheckoutPage selectCheckPayments() {
        clickElement(By.id("payment_method_cheque"));
        return this;
    }

    public CheckoutPage selectCashOnDelivery() {
        clickElement(By.id("payment_method_cod"));
        return this;
    }

    public OrderConfirmAndDetailPage clickPlaceOrder() {
        clickElement(By.id("place_order"));
        return goTo(OrderConfirmAndDetailPage.class);
    }
}

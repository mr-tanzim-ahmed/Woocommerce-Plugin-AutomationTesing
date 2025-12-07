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
        setInput(By.cssSelector("#billing_first_name"), firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        setInput(By.cssSelector("#billing_last_name"), lastName);
        return this;
    }

    public CheckoutPage enterCompanyName(String company) {
        setInput(By.cssSelector("#billing_company"), company);
        return this;
    }
    public CheckoutPage selectCountry(String country){
        selectElementFromVisibleText(By.cssSelector("span[aria-label='Country / Region']"),country);
        return this;
    }

    public CheckoutPage enterStreetAddress(String address) {
        setInput(By.cssSelector("#billing_address_1"), address);
        return this;
    }

    public CheckoutPage enterAddressOptional(String address2) {
        setInput(By.cssSelector("#billing_address_2"), address2);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        setInput(By.cssSelector("#billing_city"), city);
        return this;
    }

    public CheckoutPage selectDistrict(String district) {
        selectElementFromVisibleText(By.cssSelector("span[aria-label='District']"), district);
        return this;
    }

    public CheckoutPage enterPostcode(String postcode) {
        setInput(By.cssSelector("#billing_phone"), postcode);
        return this;
    }

    public CheckoutPage enterPhone(String phone) {
        setInput(By.cssSelector("#billing_phone"), phone);
        return this;
    }

    public CheckoutPage enterEmail(String email) {
        clearInputText(By.cssSelector("#billing_email"));
        setInput(By.id("billing_email"), email);
        return this;
    }


    public CheckoutPage selectDirectBankTransfer() {
        clickElement(By.cssSelector("#payment_method_bacs"));
        return this;
    }

    public CheckoutPage selectCheckPayments() {
        clickElement(By.cssSelector("#payment_method_cheque"));
        return this;
    }

    public CheckoutPage selectCashOnDelivery() {
        clickElement(By.cssSelector("#payment_method_cod"));
        return this;
    }

    public OrderReceivedPage clickPlaceOrder() {
        waitForElementToBeVisible(By.cssSelector("#place_order"));
        setLoadingTime(1);
        clickElement(By.cssSelector("#place_order"));
        return goTo(OrderReceivedPage.class);
    }
}

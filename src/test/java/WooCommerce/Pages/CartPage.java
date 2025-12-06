package WooCommerce.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }
    public CheckoutPage clickProceedToCheckout(){
        clickElement(By.cssSelector(".checkout-button.button.alt.wc-forward"));
        return goTo(CheckoutPage.class);
    }

    public String getCartSubtotal() {
        return getElementsText(By.cssSelector("tr.cart-subtotal td .amount bdi"));
    }

    public String getCartTaxAmount() {
        return getElementsText(By.cssSelector("tr.tax-rate td .amount bdi"));
    }



}
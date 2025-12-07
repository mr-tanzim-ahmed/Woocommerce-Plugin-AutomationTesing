package WooCommerce.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }
    public CheckoutPage clickProceedToCheckout(){
        waitForElementToBeVisible(By.xpath("a[class*='checkout-button']"));
        setLoadingTime(2);
        clickElement(By.cssSelector("a[class*='checkout-button']"));
        return goTo(CheckoutPage.class);
    }

    public String getCartSubtotal() {
        return getElementsText(By.cssSelector("tr.cart-subtotal td .amount bdi"));
    }

    public String getCartTaxAmount() {
        return getElementsText(By.cssSelector("tr.tax-rate td .amount bdi"));
    }



}
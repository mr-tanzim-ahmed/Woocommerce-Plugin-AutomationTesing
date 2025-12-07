package WooCommerce.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderReceivedPage extends BasePage{
    public OrderReceivedPage(WebDriver driver) {
        super(driver);
    }
    //CheckoutPage >> OrderReceivedPage
    public boolean checkOrderProcessedSuccessfully(){
        return getWebElements(By.cssSelector(".woocommerce-order-details")).size()>0;
    }
    public String checkPaymentMethodCOD(){
        return getElementsText(By.xpath("//strong[normalize-space()='Cash on delivery']")).trim();
    }

}

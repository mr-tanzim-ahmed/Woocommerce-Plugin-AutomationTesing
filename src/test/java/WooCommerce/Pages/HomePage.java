package WooCommerce.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public ProductDetailPage selectAprocuct(){
        clickElement(By.cssSelector("h2[class='woocommerce-loop-product__title']"));
        return goTo(ProductDetailPage.class);
    }
}

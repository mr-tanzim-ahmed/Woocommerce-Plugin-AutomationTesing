package WooCommerce.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends BasePage{
    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }
    public ProductDetailPage clickAddToCart(){
        clickElement(By.cssSelector("div[class='summary entry-summary'] button[name='add-to-cart']"));
        return this;
    }
    public CartPage clickViewCart(){
        clickElement(By.cssSelector("div[role='alert'] a[class='button wc-forward']"));
        return goTo(CartPage.class);
    }
    public ProductDetailPage setOrderQuantity(String amount){
        setInput(By.cssSelector("input[class*='input-text qty text']"),amount);
        return this;
    }


}

package WooCommerce.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserOrderDetailPage extends BasePage{
    public UserOrderDetailPage(WebDriver driver) {
        super(driver);
    }
    public boolean checkOrderSucceedOrNot(){
        return getWebElements(By.cssSelector(".order-number")).size()>0;
    }
    public String getOrderNumber(){
        return getElementsText(By.cssSelector(".order-number")).trim();
    }
    public boolean checkOrderHistoryAvailable(){
        return getWebElements(By.cssSelector(".woocommerce-order-details")).size()>0;
    }
}

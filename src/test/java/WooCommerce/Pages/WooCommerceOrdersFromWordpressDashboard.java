package WooCommerce.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WooCommerceOrdersFromWordpressDashboard extends BasePage {

    public WooCommerceOrdersFromWordpressDashboard(WebDriver driver) {
        super(driver);
    }

    public WooCommerceOrdersFromWordpressDashboard goToOrders() {
        // click on WooCommerce menu then Orders
        clickElement(By.xpath("//li[@id='menu-posts-product']//a[contains(text(),'Orders')]"));
        setLoadingTime(1);
        return this;
    }


    public WooCommerceOrdersFromWordpressDashboard searchOrder(String orderNum) {
        // find search box and type order number
        setInput(By.cssSelector("#post-search-input"), orderNum);
        // click search button
        clickElement(By.cssSelector("#search-submit"));
        setLoadingTime(1);
        return this;
    }


    public boolean orderExists(String orderNum) {

        String xpath = "//td[contains(@class,'order_number')]//a[contains(text(),'#" + orderNum + "')]";

        int size = getWebElements(By.xpath(xpath)).size();
        if(size > 0){
            return true;
        } else {
            return false;
        }
    }


    public WooCommerceOrdersFromWordpressDashboard openOrder(String orderNum) {
        String xpath = "//td[contains(@class,'order_number')]//a[contains(text(),'#" + orderNum + "')]";
        clickElement(By.xpath(xpath));
        setLoadingTime(1);
        return this;
    }


    public String getTotalAmount() {
        String total = getElementsText(By.cssSelector(".order_total .amount"));
        return total.trim();
    }

    public String getEmail() {
        String email = getElementsText(By.xpath("//div[@class='order_data_column']//p[contains(@class,'email')]//a"));
        return email.trim();
    }


    public boolean isOrderInBackend(String orderNum) {
        goToOrders();
        searchOrder(orderNum);
        return orderExists(orderNum);
    }
}
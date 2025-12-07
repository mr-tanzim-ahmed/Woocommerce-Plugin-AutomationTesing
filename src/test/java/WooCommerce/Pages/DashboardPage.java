package WooCommerce.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage{
    public DashboardPage(WebDriver driver) {

        super(driver);
    }

    public boolean isItDashboardPage(){
        return getWebElements(By.xpath("//ul[@id='adminmenu']")).size() > 0;
    }
    public boolean isPluginMenuPresent() {
        return getWebElements(By.xpath("//div[normalize-space()='Plugins']")).size() > 0;
    }

    public HomePage goHomePage(String url) {
        openNewTabAndVisit(url);
        return new HomePage(driver);
    }

    public WooCommerceOrdersFromWordpressDashboard goToWooCommerceOrders() {
        clickElement(By.xpath("//li[@id='menu-posts-product']//following-sibling::li//a[contains(text(),'Orders')] | //a[contains(@href,'edit.php?post_type=shop_order')]"));
        return goTo(WooCommerceOrdersFromWordpressDashboard.class);
    }


}

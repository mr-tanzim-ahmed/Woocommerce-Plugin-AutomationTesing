package java.FlexTablePlugin.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage{
    public DashboardPage(WebDriver driver) {

        super(driver);
    }
    //plugin icon xpath:  //div[@class='wp-menu-image dashicons-before dashicons-admin-plugins']
    public  InstalledPluginsPage clickPluginsInDashboard(){
        clickElement(By.xpath("//div[@class='wp-menu-image dashicons-before dashicons-admin-plugins']"));
        return goTo(InstalledPluginsPage.class);
    }
    public boolean isItDashboardPage(){
        return getWebElements(By.xpath("//ul[@id='adminmenu']")).size() > 0;
    }
    public boolean isPluginMenuPresent() {
        return getWebElements(By.xpath("//div[normalize-space()='Plugins']")).size() > 0;
    }
    public FlexTableDashboard clickFlexTableFromMenu() {
        clickElement(By.xpath("//div[@class='wp-menu-image dashicons-before']"));
        return goTo(FlexTableDashboard.class);
    }
    public TestPage clickPagesFromMenu() {
        clickElement(By.xpath("//div[normalize-space()='Pages']"));
        return goTo(TestPage.class);
    }


}

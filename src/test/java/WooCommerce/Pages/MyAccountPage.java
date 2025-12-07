package WooCommerce.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import config.EnvManager;

public class MyAccountPage extends BasePage{

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String checkMyAccountPageTitle(){
        waitForElementToBeVisible(By.cssSelector(".entry-title"));
        return getElementsText(By.cssSelector(".entry-title")).trim();
    }

    public MyAccountPage enterUserNameOrEmail(String userNameOrEmail){
        clearInputText(By.cssSelector("#user_login"));
        setInput(By.cssSelector("#user_login"), userNameOrEmail);
        return this;
    }

    public MyAccountPage enterUserPassword(String password){
        setInput(By.cssSelector("#user_pass"), password);
        return this;
    }

    public MyAccountPage clickLoginButton(){
        clickElement(By.cssSelector("#wp-submit"));
        waitForElementToBeVisible(By.cssSelector(".entry-title"));
        return this;
    }

    public MyAccountPage clickOrders(){
        clickElement(By.cssSelector("a[href*='/my-account/orders/']"));
        waitForElementToBeVisible(By.cssSelector("a[aria-label*='View order number']"));
        return this;
    }


    public UserOrderDetailPage clickViewOrderDetails(){
        String orderNum = EnvManager.orderNumber();
        if (orderNum == null || orderNum.isEmpty()) {
            throw new IllegalStateException("Order number is not set in .env file");
        }
        String cssSelector = "a[aria-label='View order number " + orderNum + "']";
        clickElement(By.cssSelector(cssSelector));
        return goTo(UserOrderDetailPage.class);
    }

    public MyAccountPage doUserLogin(String userNameOrEmail, String password){
        enterUserNameOrEmail(userNameOrEmail);
        enterUserPassword(password);
        clickLoginButton();
        return this;
    }
}
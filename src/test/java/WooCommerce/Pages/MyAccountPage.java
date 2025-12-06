package WooCommerce.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage{
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }
    public MyAccountPage enterAdminUserNameOrEmail(String userNameOrEmail){
        clearInputText(By.id("user_login"));
        setInput(By.id("user_login"),userNameOrEmail);
        return this;
    }
    public MyAccountPage enterAdminPassword(String password){
        setInput(By.cssSelector("#user_pass"),password);
        setLoadingTime(1);
        return this;
    }


    public DashboardPage clickLoginButton(){
        clickElement(By.cssSelector("#wp-submit"));
        return goTo(DashboardPage.class);
    }

    public DashboardPage doLogin(String userNameOrEmail, String password){
        enterAdminUserNameOrEmail(userNameOrEmail);
        enterAdminPassword(password);
        clickLoginButton();
        return goTo(DashboardPage.class);
    }

}

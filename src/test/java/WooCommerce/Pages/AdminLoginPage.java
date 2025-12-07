package WooCommerce.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLoginPage extends BasePage{

    public AdminLoginPage(WebDriver driver){
        super(driver);
    }
    public AdminLoginPage enterAdminUserNameOrEmail(String userNameOrEmail){
        clearInputText(By.id("user_login"));
        setInput(By.id("user_login"),userNameOrEmail);
        return this;
    }
    public AdminLoginPage enterAdminPassword(String password){
        setInput(By.cssSelector("#user_pass"),password);
        setLoadingTime(1);
        return this;
    }
    public AdminLoginPage checkRememberMe(){
        clickElement(By.cssSelector("#rememberme"));
        return this;
    }

    public AdminLoginPage clickPasswordVisibility(){
        clickElement(By.cssSelector(".dashicons.dashicons-visibility"));
        return this;
    }

    public DashboardPage clickLoginButton(){
        clickElement(By.cssSelector("#wp-submit"));
        return goTo(DashboardPage.class);
    }

    public DashboardPage doAdminLogin(String userNameOrEmail, String password){
        enterAdminUserNameOrEmail(userNameOrEmail);
        enterAdminPassword(password);
        clickLoginButton();
        return goTo(DashboardPage.class);
    }

}

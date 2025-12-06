package java.FlexTablePlugin.Pages;

import FlexTablePlugin.Util.FlexTablePluginUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.List;

public abstract class Page {
    public static WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;

    public Page(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(FlexTablePluginUtil.WAIT_TIME));
        this.actions = new Actions(driver);
    }

    public abstract WebElement getWebElement(By selector);

    public abstract List<WebElement> getWebElements(By selector);

    public abstract void clickElement(By selector);

    public abstract void setInput(By selector, String text);

    public abstract String  getElementsText(By selector);

    public abstract void setLoadingTime(int seconds);

    public abstract void selectElementFromVisibleText(By selector, String visibleText);
    public abstract Select selectElement(By selector);

    public abstract void clearInputText(By selector);

    public abstract String getCurrentPageURL();

    public abstract String homePageUrl(String url);

    public abstract void goToTargetPage(String url);

    //public abstract String visitPage(String url);
    public abstract String getCssAttribute(By selector,String cssPropertyName);

    public abstract void openNewTabAndVisit(String url);

    public abstract void waitForElementToBeVisible(By selector);
    //Returns an obj of any class that is extended by BasePage
    public static <T extends BasePage>T goTo(Class<T> pageClass){
        try {
            return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}

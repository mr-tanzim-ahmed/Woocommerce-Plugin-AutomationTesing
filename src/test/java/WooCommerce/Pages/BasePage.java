package WooCommerce.Pages;


import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import WooCommerce.Report.ReportTestManager;
import java.util.ArrayList;
import java.util.List;

public class BasePage extends Page {
    public static ArrayList<String> copyPasteData = new ArrayList<>();

    public BasePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getWebElement(By selector) {
        WebElement element = null;
        try {
            addInfo("Selenium WebDriver going to find a WebElement with " + selector + " selector");
            element = driver.findElement(selector);
            addInfo("Selenium WebDriver found a WebElement with " + selector + " selector");
        } catch (Exception e) {
            addFailInfo("Selenium WebDriver is not found a WebElement with " + selector + " selector");
            System.out.println("Element not found: " + selector);
        }
        return element;
    }

    @Override
    public List<WebElement> getWebElements(By selector) {
        List<WebElement> elements = null;
        try {
            addInfo("Selenium WebDriver going to find WebElements with " + selector + " selector");
            elements = driver.findElements(selector);
            addInfo("Selenium WebDriver found WebElements with " + selector + " selector");
        } catch (Exception e) {
            addFailInfo("Selenium WebDriver is not found WebElements with " + selector + " selector");
            System.out.println("Elements not found: " + selector);
        }
        return elements;
    }

    @Override
    public void clickElement(By selector) {
        waitForElementToBeVisible(selector);
        getWebElement(selector).click();
    }

    @Override
    public void setInput(By selector, String text) {
        waitForElementToBeVisible(selector);
        getWebElement(selector).clear();
        getWebElement(selector).sendKeys(text);
    }

    @Override
    public void clearInputText(By selector) {
        getWebElement(selector).clear();
    }

    @Override
    public String getElementsText(By selector) {
        return getWebElement(selector).getText();
    }

    @Override
    public Select selectElement(By selector) {

        return new Select(getWebElement(selector));
    }

    @Override
    public void selectElementFromVisibleText(By selector, String visibleText) {
        try {
            addInfo("Selecting option '" + visibleText + "' from dropdown with selector: " + selector);
            selectElement(selector).selectByVisibleText(visibleText);
            addInfo("Successfully selected option '" + visibleText + "'");
        } catch (Exception e) {
            addFailInfo("Failed to select option '" + visibleText + "' from dropdown: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public String getCurrentPageURL() {
        return driver.getCurrentUrl();
    }

    public void setLoadingTime(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void goToTargetPage(String url) {
        driver.get(url);
    }

    @Override
    public void waitForElementToBeVisible(By selector) {
        wait.until(ExpectedConditions.visibilityOf(getWebElement(selector)));
    }


    @Override
    public void openNewTabAndVisit(String url) {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(url);
        setLoadingTime(2);
    }

    public String getCssAttribute(By selector, String cssPropertyName) {
        return getWebElement(selector).getCssValue(cssPropertyName);
    }

    public void addInfo(String message) {
        if (ReportTestManager.getTest() != null) {
            ReportTestManager.getTest().log(Status.INFO, message);
        }
    }

    public void addFailInfo(String message) {
        if (ReportTestManager.getTest() != null) {
            ReportTestManager.getTest().log(Status.FAIL, message);
        }
    }
}
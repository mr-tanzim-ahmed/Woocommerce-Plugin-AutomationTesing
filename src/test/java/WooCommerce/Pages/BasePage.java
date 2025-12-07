package WooCommerce.Pages;


import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import WooCommerce.Report.ReportTestManager;
import java.util.ArrayList;
import java.util.List;

public class BasePage extends Page {

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
        } catch (NoSuchElementException e) {
            addFailInfo("Element not found: " + selector);
            System.out.println("Element not found: " + selector);
            // return null instead of throwing exception
        } catch (Exception e) {
            addFailInfo("Error finding element: " + selector + " - " + e.getMessage());
            System.out.println("Error finding element: " + selector);
        }
        return element;
    }

    @Override
    public List<WebElement> getWebElements(By selector) {
        List<WebElement> elements = new ArrayList<>();
        try {
            addInfo("Selenium WebDriver going to find WebElements with " + selector + " selector");
            elements = driver.findElements(selector);
            addInfo("Selenium WebDriver found WebElements with " + selector + " selector");
        } catch (NoSuchElementException e) {
            addFailInfo("Elements not found: " + selector);
            System.out.println("Elements not found: " + selector);
            // return empty list instead of null
        } catch (Exception e) {
            addFailInfo("Error finding elements: " + selector + " - " + e.getMessage());
            System.out.println("Error finding elements: " + selector);
        }
        return elements;
    }

    @Override
    public void clickElement(By selector) {
        try {
            waitForElementToBeVisible(selector);
            WebElement element = getWebElement(selector);
            if (element != null) {
                element.click();
            } else {
                System.out.println("Cannot click - element is null: " + selector);
            }
        } catch (TimeoutException e) {
            addFailInfo("Timeout waiting for element: " + selector);
            System.out.println("Timeout waiting for element: " + selector);
        } catch (Exception e) {
            addFailInfo("Cannot click element: " + selector + " - " + e.getMessage());
            System.out.println("Cannot click element: " + selector);
        }
    }

    @Override
    public void setInput(By selector, String text) {
        try {
            waitForElementToBeVisible(selector);
            WebElement element = getWebElement(selector);
            if (element != null) {
                element.clear();
                element.sendKeys(text);
            } else {
                System.out.println("Cannot set input - element is null: " + selector);
            }
        } catch (TimeoutException e) {
            addFailInfo("Timeout waiting for input field: " + selector);
            System.out.println("Timeout waiting for input field: " + selector);
        } catch (Exception e) {
            addFailInfo("Cannot set input: " + selector + " - " + e.getMessage());
            System.out.println("Cannot set input: " + selector);
        }
    }

    @Override
    public void clearInputText(By selector) {
        try {
            WebElement element = getWebElement(selector);
            if (element != null) {
                element.clear();
            }
        } catch (Exception e) {
            System.out.println("Cannot clear input: " + selector);
        }
    }

    @Override
    public String getElementsText(By selector) {
        try {
            WebElement element = getWebElement(selector);
            if (element != null) {
                return element.getText();
            }
        } catch (Exception e) {
            System.out.println("Cannot get text from element: " + selector);
        }
        return "";
    }

    @Override
    public Select selectElement(By selector) {
        try {
            WebElement element = getWebElement(selector);
            if (element != null) {
                return new Select(element);
            }
        } catch (Exception e) {
            System.out.println("Cannot create Select element: " + selector);
        }
        return null;
    }

    @Override
    public void selectElementFromVisibleText(By selector, String visibleText) {
        try {
            addInfo("Selecting option '" + visibleText + "' from dropdown with selector: " + selector);
            Select select = selectElement(selector);
            if (select != null) {
                select.selectByVisibleText(visibleText);
                addInfo("Successfully selected option '" + visibleText + "'");
            } else {
                addFailInfo("Cannot select - dropdown element is null: " + selector);
            }
        } catch (NoSuchElementException e) {
            addFailInfo("Option '" + visibleText + "' not found in dropdown: " + selector);
            System.out.println("Option not found: " + visibleText);
        } catch (Exception e) {
            addFailInfo("Failed to select option '" + visibleText + "' from dropdown: " + e.getMessage());
            System.out.println("Failed to select option: " + visibleText);
        }
    }

    @Override
    public String getCurrentPageURL() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            System.out.println("Cannot get current URL");
            return "";
        }
    }

    public void setLoadingTime(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            System.out.println("Thread sleep interrupted");
        }
    }

    @Override
    public void goToTargetPage(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            System.out.println("Cannot navigate to URL: " + url);
        }
    }

    @Override
    public void waitForElementToBeVisible(By selector) {
        try {
            WebElement element = getWebElement(selector);
            if (element != null) {
                wait.until(ExpectedConditions.visibilityOf(element));
            }
        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for element to be visible: " + selector);
        } catch (Exception e) {
            System.out.println("Error waiting for element: " + selector);
        }
    }


    @Override
    public void openNewTabAndVisit(String url) {
        try {
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get(url);
            setLoadingTime(2);
        } catch (Exception e) {
            System.out.println("Cannot open new tab: " + url);
        }
    }

    public String getCssAttribute(By selector, String cssPropertyName) {
        try {
            WebElement element = getWebElement(selector);
            if (element != null) {
                return element.getCssValue(cssPropertyName);
            }
        } catch (Exception e) {
            System.out.println("Cannot get CSS attribute: " + cssPropertyName);
        }
        return "";
    }

    public MyAccountPage clickUserAccount(){
        try {
            clickElement(By.cssSelector("a[aria-label='Account icon link']"));
            return goTo(MyAccountPage.class);
        } catch (Exception e) {
            System.out.println("Cannot click user account icon");
            return null;
        }
    }

    public void addInfo(String message) {
        try {
            if (ReportTestManager.getTest() != null) {
                ReportTestManager.getTest().log(Status.INFO, message);
            }
        } catch (Exception e) {
            // silently catch report errors
        }
    }

    public void addFailInfo(String message) {
        try {
            if (ReportTestManager.getTest() != null) {
                ReportTestManager.getTest().log(Status.FAIL, message);
            }
        } catch (Exception e) {

        }
    }
}
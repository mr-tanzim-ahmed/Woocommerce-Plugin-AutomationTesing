package WooCommerce.TestCases;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import WooCommerce.Pages.BasePage;
import WooCommerce.Pages.Page;
import WooCommerce.Util.FlexTablePluginUtil;
import config.EnvManager;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    Page page;
    protected static Dotenv dotenv;

    static {
        dotenv = Dotenv.load();
    }

    @BeforeClass
    public void browserSetup() {
        String browserName = dotenv.get("BROWSER", "chrome");

        switch (browserName.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome":
                driver = new ChromeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser! " + browserName);
        }

        driver.manage().window().maximize();
        driver.get(EnvManager.loginPageUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(FlexTablePluginUtil.WAIT_TIME));

        page = new BasePage(driver);
    }

    @AfterClass
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getWebDriver() {
        return driver;
    }
}
package java.FlexTablePlugin.TestCases;

import FlexTablePlugin.Pages.BasePage;
import FlexTablePlugin.Pages.Page;
import FlexTablePlugin.Util.FlexTablePluginUtil;
import config.EnvManager;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


    public class BaseTest {
        public WebDriver driver;
        Page page;
        public static Properties properties;
        protected static Dotenv dotenv;

        public BaseTest() {
            properties = new Properties();
            dotenv = Dotenv.load();
            String path = System.getProperty("user.dir") + "/src/test/resources/config.properties";

            try {
                FileInputStream inputStream = new FileInputStream(path);
                properties.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @BeforeClass
        public void browserSetup() {

            String browserName = properties.getProperty("browser");
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
            driver.get(EnvManager.adminPageUrl());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds( FlexTablePluginUtil.WAIT_TIME));

            page = new BasePage(driver);
        }

        @AfterClass
        public void closeBrowser() {
            driver.quit();
        }

        public String getUserNameOrEmail() {
            return properties.getProperty("userName");
        }

        public String getPassword() {
            return properties.getProperty("password");
        }

        public WebDriver getWebDriver() {
            return driver;
        }
    }
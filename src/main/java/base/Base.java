package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;


public class Base {
    // var
    public static WebDriver driver;
    public static JavascriptExecutor js;
    public static WebDriverWait wait;
    public static Logger logger;


    // driver initialization
    @Parameters({"browser", "url"})
    @BeforeMethod(alwaysRun = true)
    public void driverInit(@Optional("chrome") String browser, @Optional("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login") String url) {
        logger = LogManager.getLogger(this.getClass());  //lOG4J2
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.get(url);
        wait = new WebDriverWait(driver, 30);
        js = (JavascriptExecutor) driver;
    }

    // close driver
    @AfterMethod(alwaysRun = true)
    public void cleanUp(ITestResult testResult) {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            takeScreenshot(testResult.getName());
        }
        driver.close();
        driver.quit();
    }

    // selenium Api

    public void click(WebElement element) {
        element.click();
    }

    public void typeText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public String getTextInnerText(WebElement element) {
        return element.getText().trim();
    }

    public void selectFromDropDownByVisibleText(WebElement element, String visibleText) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
    }

    // helper methods

    public void takeScreenshot(String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File fileSrc = ts.getScreenshotAs(OutputType.FILE);
        String fileDes = System.getProperty("user.dir") + File.separator + "screenshot" + File.separator + testName + ".png";
        try {
            FileUtils.copyFile(fileSrc, new File(fileDes));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {

        webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://alerts.shotspotter.biz/");
    }

    @AfterMethod
    public void afterMethod() {

        webDriver.quit();
    }
}

package test;

import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Global;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;

import static java.lang.Thread.sleep;

/**
 * Created by oleksandr.orlov on 20.05.2017.
 */
public class LoginTest {

    public static WebDriver webDriver; //create variable. no value

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
       webDriver = new FirefoxDriver(); //set value to variable
       webDriver.navigate().to( "https://alerts.shotspotter.biz/");
       sleep(9000);
    }

// Before/After: Class/Test/Method
    @AfterMethod
    public void afterMethod() {

        webDriver.quit();
    }

    @Test
    public void testLoginPositive() {


//if assert fails - test stops. no further asserts executed. firefox doesn't quit.
//to avoid = use try-catch for assert

//validation: url before login
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://alerts.shotspotter.biz/", ("Wrong url before login"));

//validation: title before login
        Assert.assertEquals(webDriver.getTitle(), "Shotspotter - Login", "Main page title is wrong");

//email/password/go
        LoginPage.emailField.sendKeys("denvert1@shotspotter.net");
        LoginPage.passwordField.sendKeys("Test123!");
        LoginPage.goButton.click();

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//validation: title after login
        Assert.assertEquals(webDriver.getTitle(), "Shotspotter", "Main page title is wrong");

//validation: url after login
        Assert.assertTrue(webDriver.getCurrentUrl().contains("https://alerts.shotspotter.biz/main"), ("Wrong url after login"));
//public static WebElement
//variable
        WebElement settingsIcon = webDriver.findElement(By.className("settings"));

//validation: profile
        Assert.assertTrue(settingsIcon.isDisplayed(), ("Settings icon is not displayed"));

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        webDriver.quit();

    }
}

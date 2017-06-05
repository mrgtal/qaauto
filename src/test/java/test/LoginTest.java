package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;

import static java.lang.Thread.sleep;

public class LoginTest {

    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
       webDriver = new FirefoxDriver(); //set value to variable
       webDriver.navigate().to( "https://alerts.shotspotter.biz/");
       sleep(9000);
    }

    @AfterMethod
    public void afterMethod() {

        webDriver.quit();
    }

    @Test
    public void testLoginPositive() {

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", ("Wrong url before login"));
        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Main page title is wrong");

        MainPage mainPage = loginPage.LoginAs("denvert1@shotspotter.net","Test123!");

        Assert.assertTrue(mainPage.isPageLoaded(), "settings icon is not displayed");
        Assert.assertTrue(mainPage.getPageURL().contains("https://alerts.shotspotter.biz/main"),"Wrong url after Login");

    }

    @Test
    public void TestLoginNegative() {

        String expectedErrorMsg = "The provided credentials are not correct.";

        LoginPage loginPage = new LoginPage(webDriver);

        LoginPage resultPage = loginPage.IncorrectLogin("IncorrectEmail", "IncorrectPassword");

    }

}

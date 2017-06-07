package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;

public class LoginTest {

    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {

       webDriver = new FirefoxDriver(); //set value to variable
    }

    @AfterMethod
    public void afterMethod() {

        webDriver.quit();
    }

    @Test
    public void testLoginPositive() {

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", ("Wrong url before login"));
        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Main page title is wrong");

//        MainPage mainPage = loginPage.loginAsReturnToLoginPage("denvert1@shotspotter.net","Test123!", MainPage.class);
        MainPage mainPage = loginPage.loginAsReturnToLoginPage("denvert1@shotspotter.net","Test123!");

        Assert.assertTrue(mainPage.isPageLoaded(), "settings icon is not displayed");
        Assert.assertTrue(mainPage.getPageURL().contains("https://alerts.shotspotter.biz/main"),"Wrong url after Login");

    }

    @Test
    public void TestLoginNegative() {

        String expectedErrorMsg = "The provided credentials are not correct.";

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");

//        LoginPage resultPage = loginPage.loginAsReturnToLoginPage("IncorrectEmail", "IncorrectPassword", LoginPage.class);
        LoginPage resultPage = loginPage.loginAsReturnToLoginPage("IncorrectEmail", "IncorrectPassword");

        Assert.assertTrue(resultPage.IsInvalidCredentialsDisplayed(), "Invalid credentials is not displayed");
        Assert.assertEquals(loginPage.getErrorText(), expectedErrorMsg, "Error text is wrong");
        Assert.assertTrue(resultPage.isLoginPageLoaded(), "Login page is not loaded");

    }

}

package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;

public class LoginTest {

    /**
     * WebDriver variable
     */

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
    /**
     * username variable for login
     */
    String username = "sst.tau@gmail.com";
// denvert1@shotspotterr.net
    /**
     * password variable for login
     */    String password = "P@ssword123";
// Test123!
    /**
     * Method to execute before any Test
     * creates firefox webdriver
     * opens loginpage URL
     */


    /**
     * Positive Login Test
     *
     * Initialize LoginPage
     * Checks if LoginPage loaded, if correct URL, if correct Page Title
     * Logins using correct credentials provided
     * Checks if MainPage loaded, if URL changed
     */
    @Test
    public void testLoginPositive() {

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", ("Wrong url before login"));
        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Main page title is wrong");

        MainPage mainPage = loginPage.loginAsReturnToLoginPage(username, password);

        Assert.assertTrue(mainPage.isPageLoaded(), "settings icon is not displayed");
        Assert.assertTrue(mainPage.getPageURL().contains("https://alerts.shotspotter.biz/main"),"Wrong url after Login");

    }

    /**
     * Negative Login Test
     *
     * Initialize LoginPage
     * Checks if LoginPage loaded
     * Logins using incorrect credentials provided
     * Checks if correct error message displayed, if login was not performed
     */
    @Test
    public void TestLoginNegative() {

        String expectedErrorMsg = "The provided credentials are not correct.";

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");

        LoginPage resultPage = loginPage.loginAsReturnToLoginPage("IncorrectEmail", "IncorrectPassword");

        Assert.assertTrue(resultPage.IsInvalidCredentialsDisplayed(), "Invalid credentials is not displayed");
        Assert.assertEquals(resultPage.getErrorText(), expectedErrorMsg, "Error text is wrong");
        Assert.assertTrue(resultPage.isLoginPageLoaded(), "Login page is not loaded");

    }

    /**
     *  Positive Logout Test
     *
     *  Initialize LoginPage
     *  Login with provided correct credentials
     *  Checks if MainPage loaded
     *  Logout from MainPage
     *  Checks if LoginPage loaded, if URL changed, if PageTitle changed
     */
    @Test
    public void TestLogout() {

        LoginPage loginPage = new LoginPage(webDriver);

        MainPage mainPage = loginPage.loginAsReturnToLoginPage(username, password);

        Assert.assertTrue(mainPage.isPageLoaded(), "settings icon is not displayed");

        loginPage = mainPage.logOut();

        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", ("Wrong url before login"));
        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Main page title is wrong");

    }


}

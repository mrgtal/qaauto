package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;

public class LoginTest {

    public WebDriver webDriver;

    @Parameters({ "browserName" })
    @BeforeClass
    public void beforeClass(String browserName) {

        selectBrowser(browserName);
        webDriver.navigate().to("https://alerts.shotspotter.biz/");
    }

    @AfterClass
    public void afterClass() {

        webDriver.quit();
    }

    String username = "sst.tau@gmail.com";
    String password = "P@ssword123";


    public void selectBrowser(String browserName) {

        if (browserName.equalsIgnoreCase("Firefox")) {
            webDriver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("chrome")) {
            webDriver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("IE")) {
            webDriver = new InternetExplorerDriver();
        } else {
//default value
//           webDriver = new FirefoxDriver();
        }

    }


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

    @DataProvider
    public static Object[][] negativeLoginOptions() {
        String negativeLoginMessage = "The provided credentials are not correct.";
        return new Object[][]  {{"",                    "",                 negativeLoginMessage},
                                {" ",                   " ",                negativeLoginMessage},
                                {"InvalidEmail",        "InvalidPassword",  negativeLoginMessage},
                                {"somemail@gmail.com",  "BLABLABLA",        negativeLoginMessage},
                                {"@",                   "@",                negativeLoginMessage}
                            };
    }

    @Test (dataProvider = "negativeLoginOptions")
    public void NegativeLoginTestWithDataProvider(String negativeLoginEmail, String negativeLoginPassword, String negativeLoginMessage) {

        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page is not loaded");

        LoginPage resultPage = loginPage.loginAsReturnToLoginPage(negativeLoginEmail, negativeLoginPassword);

        Assert.assertTrue(resultPage.IsInvalidCredentialsDisplayed(), negativeLoginMessage);
        Assert.assertEquals(resultPage.getErrorText(), negativeLoginMessage, "Error text is wrong");
        Assert.assertTrue(resultPage.isLoginPageLoaded(), "Login page is not loaded");

    }

}

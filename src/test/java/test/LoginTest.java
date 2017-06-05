package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;

import static java.lang.Thread.sleep;

/**
 * Created by oleksandr.orlov on 20.05.2017.
 */
public class LoginTest {

    WebDriver webDriver; //create variable. no value

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

        LoginPage loginPage = new LoginPage(webDriver);
 //       LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
//if assert fails - test stops. no further asserts executed. firefox doesn't quit.
//to avoid = use try-catch for assert

//validation: url before login
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", ("Wrong url before login"));

//validation: title before login
        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Main page title is wrong");

//email/password/go
//        LoginPage.emailField.sendKeys("denvert1@shotspotter.net");
//        LoginPage.passwordField.sendKeys("Test123!");
//        LoginPage.goButton.click();

//        loginPage.typeEmail("denvert1@shotspotter.net");
//        loginPage.typePassword("Test123!");
//        loginPage.LoginAs("denvert1@shotspotter.net","Test123!");

//        loginPage.clickGoButton();

/*
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
*/

        MainPage mainPage = loginPage.LoginAs("denvert1@shotspotter.net","Test123!");

        Assert.assertTrue(mainPage.isPageLoaded(), "settings icon is not displayed");
        Assert.assertTrue(mainPage.getPageURL().contains("https://alerts.shotspotter.biz/main"),"Wrong url after Login");


    }

    @Test
    public void TestLoginNegative() {

/*
        String expectedErrorMsg = "The provided credentials are not correct.";

        LoginPage loginPage = new LoginPage(webDriver);

        LoginPage resultPage = loginPage.IncorrectLogin("IncorrectEmail", "IncorrectPassword");
        Assert.assertTrue(resultPage.IsInvalidCredentialsDisplayed(), "Invalid credentials is not displayed");
        Assert.assertEquals(loginPage.getErrorText(), expectedErrorMsg, "Error text is wrong");
        Assert.assertTrue(resultPage.isLoginPageLoaded(), "Login page is not loaded");
*/
 //       LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);

        String expectedErrorMsg = "The provided credentials are not correct.";

        LoginPage loginPage = new LoginPage(webDriver);



  //      loginPage.IncorrectLogin("IncorrectEmail", "IncorrectPassword");
        LoginPage resultPage = loginPage.IncorrectLogin("IncorrectEmail", "IncorrectPassword");


  //      Assert.assertTrue(loginPage.IsInvalidCredentialsDisplayed());
        Assert.assertTrue(resultPage.IsInvalidCredentialsDisplayed(), "Invalid credentials is not displayed");
        Assert.assertEquals(loginPage.getErrorText(), expectedErrorMsg, "Error text is wrong");
        Assert.assertTrue(resultPage.isLoginPageLoaded(), "Login page is not loaded");

    }





}

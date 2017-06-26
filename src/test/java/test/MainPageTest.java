package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LoginPage;
import page.MainPage;


public class MainPageTest  {

    public String username = "denvert1@shotspotter.net";
    public String password = "Test123!";

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

    @Test
    public void testIncidentsPeriodSwitch() {

        LoginPage loginPage = new LoginPage(webDriver);
        MainPage mainPage = loginPage.loginAsReturnToLoginPage(username, password);

//        mainPage.swithTimeFramePeriod(7);

 //         boolean resultChanged = mainPage.waitUntilResultCounterChanged(7);
  //      Assert.assertEquals(resultChanged, true, "Result count was not changed after Period changed");
  //      mainPage.waitResultsCountUpdated(5);
//        int resultsCount = mainPage.getResultCount();
//        int incidentCardsCount = mainPage.getIncidentCardsCount();
//        Assert.assertEquals(resultsCount, incidentCardsCount, "Results count does not match Incident Cards count");


        int[] timeFrameOptions = {24, 3, 7};

        for (int timeFrameOption : timeFrameOptions) {

            mainPage.swithTimeFramePeriod(timeFrameOption);

//            mainPage.waitResultsCountUpdated(5);
            int resultsCount = mainPage.getResultCount();
            int incidentCardsCount = mainPage.getIncidentCardsCount();
            Assert.assertEquals(resultsCount, incidentCardsCount, "Results count does not match Incident Cards count");

        }


    }


    @DataProvider
    public static Object[][] timeFrameOptions() {
        return new Object[][] {{24}, {3}, {7}};
     }


    /**
     * Test Incidents Periods Switch and validate Incident Cards qnty
     */

    @Test (dataProvider = "timeFrameOptions")
    public void testIncidentsPeriodSwitchByDataProvider(int timeFrameOption) {

        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);

        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login Page is not loaded");
        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", ("Wrong url before login"));
        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Main page title is wrong");

        MainPage mainPage = loginPage.loginAsReturnToLoginPage(username, password);

        Assert.assertTrue(mainPage.isPageLoaded(), "settings icon is not displayed");
        Assert.assertTrue(mainPage.getPageURL().contains("https://alerts.shotspotter.biz/main"),"Wrong url after Login");

        mainPage.swithTimeFramePeriod(timeFrameOption);
        int resultsCount = mainPage.getResultCount();
        int incidentCardsCount = mainPage.getIncidentCardsCount();
        Assert.assertEquals(resultsCount, incidentCardsCount, "Results count does not match Incident Cards count");


    }




















}

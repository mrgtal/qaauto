package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;


public class MainPageTest  {

    public String username = "denvert1@shotspotter.net";
    public String password = "Test123!";

    public WebDriver webDriver;
    public MainPage mainPage;

    @BeforeClass
    public void beforeClass() {
        webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://alerts.shotspotter.biz/");

        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.isLoginPageLoaded();
        mainPage = loginPage.loginAsReturnToLoginPage(username, password);

    }

    @AfterClass
    public void afterClass() {

        webDriver.quit();
    }

    @Test
    public void testIncidentsPeriodSwitch() {

        int[] timeFrameOptions = {24, 3, 7};

        for (int timeFrameOption : timeFrameOptions) {

            mainPage.swithTimeFramePeriod(timeFrameOption);

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

        mainPage.swithTimeFramePeriod(timeFrameOption);
        int resultsCount = mainPage.getResultCount();
        int incidentCardsCount = mainPage.getIncidentCardsCount();
        Assert.assertEquals(resultsCount, incidentCardsCount, "Results count does not match Incident Cards count");
    }




















}

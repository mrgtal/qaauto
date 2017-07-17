package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;
import page.TermsOfServicePage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainPageTest extends BaseTest  {

    public String username = "denvert1@shotspotter.net";
    public String password = "Test123!";

    public WebDriver webDriver;
    public MainPage mainPage;
    public TermsOfServicePage termsOfServicePage;

    @Parameters ({ "browserName" })
    @BeforeClass
    public void beforeClass(@Optional("firefox") String browserName) {
        webDriver = selectBrowserByName(browserName);
        webDriver.navigate().to("https://alerts.shotspotter.biz/");

        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.isLoginPageLoaded();
        mainPage = loginPage.loginAsReturnToLoginPage(username, password);
        mainPage.isPageLoaded();

    }

    @AfterClass
    public void afterClass() {

        webDriver.quit();
    }


    @Test(enabled = false)
    public void testListToSet() {

        List<String> listArray = new ArrayList<String>();
        listArray.add("address");
        listArray.add("city");
        listArray.add("time");
        listArray.add("address");
        listArray.add("city");
        Set setArray = new HashSet(listArray);
        System.out.println(listArray);
        System.out.println(setArray);

    }

    @Test
    public void testValidateIncidentCardDetails() {

        String requiredCity = "Denver";

        int incidentCardsCount = mainPage.getListCount("IncidentCard");
        Assert.assertNotEquals(incidentCardsCount, 0, "No Incident Cards Available");

        String[] listCountOptions = {"address", "city", "time"};

        for (String listCountOption : listCountOptions) {
            Assert.assertEquals(incidentCardsCount, mainPage.getListCount(listCountOption) ,
                listCountOption + " Count doesn't match Cards Count");
        }

        int cityIndex =  mainPage.allCitiesEqualTo(requiredCity);
        Assert.assertEquals(cityIndex, -1, "CityList element " + cityIndex + " is not " + requiredCity);

        int addressIndex =  mainPage.allAddressesNotEmpty();
        Assert.assertEquals(addressIndex, -1, "AddressList element " + addressIndex + " is empty");
        Assert.assertTrue(mainPage.ifAllTimeUnique(), "There are non-unique timestamps");
    }

//2nd test. for implementation example
    @Test
    public void testValidateCardsDetailsV2() {
        String expectedCity = "Denver";
        mainPage.openIncidentsList();
//        List<String> listCities = mainPage.getIncidentCardsCities();
//        List<String> listStreets = mainPage.getIncidentCardsStreets();
//       List<String> listTimeStamps = mainPage.getIncidentCardsTimeStamps();
        List<String> listCities = mainPage.getIncidentCardsStreetCityTime("city");
        List<String> listStreets = mainPage.getIncidentCardsStreetCityTime("street");
       List<String> listTimeStamps = mainPage.getIncidentCardsStreetCityTime("time");
System.out.println(listCities);
System.out.println(listStreets);
System.out.println(listTimeStamps);
        for (String elementCity: listCities) {
            Assert.assertEquals(elementCity, expectedCity, "City is not Denver");
        }

        for (String elementStreet: listStreets) {
            Assert.assertNotEquals(elementStreet, "", "Street address is empty");
        }

        for (String elementTimeStamp: listTimeStamps) {
            Assert.assertNotEquals(elementTimeStamp, "", "TimeStamp is empty");
        }

    }


    @Test
    public void testTermsOfServiceWindow() {

        String mainPageWindow;
        String termsOfServiceWindow;

        mainPageWindow = mainPage.getCurrentWindowHandle();

        mainPage.openAboutDisplay();
        termsOfServicePage = mainPage.openTermsOfServicePage();

        termsOfServiceWindow = mainPage.getCurrentWindowHandle();

        Assert.assertTrue(termsOfServicePage.isTermsOfServicePageLoaded(), "TermsOfService page is not loaded");
        Assert.assertEquals(termsOfServicePage.getPageURL(), "http://www.shotspotter.com/apps/tos", ("TermsOfService Page. Wrong url"));
        Assert.assertEquals(termsOfServicePage.getPageTitle(), "Apps-TOS", "TermsOfService page title is wrong");

        termsOfServicePage.closeWindow(termsOfServiceWindow);
        mainPage.switchWindowTo(mainPageWindow);
        mainPage.closeAboutScreen();
        mainPage.isPageLoaded();

    }


    @Test
    public void testIncidentsPeriodSwitch() {

        int[] timeFrameOptions = {24, 3, 7};

        for (int timeFrameOption : timeFrameOptions) {

            mainPage.switchTimeFramePeriod(timeFrameOption);

            int resultsCount = mainPage.getResultCount();
            int incidentCardsCount = mainPage.getListCount("IncidentCard");
            Assert.assertEquals(resultsCount, incidentCardsCount, "Results count does not match Incident Cards count");

        }

    }

    @DataProvider
    public static Object[][] timeFrameOptions() {
        return new Object[][] {{24}, {3}, {7}};
     }

    @Test (dataProvider = "timeFrameOptions")
    public void testIncidentsPeriodSwitchByDataProvider(int timeFrameOption) {

        mainPage.switchTimeFramePeriod(timeFrameOption);
        int resultsCount = mainPage.getResultCount();
        int incidentCardsCount = mainPage.getListCount("IncidentCard");
        Assert.assertEquals(resultsCount, incidentCardsCount, "Results count does not match Incident Cards count");
    }


}

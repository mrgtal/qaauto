package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainPageTest extends BaseTest  {

    public String username = "denvert1@shotspotter.net";
    public String password = "Test123!";

    public WebDriver webDriver;
    public MainPage mainPage;

    @Parameters ({ "browserName" })
    @BeforeClass
    public void beforeClass(@Optional("firefox") String browserName) {
        webDriver = selectBrowserByName(browserName);
        webDriver.navigate().to("https://alerts.shotspotter.biz/");

        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.isLoginPageLoaded();
        mainPage = loginPage.loginAsReturnToLoginPage(username, password);

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
    public void validateIncidentCardAddressCityTime() {

        String requiredCity = "Denver";

        int incidentCardsCount = mainPage.getListCount("IncidentCard");

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





    @Test
    public void testIncidentsPeriodSwitch() {

        int[] timeFrameOptions = {24, 3, 7};

        for (int timeFrameOption : timeFrameOptions) {

            mainPage.switchTimeFramePeriod(timeFrameOption);

            int resultsCount = mainPage.getResultCount();
//            int incidentCardsCount = mainPage.getIncidentCardsCount();
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
//        int incidentCardsCount = mainPage.getIncidentCardsCount();
        int incidentCardsCount = mainPage.getListCount("IncidentCard");
        Assert.assertEquals(resultsCount, incidentCardsCount, "Results count does not match Incident Cards count");
    }




}

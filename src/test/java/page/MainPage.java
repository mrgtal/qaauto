package page;

import com.google.common.collect.Iterables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;

/**
 * Main Page class. Inherited from BasePage class.
 */
public class MainPage extends BasePage {

    @FindBy(className = "settings")
    private WebElement settingsItem;

    @FindBy(css = ".settings.isOpen")
    private WebElement settingsOpen;

    @FindBy(xpath = "//li[text()='Logout']")
    private WebElement logoutItem;

    @FindBy(xpath = "//li[text()='About']")
    private WebElement aboutItem;

    @FindBy(xpath = "//filter-menu/div[@class='selected-option']")
    private WebElement incidentsTimeFrameSwitch;

    @FindBy(xpath = "//*[@class='result-count']")
    private WebElement resultsCount;

    @FindBy(xpath = "//div[text()='List']")
    private WebElement listButton;

    @FindBy(xpath = "//incident-list//incident-card")
    private List<WebElement> incidentsCardsList;

    @FindBy(xpath = "//incident-list//incident-card//div[@class='address']")
    private List<WebElement> incidentsCardAddresses;

    @FindBy(xpath = "//incident-list//incident-card//div[@class='city S']")
    private List<WebElement> incidentsCardCities;

    @FindBy(xpath = "//incident-list//incident-card//div[@class='cell-container']//div[@class='cell day']//div[@class='content']")
    private List<WebElement> incidentsCardTime;

    @FindBy(xpath = "//div[@class='available-options']")
    private WebElement optionsList;

    @FindBy(xpath = "//a[text()='terms of service']")
    private WebElement termsOfServiceLink;

    @FindBy(xpath = "//button[text()='Close']")
    private WebElement closeButtonElement;

    private WebElement prepareWebElementWithDynamicXpath(int timeFramePeriod) {

        return webDriver.findElement(By.xpath(
                String.format("//filter-menu/div[@class='available-options']//span[@class='time-increment' and text()='%d']", timeFramePeriod)));
    }

    /**
     * MainPage class constructor.
     * Initialize MainPage WebElements
     * invoke parent class constructor
     *
     * @param driver WebDriver object
     */
    public MainPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void openAboutDisplay() {
        settingsItem.click();

        waitUntilElementDisplayed(settingsOpen, 5);

        waitUntilElementDisplayed(aboutItem, 5);
        waitUntilElementClicable(aboutItem, 5);

        aboutItem.click();
    }

    public TermsOfServicePage openTermsOfServicePage() {

        waitUntilElementClicable(termsOfServiceLink, 5);
        termsOfServiceLink.click();
        webDriver.switchTo().window(Iterables.getLast(webDriver.getWindowHandles()));

        return PageFactory.initElements(webDriver, TermsOfServicePage.class);

    }

    public void closeAboutScreen() {

        closeButtonElement.click();
        waitUntilElementClicable(settingsItem, 5);

    }

    /**
     * Method determines results count value
     *
     * @return integer result value
     */
    public int getResultCount() {
        return Integer.parseInt(resultsCount.getText().toUpperCase().replace(" RESULTS", ""));
    }

    /**
     * Method checks if main page is loaded and specific WebElement "settingsItem" is Displayed.
     * Uses waitUntilElementDisplayed method with specific parameters.
     *
     * @return true/false
     */
    public boolean isPageLoaded() {

        return waitUntilElementDisplayed(settingsItem, 15).isDisplayed();
    }

    /**
     * Method for logout from MainPage to LoginPage
     * checks if required WebElements displayed.
     * Clicks on WebElement "logoutItem"
     *
     * @return LoginPage class
     */
    public LoginPage logOut () {

        settingsItem.click();

        waitUntilElementDisplayed(settingsOpen, 5);

        waitUntilElementDisplayed(logoutItem, 5);
        waitUntilElementClicable(logoutItem, 5);

        logoutItem.click();

        return PageFactory.initElements(webDriver, LoginPage.class);

    }

    /**
     * Method switches timeframe period to required
     *
     * @param timeFramePeriod integer period to switch
     */
    public void switchTimeFramePeriod(int timeFramePeriod) {

        waitUntilElementDisplayed(incidentsTimeFrameSwitch, 5);
        incidentsTimeFrameSwitch.click();
        waitUntilElementDisplayed(optionsList, 5);
        WebElement timeFrameSwitch = prepareWebElementWithDynamicXpath(timeFramePeriod);
        waitUntilElementDisplayed(timeFrameSwitch, 5);
        waitUntilElementClicable(timeFrameSwitch, 5);
        timeFrameSwitch.click();
        waitResultsCountUpdated(5);

    }


    /**
     * Method returns size of required list
     *
     * @param listToCount
     * @return int size of required list
     */
    public int getListCount(String listToCount) {

        switch (listToCount.toLowerCase()) {
            case "address":
                return incidentsCardAddresses.size();
            case "city":
                return incidentsCardCities.size();
            case "time":
                return incidentsCardTime.size();
            case "incidentcard":
                return incidentsCardsList.size();
            default:
                return 0;
        }

    }

    /**
     * Method validates if all cities are equal to required cityname
     *
     * @param cityName String. City to validate
     * @return -1 if ok. return element index if condition doesn't fit
     */
    public int allCitiesEqualTo(String cityName) {

        for(WebElement cityListElement : incidentsCardCities) {
            if(!cityListElement.getText().equalsIgnoreCase(cityName)) {
                return incidentsCardCities.indexOf(cityListElement);
            }
        }
        return -1;
    }

    /**
     * Method checks if all addresses are not empty
     *
     * @return -1 if ok. return element index if condition doesn't fit
     */
    public int allAddressesNotEmpty() {
        for(WebElement addressListElement : incidentsCardAddresses) {
            if(addressListElement.getText().isEmpty()) {
                return incidentsCardAddresses.indexOf(addressListElement);
            }
        }
        return -1;
    }

    /**
     * Method checks id all timestamps are unique
     *
     * @return true/false
     */
    public boolean ifAllTimeUnique() {
        Set timeCountSet = new HashSet(incidentsCardTime);
        if(timeCountSet.size() < incidentsCardTime.size()) {
            return false;
        }
        return true;
    }

    /**
     * Method waits until results count is updated within provided time in seconds
     *
     * @param maxTimeoutSec max timeout to wait. in seconds
     */
    public void waitResultsCountUpdated (int maxTimeoutSec) {
        int initialResultCount = getResultCount();

        for (int i = 0; i < maxTimeoutSec; i++ ) {
            try {
                sleep(1000);
                int currentResultCount = getResultCount();
                if (initialResultCount != currentResultCount) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

// methods for 2nd test of IncidentCardsFields
//implementation example
    /**
     * Method to open list of incident cards
     * waits until 1st element is displayed
     */
    public void openIncidentsList() {
        listButton.click();
        waitUntilElementDisplayed(incidentsCardsList.get(1), 5);
    }


    /**
     * Method generates and returns relative xpath for child elements of selected list
     *
     * @param listRequestedName - determine which list requires xpath
     * @return String relative xpath for child element
     */
    public String createXpathForIncidentCardDetails (String listRequestedName) {
        switch (listRequestedName.toLowerCase()) {
            case "street":
                return ".//div[@class='address']";
            case "city":
                return ".//div[@class='city S']";
            case "time":
                return ".//div[@class='cell-container']//div[@class='cell day']//div[@class='content']";
            default:
                return "";
        }
    }

    /**
     * Method collects all elements for required list
     *
     * @param listRequestedName listname to collect elements
     * @return list of requested elements
     */
    public List<String> getIncidentCardsStreetCityTime(String listRequestedName) {
        List<String> listRequested = new ArrayList<String>();

        String listRequestedXpath;
        listRequestedXpath = createXpathForIncidentCardDetails(listRequestedName);

        for (WebElement incidentCard: incidentsCardsList) {
            String requiredElement = incidentCard.findElement(By.xpath(listRequestedXpath)).getText();
            listRequested.add(requiredElement);
        }
        return listRequested;
    }

    /**
     * Method checks if all elements within selected list are unique
     *
     * @param listRequested list of elements
     * @return true/false
     */
    public boolean isAllElementsUniqueInList(List<String> listRequested) {

        Set listToSet = new HashSet(listRequested);
        if(listToSet.size() < listRequested.size()) {
            return false;
        }
        return true;
    }




}

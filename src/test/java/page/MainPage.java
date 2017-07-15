package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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


    public int allCitiesEqualTo(String cityName) {

        for(WebElement cityListElement : incidentsCardCities) {
            if(!cityListElement.getText().equalsIgnoreCase(cityName)) {
                return incidentsCardCities.indexOf(cityListElement);
            }
        }
        return -1;
    }


    public int allAddressesNotEmpty() {
        for(WebElement addressListElement : incidentsCardAddresses) {
            if(addressListElement.getText().isEmpty()) {
                return incidentsCardAddresses.indexOf(addressListElement);
            }
        }
        return -1;
    }


    public boolean ifAllTimeUnique() {
        Set timeCountSet = new HashSet(incidentsCardTime);
        if(timeCountSet.size() < incidentsCardTime.size()) {
            return false;
        }
        return true;

    }


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

}

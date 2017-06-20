package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


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


// redesign and parametrize to have only 1 usable locator with variable
    @FindBy(xpath = "//filter-menu/div[@class='available-options']//span[@class='time-increment' and text()='24']")
    private WebElement timeFrameSwitch24h;

    @FindBy(xpath = "//filter-menu/div[@class='available-options']//span[@class='time-increment' and text()='3']")
    private WebElement timeFrameSwitch3d;

    @FindBy(xpath = "//filter-menu/div[@class='available-options']//span[@class='time-increment' and text()='7']")
    private WebElement timeFrameSwitch7d;
//similar locators


    @FindBy(xpath = "//*[@class='result-count']")
    private WebElement resultsCount;

    @FindBy(xpath = "//div[text()='List']")
    private WebElement listButton;


    // //div[@class='incidents']//incident-card
    // //incident-list//incident-card

    @FindBy(xpath = "//incident-list//incident-card")
    private List<WebElement> incidentsCardsList;




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

/*    String string = "004-034556";
    String[] parts = string.split("-");
    String part1 = parts[0]; // 004
    String part2 = parts[1]; // 034556
*/

    public int getResultCount() {
        return Integer.parseInt(resultsCount.getText().replace(" Results", ""));
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

}

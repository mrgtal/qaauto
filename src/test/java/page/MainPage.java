package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage extends BasePage {

    @FindBy(className = "settings")
    private WebElement settingsItem;

//    @FindBy(xpath = "//*[@class='settings isOpen']")
//    @FindBy(className = "settings isOpen")
    @FindBy(css = ".settings.isOpen")
    private WebElement settingsOpen;

    @FindBy(xpath = "//settings-drop-down//li[text()='Logout']")
    private WebElement logoutItem;

    public MainPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public boolean isPageLoaded() {

        return waitUntilElementDisplayed(settingsItem, 15).isDisplayed();
    }

    public LoginPage logOut () {

        settingsItem.click();

        waitUntilElementDisplayed(settingsOpen, 5);

        waitUntilElementDisplayed(logoutItem, 5);
        waitUntilElementClicable(logoutItem, 5);

        logoutItem.click();

/*
        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
        executor.executeScript("arguments[0].click();", logoutElement);
*/

        return PageFactory.initElements(webDriver, LoginPage.class);

    }

}

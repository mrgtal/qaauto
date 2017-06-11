package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class MainPage extends BasePage {

    @FindBy(className = "settings")
    private WebElement settingsItem;

//    @FindBy(xpath = "//settings-drop-down//li[text()='Logout']")

//    @FindBy(xpath = "//*[@class='settings isOpen']")
    @FindBy(className = "settings isOpen")
    private WebElement settingsOpen;

    @FindBy(xpath = "//settings-drop-down//li[text()='Logout']")
 //   @FindBy(xpath = "//settings-drop-down//li[text()='Logout'][@style='opacity: 1;']")
    private WebElement logoutItem;

    public MainPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public boolean isPageLoaded() {

        return waitUntilElementDisplayed(settingsItem, 15).isDisplayed();
    }



    public LoginPage logOut () {
        waitUntilElementDisplayed(settingsItem, 3);
System.out.println("settings-wait");
        settingsItem.click();
System.out.println("settings-click");
        waitUntilElementDisplayed(settingsOpen, 3);
System.out.println("settings-open");

/*

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

*/

//        waitUntilElementDisplayed(logoutItem, 5);
        waitUntilElementClicable(logoutItem, 5);

System.out.println("logout-wait");

        logoutItem.click();
System.out.println("logout-click");

        return PageFactory.initElements(webDriver, LoginPage.class);

    }


}

package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.concurrent.TimeUnit;

/**
 * Created by oleksandr.orlov on 05.06.2017.
 */
public class MainPage {

    WebDriver webDriver;


    @FindBy(how = How.CLASS_NAME, using = "settings")
    WebElement settingsItem;


    public MainPage(WebDriver driver) {
        this.webDriver = driver;
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }


    public boolean isPageLoaded() {
        return settingsItem.isDisplayed();
    }

}

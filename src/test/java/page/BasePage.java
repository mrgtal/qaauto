package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage {

    public WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    public String getPageURL() {
        return webDriver.getCurrentUrl();
    }

    public String getPageTitle() {
        return webDriver.getTitle();
    }

    public WebElement waitUntilElementDisplayed(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

}
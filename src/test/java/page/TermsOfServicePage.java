package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class TermsOfServicePage extends BasePage {

    @FindBy(xpath = "//h3[text()='ShotSpotter - Terms of Service']")
    private WebElement termsOfServiceHeader;

    public TermsOfServicePage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    /**
     * Method waits until TermsOfService page is loaded
     *
     * @return true/false
     */
    public boolean isTermsOfServicePageLoaded() {

        return waitUntilElementDisplayed(termsOfServiceHeader, 15).isDisplayed();
    }

    public void closeTermsOfServicePage() {
        closeWindow(getCurrentWindowHandle());
    }

}

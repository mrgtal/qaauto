package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@class='button' and text()='GO']")
    private WebElement goButton;

    @FindBy(className = "invalid-credentials")
    WebElement invalidCredentials;

    public LoginPage (WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
        waitUntilElementDisplayed(goButton, 10);
    }

    public <T> T loginAsReturnToLoginPage(String user, String pw){
        emailField.sendKeys(user);
        passwordField.sendKeys(pw);
        goButton.click();

        if (isElementDisplayed(goButton, 3)) {

            return (T) PageFactory.initElements(webDriver, LoginPage.class);
        } else {

            return (T) PageFactory.initElements(webDriver, MainPage.class);
        }
    }

    public boolean IsInvalidCredentialsDisplayed() {
        return waitUntilElementDisplayed(invalidCredentials, 15).isDisplayed();
    }

    public String getErrorText() {

        return waitUntilElementDisplayed(invalidCredentials, 15).getText();
    }

    public boolean isLoginPageLoaded() {

        return waitUntilElementDisplayed(emailField, 15).isDisplayed();
    }

}

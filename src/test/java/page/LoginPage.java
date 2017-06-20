package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Main Page class. Inherited from BasePage class.
 */
public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@class='button' and text()='GO']")
    private WebElement goButton;

    @FindBy(className = "invalid-credentials")
    WebElement invalidCredentials;

    /**
     * LoginPage class constructor.
     * Initialize LoginPage WebElements
     * waits for WebElement "goButton" to be displayed
     * @param driver WebDriver object
     */
    public LoginPage (WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
        waitUntilElementDisplayed(goButton, 10);
    }

    /**
     * Generic method.
     * Depends on parameters provided method either returns MainPage or LoginPage
     *
     * @param user String username
     * @param pw String password
     * @param <T>
     * @return LoginPage or MainPage
     */
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

    /**
     * Method checks if invalid credentials error message displayed
     * using waitUntilElementDisplayed with specific parameters
     * @return true/false
     */
    public boolean IsInvalidCredentialsDisplayed() {
        return waitUntilElementDisplayed(invalidCredentials, 15).isDisplayed();
    }

    /**
     * Method waits for error message element to be displayed and gets message text
     *
     * @return String error message text
     */
    public String getErrorText() {

        return waitUntilElementDisplayed(invalidCredentials, 15).getText();
    }

    /**
     * Method checks if login page is loaded and specific WebElement "emailField" is Displayed.
     * Uses waitUntilElementDisplayed method with specific parameters.
     *
     * @return true/false
     */
    public boolean isLoginPageLoaded() {

        return waitUntilElementDisplayed(emailField, 15).isDisplayed();
    }

}

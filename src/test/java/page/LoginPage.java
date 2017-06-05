package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by oleksandr.orlov on 27.05.2017.
 */
public class LoginPage {
 //   public static
    WebDriver webDriver;


//variables
//    public static WebElement emailField = webDriver.findElement(By.xpath("//input[@type='email']"));
 //   public static WebElement passwordField =  webDriver.findElement(By.xpath("//input[@type='password']"));
  //  public static WebElement goButton = webDriver.findElement(By.xpath("//*[@class='button' and text()='GO']"));



//    By emailField = By.xpath("//input[@type='email']");
//    By passwordField = By.xpath("//input[@type='password']");
//    By goButton = By.xpath("//*[@class='button' and text()='GO']");

    @FindBy(how = How.XPATH, using = "//input[@type='email']")
    WebElement emailField;

    @FindBy(how = How.XPATH, using = "//input[@type='password']")
    WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//*[@class='button' and text()='GO']")
    WebElement goButton;

    @FindBy(how = How.XPATH, using = "//*[@class='invalid-credentials']")
    WebElement invalidCredentials;


    public LoginPage (WebDriver driver) {
        this.webDriver = driver;
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://alerts.shotspotter.biz/");

    }


/*
    public void typeEmail(String email) {
        webDriver.findElement(emailField).sendKeys(email);

    }

    public void typePassword(String password) {
        webDriver.findElement(passwordField).sendKeys(password);
    }
*/

//method. create. 2 params.
    public MainPage LoginAs (String email, String password) {
//        webDriver.findElement(emailField).sendKeys(email);
//        webDriver.findElement(passwordField).sendKeys(password);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        goButton.click();
        MainPage mainPage = PageFactory.initElements(webDriver, MainPage.class);
        return mainPage;


    }


    public void IncorrectLogin(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        goButton.click();
    }

    public boolean IsInvalidCredentialsDisplayed() {
        return invalidCredentials.isDisplayed();
    }



//    public void clickGoButton() {
//        webDriver.findElement(goButton).click();

//    }


}

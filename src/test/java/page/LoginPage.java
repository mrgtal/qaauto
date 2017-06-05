package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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



    By emailField = By.xpath("//input[@type='email']");
    By passwordField = By.xpath("//input[@type='password']");
    By goButton = By.xpath("//*[@class='button' and text()='GO']");

    public LoginPage (WebDriver driver) {
        this.webDriver = driver;
    }

    public void typeEmail(String email) {
        webDriver.findElement(emailField).sendKeys(email);

    }

    public void typePassword(String password) {
        webDriver.findElement(passwordField).sendKeys(password);
    }


//method. create. 2 params.
    public void LoginAs (String email, String password) {
        webDriver.findElement(emailField).sendKeys(email);
        webDriver.findElement(passwordField).sendKeys(password);
    }



    public void clickGoButton() {
        webDriver.findElement(goButton).click();

    }


}

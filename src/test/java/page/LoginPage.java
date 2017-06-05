package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by oleksandr.orlov on 27.05.2017.
 */
public class LoginPage {
    public static WebDriver webDriver;


//variables
    public static WebElement emailField = webDriver.findElement(By.xpath("//input[@type='email']"));
    public static WebElement passwordField =  webDriver.findElement(By.xpath("//input[@type='password']"));
    public static WebElement goButton = webDriver.findElement(By.xpath("//*[@class='button' and text()='GO']"));

//method. create. 2 params.
 //   public void LoginAs {



//    }




}

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by oleksandr.orlov on 23.05.2017.
 */
public class LoginNegativeTest {

        public static void main(String[] args) {

            WebDriver webDriver = new FirefoxDriver();

            webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
            webDriver.navigate().to( "https://alerts.shotspotter.biz/");

/*

            try {
                sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

*/

            webDriver.findElement(By.xpath("//input[@type='email']")).sendKeys("BLABLA");
            webDriver.findElement(By.xpath("//input[@type='password']")).sendKeys("BLA");
            webDriver.findElement(By.xpath("//*[@class='button' and text()='GO']")).click();

/*
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
*/

// verification

// The provided credentials are not correct.

//  webDriver.findElement(By.xpath("//*[@class='invalid-credentials']"));
//            System.out.println(webDriver.findElement(By.xpath("//*[@class='invalid-credentials']")).getText());

            String assert_message = webDriver.findElement(By.xpath("//*[@class='invalid-credentials']")).getText();
            Assert.assertEquals("The provided credentials are not correct.", assert_message);

//            System.out.println(assert_message);

/*
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

*/
            webDriver.quit();

    }



}

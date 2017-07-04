package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by oleksandr.orlov on 04.07.2017.
 */
public class BrowserSelect {

    public static WebDriver selectBrowser(String browserName) {

        if (browserName.equalsIgnoreCase("Firefox")) {
            return new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("chrome")) {
            return new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("IE")) {
            return new InternetExplorerDriver();
        } else {
            return null;
        }
    }
}

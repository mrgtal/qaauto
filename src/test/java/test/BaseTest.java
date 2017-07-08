package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseTest {

    public static WebDriver selectBrowserByNameqq(String browserName) {

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

    public static WebDriver selectBrowserByName(String browserName) {
        switch (browserName.toLowerCase()) {
            case "firefox":
                return new FirefoxDriver();
            case "chrome":
                return new ChromeDriver();
            case "ie":
                return new InternetExplorerDriver();
            default:
                return null;
        }

    }



}

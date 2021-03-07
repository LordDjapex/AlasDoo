package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class FunctionalTest {

    //change this to url of the website if it's not on the same machine or if it uses different port other than 3000
    protected static String targetUrl = "http://localhost:3000";
    //change
    protected static String browser = "Firefox"; //Change value to: 'Chrome' for Chrome, 'Firefox' for Firefox
    protected static WebDriver driver;

    @BeforeAll
    public static void setUp(){
        if (browser.equals("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }  else {
            //it's better not to hardcore this, and this is not really an argument...So what i did here is kind of a bad practice but in this case it's fine
            throw new InvalidArgumentException("browser can only hold one of these values: Firefox, Chrome, Explorer, Edge");
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void cleanUp(){
        driver.manage().deleteAllCookies();
    }

    @AfterAll
    public static void tearDown(){
        driver.close();
    }
}

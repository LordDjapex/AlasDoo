import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DemoAutomation {

    public static String browser = "";
    public static WebDriver driver;

    public static void main(String[] args) {

        //"E:\\Test assigment Alas Doo\\java_test_assignment\\src\\webdriver\\browserdrivers\\geckodriver.exe"
//        System.setProperty("webdriver.gecko.driver", "src\\webdriver\\browserdrivers\\geckodriver.exe");
//        FirefoxDriver firefoxDriver = new FirefoxDriver();
//        firefoxDriver.get("http://google.com");



        driver.get("http://google.com");

    }
}



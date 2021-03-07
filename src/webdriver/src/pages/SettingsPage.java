package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageObject;

public class SettingsPage extends PageObject {

    @FindBy(xpath = "//*[@id='root']/div/main/div[2]/div/div/button")
    private WebElement startButton;

    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    public void clickStart() {
        startButton.click();
        try {
            Thread.sleep(400);
        } catch (Exception e) {
            System.out.println("Cannot sleep");
        }
    }

    public boolean startButtonState() {
        return startButton.isEnabled();
    }
}

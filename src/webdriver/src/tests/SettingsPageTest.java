package tests;

import org.junit.jupiter.api.Test;
import pages.SettingsPage;
import static org.junit.jupiter.api.Assertions.*;

public class SettingsPageTest extends FunctionalTest {


    //in my opinion main problem is on frontend, it displays the same thing whether the requests to the backend were correct or not
    @Test
    public void createEntities() {
        driver.get(targetUrl + "/settings");
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.clickStart();
        assertFalse(settingsPage.startButtonState());
    }
}

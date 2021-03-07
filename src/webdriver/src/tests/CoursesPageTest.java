package tests;

import org.junit.jupiter.api.Test;
import pages.CoursesPage;
import pages.SettingsPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoursesPageTest extends FunctionalTest {

    @Test
    public void checkIndividualCourses() {

        driver.get(targetUrl + "/settings");
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.clickStart();

        driver.navigate().to(targetUrl + "/course");
        CoursesPage coursesPage = new CoursesPage(driver);
        assertTrue(coursesPage.checkIndividualCourses());
    }

    @Test
    public void deleteAllCourses() {
        driver.get(targetUrl + "/settings");
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.clickStart();

        driver.navigate().to(targetUrl + "/course");
        CoursesPage coursesPage = new CoursesPage(driver);
        assertTrue(coursesPage.deleteAllCourses());
    }

    @Test
    public void updateAllCourses() {
        driver.get(targetUrl + "/settings");
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.clickStart();

        driver.navigate().to(targetUrl + "/course");
        CoursesPage coursesPage = new CoursesPage(driver);
        assertTrue(coursesPage.updateAllCourses("yes", "2", "2"));
    }

    @Test
    public void addNewCourse() {
        driver.navigate().to(targetUrl + "/course");
        CoursesPage coursesPage = new CoursesPage(driver);
        assertTrue(coursesPage.addNewCourse("yes", "2", "2"));
    }
}

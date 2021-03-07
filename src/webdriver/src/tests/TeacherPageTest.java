package tests;

import org.junit.jupiter.api.Test;
import pages.SettingsPage;
import pages.StudentsPage;
import pages.TeacherPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeacherPageTest extends FunctionalTest {

    @Test
    public void checkIndividualTeachers() {
        driver.get(targetUrl + "/settings");
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.clickStart();

        driver.navigate().to(targetUrl + "/teacher");
        TeacherPage teacherPage = new TeacherPage(driver);
        assertTrue(teacherPage.checkIndividualTeachers());
    }

    @Test
    public void deleteAllTeachers() {
        driver.get(targetUrl + "/settings");
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.clickStart();

        driver.navigate().to(targetUrl + "/teacher");
        TeacherPage teacherPage = new TeacherPage(driver);
        assertTrue(teacherPage.deleteAllTeachers());
    }

    //test will fail if ran twice with same values because of duplicate inputs
    @Test
    public void addNewTeacher() {
        driver.get(targetUrl + "/teacher");
        TeacherPage teacherPage = new TeacherPage(driver);
        assertTrue(teacherPage.addNewTeacher("yes", "2", "2"));
    }

    //test will only work for first input
    @Test
    public void updateAllTeachers() {
        driver.get(targetUrl + "/settings");
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.clickStart();

        driver.navigate().to(targetUrl + "/teacher");
        TeacherPage teacherPage = new TeacherPage(driver);
        assertTrue(teacherPage.updateAllTeachers("s", "s", "sssss@gmail.com"));
    }

    @Test
    public void addTeacherCourse() {
        driver.get(targetUrl + "/settings");
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.clickStart();

        driver.navigate().to(targetUrl + "/teacher");
        TeacherPage teacherPage = new TeacherPage(driver);
        assertTrue(teacherPage.addNewTeacherCourse("2"));
    }

    @Test
    public void deleteTeahcerCourse() {
        driver.get(targetUrl + "/settings");
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.clickStart();

        driver.navigate().to(targetUrl + "/teacher");
        TeacherPage teacherPage = new TeacherPage(driver);
        assertTrue(teacherPage.deleteTeacherCourse());
    }



}

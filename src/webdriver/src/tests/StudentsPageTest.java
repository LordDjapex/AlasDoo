package tests;

import org.junit.jupiter.api.Test;
import pages.CoursesPage;
import pages.SettingsPage;
import pages.StudentsPage;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class StudentsPageTest extends FunctionalTest {

    @Test
    public void checkIndividualStudents() {
        driver.get(targetUrl + "/settings");
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.clickStart();

        driver.navigate().to(targetUrl + "/student");
        StudentsPage studentsPage = new StudentsPage(driver);
        assertTrue(studentsPage.checkIndividualStudents());
    }

    @Test
    public void deleteAllStudents() {
        driver.get(targetUrl + "/settings");
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.clickStart();

        driver.navigate().to(targetUrl + "/student");
        StudentsPage studentsPage = new StudentsPage(driver);
        assertTrue(studentsPage.deleteAllStudents());
    }

    @Test
    public void addNewStudent() {
        driver.get(targetUrl + "/student");
        StudentsPage studentPage = new StudentsPage(driver);
        assertTrue(studentPage.addNewStudent("yes", "2", "2", "2222@gmail.com", "23334535"));
    }

    //note, this test will fail, because some of the inputs msut not be duplicated
    @Test
    public void updateAllStudents() {
        driver.get(targetUrl + "/settings");
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.clickStart();

        driver.navigate().to(targetUrl + "/student");
        StudentsPage studentsPage = new StudentsPage(driver);
        assertTrue(studentsPage.updateAllStudents("yes", "surname", "2222@gmail.com"));
    }

    @Test
    public void addNewStudentCourse() {
        driver.get(targetUrl + "/settings");
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.clickStart();

        driver.navigate().to(targetUrl + "/student");
        StudentsPage studentsPage = new StudentsPage(driver);
        assertTrue(studentsPage.addNewStudentCourse("2"));
    }

    @Test
    public void deleteFirstCourseForStudent() {
        driver.get(targetUrl + "/settings");
        SettingsPage settingsPage = new SettingsPage(driver);
        settingsPage.clickStart();

        driver.navigate().to(targetUrl + "/student");
        StudentsPage studentsPage = new StudentsPage(driver);
        assertTrue(studentsPage.deleteStudentCourse());
    }

}

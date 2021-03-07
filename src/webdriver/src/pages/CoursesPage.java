package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PageObject;

import java.util.ArrayList;
import java.util.List;

public class CoursesPage extends PageObject {

    @FindBy(className = "MuiDataGrid-row")
    List<WebElement> coursesList;

    @FindBy(xpath = "//*[@id='root']/div/main/div[2]/div/div/div/div[3]/div/div[2]/div/div[2]/button[2]")
    WebElement nextPage;

    @FindBy(xpath = "//*[@id='root']/div/main/div[2]/div[2]/form/div[4]/button[2]")
    WebElement deleteButton;

    public CoursesPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkIndividualCourses() {
        //backend call takes some time to fetch results that's why wait is used
        WebDriverWait wait = new WebDriverWait(driver, 2);
        coursesList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));
        String startingUrl = driver.getCurrentUrl();
        List<String> previousUrls = new ArrayList<>();
        for (WebElement course : coursesList) {
            course.click();
            if (driver.getCurrentUrl().equals(startingUrl) || previousUrls.contains(driver.getCurrentUrl())) {
                return false;
            }
            previousUrls.add(driver.getCurrentUrl());
        }
        if (nextPage.isEnabled()) {
            nextPage.click();
            checkIndividualCourses();
        }
        return true;
    }

    public boolean deleteAllCourses() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        coursesList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));
        //keeps updating list
        while (true) {
            coursesList = driver.findElements(By.className("MuiDataGrid-row"));
            coursesList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));
            if (coursesList.isEmpty()) {
                break;
            } else {
                coursesList.get(0).click();
                deleteButton.click();
            }

        }
        return true;
    }

    public boolean updateAllCourses(String courseNameInput, String costPerClassInput, String classPerWeekInput) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        coursesList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));

        for (WebElement course : coursesList) {
            course.click();

            WebElement courseName = driver.findElement(By.name("developerCourseName"));
            WebElement costPerClass = driver.findElement(By.name("costPerClass"));
            WebElement classPerWeek = driver.findElement(By.name("classesPerWeek"));

            courseName.clear();
            courseName.sendKeys(courseNameInput);
            costPerClass.clear();
            costPerClass.sendKeys(costPerClassInput);
            classPerWeek.clear();
            classPerWeek.sendKeys(classPerWeekInput);

            WebElement saveButton = driver.findElement(By.className("MuiButton-containedPrimary"));
            saveButton.click();
        }
        if (nextPage.isEnabled()) {
            nextPage.click();
            updateAllCourses(courseNameInput, costPerClassInput, classPerWeekInput);
        }
        return true;
    }

    public boolean addNewCourse(String courseNameInput, String costPerClassInput, String classPerWeekInput) {
        WebElement addButton = driver.findElement(By.className("MuiFab-primary"));
        addButton.click();

        WebElement courseName = driver.findElement(By.name("developerCourseName"));
        WebElement costPerClass = driver.findElement(By.name("costPerClass"));
        WebElement classPerWeek = driver.findElement(By.name("classesPerWeek"));

        courseName.clear();
        courseName.sendKeys(courseNameInput);
        costPerClass.clear();
        costPerClass.sendKeys(costPerClassInput);
        classPerWeek.clear();
        classPerWeek.sendKeys(classPerWeekInput);

        WebElement saveButton = driver.findElement(By.className("MuiButton-containedPrimary"));
        saveButton.click();

        return true;
    }
}

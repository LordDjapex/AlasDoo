package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class TeacherPage extends PageObject {

    @FindBy(className = "MuiDataGrid-row")
    List<WebElement> teachersList;

    @FindBy(xpath = "//*[@id='root']/div/main/div[2]/div/div/div/div[3]/div/div[2]/div/div[2]/button[2]")
    WebElement nextPage;

    public TeacherPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkIndividualTeachers() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        teachersList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));
        String startingUrl = driver.getCurrentUrl();
        List<String> previousUrls = new ArrayList<>();
        for (WebElement teacher : teachersList) {
            teacher.click();
            if (driver.getCurrentUrl().equals(startingUrl) || previousUrls.contains(driver.getCurrentUrl())) {
                return false;
            }
            previousUrls.add(driver.getCurrentUrl());
        }
        if (nextPage.isEnabled()) {
            nextPage.click();
            checkIndividualTeachers();
        }
        return true;
    }

    public boolean deleteAllTeachers() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        teachersList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));
        //keeps updating list
        while (true) {
            teachersList = driver.findElements(By.className("MuiDataGrid-row"));
            teachersList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));
            if (teachersList.isEmpty()) {
                break;
            } else {
                teachersList.get(0).click();
                WebElement deleteButton = driver.findElement(By.className("MuiButton-containedSecondary"));
                deleteButton.click();
            }

        }
        return true;
    }

    public boolean addNewTeacher(String nameInput, String surnameInput, String emailInput) {
        WebElement addButton = driver.findElement(By.className("MuiFab-primary"));
        addButton.click();

        WebElement name = driver.findElement(By.name("teacherName"));
        WebElement surname = driver.findElement(By.name("teacherSurname"));
        WebElement email = driver.findElement(By.name("teacherEmail"));

        name.clear();
        surname.clear();
        email.clear();

        name.sendKeys(nameInput);
        surname.sendKeys(surnameInput);
        email.sendKeys(emailInput);

        WebElement saveButton = driver.findElement(By.className("MuiButton-containedPrimary"));
        saveButton.click();

        return true;
    }

    public boolean updateAllTeachers(String nameInput, String surnameInput, String emailInput) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        teachersList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));

        for (WebElement teacher : teachersList) {
            teacher.click();

            WebElement name = driver.findElement(By.name("teacherName"));
            WebElement surname = driver.findElement(By.name("teacherSurname"));
            WebElement email = driver.findElement(By.name("teacherEmail"));

            name.clear();
            name.sendKeys(nameInput);
            surname.clear();
            surname.sendKeys(surnameInput);
            email.clear();
            email.sendKeys(emailInput);


            WebElement saveButton = wait.until(ExpectedConditions.visibilityOf
                    (driver.findElement(By.xpath("//*[@id='root']/div/main/div[2]/div[2]/form/div[5]/button[1]"))));
            saveButton.click();

            teachersList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));
        }
        if (nextPage.isEnabled()) {
            nextPage.click();
            updateAllTeachers(nameInput, surnameInput, emailInput);
        }
        return true;
    }

    public boolean addNewTeacherCourse(String classesBoughtInput) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        teachersList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));

        if (!teachersList.isEmpty()) {
            teachersList.get(0).click();

            WebElement toggleCourses = driver.findElement(By.xpath("//*[@id='root']/div/main/div[2]/div[2]/form/div[4]/button"));
            toggleCourses.click();

            WebElement addNewCourse = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/main/div[2]/div[3]/div[1]/button")));
            addNewCourse.click();


            WebElement courses = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/main/div[2]/div[3]/div[1]/form/div[1]/div/div")));
            courses.click();

            List<WebElement> courseOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("li")));

            if (!courseOptions.isEmpty()) {
                courseOptions.get(0).click();
                WebElement saveButton = wait.until(ExpectedConditions.visibilityOf(driver.findElement
                        (By.xpath("//*[@id='root']/div/main/div[2]/div[3]/div[1]/form/div[3]/button[1]"))));
                saveButton.click();
            }

        }
        return true;
    }

    public boolean deleteTeacherCourse() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        teachersList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));

        if (!teachersList.isEmpty()) {
            teachersList.get(0).click();

            WebElement toggleCourses = driver.findElement(By.xpath("//*[@id='root']/div/main/div[2]/div[2]/form/div[4]/button"));
            toggleCourses.click();

            WebElement firstCourse = driver.findElement(By.xpath("//*[@id='root']/div/main/div[2]/div[3]/div[2]/div/div[2]/div[2]/div/div/div/div/div/div[1]"));
            firstCourse.click();

            WebElement deleteButton = driver.findElement(By.xpath("//*[@id='root']/div/main/div[2]/div[3]/div[1]/form/div[2]/button[1]"));
            deleteButton.click();

        }
        return true;
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;

import java.util.ArrayList;
import java.util.List;

public class StudentsPage extends PageObject {

    @FindBy(className = "MuiDataGrid-row")
    List<WebElement> studentsList;

    @FindBy(xpath = "//*[@id='root']/div/main/div[2]/div/div/div/div[3]/div/div[2]/div/div[2]/button[2]")
    WebElement nextPage;

    public StudentsPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkIndividualStudents() {
        //backend call takes some time to fetch results that's why wait is used
        WebDriverWait wait = new WebDriverWait(driver, 2);
        studentsList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));
        String startingUrl = driver.getCurrentUrl();
        List<String> previousUrls = new ArrayList<>();
        for (WebElement student : studentsList) {
            student.click();
            if (driver.getCurrentUrl().equals(startingUrl) || previousUrls.contains(driver.getCurrentUrl())) {
                return false;
            }
            previousUrls.add(driver.getCurrentUrl());
        }
        if (nextPage.isEnabled()) {
            nextPage.click();
            checkIndividualStudents();
        }
        return true;
    }

    public boolean deleteAllStudents() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        studentsList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));
        //keeps updating list
        while (true) {
            studentsList = driver.findElements(By.className("MuiDataGrid-row"));
            studentsList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));
            if (studentsList.isEmpty()) {
                break;
            } else {
                studentsList.get(0).click();
                WebElement deleteButton = driver.findElement(By.className("MuiButton-containedSecondary"));
                deleteButton.click();
            }

        }
        return true;
    }

    public boolean addNewStudent(String nameInput, String surnameInput, String accountNameInput, String emailInput, String bankCardNumberInput) {
        WebElement addButton = driver.findElement(By.className("MuiFab-primary"));
        addButton.click();

        WebElement name = driver.findElement(By.name("name"));
        WebElement surname = driver.findElement(By.name("surname"));
        WebElement accountName = driver.findElement(By.name("accountName"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement bankCardNumber = driver.findElement(By.name("bankCardNumber"));

        name.clear();
        surname.clear();
        accountName.clear();
        email.clear();
        bankCardNumber.clear();

        name.sendKeys(nameInput);
        surname.sendKeys(surnameInput);
        accountName.sendKeys(accountNameInput);
        email.sendKeys(emailInput);
        bankCardNumber.sendKeys(bankCardNumberInput);

        WebElement saveButton = driver.findElement(By.className("MuiButton-containedPrimary"));
        saveButton.click();

        return true;
    }

    public boolean updateAllStudents(String nameInput, String surnameInput, String emailInput) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        studentsList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));

        for (WebElement student : studentsList) {
            student.click();

            WebElement name = driver.findElement(By.name("name"));
            WebElement surname = driver.findElement(By.name("surname"));
            WebElement email = driver.findElement(By.name("email"));

            name.clear();
            name.sendKeys(nameInput);
            surname.clear();
            surname.sendKeys(surnameInput);
            email.clear();
            email.sendKeys(emailInput);

           WebElement saveButton = wait.until(ExpectedConditions.visibilityOf
                   (driver.findElement(By.xpath("//*[@id='root']/div/main/div[2]/div[2]/form/div[7]/button[1]"))));
            saveButton.click();

            studentsList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));
        }
        if (nextPage.isEnabled()) {
            nextPage.click();
            updateAllStudents(nameInput, surnameInput, emailInput);
        }
        return true;
    }


    public boolean addNewStudentCourse(String classesBoughtInput) {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        studentsList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));

        if (!studentsList.isEmpty()) {
            studentsList.get(0).click();

            WebElement toggleCourses = driver.findElement(By.xpath("//*[@id='root']/div/main/div[2]/div[2]/form/div[6]/button"));
            toggleCourses.click();


            WebElement addNewCourse = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/main/div[2]/div[3]/div[1]/button")));
            addNewCourse.click();

            WebElement courses = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("formik-select-field-32")));
            courses.click();

            List<WebElement> courseOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("li")));

            if (!courseOptions.isEmpty()) {
                courseOptions.get(0).click();
                WebElement classesBought = driver.findElement(By.id("formik-text-field-34"));
                classesBought.clear();
                classesBought.sendKeys(classesBoughtInput);
                WebElement saveButton = wait.until(ExpectedConditions.visibilityOf(driver.findElement
                        (By.xpath("//*[@id='root']/div/main/div[2]/div[3]/div[1]/form/div[3]/button[1]"))));
                saveButton.click();
            }

        }
        return true;
    }

    public boolean deleteStudentCourse() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        studentsList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("MuiDataGrid-row")));

        if (!studentsList.isEmpty()) {
            studentsList.get(0).click();

            WebElement toggleCourses = driver.findElement(By.xpath("//*[@id='root']/div/main/div[2]/div[2]/form/div[6]/button"));
            toggleCourses.click();

            WebElement firstCourse = driver.findElement(By.xpath("//*[@id='root']/div/main/div[2]/div[3]/div[2]/div/div[2]/div[2]/div/div/div/div/div/div[1]"));
            firstCourse.click();

            WebElement deleteButton = driver.findElement(By.xpath("//*[@id='root']/div/main/div[2]/div[3]/div[1]/form/div[3]/button[3]"));
            deleteButton.click();

        }
        return true;
    }
}

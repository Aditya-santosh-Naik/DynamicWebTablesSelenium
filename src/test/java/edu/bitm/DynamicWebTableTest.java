package edu.bitm;

import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DynamicWebTableTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(); // Selenium Manager resolves the matching driver
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        Path page = Path.of("src", "test", "resources", "dynamic-table.html").toAbsolutePath();
        driver.get(page.toUri().toString());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    private List<WebElement> rows() {
        return driver.findElements(By.cssSelector("#tableBody tr"));
    }

    @Test
    public void verifyInitialTableRows() {
        Assert.assertEquals(rows().size(), 3, "Table should initially contain three employees");
        Assert.assertEquals(driver.findElement(By.cssSelector("#tableBody tr:nth-child(1) td:nth-child(2)")).getText(), "Asha");
    }

    @Test
    public void addDynamicRow() {
        driver.findElement(By.id("name")).sendKeys("Kiran");
        driver.findElement(By.id("role")).sendKeys("QA Engineer");
        driver.findElement(By.id("addBtn")).click();
        wait.until(d -> rows().size() == 4);
        Assert.assertEquals(rows().get(3).findElements(By.tagName("td")).get(1).getText(), "Kiran");
        Assert.assertEquals(rows().get(3).findElements(By.tagName("td")).get(2).getText(), "QA Engineer");
    }

    @Test
    public void searchFiltersTableRows() {
        driver.findElement(By.id("search")).sendKeys("Ravi");
        wait.until(d -> rows().get(0).getCssValue("display").equals("none")
                && rows().get(1).getCssValue("display").equals("table-row"));
        Assert.assertEquals(rows().get(1).getCssValue("display"), "table-row");
        Assert.assertEquals(rows().get(0).getCssValue("display"), "none");
    }

    @Test
    public void deleteDynamicRow() {
        driver.findElement(By.cssSelector("#tableBody tr:first-child button:nth-of-type(2)")).click();
        wait.until(d -> rows().size() == 2);
        Assert.assertEquals(rows().size(), 2, "Deleting first row should leave two rows");
    }

    @Test
    public void validateRequiredFields() {
        driver.findElement(By.id("addBtn")).click();
        Assert.assertEquals(driver.findElement(By.id("status")).getText(), "Name and role are required");
        Assert.assertEquals(rows().size(), 3, "Invalid data must not add a row");
    }
}

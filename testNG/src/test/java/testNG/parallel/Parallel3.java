package testNG.parallel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Parallel3 {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void openBrowser() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://www.letskodeit.com/practice");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void alertPopUp() throws Exception {
        WebElement searchBox =driver.findElement(By.name("enter-name"));
        searchBox.sendKeys("XYZ");
        Thread.sleep(1000);
        WebElement alertButton = driver.findElement(By.id("alertbtn"));
        alertButton.click();
        Thread.sleep(1000);
        Alert acc = driver.switchTo().alert();
        acc.accept();
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}

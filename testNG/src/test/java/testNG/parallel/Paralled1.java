package testNG.parallel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Paralled1 {
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
    public void checkbox() throws Exception {
        WebElement bmw = driver.findElement(By.id("bmwcheck"));
        bmw.click();
        WebElement benz = driver.findElement(By.id("benzcheck"));
        benz.click();
        WebElement honda = driver.findElement(By.id("hondacheck"));
        honda.click();
        String actual = benz.getText();
        System.out.println("Clicked on benz " + actual);
        Thread.sleep(1000);
        String a1 = bmw.getText();
        System.out.println("Clicked on bmw " + a1);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

package testNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Report {
    WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://www.letskodeit.com/practice");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    @Test()
    public void checkbox() throws InterruptedException {
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

    @Test()
    public void mouseHover() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
        Thread.sleep(1000);
        WebElement mouseHover = driver.findElement(By.xpath("//div[@class='mouse-hover']"));
        String m1 = mouseHover.getText();
        Actions act = new Actions(driver);
        act.moveToElement(mouseHover).build().perform();
        Thread.sleep(1000);
        WebElement reload = driver.findElement(By.xpath("//div[@class='mouse-hover']/div/a[2]"));
        reload.click();
        Thread.sleep(1000);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}


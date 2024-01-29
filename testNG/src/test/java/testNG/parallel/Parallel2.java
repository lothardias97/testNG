package testNG.parallel;

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

public class Parallel2 {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void openBrowser() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://jqueryui.com/resizable/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }
    @Test
    public void reSizeable() throws InterruptedException {
        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,200)");
        WebElement iframe = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(iframe);
        WebElement reSize = driver.findElement(By.xpath("//div[@id='resizable']"));
        Actions act = new Actions(driver);
        act.moveByOffset(0,450).build().perform();
        Thread.sleep(1000);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

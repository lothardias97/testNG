package testNG.Grouping;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Grouping {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void openBrowser() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("https://www.letskodeit.com/practice");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    @Test(groups = "Let'sKodeIt--1")

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

    @Test(groups = "Let'sKodeIt--1")
    public void multiSelect() throws InterruptedException {
        WebElement box = driver.findElement(By.id("multiple-select-example"));
        Select sel = new Select(box);
        sel.selectByIndex(0);
        sel.selectByValue("orange");
        sel.selectByVisibleText("Peach");
        Thread.sleep(1000);
        List<WebElement> fruits = sel.getOptions();
        int fruit = fruits.size();
        System.out.println("The size of the fruits box is " + fruit);
        for (int i = 0; i < fruit; i++) {
            String ListFruits = fruits.get(i).getText();
            System.out.println("Here are the list of fruits " + ListFruits);
        }
    }
    @Test(groups = "Let'sKodeIt--1")
    public void hide() throws InterruptedException {
        WebElement show = driver.findElement(By.id("show-textbox"));
        show.click();
        Thread.sleep(1000);
        show.sendKeys("abc");
        Thread.sleep(1000);
        Assert.assertSame(show, show);
        System.out.println("Here is correct show button " + show);
    }
    @Test(groups = "Let'sKdeIt2")
    public void mouseHover() throws Exception {
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
    @Test(groups = "Let'sKdeIt2")
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

package testNG;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DependsonMethod {
            WebDriver driver;

        @BeforeMethod
        public void openBrowser() {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.get("https://www.letskodeit.com/practice");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        }

        @Test(dependsOnMethods = "multiSelect")
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

        @Test
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
        @Test(dependsOnMethods = "checkbox")
        public void hide() throws InterruptedException {
            WebElement show = driver.findElement(By.id("show-textbox"));
            show.click();
            Thread.sleep(1000);
            show.sendKeys("abc");
            Thread.sleep(1000);
            Assert.assertSame(show,show);
            System.out.println("Here is correct show button " + show);
        }

        @AfterMethod
        public void tearDown() {
            driver.quit();
        }

}

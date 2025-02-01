package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class S_15_Handle_Window_Tab {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Tab() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        String pageID = driver.getWindowHandle();
        String pageTitle = driver.getTitle();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//legend[text()='Window']")));
        sleepForSeconds(1);

        driver.findElement(By.xpath("//legend[text()='Window']/following-sibling::a[text()='GOOGLE']")).click();

        switchToOtherWindowByCurrentID(pageID);
        sleepForSeconds(1);

        String googleTitle = driver.getTitle();

        Assert.assertEquals(googleTitle, "Google");

        switchToWindowByTitle(pageTitle);
        sleepForSeconds(1);

        Assert.assertEquals(pageTitle, "Selenium WebDriver");

        driver.findElement(By.xpath("//legend[text()='Window']/following-sibling::a[text()='FACEBOOK']")).click();

        switchToOtherWindowByCurrentID(pageID);
        sleepForSeconds(1);

        String facebookTitle = driver.getTitle();

        Assert.assertEquals(facebookTitle, "Facebook – log in or sign up");

        closeAllWindowsExceptWindowByID(pageID);
        sleepForSeconds(1);
    }

    @Test
    public void TC_02_Window() {
        driver.get("http://live.techpanda.org/index.php/mobile.html");

        String pageID = driver.getWindowHandle();
        String pageTitle = driver.getTitle();

        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div//a[text()='Add to Compare']")).click();
        sleepForSeconds(1);

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div//a[text()='Add to Compare']")).click();
        sleepForSeconds(1);

        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div//a[text()='Add to Compare']")).click();
        sleepForSeconds(1);

        driver.findElement(By.xpath("//button[@title='Compare']")).click();

        switchToOtherWindowByCurrentID(pageID);

        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'page-title')]/h1[text()='Compare Products']")).isDisplayed());

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='IPhone']")).isDisplayed());

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Samsung Galaxy']")).isDisplayed());

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Sony Xperia']")).isDisplayed());

        driver.findElement(By.xpath("//button[@title='Close Window']"));

        switchToWindowByTitle(pageTitle);

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        sleepForSeconds(1);

        driver.switchTo().alert().accept();
        sleepForSeconds(1);

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToOtherWindowByCurrentID(String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(String windowTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(windowTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowsExceptWindowByID(String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(windowID);
    }

}

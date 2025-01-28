package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_12_Handle_Popup_Dialog {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Fixed_Not_In_DOM() {
        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.xpath("//header//button[text()='Đăng nhập']")).click();
        sleepForSeconds(1);

        By dialogBy = By.xpath("//div[@role='dialog']");

        Assert.assertTrue(driver.findElement(dialogBy).isDisplayed());

        driver.findElement(By.xpath("//div[@role='dialog']//button[contains(@class,'close')]")).click();
        sleepForSeconds(1);

        Assert.assertEquals(driver.findElements(dialogBy).size(), 0);
    }

    @Test
    public void TC_02_Random_In_DOM() {
        driver.get("https://dehieu.vn/");
        sleepForSeconds(5);

        WebElement popup = driver.findElement(By.xpath("//div[@id='modalPopupForm']/div"));

        if (popup.isDisplayed()) {
            System.out.println("Popup is displayed");
            driver.findElement(By.xpath("//div[@id='modalPopupForm']//button[@class='close']")).click();
            sleepForSeconds(1);
        }

        Assert.assertFalse(popup.isDisplayed());
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

}

package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_22_Wait_Page_Ready {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://admin-demo.nopcommerce.com/login");
    }

    @Test
    public void TC_01_Wait_Page_Ready() {
        driver.findElement(By.xpath("//button[contains(@class,'login-button')]")).click();

        waitForPageReady();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='content-header']/h1")).isDisplayed());

        driver.findElement(By.xpath("//a[@class='nav-link' and text()='Logout']")).click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void waitForPageReady() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(30)).until(driver -> (Boolean) ((JavascriptExecutor) driver).executeScript("return (typeof jQuery === 'undefined' || (jQuery.active === 0)) && document.readyState === 'complete';"));
        } catch (TimeoutException e) {
            throw new RuntimeException("Page load timeout exceeded!", e);
        }
    }

}

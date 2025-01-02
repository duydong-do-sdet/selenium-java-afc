package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_02_Selenium_Manager {
    WebDriver driver;

    @Test
    public void TC_01_Firefox() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
        Assert.assertTrue(driver.findElement(By.cssSelector("img.fb_logo")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());

        driver.quit();
    }

    @Test
    public void TC_02_Chrome() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
        Assert.assertTrue(driver.findElement(By.cssSelector("img.fb_logo")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());

        driver.quit();
    }

    @Test
    public void TC_03_Edge() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
        Assert.assertTrue(driver.findElement(By.cssSelector("img.fb_logo")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());

        driver.quit();
    }

}

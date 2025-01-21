package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_08_Handle_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.fahasa.com/customer/account/create");
    }

    @Test
    public void TC_01_Handle_Button() {
        driver.findElement(By.xpath("//li[contains(@class,'popup-login-tab-login')]/a")).click();
        sleepForSeconds(1);

        WebElement loginBtn = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));

        Assert.assertFalse(loginBtn.isEnabled());

        driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("dongafc@gmail.com");
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("SeJava@4");
        sleepForSeconds(1);

        Assert.assertTrue(loginBtn.isEnabled());

        String loginBtnHexColor = Color.fromString(loginBtn.getCssValue("background-color")).asHex().toUpperCase();
        Assert.assertEquals(loginBtnHexColor, "#C92127");
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

package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_14_Handle_Frame_IFrame {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Frame() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));

        driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("1310");

        driver.findElement(By.xpath("//a[contains(@class,'login-btn')]")).click();
        sleepForSeconds(2);

        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("SeJava@4");

        driver.findElement(By.xpath("//a[@id='loginBtn']")).click();
        sleepForSeconds(1);

        Assert.assertEquals(driver.findElement(By.xpath("//p[contains(@class,'error-msg')]")).getText(), "Customer ID/IPIN (Password) is invalid. Please try again.");
    }

    @Test
    public void TC_02_IFrame() {
        driver.get("https://toidicodedao.com/");

        WebElement fbIFrame = driver.findElement(By.xpath("//iframe[contains(@title,'fb:page')]"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fbIFrame);
        sleepForSeconds(1);

        driver.switchTo().frame(fbIFrame);

        System.out.println(driver.findElement(By.xpath("//a[@title='Tôi đi code dạo']/parent::div/following-sibling::div[contains(text(),'followers')]")).getText());

        driver.switchTo().defaultContent();

        WebElement searchTextbox = driver.findElement(By.xpath("//div[@id='content-sidebar']//input[@class='search-field']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchTextbox);
        sleepForSeconds(1);

        searchTextbox.sendKeys("Selenium Java");
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

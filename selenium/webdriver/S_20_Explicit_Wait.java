package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_20_Explicit_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_ByLocator_Parameter() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Start']"))).click();

        explicitWait.until(ExpectedConditions.textToBe(By.xpath("//div[@id='loading']"), "Loading..."));

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));

        explicitWait.until(ExpectedConditions.textToBe(By.xpath("//div[@id='finish']/h4"), "Hello World!"));
    }

    @Test
    public void TC_02_WebElement_Parameter() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[text()='Start']")))).click();

        explicitWait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@id='finish']/h4")), "Hello World!"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}

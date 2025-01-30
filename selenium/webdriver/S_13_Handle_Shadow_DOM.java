package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_13_Handle_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://automationfc.github.io/shadow-dom/");
    }

    @Test
    public void TC_01_Shadow_DOM() {
        SearchContext shadowHost = driver.findElement(By.cssSelector("div#shadow_host")).getShadowRoot();

        String shadowContent = shadowHost.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(shadowContent);

        SearchContext shadowNested = shadowHost.findElement(By.cssSelector("div#nested_shadow_host")).getShadowRoot();

        String nestedContent = shadowNested.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        System.out.println(nestedContent);

        shadowHost.findElement(By.cssSelector("input[type='text']")).sendKeys("Shadow DOM");

        WebElement checkbox = shadowHost.findElement(By.cssSelector("input[type='checkbox']"));
        Assert.assertFalse(checkbox.isSelected());
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}

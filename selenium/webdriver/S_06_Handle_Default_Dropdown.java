package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class S_06_Handle_Default_Dropdown {
    WebDriver driver;
    Select select;
    String country, city;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://rode.com/en/support/where-to-buy");

        country = "Vietnam";
        city = "Da Nang";
    }

    @Test
    public void TC_01_Select() {
        select = new Select(driver.findElement(By.xpath("//select[@id='country']")));

        Assert.assertFalse(select.isMultiple());

        select.selectByVisibleText(country);

        driver.findElement(By.xpath("//input[@id='map_search_query']")).sendKeys(city);

        driver.findElement(By.xpath("//button[text()='Search']")).click();
    }

    @Test
    public void TC_02_Verify() {
        Assert.assertEquals(select.getFirstSelectedOption().getText(), country);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//h3[text()='Dealers']")));

        List<WebElement> addressElements = driver.findElements(By.xpath("//h3[text()='Dealers']/parent::div//h4/following-sibling::div[1]"));

        for (WebElement addressElement : addressElements) {
            String address = addressElement.getText();
            Assert.assertTrue(address.contains(country));
            Assert.assertTrue(address.contains(city));
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}

package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_03_Selenium_Locators {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_By_Id() {
        driver.findElement(By.id("email"));
    }

    @Test
    public void TC_02_By_Class() {
        driver.findElement(By.className("fb_logo"));
    }

    @Test
    public void TC_03_By_Name() {
        driver.findElement(By.name("login"));
    }

    @Test
    public void TC_04_By_Tag_Name() {
        driver.findElements(By.tagName("input"));
    }

    @Test
    public void TC_05_By_Link_Text() {
        driver.findElement(By.linkText("Tiếng Việt"));
    }

    @Test
    public void TC_06_By_Partial_Link_Text() {
        driver.findElement(By.partialLinkText("Việt"));
    }

    @Test
    public void TC_07_By_CSS() {
        driver.findElement(By.cssSelector("input[id='email']"));
        driver.findElement(By.cssSelector("input#email"));
        driver.findElement(By.cssSelector("#email"));

        driver.findElement(By.cssSelector("img[class='fb_logo _8ilh img']"));
        driver.findElement(By.cssSelector("img.fb_logo"));
        driver.findElement(By.cssSelector(".fb_logo"));

        driver.findElement(By.cssSelector("button[name='login']"));

        driver.findElements(By.cssSelector("input"));

        driver.findElement(By.cssSelector("a[title='Vietnamese']"));
    }

    @Test
    public void TC_08_By_XPath() {
        driver.findElement(By.xpath("//input[@id='email']"));

        driver.findElement(By.xpath("//img[@class='fb_logo _8ilh img']"));

        driver.findElement(By.xpath("//button[@name='login']"));

        driver.findElements(By.xpath("//input"));

        driver.findElement(By.xpath("//a[@title='Vietnamese']"));

        driver.findElement(By.xpath("//a[text()='Tiếng Việt']"));
    }

    @Test
    public void TC_09_Relative_Locators() {
        By englishBy = By.xpath("//li[text()='English (UK)']");
        By signUpBy = By.xpath("//a[text()='Sign Up']");
        By messengerBy = By.xpath("//a[text()='Messenger']");
        By threadsBy = By.xpath("//a[text()='Threads']");
        By fundraisersBy = By.xpath("//a[text()='Fundraisers']");

        By loginBy = RelativeLocator.with(By.xpath("//a")).below(englishBy).above(threadsBy).toRightOf(signUpBy).toLeftOf(messengerBy).near(fundraisersBy);
        System.out.println(driver.findElement(loginBy).getText());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}

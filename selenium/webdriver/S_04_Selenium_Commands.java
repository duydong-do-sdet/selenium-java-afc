package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class S_04_Selenium_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("");
    }

    @Test
    public void TC_01_WebDriver_Commands() {
        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver = new EdgeDriver();
        driver = new InternetExplorerDriver();
        driver = new SafariDriver();

        driver.get("");
        driver.close();
        driver.quit();

        driver.getCurrentUrl();
        driver.getTitle();
        driver.getPageSource();
        driver.getWindowHandle();
        driver.getWindowHandles();

        driver.manage().getCookies();
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driver.manage().window().maximize();
        driver.manage().window().fullscreen();
        driver.manage().window().getPosition();
        driver.manage().window().getSize();

        driver.navigate().to("");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        driver.switchTo().window("");
        driver.switchTo().alert();
        driver.switchTo().frame("");
    }

    @Test
    public void TC_02_WebElement_Commands() {
        WebElement element = driver.findElement(By.xpath(""));
        element.click();
        element.clear();
        element.sendKeys("");

        element.getText();
        element.getCssValue("");
        element.getDomAttribute("");
        element.getDomProperty("");
        element.getTagName();

        element.getLocation();
        element.getSize();
        element.getRect();
        element.getShadowRoot();

        element.getScreenshotAs(OutputType.BASE64);
        element.getScreenshotAs(OutputType.FILE);
        element.getScreenshotAs(OutputType.BYTES);

        element.isDisplayed();
        element.isEnabled();
        element.isSelected();

        element.submit();

        List<WebElement> elements = driver.findElements(By.xpath(""));
        elements.size();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}

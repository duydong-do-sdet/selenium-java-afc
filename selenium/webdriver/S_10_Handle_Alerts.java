package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_10_Handle_Alerts {
    WebDriver driver;
    Alert alert;
    By resultBy = By.xpath("//p[@id='result']");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
        sleepForSeconds(1);

        alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        alert.accept();
        sleepForSeconds(1);

        Assert.assertEquals(driver.findElement(resultBy).getText(), "You successfully clicked an alert");
    }

    @Test
    public void TC_02_Confirm_Alert() {
        WebElement jsConfirmBtn = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));

        jsConfirmBtn.click();
        sleepForSeconds(1);

        alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        alert.dismiss();
        sleepForSeconds(1);

        Assert.assertEquals(driver.findElement(resultBy).getText(), "You clicked: Cancel");

        jsConfirmBtn.click();
        sleepForSeconds(1);

        alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        alert.accept();
        sleepForSeconds(1);

        Assert.assertEquals(driver.findElement(resultBy).getText(), "You clicked: Ok");
    }

    @Test
    public void TC_03_Prompt_Alert() {
        String keysToSend = "Selenium Java";
        WebElement jsPromptBtn = driver.findElement(By.xpath("//button[@onclick='jsPrompt()']"));

        jsPromptBtn.click();
        sleepForSeconds(1);

        alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        alert.sendKeys(keysToSend);
        alert.dismiss();
        sleepForSeconds(1);

        Assert.assertEquals(driver.findElement(resultBy).getText(), "You entered: null");

        jsPromptBtn.click();
        sleepForSeconds(1);

        alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        alert.sendKeys(keysToSend);
        alert.accept();
        sleepForSeconds(1);

        Assert.assertEquals(driver.findElement(resultBy).getText(), "You entered: " + keysToSend);
    }

    @Test
    public void TC_04_Authentication_Alert() {
        driver.get("https://the-internet.herokuapp.com/");

        String userName = "admin", password = "admin";

        String basicAuthUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getDomProperty("href");

        String userBasicAuthUrl = getUserBasicAuthUrl(basicAuthUrl, userName, password);

        System.out.println(userBasicAuthUrl);

        driver.get(userBasicAuthUrl);

        Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Basic Auth']/following-sibling::p")).getText(), "Congratulations! You must have the proper credentials.");
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

    public String getUserBasicAuthUrl(String basicAuthUrl, String userName, String password) {
        String[] urlArr = basicAuthUrl.split("//");
        return basicAuthUrl = urlArr[0] + "//" + userName + ":" + password + "@" + urlArr[1];
    }

}

package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class S_05_Handle_Textbox_Textarea {
    WebDriver driver;
    String firstName, lastName, fullName, emailAddress, password;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://live.techpanda.org/");

        firstName = "Dong";
        lastName = "Do";
        fullName = firstName + " " + lastName;
        emailAddress = "dongafc" + getRandomNumber() + "@gmail.com";
        password = "SeJava@4";
    }

    @Test
    public void TC_01_Register() {
        driver.findElement(By.xpath("//header//span[text()='Account']")).click();

        driver.findElement(By.xpath("//div[@id='header-account']//a[text()='Register']")).click();

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);

        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);

        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);

        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);

        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);

        driver.findElement(By.xpath("//button[@title='Register']")).click();

        driver.switchTo().alert().accept();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "Thank you for registering with Main Website Store.");

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + fullName + "!");

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(emailAddress));
    }

    @Test
    public void TC_02_Contact_Us() {
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='Contact Us']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='name']")).getDomProperty("value"), fullName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']")).getDomProperty("value"), emailAddress);

        driver.findElement(By.xpath("//input[@id='name']")).clear();
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Joey Joestar");

        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("jojo.afc@gmail.com");

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='name']")).getDomProperty("value"), "Joey Joestar");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']")).getDomProperty("value"), "jojo.afc@gmail.com");

        driver.findElement(By.xpath("//textarea[@id='comment']")).sendKeys("Selenium\nJava\nAFC");
        Assert.assertEquals(driver.findElement(By.xpath("//textarea[@id='comment']")).getDomProperty("value"), "Selenium\nJava\nAFC");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int getRandomNumber() {
        return new Random().nextInt(10000);
    }

}

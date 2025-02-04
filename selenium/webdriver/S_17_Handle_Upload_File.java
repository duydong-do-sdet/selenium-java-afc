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

import java.io.File;
import java.time.Duration;
import java.util.List;

public class S_17_Handle_Upload_File {
    WebDriver driver;
    String uploadFilesPath = System.getProperty("user.dir") + File.separator + "uploadFiles";

    String javaFile = "java.jpg";
    String csharpFile = "csharp.jpg";
    String javascriptFile = "javascript.jpg";
    String rubyFile = "ruby.jpg";
    String pythonFile = "python.jpg";

    String javaFilePath = uploadFilesPath + File.separator + javaFile;
    String csharpFilePath = uploadFilesPath + File.separator + csharpFile;
    String javascriptFilePath = uploadFilesPath + File.separator + javascriptFile;
    String rubyFilePath = uploadFilesPath + File.separator + rubyFile;
    String pythonFilePath = uploadFilesPath + File.separator + pythonFile;

    String multipleFilesPaths = javaFilePath + "\n" + csharpFilePath + "\n" + javascriptFilePath + "\n" + rubyFilePath + "\n" + pythonFilePath;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
    }

    @Test
    public void TC_01_Single_File() {
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(javaFilePath);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + javaFile + "']")).isDisplayed());

        driver.findElement(By.xpath("//td//button[contains(@class,'start')]")).click();
        sleepForSeconds(2);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + javaFile + "']")).isDisplayed());

        Assert.assertTrue(isImageDisplayedByJS("//a[@title='" + javaFile + "']/img"));

        driver.findElement(By.xpath("//td//button[contains(@class,'delete')]")).click();
        sleepForSeconds(2);
    }

    @Test
    public void TC_02_Multiple_Files() {
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(multipleFilesPaths);

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + javaFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + csharpFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + javascriptFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + rubyFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + pythonFile + "']")).isDisplayed());

        List<WebElement> startBtns = driver.findElements(By.xpath("//td//button[contains(@class,'start')]"));
        for (WebElement btn : startBtns) {
            btn.click();
            sleepForSeconds(2);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + javaFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + csharpFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + javascriptFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + rubyFile + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + pythonFile + "']")).isDisplayed());

        Assert.assertTrue(isImageDisplayedByJS("//a[@title='" + javaFile + "']/img"));
        Assert.assertTrue(isImageDisplayedByJS("//a[@title='" + csharpFile + "']/img"));
        Assert.assertTrue(isImageDisplayedByJS("//a[@title='" + javascriptFile + "']/img"));
        Assert.assertTrue(isImageDisplayedByJS("//a[@title='" + rubyFile + "']/img"));
        Assert.assertTrue(isImageDisplayedByJS("//a[@title='" + pythonFile + "']/img"));

        List<WebElement> deleteBtns = driver.findElements(By.xpath("//td//button[contains(@class,'delete')]"));
        for (WebElement btn : deleteBtns) {
            btn.click();
            sleepForSeconds(2);
        }
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

    public WebElement getWebElement(String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));
    }

    public boolean isImageDisplayedByJS(String xpathLocator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0;", getWebElement(xpathLocator));
    }

}

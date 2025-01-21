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
import java.util.List;

public class S_09_Handle_Checkbox_RadioButton {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Default_Checkbox_RadioButton() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        // Checkbox
        Assert.assertFalse(isDefaultCheckboxOrRadioButtonSelected("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input[@type='checkbox']"));

        checkDefaultCheckboxOrRadioButton("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input[@type='checkbox']");

        Assert.assertTrue(isDefaultCheckboxOrRadioButtonSelected("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input[@type='checkbox']"));

        uncheckDefaultCheckbox("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input[@type='checkbox']");

        Assert.assertFalse(isDefaultCheckboxOrRadioButtonSelected("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input[@type='checkbox']"));

        // RadioButton
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        Assert.assertTrue(isDefaultCheckboxOrRadioButtonSelected("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::span/input[@type='radio']"));

        checkDefaultCheckboxOrRadioButton("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::span/input[@type='radio']");

        Assert.assertFalse(isDefaultCheckboxOrRadioButtonSelected("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::span/input[@type='radio']"));

        Assert.assertTrue(isDefaultCheckboxOrRadioButtonSelected("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::span/input[@type='radio']"));
    }

    @Test
    public void TC_02_Custom_Checkbox_RadioButton() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        // RadioButton
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Đâu là thủ đô của Việt Nam']")));
        sleepForSeconds(1);

        WebElement hanoiRadioBtn = driver.findElement(By.xpath("//div[@aria-label='Hà Nội' and @role='radio']"));

        Assert.assertEquals(hanoiRadioBtn.getDomAttribute("aria-checked"), "false");

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", hanoiRadioBtn);
        sleepForSeconds(1);

        Assert.assertEquals(hanoiRadioBtn.getDomAttribute("aria-checked"), "true");

        // Checkbox
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Hãy chọn các tỉnh bắt đầu vs tiền tố là Quảng']")));
        sleepForSeconds(1);

        List<WebElement> quangCheckboxes = driver.findElements(By.xpath("//div[starts-with(@aria-label,'Quảng') and @role='checkbox']"));

        for (WebElement checkbox : quangCheckboxes) {
            Assert.assertEquals(checkbox.getDomAttribute("aria-checked"), "false");
        }

        for (WebElement checkbox : quangCheckboxes) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
            sleepForSeconds(1);
        }

        for (WebElement checkbox : quangCheckboxes) {
            Assert.assertEquals(checkbox.getDomAttribute("aria-checked"), "true");
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

    public void checkDefaultCheckboxOrRadioButton(String xpathLocator) {
        WebElement element = driver.findElement(By.xpath(xpathLocator));
        if (!element.isSelected() && element.isEnabled()) {
            element.click();
            sleepForSeconds(1);
        }
    }

    public void uncheckDefaultCheckbox(String xpathLocator) {
        WebElement element = driver.findElement(By.xpath(xpathLocator));
        if (element.isSelected() && element.isEnabled()) {
            element.click();
            sleepForSeconds(1);
        }
    }

    public boolean isDefaultCheckboxOrRadioButtonSelected(String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator)).isSelected();
    }

}

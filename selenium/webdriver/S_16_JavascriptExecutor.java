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
import java.util.Random;

public class S_16_JavascriptExecutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    String emailAddress;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        jsExecutor = (JavascriptExecutor) driver;
        emailAddress = "dongafc" + getRandomNumber() + "@gmail.com";
    }

    @Test
    public void TC_01() {
        navigateToUrlByJS("https://live.techpanda.org/index.php/mobile.html");
        sleepForSeconds(2);

        Assert.assertEquals(executeJS("return document.URL;"), "https://live.techpanda.org/index.php/mobile.html");
        Assert.assertEquals(executeJS("return document.title;"), "Mobile");

        highlightElementByJS("//a[@title='IPhone']/img");
        Assert.assertTrue(isImageDisplayedByJS("//a[@title='IPhone']/img"));

        scrollElementIntoViewTopByJS("//a[text()='About Us']");
        highlightElementByJS("//a[text()='About Us']");
        clickElementByJS("//a[text()='About Us']");
        sleepForSeconds(2);

        scrollElementIntoViewBottomByJS("//input[@id='newsletter']");
        highlightElementByJS("//input[@id='newsletter']");

        sendKeysToElementByJS("//input[@id='newsletter']", emailAddress);
        sleepForSeconds(1);

        removeElementAttributeByJS("//input[@id='newsletter']", "value");
        sleepForSeconds(1);

        setElementAttributeByJS("//input[@id='newsletter']", "value", emailAddress);
        sleepForSeconds(1);

        Assert.assertEquals(getElementAttributeByJS("//input[@id='newsletter']", "value"), emailAddress);

        highlightElementByJS("//button[@title='Subscribe']");
        clickElementByJS("//button[@title='Subscribe']");

        driver.switchTo().alert().accept();
        sleepForSeconds(2);

        highlightElementByJS("//li[@class='success-msg']//span");
        Assert.assertEquals(getElementTextByJS("//li[@class='success-msg']//span"), "Thank you for your subscription.");
        Assert.assertTrue(getInnerTextByJS().contains("Thank you for your subscription."));
        Assert.assertTrue(isExpectedTextPresentByJS("Thank you for your subscription."));

        scrollToBottomPageByJS();
        sleepForSeconds(1);

        scrollToTopPageByJS();
        sleepForSeconds(1);
    }

    @Test
    public void TC_02() {
        navigateToUrlByJS("https://warranty.rode.com/login");
        sleepForSeconds(2);

        highlightElementByJS("//button[@type='submit']");
        clickElementByJS("//button[@type='submit']");
        sleepForSeconds(1);

        Assert.assertEquals(getElementValidationMessageByJS("//input[@id='email']"), "Please fill out this field.");

        highlightElementByJS("//input[@id='email']");
        sendKeysToElementByJS("//input[@id='email']", emailAddress);

        highlightElementByJS("//button[@type='submit']");
        clickElementByJS("//button[@type='submit']");
        sleepForSeconds(1);

        Assert.assertEquals(getElementValidationMessageByJS("//input[@id='password']"), "Please fill out this field.");
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

    public int getRandomNumber() {
        return new Random().nextInt(10000);
    }

    public WebElement getWebElement(String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));
    }

    public Object executeJS(String javascript) {
        return jsExecutor.executeScript(javascript);
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "';");
    }

    public String getInnerTextByJS() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextPresentByJS(String expectedText) {
        return jsExecutor.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0];").equals(expectedText);
    }

    public void scrollToTopPageByJS() {
        jsExecutor.executeScript("window.scrollBy(0,-document.body.scrollHeight);");
    }

    public void scrollToBottomPageByJS() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight);");
    }

    public void scrollElementIntoViewTopByJS(String xpathLocator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(xpathLocator));
    }

    public void scrollElementIntoViewBottomByJS(String xpathLocator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(xpathLocator));
    }

    public void clickElementByJS(String xpathLocator) {
        jsExecutor.executeScript("arguments[0].click();", getWebElement(xpathLocator));
    }

    public void sendKeysToElementByJS(String xpathLocator, String keysToSend) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + keysToSend + "');", getWebElement(xpathLocator));
    }

    public String getElementTextByJS(String xpathLocator) {
        return (String) jsExecutor.executeScript("return arguments[0].innerText;", getWebElement(xpathLocator));
    }

    public String getElementValidationMessageByJS(String xpathLocator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(xpathLocator));
    }

    public String getElementAttributeByJS(String xpathLocator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(xpathLocator));
    }

    public void setElementAttributeByJS(String xpathLocator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getWebElement(xpathLocator));
    }

    public void removeElementAttributeByJS(String xpathLocator, String attributeName) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeName + "');", getWebElement(xpathLocator));
    }

    public void highlightElementByJS(String xpathLocator) {
        WebElement element = getWebElement(xpathLocator);
        String originalStyle = element.getDomAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red; border-style: dashed;');", element);
        sleepForSeconds(1);
        jsExecutor.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
    }

    public boolean isImageDisplayedByJS(String xpathLocator) {
        return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0;", getWebElement(xpathLocator));
    }

}

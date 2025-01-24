package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class S_11_User_Interactions {
    WebDriver driver;
    Actions action;
    Keys cmdCtrl;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        action = new Actions(driver);
    }

    @Test
    public void TC_01_Moves_The_Mouse() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).pause(Duration.ofSeconds(1)).perform();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Click_And_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> selectableNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

        action.clickAndHold(selectableNumbers.get(12)).pause(Duration.ofSeconds(1)).moveToElement(selectableNumbers.get(9)).pause(Duration.ofSeconds(1)).release().perform();

        List<WebElement> selectedNumbers = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));

        Assert.assertEquals(selectedNumbers.size(), 4);
    }

    @Test
    public void TC_03_Click_Select_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;

        List<WebElement> selectableNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

        action.keyDown(cmdCtrl).click(selectableNumbers.get(12)).pause(Duration.ofSeconds(1)).click(selectableNumbers.get(9)).keyUp(cmdCtrl).perform();

        List<WebElement> selectedNumbers = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));

        Assert.assertEquals(selectedNumbers.size(), 2);
    }

    @Test
    public void TC_04_Double_Click() {
        driver.get("https://qa-practice.netlify.app/double-click");

        action.doubleClick(driver.findElement(By.xpath("//button[@id='double-click-btn']"))).pause(Duration.ofSeconds(1)).perform();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='double-click-result']")).getText(), "Congrats, you double clicked!");
    }

    @Test
    public void TC_05_Context_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        WebElement contextMenuQuitItem = driver.findElement(By.xpath("//ul[contains(@class,'context-menu-list')]/li/span[text()='Quit']"));

        Assert.assertFalse(contextMenuQuitItem.isDisplayed());

        action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).pause(Duration.ofSeconds(1)).perform();

        Assert.assertTrue(contextMenuQuitItem.isDisplayed());

        action.click(contextMenuQuitItem).pause(Duration.ofSeconds(1)).perform();

        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "clicked: quit");

        alert.accept();
    }

    @Test
    public void TC_06_Drag_And_Drop() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement target = driver.findElement(By.xpath("//div[@id='droptarget']"));

        Assert.assertEquals(target.getText(), "Drag the small circle here.");

        action.dragAndDrop(source, target).pause(Duration.ofSeconds(1)).perform();

        Assert.assertEquals(target.getText(), "You did great!");

        // *Click & hold - Move to element
        driver.navigate().refresh();

        source = driver.findElement(By.xpath("//div[@id='draggable']"));
        target = driver.findElement(By.xpath("//div[@id='droptarget']"));

        Assert.assertEquals(target.getText(), "Drag the small circle here.");

        action.clickAndHold(source).pause(Duration.ofSeconds(1)).moveToElement(target).pause(Duration.ofSeconds(1)).release().perform();

        Assert.assertEquals(target.getText(), "You did great!");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}

package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class S_07_Handle_Custom_Dropdown {
    WebDriver driver;
    Select select;
    String country, city;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Single_Select() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectOptionInCustomDropdown("//input[@class='search']", "//div[@class='visible menu transition']/div", "Australia");

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Australia");

        selectOptionInEditableDropdown("//input[@class='search']", "//div[@class='visible menu transition']/div", "Argentina");

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Argentina");
    }

    @Test
    public void TC_02_Multi_Select() {
        driver.get("https://demos.telerik.com/kendo-ui/multiselect/index");

        String[] values = {"Cycling", "Badminton", "Swimming"};

        selectOptionsInMultiSelectDropdown("//input[@role='combobox']", "//ul[@id='multiselect_listbox']/li", values);

        Assert.assertTrue(verifySelectedOptions("//span[@class='k-chip-content']/span", values));
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

    public void selectOptionInCustomDropdown(String dropdownXPath, String allOptionsXPath, String expectedOption) {
        driver.findElement(By.xpath(dropdownXPath)).click();
        sleepForSeconds(1);
        List<WebElement> allOptions = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allOptionsXPath)));
        for (WebElement option : allOptions) {
            if (option.getText().trim().equals(expectedOption)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
                sleepForSeconds(1);
                option.click();
                sleepForSeconds(1);
                break;
            }
        }
    }

    public void selectOptionInEditableDropdown(String dropdownXPath, String allOptionsXPath, String expectedOption) {
        WebElement editableDropdown = driver.findElement(By.xpath(dropdownXPath));
        editableDropdown.clear();
        editableDropdown.sendKeys(expectedOption);
        sleepForSeconds(1);
        List<WebElement> allOptions = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allOptionsXPath)));
        for (WebElement option : allOptions) {
            if (option.getText().trim().equals(expectedOption)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
                sleepForSeconds(1);
                option.click();
                sleepForSeconds(1);
                break;
            }
        }
    }

    public void selectOptionsInMultiSelectDropdown(String dropdownXPath, String allOptionsXPath, String[] expectedOptions) {
        for (String expectedOption : expectedOptions) {
            driver.findElement(By.xpath(dropdownXPath)).click();
            sleepForSeconds(1);
            List<WebElement> allOptions = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allOptionsXPath)));
            for (WebElement option : allOptions) {
                if (option.getText().trim().equals(expectedOption)) {
                    option.click();
                    sleepForSeconds(1);
                    break;
                }
            }
        }
    }

    public boolean verifySelectedOptions(String actualSelectedXPath, String[] expectedOptions) {
        List<WebElement> actualSelected = driver.findElements(By.xpath(actualSelectedXPath));
        if (actualSelected.size() != (expectedOptions == null ? 0 : expectedOptions.length)) {
            System.out.println("Number of values is not correct");
            return false;
        } else {
            boolean isMatch = true;
            for (int i = 0; i < actualSelected.size(); i++) {
                if (!actualSelected.get(i).getText().equals(expectedOptions[i])) {
                    System.out.println(actualSelected.get(i).getText() + " not equal " + expectedOptions[i]);
                    isMatch = false;
                }
            }
            if (isMatch) {
                System.out.println("Actual values are equal to Expected values");
                return true;
            } else {
                return false;
            }
        }
    }

}

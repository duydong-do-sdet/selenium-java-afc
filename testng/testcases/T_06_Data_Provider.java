package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class T_06_Data_Provider {

    @Test(dataProvider = "testData")
    public void Test(String name, String role) {
        System.out.println("Name: " + name + "| Role: " + role);
    }

    @DataProvider(name = "testData")
    public Object[][] userData() {
        return new Object[][]{{"Dong", "SDET"}, {"Joey", "QAE"}, {"YuDo", "QC"}};
    }

}

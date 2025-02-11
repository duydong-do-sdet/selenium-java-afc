package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class T_02_Assertions {
    int x = 13, y = 10;

    @Test
    public void Test_01_Assert_True() {
        Assert.assertTrue(x > y);
    }

    @Test
    public void Test_02_Assert_False() {
        Assert.assertFalse(x < y);
    }

    @Test
    public void Test_03_Assert_Equals() {
        Assert.assertEquals(x * y, 130);
    }

}

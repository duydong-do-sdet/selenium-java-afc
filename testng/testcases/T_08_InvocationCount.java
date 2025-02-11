package testcases;

import org.testng.annotations.Test;

import java.util.Random;

public class T_08_InvocationCount {

    @Test(invocationCount = 10)
    public void Test() {
        System.out.println(getEmail());
    }

    public String getEmail() {
        return "dongsdet" + getRandomNumber() + "@gmail.com";
    }

    public int getRandomNumber() {
        return new Random().nextInt(10000);
    }

}

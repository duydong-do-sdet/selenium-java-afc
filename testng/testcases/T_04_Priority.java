package testcases;

import org.testng.annotations.Test;

public class T_04_Priority {

    @Test(priority = 0)
    public void Create() {
        System.out.println("Create");
    }

    @Test(priority = 1)
    public void Read() {
        System.out.println("Read");
    }

    @Test(priority = 2)
    public void Update() {
        System.out.println("Update");
    }

    @Test(priority = 3)
    public void Delete() {
        System.out.println("Delete");
    }

}

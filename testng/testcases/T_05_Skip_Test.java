package testcases;

import org.testng.annotations.Test;

public class T_05_Skip_Test {

    @Test
    public void Create() {
        System.out.println("Create");
    }

    @Test(enabled = false)
    public void Read() {
        System.out.println("Read");
    }

    @Test(enabled = false)
    public void Update() {
        System.out.println("Update");
    }

    @Test
    public void Delete() {
        System.out.println("Delete");
    }

}

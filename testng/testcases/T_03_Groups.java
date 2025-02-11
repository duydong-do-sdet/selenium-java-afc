package testcases;

import org.testng.annotations.Test;

public class T_03_Groups {

    @Test(groups = "admin")
    public void Test_01_Create() {
        System.out.println("Create");
    }

    @Test(groups = {"admin", "user"})
    public void Test_02_Read() {
        System.out.println("Read");
    }

    @Test(groups = {"admin", "user"})
    public void Test_03_Update() {
        System.out.println("Update");
    }

    @Test(groups = "admin")
    public void Test_04_Delete() {
        System.out.println("Delete");
    }

}

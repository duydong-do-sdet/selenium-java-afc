package testcases;

import org.testng.annotations.Test;

public class T_09_DependsOnMethods {

    @Test(priority = 0)
    public void Create() {
        System.out.println("Create");
    }

    @Test(dependsOnMethods = "Create")
    public void Read() {
        System.out.println("Read");
    }

    @Test(dependsOnMethods = "Read")
    public void Update() {
        System.out.println("Update");
    }

    @Test(dependsOnMethods = "Update")
    public void Delete() {
        System.out.println("Delete");
    }

}

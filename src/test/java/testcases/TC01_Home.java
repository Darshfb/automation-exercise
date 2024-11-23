package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_HomePage;

public class TC01_Home extends TestBase
{
    @Test(priority = 1)
    public void enterAuthPage()
    {
        Assert.assertTrue(new P01_HomePage(driver).checkPage());

        new P01_HomePage(driver).openAuthPage();
    }

    @Test(priority = 8)
    public void getUserName(){
        new P01_HomePage(driver).getUsername();
    }
}
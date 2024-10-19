import org.testng.annotations.Test;
import pages.P01_HomePage;

public class TC01_Home extends TestBase
{
    @Test(priority = 1)
    public void enterAuthPage() {
        new P01_HomePage(driver).openAuthPage();
    }
}

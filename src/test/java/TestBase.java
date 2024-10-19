import common.MyScreenRecorder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.time.Duration;
import static common.PageBase.quitBrowser;
import static drivers.DriverFactory.getNewInstance;
import static drivers.DriverHolder.getDriver;
import static drivers.DriverHolder.setDriver;

public class TestBase {
    static WebDriver driver;

    @BeforeSuite
    public void beforeSuite() throws Exception {
//        MyScreenRecorder.startRecording("Automation Exercise TestCases");
    }

    @Parameters(value = "browserName")
    @BeforeTest
    public void setupWebDriver(String browserName)
    {
        setDriver(getNewInstance(browserName));
        driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://automationexercise.com/");
    }


    @AfterTest
    public void tearDown() {
        quitBrowser(driver);
    }

    @AfterSuite
    public void afterSuite() throws Exception {
//        MyScreenRecorder.stopRecording();
    }

}

package listeners;

import common.MyScreenRecorder;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import static drivers.DriverHolder.getDriver;

public class Listener implements ITestListener
{

    // Listener: Is for controlling test execution
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Case " + result.getName() + " is Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // TODO: take screenshot on test failure
//        takeScreenshot();
        WebDriver driver = getDriver();
        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(result.getName(), new ByteArrayInputStream(screenshot));
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }


    @Override
    public void onStart(ITestContext context) {
        //TODO: Start screen recorder
        try {
            MyScreenRecorder.startRecording(context.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }    }


    @Override
    public void onFinish(ITestContext context) {
        //TODO: Stop screen recorder
        try {
            MyScreenRecorder.stopRecording();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void takeScreenshot() {
//        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//        Date currntDate = new Date();
//        String screenshotName = currntDate.toString().replace(" ", "-").replace(":", "-");
//        try {
//            FileHandler.copy(takesScreenshot.getScreenshotAs(OutputType.FILE), new File(System.getProperty("user.dir")
//                    + "/src/test/resources/Screenshots/" + screenshotName + ".png"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}

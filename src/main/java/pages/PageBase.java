package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.fail;

public class PageBase {

    public static void waitForElement(WebDriver driver, By locator) {
        try {
            // Create WebDriverWait with the specified timeout
            longWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));

            System.out.println("Element found: " + locator);
        } catch (TimeoutException e) {
            // Handle TimeoutException if the element was not found within the timeout period
            fail("TimeoutException: Element not found within " + 10 + " seconds: " + locator);
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            // Handle NoSuchElementException if the element does not exist in the DOM
            fail("NoSuchElementException: Element not found in the DOM: " + locator);
            e.printStackTrace();
        } catch (Exception e) {
            // Handle any other exceptions that might occur
            fail("Exception occurred while waiting for the element: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void scrollToElement(WebDriver driver, By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        waitForElement(driver, locator);
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
    }

    public static void scrollAndHoverToAnElementByLocator(WebDriver driver, By locator) {
        final Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator)).perform();
        longWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void scrollAndHoverToAnElementByWebElement(WebDriver driver, WebElement element) {
        final Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        longWait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement selectRandomElement(List<WebElement> elements) {
        Random random = new Random();
        int index = random.nextInt(elements.size());
        System.out.println("the index is " + index + " the size is " + elements.size());
        return elements.get(index);
    }

    public static void closeAds(WebDriver driver)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("const elements = document.getElementsByClassName('adsbygoogle adsbygoogle-noablate'); while (elements.length > 0) elements[0].remove()");
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("document.querySelector('.ad-close-button').click();");
    }

    public static void quitBrowser(WebDriver driver) {
        // clear browser localStorage , sessionStorage and delete All Cookies
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
        ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");
        driver.manage().deleteAllCookies();
        driver.quit();
        // kill browser process on background
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
//                Runtime.getRuntime().exec("taskkill /F /IM chrome.exe /T");
            } else if (os.contains("mac") || os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec("pkill -f chromedriver");
//                Runtime.getRuntime().exec("pkill -f chrome");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: Capture Screenshot
    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        try {
            FileHandler.copy(takesScreenshot.getScreenshotAs(OutputType.FILE), new File(System.getProperty("user.dir")
                    + "/src/test/resources/Screenshots/" + screenshotName + System.currentTimeMillis() + ".png"));
        } catch (WebDriverException | IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: explicit wait until web element visibility
    public static void explicitWait(WebDriver driver, By element) {
        // explicit wait - to wait for the compose button to be click-able
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    // TODO: handel fluent wait
    public static void fluentWaitHandling(WebDriver driver, By element) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(50))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    // long explicit wait
    public static WebDriverWait longWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    // short explicit wait
    public static WebDriverWait shortWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}

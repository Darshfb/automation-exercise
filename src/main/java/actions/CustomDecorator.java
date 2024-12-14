package actions;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PageBase;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class CustomDecorator implements WebElement {
    private final WebDriver driver;
    private final By element;

    public CustomDecorator(WebDriver driver, By element) {
        this.driver = driver;
        this.element = element;
    }


    // TODO: explicit wait until web element visibility
    public static void explicitWait(WebDriver driver, By element) {
        // explicit wait - to wait for the compose button to be click-able
        PageBase.explicitWait(driver, element);
    }
    // short explicit wait
    public static WebDriverWait shortWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Override
    public void click() {
        System.out.println("Clicking on element: " + element.toString());
//        explicitWait(driver, element);
        driver.findElement(element).click();
    }

    @Override
    public void submit() {
        System.out.println("Submitting on element: " + element.toString());
        shortWait(driver).until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        System.out.println("Typing: " + Arrays.toString(keysToSend) + " on element: " + element.toString());
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).clear();
    }

    @Override
    public String getTagName()
    {
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getTagName();
    }

    @Override
    public @Nullable String getDomProperty(String name) {
        return WebElement.super.getDomProperty(name);
    }

    @Override
    public @Nullable String getDomAttribute(String name) {
        return WebElement.super.getDomAttribute(name);
    }

    @Override
    public @Nullable String getAttribute(String name) {
        return "";
    }

    @Override
    public @Nullable String getAriaRole() {
        return WebElement.super.getAriaRole();
    }

    @Override
    public @Nullable String getAccessibleName() {
        return WebElement.super.getAccessibleName();
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getText()
    {
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return List.of();
    }

    @Override
    public WebElement findElement(By by) {
        return null;
    }

    @Override
    public SearchContext getShadowRoot() {
        return WebElement.super.getShadowRoot();
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public Dimension getSize() {
        return null;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    @Override
    public String getCssValue(String propertyName) {
        return "";
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }
}

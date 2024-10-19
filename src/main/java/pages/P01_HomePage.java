package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_HomePage {
    WebDriver driver;

    public P01_HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By authPage = By.xpath("(//a)[5]");


    public P01_HomePage openAuthPage()
    {
        driver.findElement(authPage).click();
        return this;
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_HomePage {
    private final WebDriver driver;

    public P01_HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By authPage = By.xpath("(//a)[5]");


    public void openAuthPage() {
        driver.findElement(authPage).click();
    }

    By homePage = By.xpath("(//a)[@style='color: orange;']");

    public Boolean checkPage() {
        System.out.println("The home page is " + driver.findElement(homePage).getText());
        return driver.findElement(homePage).getText().equals("Home");
    }

    private final By userName = By.xpath("(//div)[@class='col-sm-8']/div/ul/li[10]/a/i");
    public void getUsername()
    {
        String text = driver.findElement(this.userName).getText();
        System.out.println("The username is " + text);
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P10_CategoryProduct
{
    private final WebDriver driver;
    public P10_CategoryProduct(WebDriver driver) {
        this.driver = driver;
    }

    private final By categoryProductName = By.xpath("//h2[@class='title text-center']");

    public Boolean verifySelectedCategoryProduct(String subcategoryName){
        return driver.findElement(categoryProductName).getText().contains(subcategoryName);
    }
}

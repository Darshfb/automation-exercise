package pages;

import actions.CustomDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static pages.PageBase.waitForElement;

public class P06_productDetailsPage
{
    private final WebDriver driver;
    public P06_productDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By productName = By.xpath("(//h2)[3]");
    private final By productCategory = By.xpath("(//div/div/p)[3]");
    private final By productPrice = By.xpath("//span/span");
    private final By productAvailability = By.xpath("//div[@class='product-details']//p[2]");
    private final By productCondition = By.xpath("//body//section//p[3]");
    private final By productBrand = By.xpath("//body//section//p[4]");

    public Boolean checkProductDetailsVisible()
    {
        return driver.findElement(productName).isDisplayed() &&
                driver.findElement(productCategory).isDisplayed() &&
                driver.findElement(productPrice).isDisplayed() &&
                driver.findElement(productAvailability).isDisplayed() &&
                driver.findElement(productCondition).isDisplayed() &&
                driver.findElement(productBrand).isDisplayed();
    }

    private final By quantityInput = By.id("quantity");
    private final By addToCartButton = By.xpath("//button[@type='button']");
    public P06_productDetailsPage addToCart(int quantity)
    {
        waitForElement(driver, quantityInput);
        driver.findElement(quantityInput).clear();
        new CustomDecorator(driver, quantityInput).sendKeys(Integer.toString(quantity));
        new CustomDecorator(driver, addToCartButton).click();
        return this;
    }

    private final By viewCartButton = By.xpath("//u[normalize-space()='View Cart']");
    public void goToCart()
    {
        waitForElement(driver, viewCartButton);
        new CustomDecorator(driver, viewCartButton).click();
    }

    public String getProductName(){
        return driver.findElement(productName).getText();
    }
//    public String getProductQuantity(){
//        return driver.findElement(quantityInput).getText();
//    }
}

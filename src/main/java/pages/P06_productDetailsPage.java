package pages;

import actions.CustomDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static pages.PageBase.waitForElement;

public class P06_productDetailsPage {
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
    private final By quantityInput = By.id("quantity");
    private final By addToCartButton = By.xpath("//button[@type='button']");
    private final By reviewText = By.xpath("(//li)[@class='active']/a");
    private final By name = By.id("name");
    private final By email = By.id("email");
    private final By reviewTextField = By.id("review");
    private final By submitButton = By.id("button-review");
    private final By viewCartButton = By.xpath("//u[normalize-space()='View Cart']");
    private final By successReviewMsg = By.xpath("(//div)[@class='alert-success alert']/span");

    public Boolean checkProductDetailsVisible() {
        waitForElement(driver, productName);
        return driver.findElement(productName).isDisplayed() &&
                driver.findElement(productCategory).isDisplayed() &&
                driver.findElement(productPrice).isDisplayed() &&
                driver.findElement(productAvailability).isDisplayed() &&
                driver.findElement(productCondition).isDisplayed() &&
                driver.findElement(productBrand).isDisplayed();
    }


    public P06_productDetailsPage addToCart(int quantity) {
        waitForElement(driver, quantityInput);
        driver.findElement(quantityInput).clear();
        new CustomDecorator(driver, quantityInput).sendKeys(Integer.toString(quantity));
        new CustomDecorator(driver, addToCartButton).click();
        return this;
    }

    public void goToCart() {
        waitForElement(driver, viewCartButton);
        new CustomDecorator(driver, viewCartButton).click();
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public Boolean verifyReviewTextVisible() {
        return driver.findElement(reviewText).getText().equals("WRITE YOUR REVIEW");
    }

    public P06_productDetailsPage enterName(String name) {
        waitForElement(driver, this.name);
        new CustomDecorator(driver, this.name).sendKeys(name);
        return this;
    }

    public P06_productDetailsPage enterEmail(String email) {
        waitForElement(driver, this.email);
        new CustomDecorator(driver, this.email).sendKeys(email);
        return this;
    }

    public P06_productDetailsPage enterReview(String review) {
        waitForElement(driver, this.reviewTextField);
        new CustomDecorator(driver, this.reviewTextField).sendKeys(review);
        return this;
    }

    public void submitReview() {
        waitForElement(driver, this.submitButton);
        new CustomDecorator(driver, this.submitButton).click();
    }

    public Boolean checkReviewSentSuccessfully() {
        waitForElement(driver, successReviewMsg);
        String message = "Thank you for your review.";
        return driver.findElement(successReviewMsg).getText().equals(message);
    }

}

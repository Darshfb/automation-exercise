package pages;

import actions.CustomDecorator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;

import static pages.P05_ProductsPage.listNames;
import static pages.P05_ProductsPage.listPrices;
import static pages.PageBase.*;

public class P01_HomePage {
    private final WebDriver driver;

    public P01_HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By authPage = By.xpath("(//a)[5]");


    public void openAuthPage() {
        driver.findElement(authPage).click();
    }

    private final By homePage = By.xpath("(//a)[@style='color: orange;']");

    public Boolean checkPage() {
        return driver.findElement(homePage).getText().equals("Home");
    }

    private final By userName = By.xpath("(//div)[@class='col-sm-8']/div/ul/li[10]/a/b");

    public String getUsername() {
        return driver.findElement(this.userName).getText();
    }

    private final By deleteButton = By.xpath("//a[normalize-space()='Delete Account']");

    public void deleteAccount() {
        new CustomDecorator(driver, deleteButton).click();
    }

    private final By deletedAccountMessage = By.xpath("//b[normalize-space()='Account Deleted!']");


    public Boolean verifyDeleteAccount() {
        return driver.findElement(deletedAccountMessage).getText().equals("ACCOUNT DELETED!");
    }

    private final By goHomePage = By.xpath("//a[@class='btn btn-primary']");

    public void returnToHomePage() {
        new CustomDecorator(driver, goHomePage).click();
    }

    private final By logout = By.xpath("//a[normalize-space()='Logout']");

    public void logout() {
        new CustomDecorator(driver, logout).click();
    }

    private final By contactUsButton = By.xpath("//a[normalize-space()='Contact us']");

    public void enterContactUsPage() {
        new CustomDecorator(driver, contactUsButton).click();
    }

    private final By testCasesButton = By.xpath("//div[@class='item active']//button[@type='button'][normalize-space()='Test Cases']");

    public void enterTestCasesPage() {
        waitForElement(driver, testCasesButton);
        new CustomDecorator(driver, testCasesButton).click();
    }

    public Boolean verifyUserNavigateToTestCasesPage() {
        shortWait(driver).until(ExpectedConditions.urlContains("test_cases"));
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("test_cases");
    }

    private final By productsButton = By.xpath("//a[@href='/products']");

    public void enterProductsPage()
    {
        if(!listNames.isEmpty()){
            listNames.clear();
            listPrices.clear();
        }
        waitForElement(driver, productsButton);
        new CustomDecorator(driver, productsButton).click();
    }

    private final By allProductsText = By.xpath("//h2[@class='title text-center']");

    public Boolean checkAllProductsText() {
        waitForElement(driver, allProductsText);
        return driver.findElement(allProductsText).getText().contains("ALL PRODUCTS");
    }

    private final By subscriptionTextField = By.xpath("//input[@id='susbscribe_email']");
    private final By subscriptionButton = By.xpath("//i[@class='fa fa-arrow-circle-o-right']");
    private final By footer = By.xpath("//div[@class='footer-widget']//div[@class='container']");

    public void sendSubscriptionEmail(String email) {
        scrollToElement(driver, footer);
        waitForElement(driver, subscriptionTextField);
        new CustomDecorator(driver, subscriptionTextField).sendKeys(email);
        waitForElement(driver, subscriptionButton);
        new CustomDecorator(driver, subscriptionButton).click();
    }

    public Boolean verifySubscribeSentSuccessfully() {
        final By alert = By.xpath("//div[@class='alert-success alert']");
        waitForElement(driver, alert);
        return driver.findElement(alert).getText().equals("You have been successfully subscribed!");
    }

    private final By cartButton = By.xpath("//a[normalize-space()='Cart']");

    public void enterCartPage() {
        waitForElement(driver, cartButton);
        new CustomDecorator(driver, cartButton).click();
    }

    private final By firstItemViewDetails = By.xpath("((//div)[@class='product-image-wrapper']/div[2])[1]");

    public void viewProductDetails() {
        waitForElement(driver, firstItemViewDetails);
        new CustomDecorator(driver, firstItemViewDetails).click();
    }

    private By addItemToCart(String index) {
        return By.xpath("((//div)[@class='overlay-content']/a)[" + index + "]");
    }

    private final By continueShopping = By.xpath("//button[@class='btn btn-success close-modal btn-block']");

    private By selectItem(String index) {
        return By.xpath("(//div)[@class='single-products'][" + index + "]");
    }

    public P01_HomePage addItemsToCart(String index) {
        waitForElement(driver, selectItem(index));
        scrollAndHoverToAnElementByLocator(driver, selectItem(index));
        waitForElement(driver, addItemToCart(index));
        new CustomDecorator(driver, addItemToCart(index)).click();
        waitForElement(driver, continueShopping);
        new CustomDecorator(driver, continueShopping).click();
        return this;
    }

    private final By categorySideBar = By.xpath("//h2[normalize-space()='Category']");

    private By category(String index) {
        return By.xpath("((//h4)[@class='panel-title']/a)[" + index + "]");
    }

    public Boolean checkCategorySideBarVisible() {
        return driver.findElement(categorySideBar).isDisplayed();
    }

    public void selectCategory(String index) {
        waitForElement(driver, category(index));
        scrollToElement(driver, category(index));
        new CustomDecorator(driver, category(index)).click();
    }

    private final By womenSubCategoryList = By.xpath("((//div)[@id='Women']/div/ul/li/a)");
    private final By menSubCategoryList = By.xpath("((//div)[@id='Men']/div/ul/li/a)");

    @Getter
    private String subCategoryName = "";

    public P01_HomePage selectWomenSubCategory() {
        waitForElement(driver, womenSubCategoryList);
        WebElement element = selectRandomElement(driver.findElements(womenSubCategoryList));
        subCategoryName = element.getText();
        element.click();
        return this;
    }

    public P01_HomePage selectMenSubCategory() {
        waitForElement(driver, menSubCategoryList);
        WebElement element = selectRandomElement(driver.findElements(menSubCategoryList));
        subCategoryName = element.getText();
        element.click();
        return this;
    }

    private final By recommendedItems = By.xpath("(//div)[@class='recommended_items']/h2");

    public Boolean verifyRecommendedItemsVisible() {
        scrollToElement(driver, recommendedItems);
        return driver.findElement(recommendedItems).isDisplayed();
    }

    private By listOfRecommendedItems(int index) {
        return By.xpath("((//div)[@class='recommended_items']/div/div[@class='carousel-inner']/div[@class='item active']/div/div/div/div/a)[" + index + "]");
    }

    private By recommendedItemName(int index) {
        return By.xpath("((//div)[@class='recommended_items']/div/div[@class='carousel-inner']/div[@class='item active']/div/div/div/div/p)[" + index + "]");
    }

    @Getter
    private String itemName = "";

    public P01_HomePage selectRandomRecommendedItem() {
        waitForElement(driver, listOfRecommendedItems(1));
        itemName = driver.findElement(recommendedItemName(1)).getText();
        driver.findElement(listOfRecommendedItems(1)).click();
        return this;
    }

    private final By viewCart = By.xpath("//u[normalize-space()='View Cart']");

    public P01_HomePage clickViewCart() {
        waitForElement(driver, viewCart);
        new CustomDecorator(driver, this.viewCart).click();
        return this;
    }

    private final By subscriptionText = By.xpath("(//div)[@class='single-widget']/h2");
    private final By scrollUp = By.id("scrollUp");
    public P01_HomePage scrollToTheBottomPage()
    {
      waitForElement(driver, subscriptionText);
      scrollToElement(driver, subscriptionText);
        return this;
    }

    public Boolean verifySubscriptionTextVisible()
    {

        waitForElement(driver, subscriptionText);
        return driver.findElement(subscriptionText).getText().equals("SUBSCRIPTION");
    }

    public P01_HomePage scrollUp(){
        waitForElement(driver, scrollUp);
        new CustomDecorator(driver, scrollUp).click();
        return this;
    }

    public P01_HomePage scrollUpWithoutArrowButton(){
        waitForElement(driver, homePage);
        scrollToElement(driver, homePage);
        return this;
    }

    private final By bannerText = By.xpath("//div[@class='col-sm-6']/h2");

    public Boolean verifyBannerTextVisible() {
        waitForElement(driver, bannerText);
        return driver.findElement(bannerText).getText().equals("Full-Fledged practice website for Automation Engineers");
    }

}

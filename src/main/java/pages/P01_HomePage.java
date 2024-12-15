package pages;

import actions.CustomDecorator;
import lombok.Getter;
import org.openqa.selenium.By;
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
    private final By homePage = By.xpath("(//a)[@style='color: orange;']");
    private final By userName = By.xpath("(//div)[@class='col-sm-8']/div/ul/li[10]/a/b");
    private final By deleteButton = By.xpath("//a[normalize-space()='Delete Account']");
    private final By deletedAccountMessage = By.xpath("//b[normalize-space()='Account Deleted!']");
    private final By goHomePage = By.xpath("//a[@class='btn btn-primary']");
    private final By logout = By.xpath("//a[normalize-space()='Logout']");
    private final By contactUsButton = By.xpath("//a[normalize-space()='Contact us']");
    private final By testCasesButton = By.xpath("//div[@class='item active']//button[@type='button'][normalize-space()='Test Cases']");
    private final By productsButton = By.xpath("//a[@href='/products']");
    private final By allProductsText = By.xpath("//h2[@class='title text-center']");
    private final By subscriptionTextField = By.xpath("//input[@id='susbscribe_email']");
    private final By subscriptionButton = By.xpath("//i[@class='fa fa-arrow-circle-o-right']");
    private final By footer = By.xpath("//div[@class='footer-widget']//div[@class='container']");
    private final By cartButton = By.xpath("//a[normalize-space()='Cart']");
    private final By firstItemViewDetails = By.xpath("((//div)[@class='product-image-wrapper']/div[2])[1]");
    private final By continueShopping = By.xpath("//button[@class='btn btn-success close-modal btn-block']");
    private final By categorySideBar = By.xpath("//h2[normalize-space()='Category']");
    private final By womenSubCategoryList = By.xpath("((//div)[@id='Women']/div/ul/li/a)");
    private final By menSubCategoryList = By.xpath("((//div)[@id='Men']/div/ul/li/a)");
    private final By recommendedItems = By.xpath("(//div)[@class='recommended_items']/h2");
    private final By subscriptionText = By.xpath("(//div)[@class='single-widget']/h2");
    private final By scrollUp = By.id("scrollUp");
    private final By bannerText = By.xpath("//div[@class='col-sm-6']/h2");
    private final By viewCart = By.xpath("(//p)[@class='text-center']/a/u[text()='View Cart']");

    private By selectItem(String index) {
        return By.xpath("(//div)[@class='single-products'][" + index + "]");
    }

    private By addItemToCart(String index) {
        return By.xpath("((//div)[@class='overlay-content']/a)[" + index + "]");
    }

    private By listOfRecommendedItems() {
        return By.xpath("((//div)[@class='recommended_items']/div/div[@class='carousel-inner']/div[@class='item active']/div/div/div/div/a)[" + 1 + "]");
    }

    private By recommendedItemName() {
        return By.xpath("((//div)[@class='recommended_items']/div/div[@class='carousel-inner']/div[@class='item active']/div/div/div/div/p)[" + 1 + "]");
    }

    private By category(String index) {
        return By.xpath("((//h4)[@class='panel-title']/a)[" + index + "]");
    }

    @Getter
    private String subCategoryName = "";
    @Getter
    private String itemName = "";

    public void openAuthPage() {
        driver.findElement(authPage).click();
    }

    public Boolean checkPage() {
        return driver.findElement(homePage).getText().equals("Home");
    }

    public String getUsername() {
        return driver.findElement(this.userName).getText();
    }

    public void deleteAccount() {
        new CustomDecorator(driver, deleteButton).click();
    }

    public Boolean verifyDeleteAccount() {
        return driver.findElement(deletedAccountMessage).getText().equals("ACCOUNT DELETED!");
    }

    public void returnToHomePage() {
        new CustomDecorator(driver, goHomePage).click();
    }


    public void logout() {
        new CustomDecorator(driver, logout).click();
    }

    public void enterContactUsPage() {
        new CustomDecorator(driver, contactUsButton).click();
    }

    public void enterTestCasesPage() {
        waitForElement(driver, testCasesButton);
        new CustomDecorator(driver, testCasesButton).click();
    }

    public Boolean verifyUserNavigateToTestCasesPage() {
        shortWait(driver).until(ExpectedConditions.urlContains("test_cases"));
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("test_cases");
    }

    public void enterProductsPage() {
        if (!listNames.isEmpty()) {
            listNames.clear();
            listPrices.clear();
        }
        waitForElement(driver, productsButton);
        new CustomDecorator(driver, productsButton).click();
    }

    public Boolean checkAllProductsText() {
        waitForElement(driver, allProductsText);
        return driver.findElement(allProductsText).getText().contains("ALL PRODUCTS");
    }

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

    public void enterCartPage() {
        waitForElement(driver, cartButton);
        new CustomDecorator(driver, cartButton).click();
    }

    public void viewProductDetails() {
        waitForElement(driver, firstItemViewDetails);
        new CustomDecorator(driver, firstItemViewDetails).click();
    }

    public P01_HomePage addItemsToCart(String index)
    {
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(selectItem(index)));
        closeAds(driver);
        scrollAndHoverToAnElementByLocator(driver, selectItem(index));
        shortWait(driver).until(ExpectedConditions.elementToBeClickable(addItemToCart(index)));
        new CustomDecorator(driver, addItemToCart(index)).click();
        waitForElement(driver, continueShopping);
        new CustomDecorator(driver, continueShopping).click();
        return this;
    }

    public Boolean checkCategorySideBarVisible() {
        return driver.findElement(categorySideBar).isDisplayed();
    }

    public void selectCategory(String index) {
        waitForElement(driver, category(index));
        scrollToElement(driver, category(index));
        new CustomDecorator(driver, category(index)).click();
    }

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


    public Boolean verifyRecommendedItemsVisible() {
        scrollToElement(driver, recommendedItems);
        return driver.findElement(recommendedItems).isDisplayed();
    }


    public P01_HomePage selectRandomRecommendedItem() {
        waitForElement(driver, listOfRecommendedItems());
        itemName = driver.findElement(recommendedItemName()).getText();
        driver.findElement(listOfRecommendedItems()).click();
        return this;
    }

    public P01_HomePage clickViewCart() {
//        waitForElement(driver, viewCart);
        longWait(driver).until(ExpectedConditions.visibilityOfElementLocated(viewCart));
        new CustomDecorator(driver, this.viewCart).click();
        return this;
    }

    public P01_HomePage scrollToTheBottomPage() {
        waitForElement(driver, subscriptionText);
        scrollToElement(driver, subscriptionText);
        return this;
    }

    public Boolean verifySubscriptionTextVisible() {

        waitForElement(driver, subscriptionText);
        return driver.findElement(subscriptionText).getText().equals("SUBSCRIPTION");
    }

    public P01_HomePage scrollUp() {
        waitForElement(driver, scrollUp);
        new CustomDecorator(driver, scrollUp).click();
        return this;
    }

    public P01_HomePage scrollUpWithoutArrowButton() {
        waitForElement(driver, homePage);
        scrollToElement(driver, homePage);
        return this;
    }

    public Boolean verifyBannerTextVisible() {
        waitForElement(driver, bannerText);
        return driver.findElement(bannerText).getText().equals("Full-Fledged practice website for Automation Engineers");
    }

}

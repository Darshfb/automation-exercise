package pages;

import actions.CustomDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static pages.PageBase.*;

public class P05_ProductsPage {
    private final WebDriver driver;

    public P05_ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By firstViewProduct = By.xpath("//div[@class='col-sm-9 padding-right']//div[2]//div[1]//div[2]//ul[1]//li[1]//a[1]");

    public void clickFirstViewProduct() {
        waitForElement(driver, firstViewProduct);
        new CustomDecorator(driver, firstViewProduct).click();
    }

    private final By searchTextField = By.xpath("//input[@id='search_product']");

    public P05_ProductsPage searchProduct(String productName) {
        waitForElement(driver, searchTextField);
        new CustomDecorator(driver, searchTextField).sendKeys(productName);
        return this;
    }

    private final By searchButton = By.xpath("//button[@id='submit_search']");

    public void clickSearchButton() {
        waitForElement(driver, searchButton);
        new CustomDecorator(driver, searchButton).click();
    }
    private final By searchedProductTitle = By.xpath("//div[@class='productinfo text-center']/p");
    private final By searchPage = By.xpath("//h2[@class='title text-center']");
    public Boolean checkSearchedProductVisible() {
        String searchedText = "Top";
        waitForElement(driver, searchedProductTitle);
        scrollToElement(driver, searchPage);
        return driver.findElement(searchedProductTitle).getText().contains(searchedText);
    }

    private By addItemToCart(String index) {
        return By.xpath("(//div)[@class='single-products'][" + index + "]/div/div/a");
    }

    private By selectItem(String index) {
        return By.xpath("((//div)[@class='productinfo text-center'])[" + index + "]");
    }

    private final By continueToShopping = By.xpath("//button[@class='btn btn-success close-modal btn-block']");

    private final By viewCart = By.xpath("//u[normalize-space()='View Cart']");

    private String itemPrice(String index) {
        return driver.
                findElement(By.xpath("(//div[@class='overlay-content']/h2)[" + index + "]"))
                .getText();
    }

    private String itemName(String index) {
        return driver.findElement(By.xpath("(//div[@class='overlay-content']/p)[" + index + "]")).getText();
    }

    public static List<String> listPrices = new ArrayList<>();
    public static List<String> listNames = new ArrayList<>();

    public P05_ProductsPage addItemToCart(String index, boolean viewCart)
    {
        scrollAndHoverToAnElementByLocator(driver, selectItem(index));
        waitForElement(driver, addItemToCart(index));
        listPrices.add(itemPrice(index));
        listNames.add(itemName(index));
        new CustomDecorator(driver, addItemToCart(index)).click();
        waitForElement(driver, continueToShopping);
        if (!viewCart)
            new CustomDecorator(driver, continueToShopping).click();
        else
            new CustomDecorator(driver, this.viewCart).click();
        return this;
    }

    private final By brandsTextButtonOnLeftSideBar = By.xpath("(//div)[@class='brands_products']/h2");
    private final By listOfBrandNames = By.xpath("(//div)[@class='brands-name']/ul/li");
    public Boolean verifyBrandsVisible()
    {
        scrollToElement(driver, brandsTextButtonOnLeftSideBar);
        return driver.findElement(brandsTextButtonOnLeftSideBar).getText().contains("BRANDS");
    }

    public String brandName= "";

    public void selectBrand(){
        waitForElement(driver, listOfBrandNames);
        scrollToElement(driver, listOfBrandNames);
        WebElement element = selectRandomElement(driver.findElements(listOfBrandNames));
        brandName = element.getText();
        element.click();
    }

    public Boolean verifyBrandSelected(String brandTitle){
        String brandPageTitle = driver.findElement(By.xpath("(//div)[@class='features_items']/h2")).getText();
        return brandPageTitle.contains(brandTitle);
    }
    private final By listOfBrandProducts = By.xpath("(//div)[@class='features_items']/div[@class='col-sm-4']");
    public Boolean verifyBrandProductsVisible(){
        return !driver.findElements(listOfBrandProducts).isEmpty();
    }

    private final By listItems = By.xpath("(//div)[@class='single-products']");
    private By addToCartLocator(int index){
        return By.xpath("(//div[2]/div/a)["+ index +"]");
    }
    private final By continueShopping = By.xpath("//button[@class='btn btn-success close-modal btn-block']");

    public void addProductItemsToCart()
    {
        waitForElement(driver, listItems);
        for(int i = 0; i < driver.findElements(listItems).size(); i++){
            longWait(driver).until(ExpectedConditions.visibilityOf(driver.findElements(listItems).get(i)));
            scrollAndHoverToAnElementByWebElement(driver, driver.findElements(listItems).get(i));
            scrollAndHoverToAnElementByLocator(driver, addToCartLocator(i+1));
            listNames.add(itemName(String.valueOf(i+1)));
            shortWait(driver).until(ExpectedConditions.elementToBeClickable(addToCartLocator(i+1)));
            new CustomDecorator(driver,addToCartLocator(i+1)).click();
            waitForElement(driver, continueShopping);
            new CustomDecorator(driver, continueShopping).click();
        }
    }

    private final By cartButton = By.xpath("//a[normalize-space()='Cart']");

    public void enterCartPage()
    {
        waitForElement(driver, cartButton);
        new CustomDecorator(driver, cartButton).click();
    }


}

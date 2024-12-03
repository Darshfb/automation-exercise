package pages;

import actions.CustomDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

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

    public Boolean checkSearchedProductVisible() {
        String searchedText = "Men";
        final By searchedProductTitle = By.xpath("//div[@class='productinfo text-center']/p");
        waitForElement(driver, searchedProductTitle);
        System.out.println("The searched product is " + driver.findElement(searchedProductTitle).getText());
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
    private String itemPrice(String index)
    {
        return driver.
                findElement(By.xpath("(//div[@class='overlay-content']/h2)[" + index + "]"))
                .getText();
    }

    private String itemName(String index){
        return driver.findElement(By.xpath("(//div[@class='overlay-content']/p)["+ index +"]")).getText();
    }
    public static List<String> listPrices = new ArrayList<>();
    public static List<String> listNames = new ArrayList<>();
    public P05_ProductsPage addItemToCart(String index, boolean viewCart) {
        scrollAndHoverToAnElement(driver, selectItem(index));
//        scrollToElement(driver, selectItem(index));
        waitForElement(driver, addItemToCart(index));
        listPrices.add(itemPrice(index));
        listNames.add(itemName(index));
        System.out.println(listPrices + " [list prices]" + listNames + " [list names]");
        new CustomDecorator(driver, addItemToCart(index)).click();
        waitForElement(driver, continueToShopping);
        if (!viewCart)
            new CustomDecorator(driver, continueToShopping).click();
        else
            new CustomDecorator(driver, this.viewCart).click();
        return this;
    }
}

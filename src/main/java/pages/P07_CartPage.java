package pages;

import actions.CustomDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static pages.P05_ProductsPage.listNames;
import static pages.P05_ProductsPage.listPrices;
import static pages.PageBase.scrollToElement;
import static pages.PageBase.waitForElement;

public class P07_CartPage {
    private final WebDriver driver;

    public P07_CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By subscriptionTextField = By.xpath("//input[@id='susbscribe_email']");
    private final By subscriptionButton = By.id("subscribe");
    private final By subscriptionMessage = By.xpath("//div[@class='alert-success alert']");

    public void scrollToSubscriptionEmailAndSendEmail(String email) {
        scrollToElement(driver, subscriptionTextField);
        new CustomDecorator(driver, subscriptionTextField).sendKeys(email);
        new CustomDecorator(driver, subscriptionButton).click();
    }

    public Boolean checkSubscriptionEmailSentSuccessfully() {
        String subscriptionText = "You have been successfully subscribed!";
        waitForElement(driver, subscriptionMessage);
        return driver.findElement(subscriptionMessage).getText().equals(subscriptionText);
    }

    private final By listCartItemNames = By.xpath("(//td)[@class='cart_description']/h4/a");
    private final By listCartItemPrices = By.xpath("(//td)[@class='cart_price']/p");

    public Boolean checkListCartItemsNames() {
        waitForElement(driver, listCartItemNames);
        List<String> listItemsNames = driver.findElements(listCartItemNames).stream().map(WebElement::getText).collect(Collectors.toList());
        return listNames.equals(listItemsNames);
    }

    public Boolean checkCartListItemPrices() {
        waitForElement(driver, listCartItemPrices);
        List<String> listCartPrices = driver.findElements(listCartItemPrices).stream().map(WebElement::getText).collect(Collectors.toList());
        return listPrices.equals(listCartPrices);
    }

    private final By cartListQuantities = By.xpath("(//td)[@class='cart_quantity']/button");

    public Boolean checkCartListQuantities(String itemQuantity) {
        List<WebElement> list = driver.findElements(cartListQuantities);
        System.out.println(list.size());
        return list.stream().allMatch(element -> element.getText().equals(itemQuantity));
    }

    private final By itemQuantity = By.xpath("((//td)[@class='cart_quantity']/button)[1]");
    public Boolean verifyItemQuantity(String quantity)
    {
        return driver.findElement(itemQuantity).getText().equals(quantity);
    }

    public Boolean verifyItemName(String name){
        return driver.findElements(listCartItemNames).get(0).getText().equals(name);
    }

    private final By proceedToCheckout = By.xpath("//a[@class='btn btn-default check_out']");

    public P07_CartPage clickProceedToCheckout(){
        waitForElement(driver, proceedToCheckout);
        new CustomDecorator(driver, proceedToCheckout).click();
        return this;
    }

    private final By registerLoginButton = By.xpath("//u[normalize-space()='Register / Login']");

    public void clickRegisterLoginButton(){
        waitForElement(driver, registerLoginButton);
        new CustomDecorator(driver, registerLoginButton).click();
    }
}

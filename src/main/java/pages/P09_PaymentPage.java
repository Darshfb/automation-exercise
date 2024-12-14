package pages;

import actions.CustomDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static pages.PageBase.waitForElement;

public class P09_PaymentPage
{
    private final WebDriver driver;

    public P09_PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By nameOnCard = By.xpath("//input[@name='name_on_card']");
    private final By cardNumber = By.xpath("//input[@name='card_number']");
    private final By cvc = By.xpath("//input[@placeholder='ex. 311']");
    private final By expiryMonthDate = By.xpath("//input[@placeholder='MM']");
    private final By expiryYearDate = By.xpath("//input[@placeholder='YYYY']");
    private final By submitButton = By.id("submit");

    public P09_PaymentPage enterNameOnCard(String name){
        new CustomDecorator(driver, nameOnCard).sendKeys(name);
        return this;
    }

    public P09_PaymentPage enterCardNumber(String cardNumber){
        new CustomDecorator(driver, this.cardNumber).sendKeys(cardNumber);
        return this;
    }

    public P09_PaymentPage enterCvc(String cvc){
        new CustomDecorator(driver, this.cvc).sendKeys(cvc);
        return this;
    }

    public P09_PaymentPage enterExpiryMonthDate(String expiryMonthDate){
        new CustomDecorator(driver, this.expiryMonthDate).sendKeys(expiryMonthDate);
        return this;
    }

    public P09_PaymentPage enterExpiryYearDate(String expiryYearDate){
        new CustomDecorator(driver, this.expiryYearDate).sendKeys(expiryYearDate);
        return this;
    }

    public void submitOrder()
    {
        waitForElement(driver, submitButton);
        new CustomDecorator(driver, submitButton).click();
    }

    private final By verifySuccessPayment = By.xpath("//p[normalize-space()='Congratulations! Your order has been confirmed!']");

    public Boolean verifySuccessPaymentAndPlaceOrder()
    {
        waitForElement(driver, verifySuccessPayment);
        return driver.findElement(verifySuccessPayment).getText().equals("Congratulations! Your order has been confirmed!");
    }

    private final By downloadInvoiceFile = By.xpath("//a[@class='btn btn-default check_out']");

    public void downloadInvoiceFile() throws AWTException {
        waitForElement(driver, downloadInvoiceFile);
        WebElement element = driver.findElement(downloadInvoiceFile);
        element.click();

//        Robot robot = new Robot();
//        robot.keyPress(KeyEvent.VK_TAB);
//        robot.keyRelease(KeyEvent.VK_TAB);
//        robot.keyRelease(KeyEvent.VK_TAB);
//        robot.keyRelease(KeyEvent.VK_TAB);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

}

package pages;

import actions.CustomDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static pages.PageBase.shortWait;

public class P08_CheckoutPage {
    private final WebDriver driver;

    public P08_CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By firstAndLastName = By.xpath("//ul[@id='address_delivery']//li[@class='address_firstname address_lastname']");
    private final By companyName = By.xpath("//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][1]");
    private final By streetAddressName = By.xpath("//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][2]");
    private final By secondStreetAddressName = By.xpath("//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][3]");
    private final By cityNameAndStateNameAndZipCode = By.xpath("//ul[@id='address_delivery']//li[@class='address_city address_state_name address_postcode']");
    private final By countryName = By.xpath("//ul[@id='address_delivery']//li[@class='address_country_name']");
    private final By phoneNumber = By.xpath("//ul[@id='address_delivery']//li[@class='address_phone']");
    private final By commentTextField = By.name("message");
    private final By placeOrderButton = By.xpath("//a[@class='btn btn-default check_out']");

    private By billingAddress(int index){
        return By.xpath("(//ul[@class='address alternate_item box']/li)[" + index + "]");
    }

    public Boolean verifyAddressDetails(String firstNameAddress,
                                        String lastNameAddress,
                                        String companyName,
                                        String streetAddressName,
                                        String secondStreetAddressName,
                                        String cityName,
                                        String stateName,
                                        String postCode,
                                        String countryName,
                                        String phoneNumber,
                                        String genderName
    ) {
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(firstAndLastName));
        if (!driver.findElement(firstAndLastName).getText().equals(genderName + " " + firstNameAddress + " " + lastNameAddress)) {
            return false;
        } else if (!driver.findElement(this.companyName).getText().equals(companyName))
        {
            return false;
        } else if (!driver.findElement(this.streetAddressName).getText().equals(streetAddressName)) {
            return false;
        } else if (!driver.findElement(this.secondStreetAddressName).getText().equals(secondStreetAddressName)) {
            return false;
        } else if (!driver.findElement(this.cityNameAndStateNameAndZipCode).getText().equals(cityName + " " + stateName + " " + postCode)) {
            return false;
        } else if (!driver.findElement(this.countryName).getText().equals(countryName)) {
            return false;
        } else
        return driver.findElement(this.phoneNumber).getText().equals(phoneNumber);
    }

    public Boolean verifyBillingAddressDetails(String firstNameAddress,
                                        String lastNameAddress,
                                        String companyName,
                                        String streetAddressName,
                                        String secondStreetAddressName,
                                        String cityName,
                                        String stateName,
                                        String postCode,
                                        String countryName,
                                        String phoneNumber,
                                        String genderName
    ) {
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(billingAddress(2)));
        if (!driver.findElement(billingAddress(2)).getText().equals(genderName + " " + firstNameAddress + " " + lastNameAddress)) {
            return false;
        } else if (!driver.findElement(this.billingAddress(3)).getText().equals(companyName)) {
            return false;
        } else if (!driver.findElement(this.billingAddress(4)).getText().equals(streetAddressName)) {
            return false;
        } else if (!driver.findElement(this.billingAddress(5)).getText().equals(secondStreetAddressName)) {
            return false;
        } else if (!driver.findElement(this.billingAddress(6)).getText().equals(cityName + " " + stateName + " " + postCode)) {
            return false;
        } else if (!driver.findElement(this.billingAddress(7)).getText().equals(countryName)) {
            return false;
        } else
            return driver.findElement(this.billingAddress(8)).getText().equals(phoneNumber);
    }


    public P08_CheckoutPage enterComment(String comment) {
        new CustomDecorator(driver, commentTextField).sendKeys(comment);
        return this;
    }
    public void placeOrder(){
        new CustomDecorator(driver, placeOrderButton).click();
    }
}
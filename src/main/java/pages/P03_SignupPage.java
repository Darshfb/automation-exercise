package pages;

import actions.CustomDecorator;
import common.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class P03_SignupPage
{
    WebDriver driver;
    public P03_SignupPage(WebDriver driver)
    {
        this.driver = driver;
    }

    private final By genders = By.xpath("(//div)[@class='radio']");
    private final By passwordText = By.id("password");
    private final By day = By.id("days");
    private final By monthList = By.xpath("(//select)[@name='months']/option");
    private final By month = By.id("months");
    private final By year = By.id("years");

    private final By addressFirstName = By.id("first_name");
    private final By addressLastName = By.id("last_name");
    private final By company = By.id("company");
    private final By address1 = By.id("address1");
    private final By address2 = By.id("address2");
    private final By country = By.id("country");
    private final By state = By.id("state");
    private final By city = By.id("city");
    private final By zipcode = By.id("zipcode");
    private final By phoneNumber = By.id("mobile_number");
    private final By registerButton = By.xpath("(//button)[@data-qa='create-account']");

    public P03_SignupPage selectGender(){
        List<WebElement> genderList = driver.findElements(genders);
        PageBase.selectRandomElement(genderList).click();
        return this;
    }
    public P03_SignupPage enterPassword(String password) {
        new CustomDecorator(driver, passwordText, 0).sendKeys(password);
        return this;
    }
    public P03_SignupPage selectDay(){
        WebElement selectDay = driver.findElement(day);
        Select select = new Select(selectDay);
        select.selectByValue("25");
        return this;
    }
    public P03_SignupPage selectMonth(){
        WebElement element = PageBase.selectRandomElement(driver.findElements(monthList));
        if(element.getText().equals("Month"))
        {
            element = PageBase.selectRandomElement(driver.findElements(monthList));
            element.click();

        }else
        {
            element.click();;
        }
        return this;
    }
    public P03_SignupPage selectYear(){
        WebElement selectYear = driver.findElement(year);
        Select select = new Select(selectYear);
        select.selectByValue("1993");
        return this;
    }
    public P03_SignupPage enterAddressFirstName(String AddressFirstName){
        new CustomDecorator(driver, addressFirstName, 0).sendKeys(AddressFirstName);
        return this;
    }
    public P03_SignupPage enterAddressLastName(String AddressLastName){
        new CustomDecorator(driver, addressLastName, 0).sendKeys(AddressLastName);
        return this;
    }
    public P03_SignupPage enterCompany(String Company){
        new CustomDecorator(driver, company, 0).sendKeys(Company);
        return this;
    }
    public P03_SignupPage enterAddress1(String Address1){
        new CustomDecorator(driver, address1, 0).sendKeys(Address1);
        return this;
    }
    public P03_SignupPage enterAddress2(String Address2){
        new CustomDecorator(driver, address2, 0).sendKeys(Address2);
        return this;
    }
    public P03_SignupPage selectCountry(){
        WebElement selectCountry = driver.findElement(country);
        Select select = new Select(selectCountry);
        select.selectByValue("New Zealand");
        return this;
    }
    public P03_SignupPage enterState(String State){
        new CustomDecorator(driver, state, 0).sendKeys(State);
        return this;
    }
    public P03_SignupPage enterCity(String City){
        new CustomDecorator(driver, city, 0).sendKeys(City);
        return this;
    }
    public P03_SignupPage enterZipcode(String Zipcode){
        new CustomDecorator(driver, zipcode, 0).sendKeys(Zipcode);
        return this;
    }
    public P03_SignupPage enterPhoneNumber(String PhoneNumber){
        new CustomDecorator(driver, phoneNumber, 0).sendKeys(PhoneNumber);
        return this;
    }
    public void register(){
        new CustomDecorator(driver, registerButton, 0).click();
    }



}


package pages;

import actions.CustomDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    private final By genders = By.xpath("(//div)/label/div");
    private final By passwordText = By.id("password");
    private final By newsletter = By.xpath("((//input)[@type='checkbox'])[1]");
    private final By checkbox = By.xpath("((//div)[@class='checkbox'])[2]");
    private final By day = By.id("days");
    private final By monthList = By.xpath("(//select)[@name='months']/option");
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
    private final By verifySignupPage = By.xpath("(//div)[@class='login-form']/h2");
    public P03_SignupPage selectGender(){
        List<WebElement> genderList = driver.findElements(genders);
        System.out.println("The total gender list is " + genderList.size());
        PageBase.selectRandomElement(genderList).click();
        return this;
    }
    public P03_SignupPage enterPassword(String password) {
        new CustomDecorator(driver, passwordText).sendKeys(password);
        return this;
    }
    public P03_SignupPage addNewsletter() {
        WebElement element = driver.findElement(newsletter);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//        new CustomDecorator(driver, newsletter, 3).click();
        element.click();
        return this;
    }

    public P03_SignupPage addCheckbox() {
        new CustomDecorator(driver, checkbox).click();
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
            element.click();
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
        new CustomDecorator(driver, addressFirstName).sendKeys(AddressFirstName);
        return this;
    }
    public P03_SignupPage enterAddressLastName(String AddressLastName){
        new CustomDecorator(driver, addressLastName).sendKeys(AddressLastName);
        return this;
    }
    public P03_SignupPage enterCompany(String Company){
        new CustomDecorator(driver, company).sendKeys(Company);
        return this;
    }
    public P03_SignupPage enterAddress1(String Address1){
        new CustomDecorator(driver, address1).sendKeys(Address1);
        return this;
    }
    public P03_SignupPage enterAddress2(String Address2){
        new CustomDecorator(driver, address2).sendKeys(Address2);
        return this;
    }
    public P03_SignupPage selectCountry(){
        WebElement selectCountry = driver.findElement(country);
        Select select = new Select(selectCountry);
        select.selectByValue("New Zealand");
        return this;
    }
    public P03_SignupPage enterState(String State){
        new CustomDecorator(driver, state).sendKeys(State);
        return this;
    }
    public P03_SignupPage enterCity(String City){
        new CustomDecorator(driver, city).sendKeys(City);
        return this;
    }
    public P03_SignupPage enterZipcode(String Zipcode){
        new CustomDecorator(driver, zipcode).sendKeys(Zipcode);
        return this;
    }
    public P03_SignupPage enterPhoneNumber(String PhoneNumber){
        new CustomDecorator(driver, phoneNumber).sendKeys(PhoneNumber);
        return this;
    }
    public P03_SignupPage register(){
        WebElement element = driver.findElement(registerButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        return this;
    }

    public Boolean verifySignupPage(){
        System.out.println("enter account " + driver.findElement(verifySignupPage).getText());
        return driver.findElement(verifySignupPage).getText().equals("ENTER ACCOUNT INFORMATION");
    }

    By accountCreated = By.xpath("(//h2)[@data-qa='account-created']");
    By continueButton = By.xpath("(//a)[@data-qa='continue-button']");

    public Boolean verifyAccountCreated(){
        System.out.println("account verified " + driver.findElement(accountCreated).getText());
        return driver.findElement(accountCreated).getText().equals("ACCOUNT CREATED!");
    }

    public void continueToAccount(){
        new CustomDecorator(driver, continueButton).click();
    }

}


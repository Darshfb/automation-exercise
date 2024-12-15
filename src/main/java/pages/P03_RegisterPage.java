package pages;

import actions.CustomDecorator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class P03_RegisterPage {
    WebDriver driver;

    public P03_RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By genders = By.xpath("(//div)[@class='radio-inline']");
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
    private final By accountCreated = By.xpath("(//h2)[@data-qa='account-created']");
    private final By continueButton = By.xpath("(//a)[@data-qa='continue-button']");

    @Getter
    private String genderName;

    public P03_RegisterPage selectGender() {
        List<WebElement> genderList = driver.findElements(genders);
        WebElement gender = PageBase.selectRandomElement(genderList);
        genderName = gender.getText();
        gender.click();
        return this;
    }

    public P03_RegisterPage enterPassword(String password) {
        new CustomDecorator(driver, passwordText).sendKeys(password);
        return this;
    }

    public P03_RegisterPage addNewsletter() {
        WebElement element = driver.findElement(newsletter);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        return this;
    }

    public P03_RegisterPage addCheckbox() {
        new CustomDecorator(driver, checkbox).click();
        return this;
    }

    public P03_RegisterPage selectDay() {
        WebElement selectDay = driver.findElement(day);
        Select select = new Select(selectDay);
        select.selectByValue("25");
        return this;
    }

    public P03_RegisterPage selectMonth() {
        WebElement element = PageBase.selectRandomElement(driver.findElements(monthList));
        if (element.getText().equals("Month")) {
            element = PageBase.selectRandomElement(driver.findElements(monthList));
            element.click();

        } else {
            element.click();
        }
        return this;
    }

    public P03_RegisterPage selectYear() {
        WebElement selectYear = driver.findElement(year);
        Select select = new Select(selectYear);
        select.selectByValue("1993");
        return this;
    }

    public P03_RegisterPage enterAddressFirstName(String firstName) {
        new CustomDecorator(driver, this.addressFirstName).sendKeys(firstName);
        return this;
    }

    public P03_RegisterPage enterAddressLastName(String lastName) {
        new CustomDecorator(driver, this.addressLastName).sendKeys(lastName);
        return this;
    }

    public P03_RegisterPage enterCompany(String Company) {
        new CustomDecorator(driver, company).sendKeys(Company);
        return this;
    }

    public P03_RegisterPage enterAddress1(String Address1) {
        new CustomDecorator(driver, address1).sendKeys(Address1);
        return this;
    }

    public P03_RegisterPage enterAddress2(String Address2) {
        new CustomDecorator(driver, address2).sendKeys(Address2);
        return this;
    }

    public P03_RegisterPage selectCountry(String selectedCountry) {
        WebElement selectCountry = driver.findElement(country);
        Select select = new Select(selectCountry);
        select.selectByValue(selectedCountry);
        return this;
    }

    public P03_RegisterPage enterState(String State) {
        new CustomDecorator(driver, state).sendKeys(State);
        return this;
    }

    public P03_RegisterPage enterCity(String City) {
        new CustomDecorator(driver, city).sendKeys(City);
        return this;
    }

    public P03_RegisterPage enterZipcode(String Zipcode) {
        new CustomDecorator(driver, zipcode).sendKeys(Zipcode);
        return this;
    }

    public P03_RegisterPage enterPhoneNumber(String PhoneNumber) {
        new CustomDecorator(driver, phoneNumber).sendKeys(PhoneNumber);
        return this;
    }

    public P03_RegisterPage register() {
        WebElement element = driver.findElement(registerButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        return this;
    }

    public Boolean verifySignupPage() {
        return driver.findElement(verifySignupPage).getText().equals("ENTER ACCOUNT INFORMATION");
    }

    public Boolean verifyAccountCreated() {
        return driver.findElement(accountCreated).getText().equals("ACCOUNT CREATED!");
    }

    public void continueToAccount() {
        new CustomDecorator(driver, continueButton).click();
    }

}


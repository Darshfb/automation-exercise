package pages;

import actions.CustomDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02_AuthPage {
    WebDriver driver;

    public P02_AuthPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loginEmail = By.xpath("(//input)[@data-qa='login-email']");
    private final By passwordText = By.name("password");
    private final By loginButton = By.xpath("//button[@data-qa='login-button']");
    private final By signUpEmail = By.xpath("(//input)[@data-qa='signup-email')");
    private final By signUpName = By.name("name");
    private final By SignUpButton = By.xpath("//button[@data-qa='signup-button']");

    public P02_AuthPage enterEmail(String email) {
        new CustomDecorator(driver, loginEmail, 0).sendKeys(email);
        return this;
    }

    public P02_AuthPage enterPassword(String password) {
        new CustomDecorator(driver, passwordText, 0).sendKeys(password);
        return this;
    }

    public P02_AuthPage clickLoginButton() {
        new CustomDecorator(driver, loginButton, 0).click();
        return this;
    }

    public P02_AuthPage enterSignUpEmail(String email) {
        new CustomDecorator(driver, signUpName, 0).sendKeys(email);
        return this;
    }

    public P02_AuthPage enterSignUpName(String name) {
        new CustomDecorator(driver, signUpName, 0).sendKeys(name);
        return this;
    }

    public P02_AuthPage clickSignUpButton() {
        new CustomDecorator(driver, SignUpButton, 0).click();
        return this;
    }

}

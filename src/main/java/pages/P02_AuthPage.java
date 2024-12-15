package pages;

import static pages.PageBase.shortWait;
import static org.junit.Assert.fail;

import actions.CustomDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class P02_AuthPage {
    WebDriver driver;

    public P02_AuthPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loginEmail = By.xpath("(//input)[@data-qa='login-email']");
    private final By passwordText = By.name("password");
    private final By loginButton = By.xpath("//button[@data-qa='login-button']");
    private final By signUpEmail = By.xpath("(//input)[@data-qa='signup-email']");
    private final By signUpName = By.name("name");
    private final By SignUpButton = By.xpath("//button[@data-qa='signup-button']");
    private final By verityAuthPage = By.xpath("((//div)/h2)[3]");
    private final By invalidLoginMSG = By.xpath("//p[normalize-space()='Your email or password is incorrect!']");
    private final By loginText = By.xpath("//h2[normalize-space()='Login to your account']");
    private final By signupErrorMsg = By.xpath("//p[normalize-space()='Email Address already exist!']");

    public P02_AuthPage enterLoginEmail(String email) {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(loginEmail));
            new CustomDecorator(driver, loginEmail).sendKeys(email);
        } catch (TimeoutException e) {
            fail("Email Element is not found");
            e.printStackTrace();
        }
        return this;
    }

    public P02_AuthPage enterPassword(String password) {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(passwordText));
            new CustomDecorator(driver, passwordText).sendKeys(password);

        } catch (TimeoutException e) {
            fail("Password Element is not found");
            e.printStackTrace();

        }
        return this;
    }


    public void clickLoginButton() {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(loginButton));
            new CustomDecorator(driver, loginButton).click();
        } catch (TimeoutException e) {
            fail("login Button Element is not found");
            e.printStackTrace();
        }
    }

    public Boolean verifyInvalidLoginMSG() {
        return driver.findElement(invalidLoginMSG).getText().equals("Your email or password is incorrect!");
    }

    public P02_AuthPage enterSignUpEmail(String email) {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(signUpEmail));
            new CustomDecorator(driver, signUpEmail).sendKeys(email);

        } catch (TimeoutException e) {
            fail("Element is not found in signup email");
            e.printStackTrace();
        }
        return this;
    }

    public P02_AuthPage enterSignUpName(String name) {
        new CustomDecorator(driver, signUpName).sendKeys(name);
        return this;
    }

    public void clickSignUpButton() {
        new CustomDecorator(driver, SignUpButton).click();
    }

    public Boolean checkNewUserSignUp() {
        String text = "New User Signup!";
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(verityAuthPage));
        return driver.findElement(verityAuthPage).getText().contains("New User Signup!");
    }

    public Boolean verifyLoginTextIsVisible() {
        return driver.findElement(loginText).getText().equals("Login to your account");
    }

    public Boolean verifySignupErrorMsg() {
        return driver.findElement(signupErrorMsg).getText().equals("Email Address already exist!");
    }
}

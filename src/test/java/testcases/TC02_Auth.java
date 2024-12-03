package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P02_AuthPage;

import static testcases.TC03_Register.password;

public class TC02_Auth extends TestBase {
    static String email;
    static String name;

    @Test(priority = 1)
    public void registerAuth() {
        email = faker.internet().emailAddress();
        name = faker.name().fullName();
        Assert.assertTrue(new P02_AuthPage(driver).checkNewUserSignUp());

        new P02_AuthPage(driver)
                .enterSignUpName(name)
                .enterSignUpEmail(email)
                .clickSignUpButton();
    }

    @Test(priority = 3)
    public void loginAuth()
    {
        if (email == null)
            email = "mostafamahmoudaboads@gmail.com";
        if(password == null)
            password = "Ab123456";

        System.out.println("The email is " + email + " and password is " + password);

        Assert.assertTrue(new P02_AuthPage(driver).verifyLoginTextIsVisible());
        new P02_AuthPage(driver)
                .enterLoginEmail(email)
                .enterPassword(password)
                .clickLoginButton();
        System.out.println("The email is " + email + " and password is " + password);
    }

    String incorrectEmail = "admin@gmail.com";
    String incorrectPassword = "admin";

    @Test(priority = 1)
    public void enterIncorrectEmailAndPassword()
    {
        Assert.assertTrue(new P02_AuthPage(driver).verifyLoginTextIsVisible());

        new P02_AuthPage(driver)
                .enterLoginEmail(incorrectEmail)
                .enterPassword(incorrectPassword)
                .clickLoginButton();

        Assert.assertTrue(new P02_AuthPage(driver).verifyInvalidLoginMSG());

    }

    @Test(priority = 1)
    public void registerAuthWithExistingEmail()
    {
        String email = "mostafamahmoudaboads@gmail.com";
        String name = faker.name().fullName();

        Assert.assertTrue(new P02_AuthPage(driver).checkNewUserSignUp());

        new P02_AuthPage(driver)
                .enterSignUpName(name)
                .enterSignUpEmail(email)
                .clickSignUpButton();

        Assert.assertTrue(new P02_AuthPage(driver).verifySignupErrorMsg());


    }
}

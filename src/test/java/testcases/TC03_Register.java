package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P03_SignupPage;


public class TC03_Register extends TestBase {

    @Test(priority = 3)
    public void registerWithValidData() {
        TC02_Auth.password = faker.internet().password();
        Assert.assertTrue(new P03_SignupPage(driver).verifySignupPage());
        boolean isAccountCreated = new P03_SignupPage(driver)
                .selectGender()
                .enterPassword(TC02_Auth.password)
                .selectDay()
                .selectMonth()
                .selectYear()
                .addNewsletter()
                .addCheckbox()
                .enterAddressFirstName(faker.address().firstName())
                .enterAddressLastName(faker.address().lastName())
                .enterCompany(faker.company().name())
                .enterAddress1(faker.address().streetAddress())
                .enterAddress2(faker.address().secondaryAddress())
                .selectCountry()
                .enterState(faker.address().state())
                .enterCity(faker.address().city())
                .enterZipcode(faker.address().zipCode())
                .enterPhoneNumber(faker.phoneNumber().phoneNumber())
                .register()
                .verifyAccountCreated();
        Assert.assertTrue(isAccountCreated);

        new P03_SignupPage(driver).continueToAccount();
    }
}

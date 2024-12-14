package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P03_RegisterPage;


public class TC03_Register extends TestBase
{
    static String password;
    static String firstNameAddress;
    static String lastNameAddress;
    static String companyName;
    static String streetAddressName;
    static String secondStreetAddressName;
    static String countryName;
    static String phoneNumber;
    static String cityName;
    static String stateName;
    static String postCode;
    static String genderName;
    @Test(priority = 1)
    public void registerWithValidData()
    {
        password = faker.internet().password();
        firstNameAddress = faker.address().firstName();
        lastNameAddress = faker.address().lastName();
        companyName = faker.company().name();
        streetAddressName = faker.address().streetAddress();
        secondStreetAddressName = faker.address().streetName();
        countryName = "New Zealand";
        phoneNumber = faker.phoneNumber().phoneNumber();
        cityName = faker.address().city();
        stateName = faker.address().state();
        postCode = faker.address().zipCode();

        Assert.assertTrue(new P03_RegisterPage(driver).verifySignupPage());
        genderName = new P03_RegisterPage(driver).selectGender().getGenderName();

        boolean isAccountCreated = new P03_RegisterPage(driver).enterPassword(password)
                .selectDay()
                .selectMonth()
                .selectYear()
                .addNewsletter()
                .addCheckbox()
                .enterAddressFirstName(firstNameAddress)
                .enterAddressLastName(lastNameAddress)
                .enterCompany(companyName)
                .enterAddress1(streetAddressName)
                .enterAddress2(secondStreetAddressName)
                .selectCountry(countryName)
                .enterState(stateName)
                .enterCity(cityName)
                .enterZipcode(postCode)
                .enterPhoneNumber(phoneNumber)
                .register()
                .verifyAccountCreated();
        Assert.assertTrue(isAccountCreated);

        new P03_RegisterPage(driver).continueToAccount();
    }

}

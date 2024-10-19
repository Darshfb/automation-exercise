import org.testng.annotations.Test;
import pages.P03_SignupPage;


public class TC03_Register extends TestBase
{

    @Test(priority = 3)
    public void registerWithValidData()
    {
        TC02_Auth.password = TC02_Auth.faker.internet().password();
        new P03_SignupPage(driver)
                .selectGender()
                .enterPassword(TC02_Auth.password)
                .selectDay()
                .selectMonth()
                .selectYear()
                .enterAddressFirstName(TC02_Auth.faker.address().firstName())
                .enterAddressLastName(TC02_Auth.faker.address().lastName())
                .enterCompany(TC02_Auth.faker.company().name())
                .enterAddress1(TC02_Auth.faker.address().streetAddress())
                .enterAddress2(TC02_Auth.faker.address().secondaryAddress())
                .selectCountry()
                .enterState(TC02_Auth.faker.address().state())
                .enterCity(TC02_Auth.faker.address().city())
                .enterZipcode(TC02_Auth.faker.address().zipCode())
                .enterPhoneNumber(TC02_Auth.faker.phoneNumber().phoneNumber())
                .register();
    }
}

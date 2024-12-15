package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P09_PaymentPage;

import java.awt.*;

import static testcases.TC02_Auth.email;
import static testcases.TC02_Auth.name;

public class TC09_Payment extends TestBase
{

    String cardNumber;
    String cvc;
    String cardUserName;
    String expiryMonthDate;
    String expiryYearDate;


    @Test
    public void payAndConfirmOrder()
    {


        cardNumber = faker.business().creditCardNumber();
        cvc = faker.number().digits(3);
        cardUserName = faker.name().fullName();
        expiryMonthDate = faker.number().digits(2);
        expiryYearDate = "2027";

        new P09_PaymentPage(driver).enterNameOnCard(cardUserName)
                .enterCardNumber(cardNumber)
                .enterCvc(cvc)
                .enterExpiryMonthDate(expiryMonthDate)
                .enterExpiryYearDate(expiryYearDate)
                .submitOrder();
    }

    @Test
    public void verifySuccessPaymentAndPlaceOrder(){
        Assert.assertTrue(new P09_PaymentPage(driver).verifySuccessPaymentAndPlaceOrder());
    }

    @Test
    public void deleteAccountAndReturnBackToHomePage() {
        new P01_HomePage(driver).deleteAccount();
        TC03_Register.password = null;
        email = null;
        name = null;
        Assert.assertTrue(new P01_HomePage(driver).verifyDeleteAccount());
        new P01_HomePage(driver).returnToHomePage();
    }

    @Test
    public void downloadInvoiceFile(){
        try {
            new P09_PaymentPage(driver).downloadInvoiceFile();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }


}

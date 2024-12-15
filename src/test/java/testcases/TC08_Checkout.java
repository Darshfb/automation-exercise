package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P07_CartPage;
import pages.P08_CheckoutPage;

import static testcases.TC03_Register.*;

public class TC08_Checkout extends TestBase
{

    @Test(description = "Check user name after registration and navigate to cart page")
    public void enterCartPage(){
        new P01_HomePage(driver).enterCartPage();
    }

    @Test
    public void proceedToCheckout() {
        new P07_CartPage(driver).clickProceedToCheckout();
    }

    @Test
    public void checkAddressDetails(){
        Assert.assertTrue(new P08_CheckoutPage(driver)
                        .verifyAddressDetails(firstNameAddress,
                                lastNameAddress,
                                companyName,
                                streetAddressName,
                                secondStreetAddressName,
                                cityName,
                                stateName,
                                postCode,
                                countryName,
                                phoneNumber,
                                genderName
                        ),
                "Address details are not correct");

    }

    String comment;
    @Test
    public void placeOrder(){
        comment = faker.lorem().sentence();
        new P08_CheckoutPage(driver).enterComment(comment).placeOrder();
    }

    @Test()
    public void addItemsToCart(){
        new P01_HomePage(driver).addItemsToCart("1").addItemsToCart("2");
    }

    @Test
    public void removeItemFromCart(){
        new P07_CartPage(driver).removeItemFromCart();
        Assert.assertTrue(new P07_CartPage(driver).checkCartListQuantities("1"), "All products quantity aren't equal to 1");
    }
    @Test(priority = 3)
    public void verifyAddressAndBillingAddress(){
        checkAddressDetails();
        Assert.assertTrue(new P08_CheckoutPage(driver)
                        .verifyBillingAddressDetails(firstNameAddress,
                                lastNameAddress,
                                companyName,
                                streetAddressName,
                                secondStreetAddressName,
                                cityName,
                                stateName,
                                postCode,
                                countryName,
                                phoneNumber,
                                genderName
                        ),
                "Address details are not correct");
    }
}

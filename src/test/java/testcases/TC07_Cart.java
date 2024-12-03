package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P07_CartPage;

import static testcases.TC06_ProductDetails.productDetailsName;

public class TC07_Cart extends TestBase
{
    @Test(priority = 3)
    public void sendSubscriptionEmail() {
        new P07_CartPage(driver).scrollToSubscriptionEmailAndSendEmail(faker.internet().emailAddress());
        Assert.assertTrue(new P07_CartPage(driver).checkSubscriptionEmailSentSuccessfully());
    }

    @Test(priority = 4)
    public void checkListCartItemsNames(){
        Assert.assertTrue(new P07_CartPage(driver).checkListCartItemsNames(), "All Products Names aren't the same");
        Assert.assertTrue(new P07_CartPage(driver).checkCartListItemPrices(), "All Products price aren't the same");
        Assert.assertTrue(new P07_CartPage(driver).checkCartListQuantities("1"), "All products quantity aren't equal to 1");
    }

    @Test(priority = 6)
    public void verifyItemNameAndQuantity()
    {
        // Verify item quantity in cart
        Assert.assertTrue(new P07_CartPage(driver).verifyItemQuantity("4"));
        // verify item name in cart
        Assert.assertTrue(new P07_CartPage(driver).verifyItemName(productDetailsName));
    }

    @Test(priority = 3)
    public void enterLoginRegisterPage(){
        new P07_CartPage(driver).clickProceedToCheckout().clickRegisterLoginButton();
    }

    @Test(priority = 3)
    public void proceedToCheckout(){
        new P07_CartPage(driver).clickProceedToCheckout();
    }
}

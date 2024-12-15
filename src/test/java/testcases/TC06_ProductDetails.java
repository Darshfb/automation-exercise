package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P06_productDetailsPage;

public class TC06_ProductDetails extends TestBase {

    @Test(priority = 4)
    public void checkProductDetailsVisible() {
        Assert.assertTrue(new P06_productDetailsPage(driver).checkProductDetailsVisible());
        Assert.assertTrue(new P06_productDetailsPage(driver).verifyReviewTextVisible());
    }

    static String productDetailsName;

    @Test(priority = 5)
    public void checkIt() {
        productDetailsName = new P06_productDetailsPage(driver).addToCart(4).getProductName();
        new P06_productDetailsPage(driver).goToCart();

    }

    @Test(priority = 4)
    public void submitReview(){
        new P06_productDetailsPage(driver)
                .enterName(faker.name().fullName())
                .enterEmail(faker.internet().emailAddress())
                .enterReview(faker.lorem().sentence(30))
                .submitReview();

        Assert.assertTrue(new P06_productDetailsPage(driver).checkReviewSentSuccessfully());

    }
}

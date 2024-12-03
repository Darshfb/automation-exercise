package testcases;

import org.testng.annotations.Test;
import pages.P06_productDetailsPage;

public class TC06_ProductDetails extends TestBase {

    @Test(priority = 4)
    public void checkProductDetailsVisible() {
        new P06_productDetailsPage(driver).checkProductDetailsVisible();
    }

    static String productDetailsName;

    @Test(priority = 5)
    public void checkIt() {
        productDetailsName = new P06_productDetailsPage(driver).addToCart(4).getProductName();
        new P06_productDetailsPage(driver).goToCart();

    }
}

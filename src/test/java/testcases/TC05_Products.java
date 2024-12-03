package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P05_ProductsPage;

public class TC05_Products extends TestBase
{
    @Test(priority = 3)
    public void checkAllProducts()
    {
        new P05_ProductsPage(driver).clickFirstViewProduct();
    }

    @Test(priority = 3)
    public void searchForProduct(){
        new P05_ProductsPage(driver).searchProduct("Men Tshirt").clickSearchButton();
        Assert.assertTrue(new P05_ProductsPage(driver).checkSearchedProductVisible());
    }

    @Test(priority = 3)
    public void addFirstItemToCart(){
        new P05_ProductsPage(driver)
                .addItemToCart("1", false)
                .addItemToCart("2", true);
    }
}

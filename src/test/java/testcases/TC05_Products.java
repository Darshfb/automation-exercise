package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P05_ProductsPage;

public class TC05_Products extends TestBase
{
    @Test(priority = 3)
    public void openFirstVIewProduct()
    {
        new P05_ProductsPage(driver).clickFirstViewProduct();
    }

    @Test(priority = 3)
    public void searchForProduct(){
        new P05_ProductsPage(driver).searchProduct("Top").clickSearchButton();
        Assert.assertTrue(new P05_ProductsPage(driver).checkSearchedProductVisible());
        Assert.assertTrue(new P05_ProductsPage(driver).verifyBrandProductsVisible());
    }

    @Test(priority = 3)
    public void addFirstItemToCart(){
        new P05_ProductsPage(driver)
                .addItemToCartWithIndex("1", false)
                .addItemToCartWithIndex("2", true);
    }

    @Test
    public void verifyBrandsOnLeftSideBarVisible(){
        Assert.assertTrue(new P05_ProductsPage(driver).verifyBrandsVisible());
    }
    static String randomBrand = "";

    @Test
    public void selectRandomBrand(){
        new P05_ProductsPage(driver).selectBrand();
        randomBrand = new P05_ProductsPage(driver).getBrandName();
    }

    @Test
    public void verifySelectedBrandAndProductsAreDisplayed(){
        Assert.assertTrue(new P05_ProductsPage(driver).verifyBrandSelected(randomBrand));
        Assert.assertTrue(new P05_ProductsPage(driver).verifyBrandProductsVisible());
    }
    @Test
    public void selectOtherBrandAndVerifyIt(){
        selectRandomBrand();
        verifySelectedBrandAndProductsAreDisplayed();
    }

    @Test(priority = 4)
    public void addItemsToCart(){
        new P05_ProductsPage(driver).addProductItemsToCart();
    }

    @Test(priority = 5)
    public void enterCartPage(){
        new P05_ProductsPage(driver).enterCartPage();
    }

    @Test(priority = 3)
    public void openProductDetails()
    {
        new P01_HomePage(driver).viewProductDetails();
    }

}

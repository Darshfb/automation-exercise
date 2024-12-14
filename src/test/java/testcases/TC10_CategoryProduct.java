package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P10_CategoryProduct;

import static testcases.TC01_Home.subCategoryName;

public class TC10_CategoryProduct extends TestBase {

    @Test
    public void verifySelectedCategoryProducts() {
        Assert.assertTrue(new P10_CategoryProduct(driver).verifySelectedCategoryProduct(subCategoryName));
    }

    @Test
    public void selectMenCategory() {
        new P01_HomePage(driver).selectCategory("2");
    }

    @Test
    public void selectmenSubCategory() {
        subCategoryName = new P01_HomePage(driver).selectMenSubCategory().getSubCategoryName();
        verifySelectedCategoryProducts();
    }
}

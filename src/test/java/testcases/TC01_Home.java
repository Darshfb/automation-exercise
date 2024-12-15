package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_HomePage;

import static testcases.TC02_Auth.email;
import static testcases.TC02_Auth.name;


public class TC01_Home extends TestBase {
    @Test(priority = 1)
    public void checkHomePage() {
        Assert.assertTrue(new P01_HomePage(driver).checkPage());
    }

    @Test(priority = 1, dependsOnMethods = "testcases.TC01_Home.checkHomePage")
    public void enterAuthPage() {
        new P01_HomePage(driver).openAuthPage();
    }

    @Test(priority = 4, dependsOnMethods = {"testcases.TC02_Auth.loginAuth"})
    public void checkUsernameIsVisible() {
        if (name == null)
            name = "Mostafa Mahmoud";
        Assert.assertEquals(new P01_HomePage(driver).getUsername(), name);
    }

    @Test(priority = 4, dependsOnMethods = {"testcases.TC03_Register.registerWithValidData"})
    public void checkUsernameIsVisibleAfterRegister() {
        Assert.assertEquals(new P01_HomePage(driver).getUsername(), name);
    }

    @Test(priority = 2)
    public void logout() {
        new P01_HomePage(driver).logout();
    }

    @Test(priority = 4)
    public void logoutAfterLogin() {
        new P01_HomePage(driver).logout();
    }

    @Test(priority = 4)
    public void deleteAccountAndReturnBackToHomePage() {
        new P01_HomePage(driver).deleteAccount();
        TC03_Register.password = null;
        email = null;
        name = null;
        Assert.assertTrue(new P01_HomePage(driver).verifyDeleteAccount());
        new P01_HomePage(driver).returnToHomePage();
    }

    @Test(priority = 2)
    public void enterContactUsPage() {
        new P01_HomePage(driver).enterContactUsPage();
    }

    @Test(priority = 2)
    public void checkTestCasesPage(){
        new P01_HomePage(driver).enterTestCasesPage();
        Assert.assertTrue(new P01_HomePage(driver).verifyUserNavigateToTestCasesPage());
    }

    @Test(priority = 2)
    public void enterProductsPage(){
        new P01_HomePage(driver).enterProductsPage();
        Assert.assertTrue(new P01_HomePage(driver).checkAllProductsText());
    }

    @Test(priority = 2)
    public void sendSubscriptionEmail(){
        new P01_HomePage(driver).sendSubscriptionEmail(faker.internet().emailAddress());
        Assert.assertTrue(new P01_HomePage(driver).verifySubscribeSentSuccessfully());
    }

    @Test(priority = 2)
    public void enterCartPage(){
        new P01_HomePage(driver).enterCartPage();
    }

    @Test(priority = 2)
    public void viewProductDetails()
    {
        new P01_HomePage(driver).viewProductDetails();

    }

    @Test(priority = 2)
    public void addItemsToCart(){
        new P01_HomePage(driver).addItemsToCart("1").addItemsToCart("2");
    }

    @Test
    public void verifyVisibilityCategorySideBar(){
        Assert.assertTrue(new P01_HomePage(driver).checkCategorySideBarVisible());
    }

    @Test
    public void selectWomenCategory(){
        new P01_HomePage(driver).selectCategory("1");
    }

    static String subCategoryName;
    @Test
    public void selectWomenSubCategory(){
        subCategoryName =  new P01_HomePage(driver).selectWomenSubCategory().getSubCategoryName();
    }

    @Test
    public void checkRecommendItems()
    {
        Assert.assertTrue(new P01_HomePage(driver).verifyRecommendedItemsVisible());
    }

    static String itemName = "";
    @Test
    public void selectRecommendedItemAndViewCart()
    {
        itemName =  new P01_HomePage(driver).selectRandomRecommendedItem().clickViewCart().getItemName();
    }

    @Test(priority = 2)
    public void verifyUserAfterRegisterAndAddItemToCartThenEnterCart()
    {
        checkUsernameIsVisibleAfterRegister();
        new P01_HomePage(driver).addItemsToCart("1").addItemsToCart("2").enterCartPage();
    }

    @Test
    public void scrollDownToTheBottomOfThePageAndScrollUpWithArrowButton(){
        Assert.assertTrue(new P01_HomePage(driver).scrollToTheBottomPage().verifySubscriptionTextVisible());
        Assert.assertTrue(new P01_HomePage(driver).scrollUp().verifyBannerTextVisible());
    }

    @Test
    public void scrollDownToTheBottomOfThePageAndScrollUpWithFunctionality(){
        Assert.assertTrue(new P01_HomePage(driver).scrollToTheBottomPage().verifySubscriptionTextVisible());
        Assert.assertTrue(new P01_HomePage(driver).scrollUpWithoutArrowButton().verifyBannerTextVisible());

    }


}

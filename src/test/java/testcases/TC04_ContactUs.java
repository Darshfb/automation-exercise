package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P04_ContactUsPage;

public class TC04_ContactUs extends TestBase {
    @Test(priority = 3)
    public void contactUs() {
        // Verify that home page is visible successfully
        Assert.assertTrue(new P04_ContactUsPage(driver).verifyGetInTouchTitleText());
        new P04_ContactUsPage(driver)
                .enterName(faker.name().fullName())
                .enterEmail(faker.internet().emailAddress())
                .enterSubject(faker.lorem().sentence(3))
                .enterMessage(faker.lorem().paragraphs(30).toString())
                .chooseFile()
                .submitContactUs();

        Assert.assertTrue(new P04_ContactUsPage(driver).checkMessageSentSuccessfully());
        new P04_ContactUsPage(driver).returnBackToHomePage();
    }
}

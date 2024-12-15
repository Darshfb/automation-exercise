package pages;

import actions.CustomDecorator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static pages.PageBase.waitForElement;

public class P04_ContactUsPage {
    private final WebDriver driver;

    public P04_ContactUsPage(WebDriver driver) {
        this.driver = driver;
    }


    private final By getInTouchTitleText = By.xpath("//h2[normalize-space()='Get In Touch']");
    private final By nameTextField = By.xpath("//input[@placeholder='Name']");
    private final By emailTextField = By.xpath("//input[@placeholder='Email']");
    private final By subjectTextField = By.xpath("//input[@placeholder='Subject']");
    private final By messageTextField = By.xpath("//textarea[@id='message']");
    private final By chooseFileButton = By.xpath("//input[@name='upload_file']");
    private final By submitButton = By.xpath("//input[@name='submit']");


    public Boolean verifyGetInTouchTitleText() {
        return driver.findElement(getInTouchTitleText).getText().equals("GET IN TOUCH");
    }

    public P04_ContactUsPage enterName(String name) {
        waitForElement(driver, nameTextField);
        new CustomDecorator(driver, nameTextField).sendKeys(name);

        return this;
    }

    public P04_ContactUsPage enterEmail(String email) {
        waitForElement(driver, emailTextField);
        new CustomDecorator(driver, emailTextField).sendKeys(email);
        return this;
    }

    public P04_ContactUsPage enterSubject(String subject) {
        waitForElement(driver, subjectTextField);
        new CustomDecorator(driver, subjectTextField).sendKeys(subject);
        return this;
    }

    public P04_ContactUsPage enterMessage(String message) {
        waitForElement(driver, messageTextField);
        new CustomDecorator(driver, messageTextField).sendKeys(message);
        return this;
    }

    public P04_ContactUsPage chooseFile() {
        waitForElement(driver, chooseFileButton);
        // default file from the project file "file.jpg" in sources folder
        File uploadFile = new File(System.getProperty("user.dir") + "\\sources/file.jpg");
        new CustomDecorator(driver, chooseFileButton).sendKeys(uploadFile.getAbsolutePath());
        return this;
    }

    public void submitContactUs() {
        waitForElement(driver, submitButton);
        new CustomDecorator(driver, submitButton).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public Boolean checkMessageSentSuccessfully() {
        final By messageThatSentFromContactUs = By.xpath("//div[@class='status alert alert-success']");
        waitForElement(driver, messageThatSentFromContactUs);
        return driver.findElement(messageThatSentFromContactUs).getText().equals("Success! Your details have been submitted successfully.");
    }

    public void returnBackToHomePage() {
        final By backToHomePage = By.xpath("//span[normalize-space()='Home']");
        waitForElement(driver, backToHomePage);
        new CustomDecorator(driver, backToHomePage).click();
    }
}
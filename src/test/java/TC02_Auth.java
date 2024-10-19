import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.P02_AuthPage;

public class TC02_Auth extends TestBase
{
    static Faker faker;
    static String email;
    static String password;

    @Test(priority = 2)
    public void registerAuth(){
        faker = new Faker();
        email = faker.internet().emailAddress();
        new P02_AuthPage(driver)
                .enterSignUpName(faker.name().fullName())
                .enterSignUpEmail(email)
                .clickSignUpButton();
    }

    @Test(priority = 2)
    public void loginAuth()
    {
        new P02_AuthPage(driver)
                .enterEmail(email)
                .enterPassword("")
                .clickLoginButton();
    }
}

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestProcess {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeAll
    public static void setup() {
        System.setProperty(Utils.WEBDRIVER, Utils.CHROME_DRIVER_LOCATION);
    }


    private static LoginPage loginPage = new LoginPage(driver);
    private static ProfilePage profilePage = new ProfilePage(driver);


    @ParameterizedTest()
    @DisplayName("Successful Login")
    @CsvFileSource(resources = "/Credentials.csv", numLinesToSkip = 1)
    public void successfulLogin(String email, String pass) {
        loginPage.maximizeWindow();

        loginPage.openLoginPage();

        loginPage.clickCookieConsentAgreeButton();

        loginPage.setEmail(email);
        loginPage.setPass(pass);
        loginPage.clickLoginButton();

        profilePage.verifySuccess();

        profilePage.logout();

    }
    @ParameterizedTest()
    @DisplayName("Incorrect Credentials")
    @CsvFileSource(resources = "/IncorrectCredentials.csv", numLinesToSkip = 1)
    public void incorrectCredentials(String email, String pass) {
        loginPage.maximizeWindow();

        loginPage.openLoginPage();

        loginPage.clickCookieConsentAgreeButton();

        loginPage.setEmail(email);
        loginPage.setPass(pass);
        loginPage.clickLoginButton();

        loginPage.verifyIncorrectCredentials();

    }

    @Test
    @DisplayName("Missing Password")
    public void missingPassword() {
        loginPage.maximizeWindow();

        loginPage.openLoginPage();

        loginPage.clickCookieConsentAgreeButton();

        loginPage.setEmail("teszt.emil@outlook.com");
        loginPage.clickLoginButton();

        loginPage.verifyMissingPassword();

    }

    @Test
    @DisplayName("Missing Email")
    public void missingEmail() {
        loginPage.maximizeWindow();

        loginPage.openLoginPage();

        loginPage.clickCookieConsentAgreeButton();

        loginPage.setPass("ABC-123");
        loginPage.clickLoginButton();

        loginPage.verifyMissingEmail();

    }

    @AfterAll
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
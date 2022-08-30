import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
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
    public void enterData(String email, String pass) {
        loginPage.maximizeWindow();

        loginPage.openLoginPage();

        loginPage.clickCookieConsentAgreeButton();

        loginPage.openLoginPage();

        loginPage.setEmail(email);
        loginPage.setPass(pass);
        loginPage.clickLoginButton();

        profilePage.verifySuccess();

    }

    @AfterAll
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
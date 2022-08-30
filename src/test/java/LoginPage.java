import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {

    @FindBy(id = "name")
    private WebElement fieldEmail;

    @FindBy(id = "password")
    private WebElement fieldPass;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='login']/div[1]")
    private WebElement feedbackText;

    @FindBy(xpath = "//div[5]/div[2]/button")
    private WebElement cookieConsentAgreeButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void maximizeWindow() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    public void openLoginPage() {
        driver.get(Utils.BASE_URL);
    }

    public void clickLoginButton() {
        clickOn(loginButton);
    }

    @Override
    protected void navigate(String URL) {
        super.navigate(URL);
    }

    public void setEmail(String email) {
        waitForClickable(fieldEmail);
        fieldEmail.sendKeys(email);
    }

    public void setPass(String date) {
        waitForClickable(fieldPass);
        fieldPass.sendKeys(date);
    }

    public void verifyUnSuccess() {
        waitForVisibility(feedbackText);
        Assert.assertTrue(feedbackText.getText().endsWith("nem egyezik."));
    }

    public void clickCookieConsentAgreeButton() {

        try {
            clickOn(cookieConsentAgreeButton);
        } catch (Exception ignore) {
        }
    }
}

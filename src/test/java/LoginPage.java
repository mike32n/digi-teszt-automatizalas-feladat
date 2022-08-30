import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class LoginPage extends PageObject {

    @FindBy(id = "name")
    private WebElement fieldEmail;

    @FindBy(id = "password")
    private WebElement fieldPass;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='login']/div[1]")
    private WebElement feedbackText;

    @FindBy(xpath = "//*[@id='home']//div[3]/div/div[1]")
    private WebElement passAlertText;

    @FindBy(xpath = "//*[@id='home']/form/div[2]/div/div[1]")
    private WebElement emailAlertText;

    @FindBy(xpath = "//div[5]/div[2]/button")
    private WebElement cookieConsentAgreeButton;

    @FindBy(xpath = "/html/body/div[5]")
    private WebElement cookieConsentTextArea;

    public LoginPage(WebDriver driver) {
        super(driver);
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

    public void verifyIncorrectCredentials() {
        waitForVisibility(feedbackText);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        String alert = feedbackText.getText();
        Assert.assertTrue(alert.contains("nem egyezik"));
    }

    public void verifyMissingPassword() {
        waitForVisibility(passAlertText);
        String alert = passAlertText.getText();
        Assert.assertTrue(alert.contains("jelszó mező kitöltése kötelező"));
    }

    public void verifyMissingEmail() {
        waitForVisibility(emailAlertText);
        String alert = emailAlertText.getText();
        Assert.assertTrue(alert.contains("név mező kitöltése kötelező"));
    }

    public void clickCookieConsentAgreeButton() {

        try {
            clickOn(cookieConsentAgreeButton);
            waitForInvisibility(cookieConsentTextArea);
        } catch (Exception ignore) {
        }
    }
}

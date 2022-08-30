import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends PageObject {

    @FindBy(xpath = "//*[@id='profile-holder']//span")
    private WebElement userEmail;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void verifySuccess() {
        waitForVisibility(userEmail);
        Assert.assertTrue(userEmail.getText().equalsIgnoreCase("teszt.emil@outlook.com"));
    }
}

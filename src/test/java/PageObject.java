import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
    protected WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void maximizeWindow() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    protected void clickOn(WebElement webElement) {
        waitForClickable(webElement);
        webElement.click();
    }

    protected void waitForClickable(WebElement webElement) {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void waitForVisibility(WebElement webElement) throws Error {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitForInvisibility(WebElement webElement) throws Error {
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.invisibilityOf(webElement));
    }

    protected void navigate(String URL) {
        driver.get(URL);
    }
}


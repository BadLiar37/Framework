package page;

import driver.DriverSingleton;
import model.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MegaTopLoginPage extends AbstractPage {
    private static final String PAGE_URL = "https://my.megatop.by/login";

    @FindBy(id = "input-39")
    private WebElement inputPhoneNumber;

    @FindBy(id = "input-40")
    private WebElement inputPassword;

    @FindBy(xpath = "//*[contains(@class,'primary-button v-btn v-btn--block v-btn--depressed v-btn--flat v-btn--outlined theme--light v-size--default')]")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//*[contains(@class,'v-messages__message')]")
    private WebElement errorMessage;

    public MegaTopLoginPage(){
        super(DriverSingleton.getInstance());
    }

    public MegaTopLoginPage openPage(){
        driver.get(PAGE_URL);
        return this;
    }

    public MegaTopPersonalAccountPage login(User user)
    {
        inputPhoneNumber.sendKeys(user.getPhoneNumber());
        inputPassword.sendKeys(user.getPassword());
        buttonSubmit.click();
        return new MegaTopPersonalAccountPage();
    }

    public String  loginWithInvalidPhoneNumber(User user)
    {
        inputPhoneNumber.sendKeys(user.getPhoneNumber());
        inputPassword.sendKeys(user.getPassword());
        buttonSubmit.click();
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(errorMessage)).getText().trim();
    }

}

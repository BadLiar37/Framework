package page;

import driver.DriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.TestDataReader;

public class MegaTopItemPage extends AbstractPage{
    private final String itemPage;
    public static final String TESTDATA_GOOD_MODEL = "test.data.model";
    public static final String TESTDATA_GOOD_PRICE = "test.data.price";

    @FindBy(xpath = "//*[contains(@class,'svg-ico-user')]")
    private WebElement buttonForLogin;

    @FindBy(xpath = "//*[contains(@class,'products__favorite toggle-state-js')]")
    private WebElement addToFavourite;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/section[1]/div[2]/div[4]/div/label[4]/span")
    private WebElement setSizeButton;

    @FindBy(xpath = "//*[contains(@class,'btn-default btn-with-icon btn-exist')]")
    private WebElement reserveButton;

    @FindBy(xpath = "//*[contains(@class,'model')]")
    private WebElement model;

    @FindBy(xpath = "//*[contains(@class,'price')]")
    private WebElement price;

    @FindBy(xpath = "//*[contains(@data-shopid,'3003400')]")
    private WebElement shop;


    @FindBy(xpath = "//*[contains(@class,'book-button')]")
    private WebElement bookButton;

    @FindBy(xpath = "//*[contains(@class,'close-icon')]")
    private WebElement closeIcon;

    public MegaTopItemPage(){
        super(DriverSingleton.getInstance());
        itemPage = TestDataReader.getTestData("test.data.item.url");
    }

    public MegaTopItemPage openPage() {
        driver.get(itemPage);
        return this;
    }

    public MegaTopLoginPage openLoginPage(){
        buttonForLogin.click();
        return new MegaTopLoginPage();
    }

    public  boolean addToFavourite() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(addToFavourite)).click();
        return addToFavourite.getAttribute("class").endsWith("active");
    }

    public  MegaTopItemPage inputSize() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(setSizeButton)).click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(reserveButton)).click();
        return new MegaTopItemPage();
    }

    public  boolean isCorrectOptions() {
        return model.getText().startsWith(TestDataReader.getTestData(TESTDATA_GOOD_MODEL)) &&
                price.getText().substring(0,5).equals(TestDataReader.getTestData(TESTDATA_GOOD_PRICE));
    }

    public  MegaTopItemPage addToOrder() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(shop)).click();
        bookButton.click();
        closeIcon.click();
        return new MegaTopItemPage();
    }
}

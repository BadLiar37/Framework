package page;

import driver.DriverSingleton;
import model.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class MegaTopPersonalAccountPage extends AbstractPage{
    private static final String PAGE_URL = "https://my.megatop.by/login";

    @FindBy(xpath = "//*[contains(@class,'products__list__holder')]")
    private WebElement itemsHolder;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div[3]/div[2]/div/div/div[3]/div/div[2]/div/div/div/div[1]/div/div/div/div[3]/div[2]")
    private WebElement userPhoneNumberString;

    public MegaTopPersonalAccountPage(){
        super(DriverSingleton.getInstance());
    }

    @FindBy(xpath = "//a[contains(@href,'/catalog/muzhchiny/')]")
    private WebElement manCategorySwitcher;

    @FindBy(id = "bx_3966226736_247444_362ce596257894d11ab5c1d73d13c755")
    private WebElement needGood;

    @FindBy(id = "input-47")
    private WebElement advertisementCheckbox;

    @FindBy(xpath = "//*[contains(@class,'status-chip status-chip__active')]")
    private WebElement bonusStatus;

    @FindBy(xpath = "//*[contains(@class,'change-button')]")
    private WebElement buttonToChangeUserData;

    @FindBy(id = "input-146")
    private WebElement changeUserNameTextField;

    @FindBy(xpath = "//*[contains(@class,'primary-button font-weight-bold v-btn v-btn--block v-btn--depressed theme--light v-size--default')]")
    private WebElement saveChangesButton;

    @FindBy(xpath = "//*[contains(@class,'user-data-field-value text-no-wrap half-width')]")
    private WebElement userNameField;

    @FindBy(xpath = "//a[contains(@href,'/profile/orders')]")
    private WebElement userBook;

    @FindBy(xpath = "//[contains(@class,'d-flex pr-0 col col-4)']")
    private WebElement deleteGoodButton;

    @FindBy(xpath = "//[contains(@class,'v-btn__content']")
    private WebElement deleteGoodApplyButton;

    private String item = "//*[contains(@class,'order-card v-card v-sheet theme--light elevation-2')]";
    private String itemInfo = "//*[contains(@class,'pt-0 pb-1 product-title col col-12')]";
    private String itemPrice = "//*[contains(@class,'py-0 price-text col col-12')]";

    public MegaTopPersonalAccountPage openPage() {
        driver.get(PAGE_URL);

        return this;
    }


    public String getLoggedPhoneNumber() {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(userPhoneNumberString)).getText().substring(5);
    }

    public boolean isBonusActive(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(userPhoneNumberString)).click();
        return  bonusStatus.getAttribute("class").endsWith("active");
    }

    public boolean changeUserName(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(buttonToChangeUserData)).click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(changeUserNameTextField)).click();
        changeUserNameTextField.clear();
        changeUserNameTextField.sendKeys("Саня");
        saveChangesButton.click();
        return  new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(userNameField)).getText().endsWith("Саня");
    }

    public boolean findGoodInBook(){
        userBook.click();
        List<WebElement> webElements = driver.findElements(By.xpath(item));

        List<Item> items = webElements.stream().map(i -> new Item(i.findElement(By.xpath(item)).getText(),
                i.findElement(By.xpath(itemInfo)).getText(),
                Integer.parseInt(i.findElement(By.xpath(itemPrice)).getText().replace(".",""))))
                .collect(Collectors.toList());
        return items.size()>0;
    }

    public boolean removeGoodFromBook(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(userBook)).click();
        deleteGoodButton.click();
        List<WebElement> webElements = driver.findElements(By.xpath(item));

        List<Item> items = webElements.stream().map(i -> new Item(i.findElement(By.xpath(item)).getText(),
                i.findElement(By.xpath(itemInfo)).getText(),
                Integer.parseInt(i.findElement(By.xpath(itemPrice)).getText().replace(".",""))))
                .collect(Collectors.toList());
        return items.size()==0;
    }
}


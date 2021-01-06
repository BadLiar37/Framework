package page;

import driver.DriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MegaTopMainPage extends AbstractPage{
    private static final String PAGE_URL = "https://megatop.by/";

    @FindBy(xpath = "//a[contains(@href,'/catalog/muzhchiny/')]")
    private WebElement manCategorySwitcher;

    @FindBy(id="title-search-input")
    private WebElement searchField;

    public MegaTopMainPage() {
        super(DriverSingleton.getInstance());
    }

    public MegaTopMainPage openPage(){
        driver.get(PAGE_URL);

        return this;
    }

    public MegaTopSearchResultPage showOnlyManGoods(){
        waitUntilElementIsClickable(manCategorySwitcher).click();
        jQueryAJAXCompleted();

        return new MegaTopSearchResultPage();
    }

    public MegaTopSearchResultPage inputGoodId(){
        waitUntilElementIsClickable(searchField).click();
        searchField.sendKeys("074689381");

        return new MegaTopSearchResultPage();
    }
}

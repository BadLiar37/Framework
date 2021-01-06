package page;

import driver.DriverSingleton;
import model.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class MegaTopSearchResultPage extends AbstractPage {

    @FindBy(xpath = "//*[contains(@class,'products__list__holder')]")
    private WebElement itemsHolder;

    @FindBy(xpath = "//*[contains(@title, '150 - 200 BYN')]")
    private WebElement filterButton;

    @FindBy(xpath = "//*[contains(@href, 'https://megatop.by/catalog/muzhchiny/filter/price_filter-is-150_200/apply/')]")
    private WebElement filterApplyButton;

    @FindBy(xpath = "//*[contains(@class,'title-search-result')]")
    private WebElement linkForSearchGood;

    @FindBy(xpath = "//*[contains(@class,'heading')]")
    private WebElement goodProperties;

    private String item = "//*[contains(@class,'products__item')]";
    private String itemInfo = ".//*[contains(@class,'products__info__brand')]";
    private String itemPrice = ".//*[contains(@class,'products__price__cur__val')]//span";

    public MegaTopSearchResultPage(){
        super(DriverSingleton.getInstance());
    }

    public MegaTopSearchResultPage filter(){
        waitUntilElementIsClickable(filterButton).click();
        waitUntilElementIsClickable(filterApplyButton).click();
        waitUntilAjaxCompleted();
        return this;
    }

    public List<Item> getAllGoods(){
        waitUntilAjaxCompleted();

        List<WebElement> webElements = driver.findElements(By.xpath(item));

        return webElements.stream().map(i -> new Item(i.findElement(By.xpath(item)).getText(),
                i.findElement(By.xpath(itemInfo)).getText(),
                Integer.parseInt(i.findElement(By.xpath(itemPrice)).getText().replace(".",""))))
                .collect(Collectors.toList());
    }
    public static   boolean checkGoodsByParameters(List<Item> items) {
        for (Item item : items) {
            if (item.getPrice() < 15000 || item.getPrice() > 20000 || !(item.getGender().endsWith("мужские"))) return false;
        }
        return true;
    }

    public boolean findGood(){
        waitUntilElementIsClickable(linkForSearchGood).click();
        return goodProperties.getText().endsWith("074689381");
    }
}

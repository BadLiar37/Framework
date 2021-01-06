package test;

import model.Item;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.MegaTopMainPage;

import java.util.List;

import static page.MegaTopSearchResultPage.checkGoodsByParameters;

public class SearchTest extends BaseTest {

    @Test
    public void getAllGoodsWtihCategoriesTest(){
       List<Item> itemList= new MegaTopMainPage()
                .openPage()
                .showOnlyManGoods()
                .filter()
                .getAllGoods();
       boolean recieved=checkGoodsByParameters(itemList);
        Assert.assertTrue(recieved);
    }

    @Test
    public void findGood(){
        Assert.assertTrue(new MegaTopMainPage()
                .openPage()
                .inputGoodId()
                .findGood());
    }
}

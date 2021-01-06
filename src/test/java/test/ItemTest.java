package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.MegaTopItemPage;
import page.MegaTopLoginPage;
import page.MegaTopPersonalAccountPage;

public class ItemTest extends BaseTest{

    @Test
    public void addToFavouriteTest(){
        new MegaTopItemPage()
                .openPage()
                .openLoginPage()
                .login(testUser);

        Assert.assertTrue(new MegaTopItemPage().addToFavourite());
    }

    @Test
    public void isCorrectValuesTest(){
        new MegaTopItemPage()
                .openPage()
                .openLoginPage()
                .login(testUser);
        Assert.assertTrue(new MegaTopItemPage().inputSize().isCorrectOptions());
    }

    @Test
    public void addGoodToOrder(){
        new MegaTopItemPage()
                .openPage()
                .openLoginPage()
                .login(testUser);
        new MegaTopItemPage().inputSize().addToOrder().openLoginPage();
        new MegaTopPersonalAccountPage().findGoodInBook();
        Assert.assertTrue(new MegaTopPersonalAccountPage().findGoodInBook());
    }

        @Test
    public void removeGoodFromBook(){
            Assert.assertTrue(new MegaTopLoginPage()
                    .openPage()
                    .login(testUser)
                    .removeGoodFromBook());
    }
}

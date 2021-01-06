package test;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.MegaTopLoginPage;
import service.TestDataReader;
import service.UserCreator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

public class UserTest extends BaseTest {

    @Test
    public void loginMegaTop(){

        String loggedUserPhoneNumber = new MegaTopLoginPage()
                .openPage()
                .login( testUser)
                .getLoggedPhoneNumber();
        assertThat(loggedUserPhoneNumber, is(equalTo(testUser.getPhoneNumber())));
    }

    @Test
    public void setStatusOfBonusActiveTest(){
        Assert.assertTrue(new MegaTopLoginPage()
                .openPage()
                .login(testUser)
                .isBonusActive());
    }

    @Test
    public void changeUserNameTest(){
        Assert.assertTrue(new MegaTopLoginPage()
                .openPage()
                .login(testUser)
                .changeUserName());
    }

    @Test
    public void loginMegaTopWithInvalidPhoneNumber(){
        User testUser = UserCreator.withCredentialsFromProperty();
        testUser.setPhoneNumber(TestDataReader.getTestData("test.data.invalidPhoneNumber"));
        String errorText = new MegaTopLoginPage()
                .openPage()
                .loginWithInvalidPhoneNumber(testUser);
        Assert.assertEquals(errorText, TestDataReader.getTestData("test.data.expectedErrorText"));
    }
}
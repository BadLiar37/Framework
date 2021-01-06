package test;

import driver.DriverSingleton;
import model.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import service.UserCreator;
import util.TestListener;

@Listeners(TestListener.class)
public abstract class BaseTest {
    protected WebDriver driver;
    protected User testUser;

    @BeforeClass
    public void init(){
        testUser = UserCreator.withCredentialsFromProperty();
        driver = DriverSingleton.getInstance();
    }

    @AfterMethod(alwaysRun = true)
    public void close(){
        DriverSingleton.deleteAllCookies();
    }

    @AfterClass(alwaysRun = true)
    public void dispose(){
        DriverSingleton.closeDriver();
    }
}

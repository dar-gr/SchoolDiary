package tests;

import driver.DriverSingleton;
import org.testng.annotations.*;
import util.TestListener;
import org.openqa.selenium.WebDriver;

@Listeners({TestListener.class})
public class CommonConditions {

    protected WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void browserSetup() {
        driver = DriverSingleton.getDriver();
    }

    @AfterTest(alwaysRun = true)
    public void browserTearDown() {
        DriverSingleton.closeDriver();
    }
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    protected WebDriver driver;

    protected abstract AbstractPage openPage();
    protected final long WAIT_TIMEOUT_SECONDS = 5;

    protected AbstractPage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}

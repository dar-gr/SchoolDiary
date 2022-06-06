package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class MainPage extends AbstractPage{

    private static final String MAINPAGE_URL = "https://schools.by/";
    WebDriverWait wait = new WebDriverWait(driver,WAIT_TIMEOUT_SECONDS);

    @FindBy(xpath = "//span[contains(text(), 'Возможности')]")
    WebElement capabilitiesButtonLink;

    @FindBy(xpath = "//span[contains(text(), 'Стоимость')]")
    WebElement costButtonLink;

    @FindBy(xpath = "//span[contains(text(), 'Помощь')]")
    WebElement helpButtonLink;

    @FindBy(xpath = "//span[contains(text(), 'О сервисе')]")
    WebElement aboutButtonLink;

    @FindBy(xpath = "//span[contains(text(), 'Контакты')]")
    WebElement contactButtonLink;

    @FindBy(xpath = "//div[@class='actions']//a[contains(text(),'Попробовать демо')]")
    WebElement openDemoButton;

    @FindBy(xpath = "//a[contains(text(), 'Смотреть') and contains(@href, 'teacher')]")
    WebElement teacherDemoPageOpen;

    @FindBy(xpath = "//a[contains(text(), \'Смотреть\') and contains(@href, \'teacher\')]")
    WebElement dialogDiv;


    public MainPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Перейти на страницу Возможностей")
    public MainPage openCapabilitiesPage(){
        capabilitiesButtonLink.click();
        return this;
    }

    @Step("Перейти на страницу Стоимости")
    public MainPage openCostPage(){
        costButtonLink.click();
        return this;
    }

    @Step("Перейти на страницу Помощи")
    public MainPage openHelpPage(){
        helpButtonLink.click();
        return this;
    }

    @Step("Перейти на страницу О сервисе")
    public MainPage openAboutPage(){
        aboutButtonLink.click();
        return this;
    }

    @Step("Перейти на страницу Контакты")
    public MainPage openContactPage(){
        contactButtonLink.click();
        return this;
    }

    @Step("Открыть демо страницу")
    public DemoTeacherPage openDemoPage(){
        openDemoButton.click();
        wait.until(ExpectedConditions.visibilityOf(dialogDiv));
        teacherDemoPageOpen.click();
        return new DemoTeacherPage(driver);
    }

    @Step("Сверить url страниц")
    public boolean checkPageUrl(String expectedUrl){
        String actualUrl = driver.getCurrentUrl();
        return Objects.equals(expectedUrl, actualUrl);
    }

    @Override
    public MainPage openPage(){
        driver.get(MAINPAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        return this;
    }
}

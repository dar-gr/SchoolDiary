package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class DemoTeacherPage extends AbstractPage{

    private static final String DEMOPAGE_URL = "https://demo.schools.by/director/104631";
    WebDriverWait wait = new WebDriverWait(driver,WAIT_TIMEOUT_SECONDS);

    @FindBy(xpath = "//div[@class='sch_name']//a")
    WebElement schoolNameElement;

    @FindBy(xpath = "//div[@class='pp_line']//a[contains(text(),'школы')]")
    WebElement schoolPageLink;

    @FindBy(xpath = "//div[@class='sch_main_info island']/dl/dd")
    WebElement getSchoolNameElementOnSchoolPage;

    @FindBy(xpath = "//a[contains(text(),'журнал')]")
    WebElement journalButton;

    @FindBy(xpath = "//a[contains(text(),'дневник')]")
    WebElement diaryButton;

    @FindBy(xpath = "//div[@class='sch_header_pop about_login sch_pop']")
    WebElement demoPagesDiv;

    @FindBy(xpath = "//div[@class='title_box']/h1")
    WebElement usernameElement;

    @FindBy(xpath = "//div[@class='cnt']//a[contains(@href, 'subject')]")
    WebElement subjectButton;

    @FindBy(xpath = "//a[@class='user_type_4']")
    WebElement subjectTeacherElement;

    @FindBy(xpath = "//div[@class='cnt']//a[contains(@href, 'class')]")
    WebElement classButton;

    @FindBy(xpath = "//a[contains(@href,'class-subject')]")
    WebElement classTeachersElement;

    @FindBy(xpath = "//a[@class='user_type_4']")
    List<WebElement> teacherList;

    @FindBy(xpath = "//a[@class='schoolpay-button']")
    WebElement schoolPayButton;

    @FindBy(xpath = "//a[@class='effor-button']")
    WebElement efforButton;

    @FindBy(xpath = "//div[@class='pp_line_actions__item line_small']//a")
    WebElement editInfoButton;

    @FindBy(xpath = "//div[@class='grid_pst_c']//div[contains(text(), '+375')]")
    WebElement phoneNumberElement;

    @FindBy(xpath = "//div[@class='grid_pst_c']//div[contains(text(), '@')]")
    WebElement mailElement;

    @FindBy(id = "id_phone")
    WebElement inputPhoneNumber;

    @FindBy(id = "id_email")
    WebElement inputEmail;

    @Step("Получить название школы из шапки")
    public String getSchoolName(){
        return schoolNameElement.getText();
    }

    @Step("Открыть страницу школы")
    public DemoTeacherPage openSchoolPage(){
        schoolPageLink.click();
        return this;
    }

    @Step("Получить полное название школы со страницы информации о школе")
    public String getSchoolNameFromSchoolPage(){
        return getSchoolNameElementOnSchoolPage.getText();
    }

    @Step("Открыть страницу журнала")
    public JournalPage openJournalPage(){
        wait.until(ExpectedConditions.visibilityOf(demoPagesDiv));
        journalButton.click();
        return new JournalPage(driver);
    }

    @Step("Открыть страницу дневника")
    public DiaryPage openDiaryPage(){
        wait.until(ExpectedConditions.visibilityOf(demoPagesDiv));
        diaryButton.click();
        return new DiaryPage(driver);
    }

    @Step("Получить имя пользователя")
    public String getUsername(){
        return usernameElement.getText();
    }

    @Step("Открыть страницу преподаваемого предмета")
    public DemoTeacherPage openSubjectPage(){
        subjectButton.click();
        return this;
    }

    @Step("Получить преподавателя предмета")
    public String getTeacherName(){
        return subjectTeacherElement.getText();
    }

    @Step("Открыть страницу класса")
    public DemoTeacherPage openClassPage(){
        classButton.click();
        return this;
    }

    @Step("Открыть страницу учителей класса")
    public DemoTeacherPage openClassTeachersPage(){
        classTeachersElement.click();
        return this;
    }

    @Step("Получить список учителей класса")
    public ArrayList<String> getTeachersList(){
        ArrayList<String> teachers = new ArrayList<>();
        for (WebElement teacher:teacherList) {
            teachers.add(teacher.getText());
        }
        return teachers;
    }

    @Step("Открыть School Pay")
    public DemoTeacherPage openSchoolPayPage()
    {
        schoolPayButton.click();
        return this;
    }

    @Step("Получить url новой вкладки")
    public String getNewTabUrl()
    {
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        return driver.getCurrentUrl();
    }

    @Step("Открыть Эффор")
    public DemoTeacherPage openEfforPage()
    {
        efforButton.click();
        return this;
    }

    @Step("Редактировать инфорацию")
    public DemoTeacherPage openEditInfoPage(){
        editInfoButton.click();
        return this;
    }

    @Step("Получить номер телефона с страницы")
    public String getPhoneNumber(){
        return phoneNumberElement.getText();
    }

    @Step("Получить email с страницы")
    public String getEmail(){
        return mailElement.getText();
    }

    @Step("Получить номер телефона с страницы редактирования")
    public String getInputPhoneNumber(){
        return inputPhoneNumber.getAttribute("value");
    }

    @Step("Получить email с страницы редактировани")
    public String getInputEmail(){
        return inputEmail.getAttribute("value");
    }

    public DemoTeacherPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public DemoTeacherPage openPage(){
        driver.get(DEMOPAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        return this;
    }
}

package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedHashSet;
import java.util.List;

public class DiaryPage extends AbstractPage{

    private static final String DIARYPAGE_URL = "https://demo.schools.by/pupil/100140?1#dnevnik";
    WebDriverWait wait = new WebDriverWait(driver,WAIT_TIMEOUT_SECONDS);


    @FindBy(xpath = "//table[@class='db_table ']//td[@class='lesson ']//a")
    List<WebElement> diarySubjects;

    @FindBy(xpath = "//table[@class='ttb_tbl']//a[@class='subj']")
    List<WebElement> timetableSubjects;

    @FindBy(xpath = "//a[contains(@for,'pupil_tabs_timetable')]")
    WebElement timetableButton;


    @Step("Получить список предметов из дневника")
    public LinkedHashSet getSubjectsFromDiary(){
        LinkedHashSet<String> subjects = new LinkedHashSet<>();
        for (WebElement subjectsFromDiary:diarySubjects) {
            subjects.add(subjectsFromDiary.getText());
        }
        return subjects;
    }

    @Step("Получить список предметов из расписания")
    public LinkedHashSet getSubjectsFromTimetable(){
        LinkedHashSet<String> subjects = new LinkedHashSet<>();
        for (WebElement subjectsFromDiary:timetableSubjects) {
            subjects.add(subjectsFromDiary.getText());
        }
        return subjects;
    }

    @Step("Открыть расписание")
    public DiaryPage openTimetable(){
        timetableButton.click();
        return this;
    }

    public DiaryPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public DiaryPage openPage(){
        driver.get(DIARYPAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        return this;
    }
}

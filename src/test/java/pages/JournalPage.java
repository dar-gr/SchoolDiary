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

public class JournalPage extends AbstractPage{

    public static final String JOURNALPAGE_URL = "https://demo.schools.by/journal/100121";
    WebDriverWait wait = new WebDriverWait(driver,WAIT_TIMEOUT_SECONDS);

    @FindBy(xpath = "//table[@id='journal_pupils_table_57']//tr[@pupil_id]")
    List<WebElement> userNameIdsList;

    @FindBy(xpath = "//ul[@id='journal_subgroups_menu_57']//a[contains(text(),'1')]")
    WebElement firstHalfClassButton;

    @FindBy(xpath = "//ul[@id='journal_subgroups_menu_57']//a[contains(text(),'2')]")
    WebElement secondHalfClassButton;

    @FindBy(xpath = "//table[@id='journal_pupils_table_57']")
    WebElement pupilTable;

    @FindBy(xpath = "//a[@quarter_id='47']//span")
    WebElement firstQuarterButton;

    @FindBy(xpath = "//a[@quarter_id='55']//span")
    WebElement secondQuarterButton;

    @FindBy(xpath = "//a[@quarter_id='56']//span")
    WebElement thirdQuarterButton;

    @FindBy(xpath = "//a[@quarter_id='57']//span")
    WebElement fourthQuarterButton;

    @FindBy(xpath = "//div[@class = 'tabs2_wrap']//ul[not(@style)]//a[@class='quarters dashed-lnk2']")
    WebElement yearMarkButton;

    @FindBy(xpath = "//table[@id='qmarks_table_2022']//tr[@pupil_id = '100135']//td[@quarter_id='47']")
    WebElement firstQuarterMark;

    @FindBy(xpath = "//table[@id='qmarks_table_2022']//tr[@pupil_id = '100135']//td[@quarter_id='55']")
    WebElement secondQuarterMark;

    @FindBy(xpath = "//table[@id='qmarks_table_2022']//tr[@pupil_id = '100135']//td[@quarter_id='56']")
    WebElement thirdQuarterMark;

    @FindBy(xpath = "//table[@id='qmarks_table_2022']//tr[@pupil_id = '100135']//td[@quarter_id='57']")
    WebElement forthQuarterMark;

    @FindBy(xpath = "//table[@id='qmarks_table_2022']//tr[@pupil_id = '100135']//td[@class='avg']")
    WebElement averageQuarterMark;

    @Step ("Получить список учеников")
    public ArrayList<Integer> getFullPupilList(){
        ArrayList<Integer> userNames = new ArrayList<>();
        wait.until(ExpectedConditions.visibilityOf(pupilTable));
        for (WebElement userName: userNameIdsList)
             {
                 if(userName.isDisplayed()) {
                     userNames.add(Integer.parseInt(userName.getAttribute("pupil_id")));
                 }
             }
        return userNames;
    }

    @Step("Открыть первую подгруппу по ин. языку")
    public JournalPage openFirstForeignPart(){
        wait.until(ExpectedConditions.visibilityOf(firstHalfClassButton));
        firstHalfClassButton.click();
        return this;
    }

    @Step("Открыть вторую подгруппу по ин. языку")
    public JournalPage openSecondForeignPart(){
        secondHalfClassButton.click();
        return this;
    }

    @Step("Проверить, что корректно отображается первая четверть")
    public boolean checkFirstQuarter(){
        if(firstQuarterButton.isDisplayed()) {
            return firstQuarterButton.getText().equals("1 четверть");
        }
        return false;
    }

    @Step("Проверить, что корректно отображается вторая четверть")
    public boolean checkSecondQuarter(){
        if(secondQuarterButton.isDisplayed()) {
            return secondQuarterButton.getText().equals("2 четверть");
        }
        return false;
    }

    @Step("Проверить, что корректно отображается третья четверть")
    public boolean checkThirdQuarter(){
        if(thirdQuarterButton.isDisplayed()) {
            return thirdQuarterButton.getText().equals("3 четверть");
        }
        return false;
    }

    @Step("Проверить, что корректно отображается четвёртая четверть")
    public boolean checkFourthQuarter(){
        if(fourthQuarterButton.isDisplayed()) {
            return fourthQuarterButton.getText().equals("4 четверть");
        }
        return false;
    }

    @Step("Открыть годовые и четвертные оценки")
    public JournalPage openYearMarks(){
        yearMarkButton.click();
        return this;
    }

    @Step("Получили оценку за первую четверть")
    public Integer getFirstQuarterMark(){
        wait.until(ExpectedConditions.visibilityOf(firstQuarterMark));
        try {
            return Integer.parseInt(firstQuarterMark.getText());
        }
        catch (NumberFormatException e){
            return 0;
        }
    }

    @Step("Получили оценку за вторую четверть")
    public Integer getSecondQuarterMark(){
        try {
            return Integer.parseInt(secondQuarterMark.getText());
        }
        catch (NumberFormatException e){
            return 0;
        }
    }

    @Step("Получили оценку за третью четверть")
    public Integer getThirdQuarterMark(){
        try {
            return Integer.parseInt(thirdQuarterMark.getText());
        }
        catch (NumberFormatException e){
            return 0;
        }
    }

    @Step("Получили оценку за четвертую четверть")
    public Integer getFourthQuarterMark(){
        try {
            return Integer.parseInt(forthQuarterMark.getText());
        }
        catch (NumberFormatException e){
            return 0;
        }
    }

    @Step("Получили среднюю оценку зачетверти")
    public Double averageQuarterMark(){
        return Double.parseDouble(averageQuarterMark.getText());
    }

    public JournalPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public JournalPage openPage(){
        driver.get(JOURNALPAGE_URL);
        return this;
    }
}

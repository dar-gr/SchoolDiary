package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DiaryPage;
import pages.MainPage;

import java.util.LinkedHashSet;

public class DiaryPageTest extends CommonConditions{

    @Test
    public void checkTimetableAndDiary(){
        DiaryPage diaryPage = new MainPage(driver).openPage().openDemoPage().openDiaryPage();

        LinkedHashSet<String> diarySet = diaryPage.getSubjectsFromDiary();
        LinkedHashSet<String> timetableSet = diaryPage.openTimetable().getSubjectsFromTimetable();

        Assert.assertEquals(diarySet, timetableSet);

    }
}

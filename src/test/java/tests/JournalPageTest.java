package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.JournalPage;
import pages.MainPage;

import java.util.ArrayList;

public class JournalPageTest extends CommonConditions{

    @Test
    public void checkJournalUrlLink(){
        new MainPage(driver)
                .openPage()
                .openDemoPage()
                .openJournalPage();

        Assert.assertEquals(driver.getCurrentUrl(), JournalPage.JOURNALPAGE_URL);
    }

    @Test
    public void firstPartStudentsListTest(){
        SoftAssert softAssert = new SoftAssert();

        JournalPage journalPage = new MainPage(driver)
                .openPage()
                .openDemoPage()
                .openJournalPage();

        ArrayList<Integer> fullList = journalPage
                .getFullPupilList();

        ArrayList<Integer> firstPartList = journalPage
                .openFirstForeignPart()
                .getFullPupilList();

        for (Integer userFromFirstPart:firstPartList) {
            softAssert.assertTrue(fullList.contains(userFromFirstPart));
        }

        softAssert.assertAll();
    }

    @Test
    public void secondPartStudentsListTest(){
        SoftAssert softAssert = new SoftAssert();

        JournalPage journalPage = new MainPage(driver).openPage().openDemoPage().openJournalPage();

        ArrayList<Integer> fullList = journalPage
                .getFullPupilList();

        ArrayList<Integer> secondPartList = journalPage
                .openSecondForeignPart()
                .getFullPupilList();

        for (Integer userFromSecondPart:secondPartList) {
            softAssert.assertTrue(fullList.contains(userFromSecondPart));
        }

        softAssert.assertAll();
    }

    @Test
    public void quarterDisplayTest(){
        JournalPage journalPage = new MainPage(driver).openPage().openDemoPage().openJournalPage();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(journalPage.checkFirstQuarter());
        softAssert.assertTrue(journalPage.checkSecondQuarter());
        softAssert.assertTrue(journalPage.checkThirdQuarter());
        softAssert.assertTrue(journalPage.checkFourthQuarter());

        softAssert.assertAll();
    }

    @Test
    public void competeAverageMarks(){
        JournalPage journalPage = new MainPage(driver).openPage().openDemoPage().openJournalPage().openYearMarks();

        int n = 4;
        ArrayList<Double> quarters = new ArrayList<>(n);

        quarters.add(journalPage.getFirstQuarterMark().doubleValue());
        quarters.add(journalPage.getSecondQuarterMark().doubleValue());
        quarters.add(journalPage.getThirdQuarterMark().doubleValue());
        quarters.add(journalPage.getFourthQuarterMark().doubleValue());

        Double averageSum = 0.0;
        for (Double quarter:quarters) {
            if(quarter == 0){
                n--;
            }
            averageSum += quarter;
        }

        Double expectedAverage = averageSum / n;

        Assert.assertEquals(journalPage.averageQuarterMark(), expectedAverage);
    }
}

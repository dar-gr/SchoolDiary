package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.DemoTeacherPage;
import pages.MainPage;

import java.util.ArrayList;

public class DemoPageTest extends CommonConditions {

    @Test
    public void CheckCorrectHeaderSchoolName(){
        DemoTeacherPage demoTeacherPage = new DemoTeacherPage(driver);

        String shortName = demoTeacherPage.openPage().getSchoolName();
        String fullName = demoTeacherPage.openSchoolPage().getSchoolNameFromSchoolPage();

        Assert.assertTrue(fullName.contains(shortName));
    }

    @Test
    public void TeachingSubjectTest(){
        DemoTeacherPage demoTeacherPage = new MainPage(driver).openPage().openDemoPage();

        String expectedName = demoTeacherPage.getUsername();

        String actualName = demoTeacherPage.openSubjectPage().getTeacherName();

        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void TeachingClassTest(){
        DemoTeacherPage demoTeacherPage = new MainPage(driver).openPage().openDemoPage();

        String expectedName = demoTeacherPage.getUsername();

        ArrayList<String> actualNames = demoTeacherPage
                .openClassPage()
                .openClassTeachersPage()
                .getTeachersList();

        Assert.assertTrue(actualNames.contains(expectedName));
    }

    @Test
    public void compareSchoolPayLinks(){
        String schoolPayUrl = new MainPage(driver).openPage().openDemoPage().openSchoolPayPage().getNewTabUrl();

        Assert.assertTrue(schoolPayUrl.contains("https://schoolpay.by/"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void compareEfforLinks(){
        String efforUrl = new MainPage(driver).openPage().openDemoPage().openEfforPage().getNewTabUrl();

        Assert.assertTrue(efforUrl.contains("https://effor.by/"));
    }

    @Test
    public void comparePageAndEditPageInformation(){
        DemoTeacherPage demoTeacherPage = new MainPage(driver).openPage().openDemoPage();
        String pagePhoneNumber = demoTeacherPage.getPhoneNumber();
        String pageEmail = demoTeacherPage.getEmail();

        demoTeacherPage.openEditInfoPage();

        String editPhoneNumber = demoTeacherPage.getInputPhoneNumber();
        String editEmail = demoTeacherPage.getInputEmail();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(pagePhoneNumber, editPhoneNumber);
        softAssert.assertEquals(pageEmail, editEmail);

        softAssert.assertAll();
    }
}

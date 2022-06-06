package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.MainPage;

public class MainPageTest extends CommonConditions {

    @Test(suiteName = "navMenu")
    public void checkNavMenuLinksTest(){
        SoftAssert softAssert = new SoftAssert();

        MainPage mainPage = new MainPage(driver).openPage();

        softAssert
                .assertTrue(mainPage
                        .openCapabilitiesPage()
                        .checkPageUrl("https://schools.by/capabilities"));

        softAssert
                .assertTrue(mainPage
                        .openCostPage()
                        .checkPageUrl("https://schools.by/cost"));

        softAssert
                .assertTrue(mainPage
                        .openHelpPage()
                        .checkPageUrl("https://schools.by/help/2"));

        softAssert
                .assertTrue(mainPage
                        .openAboutPage()
                        .checkPageUrl("https://schools.by/about"));

        softAssert
                .assertTrue(mainPage
                        .openContactPage()
                        .checkPageUrl("https://schools.by/contact"));

        softAssert.assertAll();
    }

    @Test
    public void openDemoTeacherTest(){
        new MainPage(driver).openPage().openDemoPage();
        Assert.assertTrue(driver.getCurrentUrl().contains("demo.schools.by/director"));
    }
}

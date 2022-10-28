package com.kinship.automation.testcases.mobile;

import com.kinship.automation.BaseTest;
import com.kinship.automation.commontestmanager.TestManager;
import com.kinship.automation.utils.driver.DriverFactory;
import com.kinship.automation.utils.listeners.TestListener;
import com.kinship.automation.utils.testrail.TestRails;
import com.kinship.automation.constants.WhistleConstants;
import com.kinship.automation.pageobjects.HomePageWhistle;
import com.kinship.automation.pageobjects.SignInPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by Murali Jaladurgam on 11 April, 2022
 * */

@Listeners(TestListener.class)
public class PetTimezoneTest extends TestManager {

    @TestRails(id = "1008558")
    @Description("Timezone - View")
    @Test(priority = 0, groups = {"P1"})
    @Feature("Pet Timezone - View")

    public void testViewPetTimezone() throws InterruptedException {
        if ((DriverFactory.platfrom.equalsIgnoreCase("android")) || (DriverFactory.platfrom.equalsIgnoreCase("BS-android"))) {
            SignInPage signInPage = new SignInPage(testDriver);
            HomePageWhistle homePageWhistle = new HomePageWhistle(testDriver);
            if (!testDriver.isDisplayedWait(signInPage.signInLink, 3)) {
                whistleBasePage.write2Log4jAllureStep("user is already logged in");
            } else {
                homePageWhistle = signInPage.signInToHomePage();
                homePageWhistle.handleNotificationBanner();
            }
            viewPetProfilePage = homePageWhistle.clickPetMenuOptn();
            petEditProfilePage = viewPetProfilePage.selectEditIcon();

            whistleBasePage.write2Log4jAllureStep("Verifying Timezone Details is Displayed");
            Assert.assertTrue(viewPetProfilePage.ischeckTimeZoneDisplayed(), "Pet TimeZone is not displayed");

            viewPetProfilePage.clickBackBtnToPetProfilePage();
        }
    }

    @TestRails(id = "1008559")
    @Description("Timezone- Update")
    //@Test(priority = 1, groups = {"P1"})
    @Feature("Pet Timezone- Update")

    public void testUpdatePetTimezone() throws InterruptedException {

            SignInPage signInPage = new SignInPage(testDriver);
            HomePageWhistle homePageWhistle = new HomePageWhistle(testDriver);
            if (!testDriver.isDisplayedWait(signInPage.signInLink, 3)) {
                whistleBasePage.write2Log4jAllureStep("user is already logged in");
            } else {
                homePageWhistle = signInPage.signInToHomePage();
                homePageWhistle.handleNotificationBanner();
            }
            viewPetProfilePage = homePageWhistle.clickPetMenuOptn();
            petEditProfilePage = viewPetProfilePage.selectEditIcon();

            whistleBasePage.write2Log4jAllureStep("Verifying Timezone Details is Displayed");
            Assert.assertTrue(viewPetProfilePage.ischeckTimeZoneDisplayed(), "Pet TimeZone is not displayed");

            whistleBasePage.write2Log4jAllureStep("Updating TimeZone Value");
            petEditProfilePage.editTimezoneField(WhistleConstants.UPDATE_TIME_ZONE,"0");

            whistleBasePage.write2Log4jAllureStep("Re-Updating TimeZone Value");
            petEditProfilePage.editTimezoneField(WhistleConstants.REUPDATE_TIME_ZONE, "0");

            viewPetProfilePage = petEditProfilePage.navigateBackPage();
        }
    }


package com.kinship.automation.testcases.mobile;

import com.kinship.automation.BaseTest;
import com.kinship.automation.commontestmanager.TestManager;
import com.kinship.automation.utils.listeners.TestListener;
import com.kinship.automation.utils.testrail.TestRails;
import com.kinship.automation.constants.WhistleConstants;
import com.kinship.automation.pageobjects.HomePageWhistle;
import com.kinship.automation.pageobjects.LandingPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by Hasmukh Patel on 30 March, 2022
 */

@Listeners(TestListener.class)
public class PetNeuteredSpayTest extends TestManager {

    String sUpdatePetStatus;
    String sPetGender;
	String sPetPreviousStatus;

    @TestRails(id = "1008542")
    @Description("Update Pet Status value")
    @Test(priority = 0, groups = {"P0"})
    @Feature("Pet Profile- Update Status")
    public void testUpdatePetStatus() throws InterruptedException {
        LandingPage landingPage = new LandingPage(testDriver);
        HomePageWhistle homePageWhistle = new HomePageWhistle(testDriver);

        if (!landingPage.isSignInBtnDisplayed()) {
            whistleBasePage.write2Log4jAllureStep("user is already logged in");
        } else {
            whistleBasePage.write2Log4jAllureStep("Clicked on Sign in Button");
            signInPage = landingPage.clickSignInLnk();

            whistleBasePage.write2Log4jAllureStep("Enter Email Address / User ID " + WhistleConstants.emailMobile);
            signInPage.enterEmail(WhistleConstants.emailMobile);

            whistleBasePage.write2Log4jAllureStep("Enter Password " + WhistleConstants.password);
            signInPage.enterPassword(WhistleConstants.password);

            whistleBasePage.write2Log4jAllureStep("Click on Login button");
            homePageWhistle = signInPage.clickLoginBtn();

            whistleBasePage.write2Log4jAllureStep("Verify Family Notification Screen");
            homePageWhistle.handleNotificationBanner();
        }

        whistleBasePage.write2Log4jAllureStep("Click on Pet");
        viewPetProfilePage = homePageWhistle.clickPetMenuOptn();

        whistleBasePage.write2Log4jAllureStep("Click on Pet Edit Icon");
        petEditProfilePage = viewPetProfilePage.selectEditIcon();

        whistleBasePage.write2Log4jAllureStep("Get the Pet's Gender");
        sPetGender = petEditProfilePage.getPetGenderValue();

        whistleBasePage.write2Log4jAllureStep("Get the Pet Current Status of Pet i.e. Neutered / Not Neutered OR Spayed / Not Spayed");
        sPetPreviousStatus = petEditProfilePage.getPetStatusValue();

        whistleBasePage.write2Log4jAllureStep("Click to Change Pet Status");
        petEditProfilePage.clickToChangePetStatus();

        whistleBasePage.write2Log4jAllureStep("Update The Pet Status");
        if (sPetGender.equals("Male")) {
            sUpdatePetStatus = (sPetPreviousStatus.equals("Neutered") ? petEditProfilePage.selectPetStatus("Not Neutered") : petEditProfilePage.selectPetStatus("Neutered"));
        } else {
            sUpdatePetStatus = (sPetPreviousStatus.equals("Spayed") ? petEditProfilePage.selectPetStatus("Not Spayed") : petEditProfilePage.selectPetStatus("Spayed"));
        }

        whistleBasePage.write2Log4jAllureStep("Verify Status has been updated or not");
        petEditProfilePage.assertValue(petEditProfilePage.getPetStatusValue(), sUpdatePetStatus, "Status is NOT updated");

        whistleBasePage.write2Log4jAllureStep("Navigate back to Pet main Screen");
        viewPetProfilePage = petEditProfilePage.navigateBackPage();

        whistleBasePage.write2Log4jAllureStep("Click on Pet Edit Icon");
        viewPetProfilePage.selectEditIcon();

        whistleBasePage.write2Log4jAllureStep("Click to Change Pet Status");
        petEditProfilePage.clickToChangePetStatus();

        whistleBasePage.write2Log4jAllureStep("Update The Pet Status");
        sUpdatePetStatus = petEditProfilePage.selectPetStatus(sPetPreviousStatus);

        whistleBasePage.write2Log4jAllureStep("Verify Status has been updated or not");
        petEditProfilePage.assertValue(sPetPreviousStatus, sUpdatePetStatus, "Pet Status Value NOT Updated");
    }
}
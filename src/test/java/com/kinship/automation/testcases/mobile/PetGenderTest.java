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
 * Created by Hasmukh Patel on 29 March, 2022
 * */

@Listeners(TestListener.class)
public class PetGenderTest extends TestManager {

    String sUpdatePetGender;
    String sPetPreviousGender;

    @TestRails(id = "1008541")
    @Description("Update Pet Gender value")
    @Test(priority = 0, groups = {"P0"})
    @Feature("Pet Profile- Update Gender")
    public void testUpdatePetGender() throws InterruptedException {
        LandingPage landingPage = new LandingPage(testDriver);
        HomePageWhistle homePageWhistle = new HomePageWhistle(testDriver);

        if(!landingPage.isSignInBtnDisplayed()){
            whistleBasePage.write2Log4jAllureStep("user is already logged in");
        }else {
            whistleBasePage.write2Log4jAllureStep("Clicked on Sign in Button");
            signInPage = landingPage.clickSignInLnk();

            whistleBasePage.write2Log4jAllureStep("Enter Email Address / User ID "+ WhistleConstants.emailMobile);
            signInPage.enterEmail(WhistleConstants.emailMobile);

            whistleBasePage.write2Log4jAllureStep("Enter Password "+ WhistleConstants.password);
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

        whistleBasePage.write2Log4jAllureStep("Get the Pet Current Gender");
        sPetPreviousGender = petEditProfilePage.getPetGenderValue();

        whistleBasePage.write2Log4jAllureStep("Click to Change Pet Gender");
        petEditProfilePage.clickToChangePetGender();

        whistleBasePage.write2Log4jAllureStep("Update The Pet Gender");
        sUpdatePetGender = (sPetPreviousGender.equals("Female") ? petEditProfilePage.selectPetGender("Male") : petEditProfilePage.selectPetGender("Female"));

        whistleBasePage.write2Log4jAllureStep("Verify Gender has been updated or not");
        petEditProfilePage.assertValue(petEditProfilePage.getPetGenderValue(),sUpdatePetGender, "Gender is NOT updated" );

        whistleBasePage.write2Log4jAllureStep("Navigate back to Pet main Screen");
        viewPetProfilePage = petEditProfilePage.navigateBackPage();

        whistleBasePage.write2Log4jAllureStep("Click on Pet Edit Icon");
        viewPetProfilePage.selectEditIcon();

        whistleBasePage.write2Log4jAllureStep("Click to Change Pet Gender");
        petEditProfilePage.clickToChangePetGender();

        whistleBasePage.write2Log4jAllureStep("Update The Pet Gender");
        sUpdatePetGender = petEditProfilePage.selectPetGender(sPetPreviousGender);

        whistleBasePage.write2Log4jAllureStep("Verify Gender has been updated or not");
        petEditProfilePage.assertValue(sPetPreviousGender, sUpdatePetGender, "Pet Gender Value NOT Updated");
    }
}


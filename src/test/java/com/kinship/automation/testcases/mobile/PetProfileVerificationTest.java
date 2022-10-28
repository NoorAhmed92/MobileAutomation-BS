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
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by Murali Jaladurgam on 17 March, 2022
 * */

@Listeners(TestListener.class)
public class PetProfileVerificationTest extends TestManager {

    @TestRails(id="1008534")
    @Description("View and Edit Pet Profile Settings")
    @Test( groups={ "P0" })
    @Feature("PetProfileVerificationView")
    public void testDisplayPetDetailsAfterLogin() {
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

        whistleBasePage.write2Log4jAllureStep("Verifying Edit Profile Page Label is displayed ");
        Assert.assertTrue(viewPetProfilePage.ischeckPetEditProfileLabelDisplayed(), "Pet Edit Profile Page Label is not displayed");
        whistleBasePage.write2Log4jAllureStep("Verifying Pet Profile Name is displayed ");
        Assert.assertTrue(viewPetProfilePage.ischeckPetNameDisplayed(), "Pet Name is not displayed");
        whistleBasePage.write2Log4jAllureStep("Verifying Pet Profile Icon is displayed ");
        Assert.assertTrue(viewPetProfilePage.ischeckPetEditPhotoDisplayed(), "Pet Photo is not displayed");
        whistleBasePage.write2Log4jAllureStep("Verifying Pet Breed is displayed ");
        Assert.assertTrue(viewPetProfilePage.ischeckPetBreedDisplayed(), "Pet Breed is not displayed");
        whistleBasePage.write2Log4jAllureStep("Verifying Pet Sex (Gender) is displayed ");
        Assert.assertTrue(viewPetProfilePage.ischeckPetGenderDisplayed(), "Pet Sex (Gender) is not displayed");
        whistleBasePage.write2Log4jAllureStep("Verifying Pet Neutered/Spayed Status is displayed ");
        Assert.assertTrue(viewPetProfilePage.ischeckPetNeuteredSpayedStatusDisplayed(), "Pet Neutered/Spayed Status is not displayed");
        whistleBasePage.write2Log4jAllureStep("Verifying Pet Age is displayed ");
        Assert.assertTrue(viewPetProfilePage.ischeckAgeDisplayed(), "Pet Age is not displayed");
        whistleBasePage.write2Log4jAllureStep("Verifying Pet Weight is displayed ");
        Assert.assertTrue(viewPetProfilePage.ischeckWeightDisplayed(), "Pet Weight is not displayed");
        whistleBasePage.write2Log4jAllureStep("Verifying Pet TimeZone is displayed ");
        Assert.assertTrue(viewPetProfilePage.ischeckTimeZoneDisplayed(), "Pet TimeZone is not displayed");
        whistleBasePage.write2Log4jAllureStep("Verifying Pet Food is displayed ");
        Assert.assertTrue(viewPetProfilePage.ischeckPetFoodDisplayed(), "Pet Food is not displayed");
        viewPetProfilePage = petEditProfilePage.navigateBackPage();
    }
}
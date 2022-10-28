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
 * Created by JBasera on 11 Apr, 2022
 * Modified by JBasera on 19 Apr,2022
 */

@Listeners(TestListener.class)
public class PetWeightTest extends TestManager {

    String sUpdatedWeight;

    @TestRails(id = "1008551")
    @Description("Update Weight Status value in Lbs")
    @Test(priority = 0, groups = {"P0"})
    @Feature("Pet Profile- Update Weight")
    public void testUpdatePetWeightLbs() throws InterruptedException {
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

        whistleBasePage.write2Log4jAllureStep("Select Pet's Weight in Edit Pet Profile");
        petEditProfilePage.selectPetWeight();

        whistleBasePage.write2Log4jAllureStep("Select Pet's Weight in Lbs");
        petEditProfilePage.selectPetWeightLbs();

        whistleBasePage.write2Log4jAllureStep("Update Pet's Weight");
        petEditProfilePage.updatePetWeight(WhistleConstants.UPDATE_WEIGHT);

        whistleBasePage.write2Log4jAllureStep("click Save In Pet Weight PopUp");
        petEditProfilePage.clickSaveInPetWeightPopUp();

        whistleBasePage.write2Log4jAllureStep("Get the Pet Weight");
        sUpdatedWeight = petEditProfilePage.getPetWeightValue();

        whistleBasePage.write2Log4jAllureStep("Verify weight has been updated in lbs");
        Assert.assertEquals(sUpdatedWeight, WhistleConstants.UPDATE_WEIGHT);
    }

    @TestRails(id = "1008552")
    @Description("Update Weight Status value in Kgs")
    @Test(priority = 0, groups = {"P0"})
    @Feature("Pet Profile- Update Weight")
    public void testUpdatePetWeightKgs() throws InterruptedException {
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

        whistleBasePage.write2Log4jAllureStep("Select Pet's Weight in Edit Pet Profile");
        petEditProfilePage.selectPetWeight();

        whistleBasePage.write2Log4jAllureStep("Select Pet's Weight in Kgs");
        petEditProfilePage.selectPetWeightKgs();

        whistleBasePage.write2Log4jAllureStep("Update Pet's Weight");
        petEditProfilePage.updatePetWeight(WhistleConstants.UPDATE_WEIGHT);

        whistleBasePage.write2Log4jAllureStep("click Save In Pet Weight PopUp");
        petEditProfilePage.clickSaveInPetWeightPopUp();

        whistleBasePage.write2Log4jAllureStep("Get the Pet Weight");
        sUpdatedWeight = petEditProfilePage.getPetWeightValue();

        whistleBasePage.write2Log4jAllureStep("Verify weight has been updated in Kgs");
        Assert.assertEquals(sUpdatedWeight, WhistleConstants.UPDATE_WEIGHT);
    }

    @TestRails(id = "1008553")
    @Description("Update Weight Status value- < 2 lbs or < 1 kg")
    @Test(priority = 0, groups = {"P0"})
    @Feature("Pet Profile- Update Weight")
    public void testUpdatePetWeightLessThan2Pounds() throws InterruptedException {
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

        whistleBasePage.write2Log4jAllureStep("Select Pet's Weight in Edit Pet Profile");
        petEditProfilePage.selectPetWeight();

        whistleBasePage.write2Log4jAllureStep("Select Pet's Weight in Lbs");
        petEditProfilePage.selectPetWeightLbs();

        whistleBasePage.write2Log4jAllureStep("Update Pet's Weight in Lbs");
        petEditProfilePage.updatePetWeight(WhistleConstants.UPDATE_WEIGHT);

        whistleBasePage.write2Log4jAllureStep("Select Pet's Weight");
        petEditProfilePage.selectPetWeightKgs();

        whistleBasePage.write2Log4jAllureStep("Update Pet's Weight");
        petEditProfilePage.updatePetWeight(WhistleConstants.UPDATE_WEIGHT_ZERO);

        whistleBasePage.write2Log4jAllureStep("Verify save button for Pet Weight is disabled for weight < 2Lbs and < 1 Kg");
        Assert.assertEquals(petEditProfilePage.isPetSaveBtnEnabled(), false);
    }

    @TestRails(id = "1008556")
    @Description("Update Weight Status value in Decimal")
    @Test(priority = 0, groups = {"P0"})
    @Feature("Pet Profile- Update Weight")
    public void testUpdatePetWeightDecimal() throws InterruptedException {
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

        whistleBasePage.write2Log4jAllureStep("Select Pet's Weight in Edit Pet Profile");
        petEditProfilePage.selectPetWeight();

        whistleBasePage.write2Log4jAllureStep("Select Pet's Weight ");
        petEditProfilePage.selectPetWeightLbs();

        whistleBasePage.write2Log4jAllureStep("Update Pet's Weight in Decimal");
        petEditProfilePage.updatePetWeight(WhistleConstants.UPDATE_WEIGHT_DECIMAL);

        whistleBasePage.write2Log4jAllureStep("click Save In Pet Weight PopUp");
        petEditProfilePage.clickSaveInPetWeightPopUp();

        whistleBasePage.write2Log4jAllureStep("Get the Pet Weight");
        sUpdatedWeight = petEditProfilePage.getPetWeightValue();

        whistleBasePage.write2Log4jAllureStep("Verify weight in decimal has been updated");
        Assert.assertEquals(sUpdatedWeight, WhistleConstants.UPDATE_WEIGHT_DECIMAL);
    }
}
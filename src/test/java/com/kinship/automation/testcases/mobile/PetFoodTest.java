package com.kinship.automation.testcases.mobile;

import com.kinship.automation.BaseTest;
import com.kinship.automation.commontestmanager.TestManager;
import com.kinship.automation.utils.driver.DriverFactory;
import com.kinship.automation.utils.listeners.TestListener;
import com.kinship.automation.utils.testrail.TestRails;
import com.kinship.automation.constants.WhistleConstants;
import com.kinship.automation.pageobjects.HomePageWhistle;
import com.kinship.automation.pageobjects.SignInPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.kinship.automation.utils.driver.TestDriver.ScrollDirection.UP;

/**
 * Created by Murali Jaladurgam on 12 April, 2022
 * */

@Listeners(TestListener.class)
public class PetFoodTest extends TestManager {
    
    @TestRails(id = "1008560")
    @Description("Pet Food - Null View")
    @Test(priority = 0, groups = {"P0"})
    @Feature("Pet Food - Null View")

    public void testUpdatePetFood() {

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

            whistleBasePage.write2Log4jAllureStep("Verifying Pet Food Details is Displayed");
            Assert.assertTrue(viewPetProfilePage.ischeckPetFoodDisplayed(), "Pet Food is not displayed");

        whistleBasePage.write2Log4jAllureStep("Scroll up to Pet Food");
        if (DriverFactory.platfrom.equalsIgnoreCase("android")&&DriverFactory.platfrom.equalsIgnoreCase("bs-android")) {
            testDriver.scrollToText((AppiumDriver<MobileElement>) driver, "Pet Food");
        }
        else
            testDriver.swipe(UP);


            whistleBasePage.write2Log4jAllureStep("navigating to Pet Food Page");
            petFoodPage =petEditProfilePage.clickPetFoodField();

            whistleBasePage.write2Log4jAllureStep("Validating Pet Food Page");
            whistleBasePage.write2Log4jAllureStep("Verifying Pet Food text is displayed");
            Assert.assertTrue(petFoodPage.isDogFoodDisplayed(),"Pet Food text not displayed");

            whistleBasePage.write2Log4jAllureStep("Verifying 'Can't Find Food?' CTA is enabled state");
            Assert.assertTrue(petFoodPage.isCantFindFoodDisplayed(),"'Can't Find Food?' CTA is not enabled");

            whistleBasePage.write2Log4jAllureStep("Verifying 'What food does he eat? 'text is displayed");
            Assert.assertTrue(petFoodPage.isPetFoodHeaderDisplayed(),"'What food does he eat?' text is not displayed");

            whistleBasePage.write2Log4jAllureStep("Verifying pet food field  is displayed");
            Assert.assertTrue(petFoodPage.isPetFoodFieldDisplayed(),"pet food field  is not displayed");

            whistleBasePage.write2Log4jAllureStep("Verifying pet food text input field  is displayed");
            Assert.assertTrue(petFoodPage.isPetFoodTextInputFieldDisplayed(),"pet food text input field  is not displayed");

            whistleBasePage.write2Log4jAllureStep("Verifying pet food list field  is displayed");
            Assert.assertTrue(petFoodPage.isPetFoodListFieldDisplayed(),"pet food list field  is not displayed");

            whistleBasePage.write2Log4jAllureStep("Closing the already 'Selected food' ");
            petFoodPage.clearfood();

            whistleBasePage.write2Log4jAllureStep("If 'Selected food' toolbar in empty state, Verifying 'Continue' CTA in disabled state");
            Assert.assertFalse(petFoodPage.isPetFoodContinueCTADisabled(),"'Continue' CTA in Enabled state");

            petEditProfilePage = petFoodPage.navigateBackPagetoPetPage();
            viewPetProfilePage = petEditProfilePage.navigateBackPage();
        }


    @TestRails(id = "1008563")
    @Description("Pet Food - Can't Find")
    @Test(priority = 1, groups = {"P1"})
    @Feature("Pet Food - Can't Find")

    public void testPetFoodCantFind()  {

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

        whistleBasePage.write2Log4jAllureStep("Scroll up to Pet Food");
        if (DriverFactory.platfrom.equalsIgnoreCase("android")&&DriverFactory.platfrom.equalsIgnoreCase("bs-android")) {
            testDriver.scrollToText((AppiumDriver<MobileElement>) driver, "Pet Food");
        }
        else
            testDriver.swipe(UP);

            whistleBasePage.write2Log4jAllureStep("navigating to Pet Food Page");
            petFoodPage =petEditProfilePage.clickPetFoodField();

            whistleBasePage.write2Log4jAllureStep("navigating to Pet Food - Can't Find Page");
            petFoodCantFindPage =petFoodPage.clickPetFoodCantFind();

            whistleBasePage.write2Log4jAllureStep("Opened Pet Food - Can't Find Page");
            whistleBasePage.write2Log4jAllureStep("Verifying Back Navigation Button is displayed");
            Assert.assertTrue(petFoodCantFindPage.isBackButtonDisplayed(),"Back Navigation Button not displayed");

            whistleBasePage.write2Log4jAllureStep("Verifying Asset Icon is Displayed");
            Assert.assertTrue(petFoodCantFindPage.isAssetIconDisplayed(),"Asset Icon is not Displayed");

            whistleBasePage.write2Log4jAllureStep("Verifying Asset Header is Displayed ");
            Assert.assertTrue(petFoodCantFindPage.isAssetHeaderDisplayed(),"Asset Header is not Displayed");

            whistleBasePage.write2Log4jAllureStep("Verifying SubCopy is Displayed ");
            Assert.assertTrue(petFoodCantFindPage.isSubCopyDisplayed(),"SubCopy is not Displayed");

            whistleBasePage.write2Log4jAllureStep("Verifying Food search toolbar with 'Custom food name' ");
            Assert.assertTrue(petFoodCantFindPage.isCustomFoodNameDisplayed(),"Food search toolbar with 'Custom food name'  is not Displayed");

            whistleBasePage.write2Log4jAllureStep("If 'Selected food' toolbar in empty state, Verifying 'Continue' CTA in disabled state");
            Assert.assertFalse(petFoodCantFindPage.isPetFoodContinueCTAEnabled(),"'Continue' CTA in Enabled state");

            petFoodPage=petFoodCantFindPage.navigateBackPagetoPetFoodPage();
            petEditProfilePage = petFoodPage.navigateBackPagetoPetPage();
            viewPetProfilePage = petEditProfilePage.navigateBackPage();
        }

    @TestRails(id = "1008564")
    @Description("Pet Food - Can't Find Search")
    @Test(priority = 2, groups = {"P1"})
    @Feature("Pet Food - Can't Find Search")

    public void testPetFoodCantFindSearch()  {

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

            whistleBasePage.write2Log4jAllureStep("navigating to Pet Food Page");
            petFoodPage =petEditProfilePage.clickPetFoodField();

            whistleBasePage.write2Log4jAllureStep("navigating to Pet Food - Can't Find Page");
            petFoodCantFindPage =petFoodPage.clickPetFoodCantFind();

            whistleBasePage.write2Log4jAllureStep("Landed on 'Suggest a Dog Food' screen");

            whistleBasePage.write2Log4jAllureStep("Verifying 'Suggest a Dog Food' page name is Displayed ");
            Assert.assertTrue(petFoodCantFindPage.isAssetHeaderDisplayed(),"Asset Header is not Displayed");

            whistleBasePage.write2Log4jAllureStep("Verifying Food search toolbar with 'Custom food name' ");
            petFoodCantFindPage.enterPetFoodCantFindSearchBox(WhistleConstants.PET_FOOD);

            whistleBasePage.write2Log4jAllureStep("Continue' CTA becomes Enabled, When Begin typing into search field ");
            Assert.assertTrue(petFoodCantFindPage.isPetFoodContinueCTAEnabled(),"'Continue' CTA in Disable state");

            petFoodPage=petFoodCantFindPage.navigateBackPagetoPetFoodPage();
            petEditProfilePage = petFoodPage.navigateBackPagetoPetPage();
            viewPetProfilePage = petEditProfilePage.navigateBackPage();
        }
    }


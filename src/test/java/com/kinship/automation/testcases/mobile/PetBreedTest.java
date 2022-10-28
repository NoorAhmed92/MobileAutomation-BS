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
 * Created by Murali Jaladurgam on 30 March, 2022
 * Modified by JBasera on 31 March, 2022
 * */

@Listeners(TestListener.class)
public class PetBreedTest extends TestManager {

    @TestRails(id="1008540")
    @Description("Breed")
    @Test(priority = 0, groups={ "P0" })
    @Feature("Pet Breed Name - Update")

    public void testEditPetBreedName() throws InterruptedException {
    	if ( (DriverFactory.platfrom.equalsIgnoreCase("android")) || (DriverFactory.platfrom.equalsIgnoreCase("BS-android")) ) {
            SignInPage signInPage = new SignInPage(testDriver);
            HomePageWhistle homePageWhistle = new HomePageWhistle(testDriver);
            if (!testDriver.isDisplayedWait(signInPage.signInLink, 3)) {
                whistleBasePage.write2Log4jAllureStep("user is already logged in");
            } else {
                homePageWhistle = signInPage.signInToHomePage();
                homePageWhistle.handleNotificationBanner();
            }
            viewPetProfilePage = homePageWhistle.clickPetMenuOptn();
            whistleBasePage.write2Log4jAllureStep("Capturing First Breed Value");
            String firstBreedValue = viewPetProfilePage.getProfilePageBreedValue();
            petEditProfilePage = viewPetProfilePage.selectEditIcon();

            whistleBasePage.write2Log4jAllureStep("Verifying Breed Details is Displayed");
            if (petEditProfilePage.isCheckBreedFieldDisplayed()) {
                whistleBasePage.write2Log4jAllureStep("Updating Breed Value");
                petEditProfilePage.editBreedField(WhistleConstants.PET_BREED_NAME, WhistleConstants.BREED_INDEX);
            }
            whistleBasePage.write2Log4jAllureStep("Navigating Back to Pet Profile Page");
            viewPetProfilePage = petEditProfilePage.navigateBackPage();
            whistleBasePage.write2Log4jAllureStep("Capturing Updated Breed Value");
            String updatedBreedValue = viewPetProfilePage.getProfilePageBreedValue();
            whistleBasePage.write2Log4jAllureStep("Validating Different Breed Value updated");
            Assert.assertNotEquals(updatedBreedValue, firstBreedValue, "Breed Field Matches");
            petEditProfilePage = viewPetProfilePage.selectEditIcon();
            if (petEditProfilePage.isCheckBreedFieldDisplayed()) {
                whistleBasePage.write2Log4jAllureStep("Re-Updating Breed Value");
                petEditProfilePage.editBreedField(firstBreedValue, 0);
            }
            viewPetProfilePage=petEditProfilePage.navigateBackPage();
        }else
            whistleBasePage.write2Log4jAllureStep("This test is in scope for Android Automation only");
    }
}

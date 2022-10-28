package com.kinship.automation.testcases.mobile;

import com.kinship.automation.BaseTest;
import com.kinship.automation.commontestmanager.TestManager;
import com.kinship.automation.utils.listeners.TestListener;
import com.kinship.automation.utils.testrail.TestRails;
import com.kinship.automation.pageobjects.SignInPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.kinship.automation.utils.driver.TestDriver.ScrollDirection.UP;

/**
 * Created by Jyoti Basera on 17 Feb, 2022 and added test case C993592
 * Modified by Murali Jaladurgam on 23 Feb, 2022
 * Modified by Jyoti Basera on 07 Apr, 2022
 * */

@Listeners(TestListener.class)
public class GetStartedSetUpTest extends TestManager {

	@TestRails(id = "993592")
	@Description("Home screen- Whistle Get Started Set Up")
	@Test(priority = 0, groups={ "BAT", "P0" })
	@Feature("GetStartedSetUp")
	public void testGetStarted() throws InterruptedException {
		SignInPage signInPage = new SignInPage(testDriver);
		if( !testDriver.isDisplayedWait(signInPage.signInLink, 3 )){
			log.info("user is already logged in");
			signInPage.clickTab("Human");
			log.info("Scroll up to Account Information");
			testDriver.swipe(UP);
			signInPage.clickTab("Account Information");
			log.info("Click on Logout Option");

			testDriver.swipe(UP);
			signInPage.clickTab("Logout");
			log.info("Logged out from Account");
		}

		selectDevicePage = whistleBasePage.clickGetStartedButton();

		log.info("Verifying Select your device page title");
		Assert.assertTrue(selectDevicePage.isSelectYourDevicePageTitleDisplayed(), "Verify if select your device page title is displayed");
		log.info("Verifying if Select your device text is displayed");
		Assert.assertTrue(selectDevicePage.isSelectYourDeviceTxtDisplayed(), "Verify if Select your device text is displayed");

		whistleSetUpPage = selectDevicePage.clickOnGoExplorerDevice();
		log.info("Verifying if user landed in Whistle Setup page");
		Assert.assertTrue(whistleSetUpPage.isWhistleSetupPageTitleDisplayed(), "Verifying if Whistle Set up page title is displayed");

		log.info("Verifying if Are you home? text is displayed");
		Assert.assertTrue(whistleSetUpPage.isAreYouHomeTxtDisplayed(), "Verifying if Are you home? text is displayed");

		log.info("Verifying if Yes, I'm home CTA is displayed");
		Assert.assertTrue(whistleSetUpPage.isYesIAmHomeBtnDisplayed(), "Verify if Yes, I'm home CTA is displayed");

		log.info("Verifying if No, I'm not home right now CTA is displayed");
		Assert.assertTrue(whistleSetUpPage.isNoIAmNotHomeButtonDisplayed(), "Verify if No, I'm not home right now CTA is displayed");

		whistleSetUpPage.clickOnYesIHomeButton();
		
		whistleSetUpPage.clickAppBackNavigation();
		selectDevicePage = whistleSetUpPage.clickAppBackNavigation();
		selectDevicePage.clickAppBackNavigation();
		}
}
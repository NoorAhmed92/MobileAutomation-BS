package com.kinship.automation.testcases.mobile;

import com.kinship.automation.BaseTest;
import com.kinship.automation.commontestmanager.TestManager;
import com.kinship.automation.utils.listeners.Retry;
import com.kinship.automation.utils.driver.DriverFactory;
import com.kinship.automation.utils.listeners.TestListener;
import com.kinship.automation.utils.testrail.TestRails;
import com.kinship.automation.constants.WhistleConstants;
import com.kinship.automation.pageobjects.HomePageWhistle;
import com.kinship.automation.pageobjects.HumanStuffPage;
import com.kinship.automation.pageobjects.SignInPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.kinship.automation.utils.driver.TestDriver.ScrollDirection.UP;

/**
 * Modified by Jyoti Basera on 17 Feb, 2022 and added test case C994168
 * Modified by Murali Jaladurgam on 23 Feb, 2022 and added test case C993591
 * Modified by Jyoti Basera on 28 Feb, 2022
 * Modified by Jyoti Basera on 11 Mar, 2022
 * */

@Listeners(TestListener.class)
public class LoginLogoutTest extends TestManager {

	@TestRails(id = "994168")
	@Description("Test Sign In screen View")
	@Test(priority = 0, groups={ "BAT", "P0" }, retryAnalyzer = Retry.class)
	@Feature("LogInLogOutTest")
	public void testSignInScreenView() throws InterruptedException, IOException {
	SignInPage signInPage = new SignInPage(testDriver);
	HomePageWhistle homePageWhistle = new HomePageWhistle(testDriver);
	HumanStuffPage humanStuffPage = new HumanStuffPage(testDriver);

	if( !testDriver.isDisplayedWait(signInPage.signInLink, 3 )){
		log.info("user is already logged in");
	}else{
		signInPage = whistleBasePage.clickSignInLnk();
		log.info("Verify UI elements on Sign In Screen");

		Assert.assertTrue(signInPage.isWelcomeTextSignInScreenDisplayed(), "Verifying if Welcome text is displayed");
		Assert.assertTrue(signInPage.isSignInPageTitleDisplayed(), "Verifying if Sign In page title is displayed");
		Assert.assertTrue(signInPage.isAppBackNavigationPresent(), "Verifying if app back navigation is present");
		Assert.assertTrue(signInPage.isEmailFieldPresent(), "Verifying if Email address text is displayed");
		Assert.assertTrue(signInPage.isPasswordFieldPresent(), "Verifying if Password field is displayed");

		log.info("Verifying if email address field is clickable");
		signInPage.verifyWaterMarkText();

		log.info("Verifying if Forgot Password CTA is displayed");
		Assert.assertTrue(signInPage.isForgotPasswordCTADisplayed(), "Verifying if Forgot Password CTA is displayed");

		log.info("Verifying if Log In button is not displayed on landing Sign In screen");
		signInPage.verifyLoginBtn();

		log.info("Enter email and password");
		signInPage.enterEmail(WhistleConstants.emailMobile);
		signInPage.enterPassword(WhistleConstants.password);
		log.info("Verifying Log In button is enabled on Sign In screen after entering valid credentials");
		Assert.assertTrue(signInPage.isLogInBtnDisplayed(), "Log In button is enabled");
		homePageWhistle = signInPage.clickLoginBtn();
	}

		log.info("Verifying for Family Trip screen");
		homePageWhistle.handleNotificationBanner();

		log.info("Click on Human Tab");
		if ( (DriverFactory.platfrom.equalsIgnoreCase("android")) || (DriverFactory.platfrom.equalsIgnoreCase("ios")) ){
			humanStuffPage = signInPage.clickHumanTab();
		}else {
			signInPage.clickTab("Human");
		}

		log.info("Scroll up to Account Information");

		if (DriverFactory.platfrom.equalsIgnoreCase("android")) {
			testDriver.scrollToText((AppiumDriver<MobileElement>) driver, "Account Information");
		}
		else {
			testDriver.swipe(UP);
		}

		log.info("Click on Account Information Option");
		if (signInPage.isAndroidBS()) {
			accountInformationPage = humanStuffPage.clickAccountInformation();
			testDriver.scrollToText((AppiumDriver<MobileElement>) driver, "Log Out");
			accountInformationPage.clickLogout();
			accountInformationPage.clickLogoutPopUpBtn();
		}
		else{
			log.info("Scroll up to Account Information");
			testDriver.swipe(UP);
			signInPage.clickTab("Account Information");
			log.info("Click on Logout Option");
			signInPage.clickTab("Logout");
		}
		log.info("Logged out successfully");
}

	@TestRails(id = "993591")
	@Description("Home screen - Existing User Sign In")
//	@Test(priority = 1, groups={ "BAT", "P0" }, retryAnalyzer = Retry.class)
	@Feature("SignIn")
	public void testHomeScreenExistingUserLogin() throws InterruptedException, IOException {
		signInPage = whistleBasePage.clickSignInLnk();
		Assert.assertTrue(signInPage.isEmailFieldPresent(), "Verifying if Email address text is displayed");
		signInPage.enterEmail(WhistleConstants.emailMobile);
		Assert.assertTrue(signInPage.isPasswordFieldPresent(), "Verifying if Password field is displayed");
		signInPage.enterPassword(WhistleConstants.password);
		Assert.assertTrue(signInPage.isLogInBtnDisplayed(), "Log In button is not enabled on landing Sign");
		homePageWhistle = signInPage.clickLoginBtn();
		if (homePageWhistle.isPermissionRequiredPopUpPresent())
			homePageWhistle.clickPermissionRequiredOkBtn();
		if (homePageWhistle.isAllowLocationPopUpPresent())
			homePageWhistle.clickAllowWhileUsingApp();
	}
}

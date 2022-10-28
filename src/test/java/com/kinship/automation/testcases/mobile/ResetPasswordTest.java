package com.kinship.automation.testcases.mobile;

import com.kinship.automation.BaseTest;
import com.kinship.automation.commontestmanager.TestManager;
import com.kinship.automation.utils.listeners.Retry;
import com.kinship.automation.utils.driver.DriverFactory;
import com.kinship.automation.utils.listeners.TestListener;
import com.kinship.automation.utils.testrail.TestRails;
import com.kinship.automation.constants.WhistleConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by jBasera on 11 Jan, 2022
 * Modified by jBasera on 15 Mar, 2022
 * */

@Listeners(TestListener.class)
public class ResetPasswordTest extends TestManager {

	@TestRails(id="994069")
	@Description("Test Reset Password View")
	@Test(priority=0, groups={ "P0" }, retryAnalyzer = Retry.class)
	@Feature("ResetPassword")
    public void testResetPasswordView() throws InterruptedException, IOException {
		signInPage = whistleBasePage.clickSignInLnk();
		resetPasswordPage = signInPage.clickForgotPasswordBtn();
		resetPasswordPage.enterEmail(WhistleConstants.emailMobile);
		Assert.assertTrue(resetPasswordPage.isResetPasswordBtnEnabled(), "Reset password button is not enabled");
	}

	@TestRails(id="994070")
	@Description("Test Reset Password View using valid Email.")
	@Test(priority=1, groups={ "P0" }, retryAnalyzer = Retry.class)
	@Feature("ResetPassword")
	public void testResetPasswordViewWithValidEmail() throws InterruptedException, IOException {
		signInPage = whistleBasePage.clickSignInLnk();
		resetPasswordPage = signInPage.clickForgotPasswordBtn();
		resetPasswordPage.enterEmail(WhistleConstants.emailMobile);
		resetPasswordPage.isResetPasswordBtnEnabled();
		resetPasswordPage.clickResetPasswordBtn();
		log.info("Verifying if Reset password toast message for valid email is displayed");
		if ( (DriverFactory.platfrom.contains("android")) ){
			Assert.assertEquals(resetPasswordPage.getToastMessage(), WhistleConstants.RESET_PASSWORD_LINK_SENT_MSG);
		}else {
			signInPage.isWelcomeTextSignInScreenDisplayed();
			Assert.assertTrue(signInPage.isWelcomeTextSignInScreenDisplayed(), "User is not landed to Welcome Sign In screen");
		}
	}

	@TestRails(id="994072")
	@Description("Test Reset Password View using Invalid Email.")
	@Test(priority=2, groups={ "P1" }, retryAnalyzer = Retry.class)
	@Feature("ResetPassword")
	public void testResetPasswordViewWithInvalidEmail() throws InterruptedException, IOException {
		signInPage = whistleBasePage.clickSignInLnk();
		resetPasswordPage = signInPage.clickForgotPasswordBtn();
		resetPasswordPage.enterEmail(WhistleConstants.invalidEmail);
		log.info("Verifying if Reset password button is not displayed on entering invalid email");
		if ( (DriverFactory.platfrom.contains("android")) ){
			Assert.assertFalse(resetPasswordPage.isResetPasswordBtnDisplayed());
		}else {
			Assert.assertFalse(resetPasswordPage.isResetPasswordBtnEnabled());
		}
	}
	
	@TestRails(id="994073")
	@Description("Test Reset Password View using non-existing Email.")
	@Test(priority=1, groups={ "P1" }, retryAnalyzer = Retry.class)
	@Feature("ResetPassword")
	public void testResetPasswordViewWithNonExistingEmail() throws InterruptedException, IOException {
		signInPage = whistleBasePage.clickSignInLnk();
		resetPasswordPage = signInPage.clickForgotPasswordBtn();
		resetPasswordPage.enterEmail(WhistleConstants.nonExistingEmail);
		resetPasswordPage.isResetPasswordBtnEnabled();
		resetPasswordPage.clickResetPasswordBtn();
		log.info("Verifying if Reset password toast message for non-existing email is displayed");
		if ( (DriverFactory.platfrom.contains("android")) ){
			Assert.assertEquals(resetPasswordPage.getToastMessage(), WhistleConstants.RESET_PASSWORD_NONEXISTING_EMAIL_MSG);
		}else {
			resetPasswordPage.clickOKBtn();
		}
	}
}

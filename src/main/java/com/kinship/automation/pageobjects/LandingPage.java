package com.kinship.automation.pageobjects;

/**
 * Created by hpatel on 01 Mar, 2022
 * Modified by jBasera on 17 Mar, 2022
 */
import com.kinship.automation.utils.commonutils.ByT;
import com.kinship.automation.utils.driver.DriverFactory;
import com.kinship.automation.utils.driver.TestDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.kinship.automation.constants.WhistleConstants.*;


public class LandingPage{

	private final By getStartedBtn = ByT.locator(By.id("Get started"), By.id("welcome_screen_set_up_device_btn"));
	private final By signInButton = ByT.locator(By.xpath("//XCUIElementTypeButton[@name=\"welcome - sign in label\"]"),By.id("welcome_screen_sign_in_btn"));

	private final TestDriver driver;
	public LandingPage(TestDriver driver) {
		this.driver = driver;
	}

	@Step("Verifying Get Started Button is Display or not")
	public void isGetStartedBtnDisplayed() {
		Assert.assertTrue(driver.isElementEnabled(getStartedBtn));
	}

	@Step("Verifying Get Started Button is enable")
	public void isGetStartedBtnEnable() throws InterruptedException {
		Assert.assertTrue(driver.isElementEnabled(getStartedBtn));
	}

	@Step("Verifying if Sign In button is displayed")
	public boolean isSignInBtnDisplayed() {
		boolean flag = false;
		driver.pauseExecutionFor(2);
		flag = driver.isElementPresent(signInButton);
		return flag;
	}

	@Step("Have an account? Sign in Text Display")
	public void isSignInTxtDisplayed() {
		Assert.assertTrue(driver.isElementPresent(signInButton));
		String sSignTxt = driver.getText(signInButton, "signInButton");
		System.out.println(sSignTxt);
		if ( (DriverFactory.platfrom.equalsIgnoreCase("ios")) || (DriverFactory.platfrom.equalsIgnoreCase("bs-ios"))){
			Assert.assertEquals(sSignTxt, SIGN_IN_TEXT, "Text Doesn't match");
		}else {
			Assert.assertEquals(sSignTxt, SIGN_IN_MSG, "Text Doesn't match");
		}
	}

	@Step("Click on sign in link")
	public SignInPage clickSignInLnk() {
		driver.clickLocator(signInButton);
		return new SignInPage(driver);
	}
}

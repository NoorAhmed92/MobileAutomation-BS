package com.kinship.automation.testcases.mobile;

import com.kinship.automation.BaseTest;
import com.kinship.automation.commontestmanager.TestManager;
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
 * Created by Murali Jaladurgam on 18 March, 2022
 * */

@Listeners(TestListener.class)
public class PetEditProfileTest extends TestManager {

	@TestRails(id="1008537")
	@Description("Verify update valid Pet Name")
	@Test(priority = 0, groups={ "P0" })
	@Feature("Pet Profile- Update Name")
	public void testEditPetNameUpdate()  {
		SignInPage signInPage = new SignInPage(testDriver);
		HomePageWhistle homePageWhistle = new HomePageWhistle(testDriver);
		if( !testDriver.isDisplayedWait(signInPage.signInLink, 3 )){
			log.info("user is already logged in");
		}
		else {
			homePageWhistle = signInPage.signInToHomePage();
			homePageWhistle.handleNotificationBanner();
		}
		viewPetProfilePage=homePageWhistle.clickPetMenuOptn();
		petEditProfilePage = viewPetProfilePage.selectEditIcon();
		log.info("Updating Pet Profile Name ");
		if(petEditProfilePage.ischeckPetNameDisplayed())
			petEditProfilePage.editPetNameValue(WhistleConstants.UPDATE_PET_NAME);
		log.info("Re-Updating Pet Profile Old Name ");
		if(petEditProfilePage.ischeckPetNameDisplayed())
			petEditProfilePage.editPetNameValue(WhistleConstants.UPDATE_OLD_PET_NAME);
		viewPetProfilePage.clickBackBtnToPetProfilePage();
	}

	@TestRails(id="1008538")
	@Description("Pet Name - Blank")
	@Test(priority = 1, groups={ "P0" })
	@Feature("Pet Name - Blank")
	public void testEditPetNameNull()  {
		SignInPage signInPage = new SignInPage(testDriver);
		HomePageWhistle homePageWhistle = new HomePageWhistle(testDriver);
		if( !testDriver.isDisplayedWait(signInPage.signInLink, 3 )){
			log.info("user is already logged in");
		}
		else {
			homePageWhistle = signInPage.signInToHomePage();
			homePageWhistle.handleNotificationBanner();
		}
		viewPetProfilePage=homePageWhistle.clickPetMenuOptn();
		petEditProfilePage = viewPetProfilePage.selectEditIcon();
		log.info("Verifying Pet Name is Blank  ");
		if(petEditProfilePage.ischeckPetNameDisplayed()) {
			log.info("Verifying save Button is Disabled");
			Assert.assertFalse(petEditProfilePage.editPetNameNullValue(),"Save Button is Enabled");
			viewPetProfilePage.clickBackBtnToPetProfilePage();
		}
	}

	@TestRails(id="1008539")
	@Description("Name - Long string")
	@Test(priority = 2, groups={ "P0" })
	@Feature("Pet Name - Long string")
	public void testEditPetNameLongString() {
		SignInPage signInPage = new SignInPage(testDriver);
		HomePageWhistle homePageWhistle = new HomePageWhistle(testDriver);
		if( !testDriver.isDisplayedWait(signInPage.signInLink, 3 )){
			log.info("user is already logged in");
		}
		else {
			homePageWhistle = signInPage.signInToHomePage();
			homePageWhistle.handleNotificationBanner();
		}
		viewPetProfilePage=homePageWhistle.clickPetMenuOptn();
		petEditProfilePage = viewPetProfilePage.selectEditIcon();
		log.info("Verifying Pet Profile Name is Long string ");
		if(petEditProfilePage.ischeckPetNameDisplayed()) {
			log.info("Verifying save Button is Disabled");
			Assert.assertFalse(petEditProfilePage.editLongPetNameValue(WhistleConstants.LONG_PET_NAME),"Save Button is Enabled");

		}
	}
}
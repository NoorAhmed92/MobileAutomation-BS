package com.kinship.automation.testcases.mobile;

import com.kinship.automation.BaseTest;
import com.kinship.automation.commontestmanager.TestManager;
import com.kinship.automation.utils.listeners.TestListener;
import com.kinship.automation.utils.testrail.TestRails;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Modified by JBasera on 07 Mar, 2022
 * Modified by JBasera on 23 Mar, 2022
 * Modified by Hpatel 25 Mar, 2022
 * Modified by JBasera on 08 Apr, 2022
 * */

@Listeners(TestListener.class)
public class DeviceSetUpTest extends TestManager {

	@TestRails(id = "994077")
	@Description("Test BLE Primer Screen for Whistle Go Explorer Device")
	@Test(priority = 0, groups={ "P0" })
	@Feature("WhistleDeviceSetUp")
	public void testWhistleGoExploreDevice() throws InterruptedException {
		selectDevicePage = whistleBasePage.clickGetStartedButton();	
		whistleSetUpPage = selectDevicePage.clickOnGoExplorerDevice();
		assembleDevicePage = whistleSetUpPage.clickOnYesIHomeButton();
		boolean isEnabled = assembleDevicePage.isPairMyDeviceEnabled();
		Assert.assertEquals(isEnabled, true);
		whistleSetUpPage = assembleDevicePage.clickAppBackNavigation();
		selectDevicePage = whistleSetUpPage.clickAppBackNavigation();
		selectDevicePage.clickAppBackNavigation();
	}

	@TestRails(id = "994076")
	@Description("Test BLE Primer Screen for Whistle Switch Device")
	@Feature("WhistleDeviceSetUp")
	@Test(priority = 1, groups={ "P0" })
	public void testWhistleSwitchDevice() throws InterruptedException {
		selectDevicePage = whistleBasePage.clickGetStartedButton();	
		whistleSetUpPage = selectDevicePage.clickOnSwitchDevice();
		assembleDevicePage = whistleSetUpPage.clickOnYesIHomeButton();
		boolean isEnabled = assembleDevicePage.isPairMyDeviceEnabled();
		Assert.assertEquals(isEnabled, true);
		whistleSetUpPage = assembleDevicePage.clickAppBackNavigation();
		selectDevicePage = whistleSetUpPage.clickAppBackNavigation();
		selectDevicePage.clickAppBackNavigation();

	}

	@TestRails(id = "994080")
	@Description("Test BLE Primer Screen for Whistle Fit Device")
	@Feature("WhistleDeviceSetUp")
	@Test(priority = 2, groups={ "P0" })
	public void testWhistleFitDevice() throws InterruptedException {
		selectDevicePage = whistleBasePage.clickGetStartedButton();	
		whistleSetUpPage = selectDevicePage.clickOnFitDevice();
		assembleDevicePage = whistleSetUpPage.clickOnYesIHomeButton();
		boolean isEnabled = assembleDevicePage.isPairMyDeviceEnabled();
		Assert.assertEquals(isEnabled, true);
		whistleSetUpPage = assembleDevicePage.clickAppBackNavigation();
		selectDevicePage = whistleSetUpPage.clickAppBackNavigation();
		selectDevicePage.clickAppBackNavigation();
		selectDevicePage.clickAppBackNavigation();
	}

	@TestRails(id = "994078")
	@Description("Test BLE Primer Screen for Whistle Go Device")
	@Feature("WhistleDeviceSetUp")
	@Test(priority = 3, groups={ "P0" })
	public void testWhistleGoDevice() throws InterruptedException {
		selectDevicePage = whistleBasePage.clickGetStartedButton();
		whistleSetUpPage = selectDevicePage.clickOnGoDevice();
		assembleDevicePage = whistleSetUpPage.clickOnYesIHomeButton();
		boolean isEnabled = assembleDevicePage.isPairMyDeviceEnabled();
		Assert.assertEquals(isEnabled, true);
		whistleSetUpPage = assembleDevicePage.clickAppBackNavigation();
		selectDevicePage = whistleSetUpPage.clickAppBackNavigation();
		selectDevicePage.clickAppBackNavigation();
		selectDevicePage.clickAppBackNavigation();
	}
}

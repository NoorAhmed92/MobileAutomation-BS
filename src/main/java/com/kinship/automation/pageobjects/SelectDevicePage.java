package com.kinship.automation.pageobjects;

import com.kinship.automation.utils.commonutils.ByT;
import com.kinship.automation.utils.driver.TestDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SelectDevicePage extends WhistleBasePage {

	public TestDriver driver;

	private By selectYourDevicePageTitle = ByT.locator(By.id("Whistle Setup"), By.xpath("//android.widget.TextView[@text='Whistle Setup']"));
	private By selectYourDeviceTxt = ByT.locator(By.id("setup choose type - title label"), By.id("device_picker_header"));
	private By selectDevice = ByT.locator(By.id("Other Whistle devices"), By.xpath("//android.widget.TextView[@text='Other Whistle devices']"));
	private By switchDevice = ByT.locator(By.id("Whistle Switch"),By.id("device_picker_item_scout") );
	private By fitDevice = ByT.locator(By.id("Whistle FIT"),By.id("device_picker_item_fit"));
	private By goExplorerDevice = ByT.locator(By.id("Whistle GO Explore"),By.id("device_picker_item_go_explore"));
	private By goDevice = ByT.locator(By.id("Whistle GO"),By.id("device_picker_item_go") );
	private By navigateUpBackButton= ByT.locator(By.id("ChubbyBackArrow"), By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"));

	public SelectDevicePage(TestDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Verify if Select your device page title is displayed")
	public boolean isSelectYourDevicePageTitleDisplayed() {
		return driver.isElementPresent(selectYourDevicePageTitle);
	}

	@Step("Verify if Select Your Device Text is displayed")
	public boolean isSelectYourDeviceTxtDisplayed() {
		return driver.isElementPresent(selectYourDeviceTxt);
	}

	@Step("Click on Switch Devices")
	public WhistleSetupPage clickOnSwitchDevice() {
		driver.clickLocator(switchDevice);
		return new WhistleSetupPage(driver);
	}

	@Step("Click on Fit Devices")
	public WhistleSetupPage clickOnFitDevice() {
		driver.clickLocator(selectDevice);
		driver.clickLocator(fitDevice);
		return new WhistleSetupPage(driver);
	}

	@Step("Click on Go Devices")
	public WhistleSetupPage clickOnGoDevice() {
		driver.clickLocator(selectDevice);
		driver.clickLocator(goDevice);
		return new WhistleSetupPage(driver);
	}

	@Step("Click on Go Explorer Devices")
	public WhistleSetupPage clickOnGoExplorerDevice() {
		driver.clickLocator(goExplorerDevice);
		return new WhistleSetupPage(driver);
	}

	@Step("Click on app back navigation")
	public WhistleSetupPage clickAppBackNavigation() {
		driver.isElementPresent(navigateUpBackButton);
		driver.clickLocator(navigateUpBackButton);
		return new WhistleSetupPage(driver);
	}
}

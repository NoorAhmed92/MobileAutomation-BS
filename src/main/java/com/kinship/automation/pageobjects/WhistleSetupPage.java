package com.kinship.automation.pageobjects;

import com.kinship.automation.utils.commonutils.ByT;
import com.kinship.automation.utils.driver.TestDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class WhistleSetupPage extends WhistleBasePage {

	public TestDriver driver;

	private By whistleSetupTitle = ByT.locator(By.id("Whistle Setup"), By.xpath("//android.widget.TextView[@text='Whistle Setup']"));
	private By areYouHomeText = ByT.locator(By.id("Are you home?"), By.id("are_you_home_header"));
	private By yesHomeBtn = ByT.locator(By.id("get started - at home button"), By.id("are_you_home_yes_btn"));
	private By noHomeBtn = ByT.locator(By.id("No, I’m not home right now"), By.id("are_you_home_no_btn"));
	private By navigateUpBackButton= ByT.locator(By.id("ChubbyBackArrow"), By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"));

	public WhistleSetupPage(TestDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Verify if Whistle Setup page title is displayed")
	public boolean isWhistleSetupPageTitleDisplayed() {
		return driver.isElementPresent(whistleSetupTitle);
	}

	@Step("Verify if Are you home? text is displayed")
	public boolean isAreYouHomeTxtDisplayed() {
		return driver.isElementPresent(areYouHomeText);
	}

	@Step("Verify if Are you home? text is displayed")
	public boolean isYesIAmHomeBtnDisplayed() {
		return driver.isElementPresent(yesHomeBtn);
	}

	@Step("Verify if No, I'm not home right now button is displayed")
	public boolean isNoIAmNotHomeButtonDisplayed() {
		return driver.isElementPresent(noHomeBtn);
	}

	@Step("Clicked on yes I am home button")
	public AssembleDevicePage clickOnYesIHomeButton() throws InterruptedException {
		driver.clickLocator(yesHomeBtn);
		return new AssembleDevicePage(driver);
	}

	@Step("Clicked on No, I'm not home right now button")
	public AssembleDevicePage clickOnNoIAmNotHomeButton() throws InterruptedException {
		driver.clickLocator(noHomeBtn);
		return new AssembleDevicePage(driver);
	}

	@Step("Click on app back navigation")
	public SelectDevicePage clickAppBackNavigation() {
		driver.clickLocator(navigateUpBackButton);
		return new SelectDevicePage(driver);
	}
}
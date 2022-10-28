package com.kinship.automation.pageobjects;

import com.kinship.automation.utils.commonutils.ByT;
import com.kinship.automation.utils.driver.TestDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class AssembleDevicePage extends WhistleBasePage {

	public TestDriver driver;
	
	private By pairDeviceBtn = ByT.locator(By.id("Pair my device"), By.xpath("//android.widget.Button[@text='Pair my device']"));
	private By navigateUpBackButton= ByT.locator(By.id("ChubbyBackArrow"), By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"));

	public AssembleDevicePage(TestDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Verify if pair button is enable")
	public boolean isPairMyDeviceEnabled() {
		return driver.isElementEnabled(pairDeviceBtn);
	}
	
	@Step("Click on app back navigation")
	public WhistleSetupPage clickAppBackNavigation() {
		driver.clickLocator(navigateUpBackButton);
		return new WhistleSetupPage(driver);
	}
}
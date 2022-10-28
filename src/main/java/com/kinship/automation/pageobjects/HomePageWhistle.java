package com.kinship.automation.pageobjects;

import com.kinship.automation.utils.commonutils.ByT;
import com.kinship.automation.utils.driver.DriverFactory;
import com.kinship.automation.utils.driver.DriverManager;
import com.kinship.automation.utils.driver.TestDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePageWhistle  extends WhistleBasePage {

	TestDriver driver;

	private By appLogo = ByT.locator(By.id("Welcome to"), By.id("welcome_screen_logo"));
	private By permissionsRequiredPopUp = ByT.locator(By.id(""), By.id("md_titleFrame"));
	private By permissionsRequiredPopUpOkBtn = ByT.locator(By.id("OK"), By.id("md_buttonDefaultPositive"));
	private By allowLocationPopUp = ByT.locator(By.id("Allow Access"), By.id("com.android.permissioncontroller:id/grant_dialog"));
	private By allowWhileUsingApp = ByT.locator(By.id("Allow While Using App"), By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"));
	private By humanStuffMenuOptn = ByT.locator(By.id("Human"), By.id("main_bottom_nav_human_stuff"));
	private By petStuffMenuOptn = ByT.locator(By.xpath("//XCUIElementTypeOther[contains(@id,'Pet')]"), By.id("main_bottom_nav_pet_stuff"));
	private By petStuffMenuOptnbs = ByT.locator(By.id("Pet"), By.id("main_bottom_nav_pet_stuff"));
	private By helpMakeWhistleBetterOptn = ByT.locator(MobileBy.iOSNsPredicateString("label == \"Help Make Whistle Better\""), By.id("human_tab_list_item_make_us_better"));
	private By helpUrl = ByT.locator(By.id(""), By.id("com.sec.android.app.sbrowser:id/url_bar_text"));
	private By closeWebView = ByT.locator(By.id(""), By.id("com.sec.android.app.sbrowser:id/customtab_close"));
	private By accountInfoOptn = ByT.locator(MobileBy.iOSNsPredicateString("label == \"Account Information\""), By.id("human_tab_list_item_account_information"));
	private By logOutOptn = ByT.locator(MobileBy.iOSNsPredicateString("label == \"Logout\""), By.id("account_list_item_log_out"));
	private String family = ByT.locator("MobileBy.iOSNsPredicateString(\"label == \\\"Yer Larkin\\\"\")", "//*[@resource-id='one_line_list_item_label' and @text='%s']");
	//Need to fix for ios
	private String removeLnk = ByT.locator("//*[@text='%s']", "//*[@resource-id='family_list_item_name' and @text='%s']//following-sibling::android.widget.TextView");
	private By confirmRemove = ByT.locator(MobileBy.xpath("//XCUIElementTypeButton[@name=\"Confirm\"]"), By.id("md_buttonDefaultPositive"));
	private By areYouSurePopUp = ByT.locator(By.id(""), By.id("md_title"));
	private By confirmLogOut = ByT.locator(By.id(""), By.id("md_buttonDefaultPositive"));
	private By backButton = ByT.locator(By.id(""), By.xpath("//*[@resource-id='blank_toolbar_activity_toolbar']//android.widget.ImageButton"));
	private By addHumans = ByT.locator(By.id(""), By.id("pet_page_family_add_humans_item"));
	private By familyTripNotificationNextBtn = ByT.locator(By.id(""), By.id("safe_breach_intro_next_button"));
	private By familyTripNotificationDoneBtn = ByT.locator(By.id("Enable"), By.id("safe_breach_intro_next_button"));
	private By dismissEnableBluetooth = ByT.locator(By.xpath("//*[@text='Don’t Allow']"), By.id("alert_banner_action_secondary"));
	private By lblConfirmationMsg = MobileBy.xpath("//XCUIElementTypeStaticText[@name=\"Humpf!\"]/following-sibling::XCUIElementTypeStaticText");
	private By enableBluetoothBanner = ByT.locator(By.xpath("//*[@id='“Whistle-Beta” Would Like to Use Bluetooth']"), By.id("alert_banner_title"));
	private By findMyPetBtn = ByT.locator(By.id("Find My Pet"), By.xpath("//android.widget.TextView[@text='Pet']"));
	private By locationIconTabBar = ByT.locator(By.xpath("//*[@id='Location' and @class='UIAView']"), By.xpath("//android.widget.TextView[@text='Location']"));
	private By activityIconTabBar = ByT.locator(By.id("Activity"), By.xpath("//android.widget.TextView[@text='Activity']"));
	private By healthIconTabBar = ByT.locator(By.id("Health"), By.xpath("//android.widget.TextView[@text='Health']"));
	private By petIconTabBar = ByT.locator(By.id("Pet"), By.xpath("//android.widget.TextView[@text='Pet']"));
	private By humanIconTabBar = ByT.locator(By.id("Human"), By.xpath("//android.widget.TextView[@text='Human']"));


	public HomePageWhistle(TestDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isAppLogoDisplayed() {
		return driver.isElementPresent(appLogo);
	}

	public boolean isPermissionRequiredPopUpPresent() {
		driver.pauseExecutionFor(2);
		return driver.isElementPresent(permissionsRequiredPopUp);
	}

	@Step("Click on permission required button")
	public HomePageWhistle clickPermissionRequiredOkBtn() {
		driver.clickLocator(permissionsRequiredPopUpOkBtn);
		return this;
	}

	public boolean isAllowLocationPopUpPresent() {
		return driver.isElementPresent(allowLocationPopUp);
	}

	@Step("Click on Allow location pop up")
	public HomePageWhistle clickAllowWhileUsingApp() {
		driver.clickLocator(allowWhileUsingApp);
		return this;
	}

	@Step("Click on Human option option from footer")
	public HomePageWhistle clickHumanMenuOption() {
		driver.clickLocator(humanStuffMenuOptn);
		return this;
	}

	@Step("Click on Help make whistle better menu option")
	public HomePageWhistle clickHelpMakeWhistleBetter() {
		driver.clickLocator(helpMakeWhistleBetterOptn);
		return this;
	}

	@Step("Get the url for help make shistle better")
	public String getHelpUrl() {
		String url = "";
		if (DriverFactory.platfrom.equalsIgnoreCase("ios")) {
			((AppiumDriver) DriverManager.getDriver()).activateApp("com.apple.mobilesafari");
			System.out.println(((AppiumDriver) DriverManager.getDriver()).queryAppState("com.apple.mobilesafari"));
			WebElement urlTextFied = ((AppiumDriver) DriverManager.getDriver()).findElement(MobileBy.iOSNsPredicateString("label == \"Address\" AND name == \"URL\" AND value == \"\u200Ewhistle.typeform.com, secure and validated connection\" AND type == \"XCUIElementTypeOther\""));
			url = urlTextFied.getText();
			System.out.println(url);
			((AppiumDriver) DriverManager.getDriver()).activateApp("com.whistle.winston-ent");
		} else {
			driver.pauseExecutionFor(5);
			url = driver.getText(helpUrl, "fetching web view url");
		}
		return url;
	}

	@Step("Click on close the help window")
	public HomePageWhistle clickCloseWebView() {
		driver.clickLocator(closeWebView);
		return this;
	}

	@Step("Select account info menu option")
	public HomePageWhistle clickAccountInfoOptn() {
		driver.isElemneVisibleOnScreen(accountInfoOptn);
		driver.clickLocator(accountInfoOptn);
		return this;
	}

	@Step("Click on logout button")
	public HomePageWhistle clickLogoutBtn() {
		driver.isElemneVisibleOnScreen(logOutOptn);
		driver.clickLocator(logOutOptn);
		driver.clickLocator(confirmLogOut);
		return this;
	}

	@Step("Click on Pet option from footer")
	public ViewPetProfilePage clickPetMenuOptn() {
		driver.pauseExecutionFor(2);
		driver.clickLocator(petStuffMenuOptnbs);
		return new ViewPetProfilePage(driver);
	}

	@Step("Select the family")
	public void selectFamily(String familyName) {
		By loc = By.xpath(String.format(family, familyName));
		driver.scrollToElement(loc, addHumans, TestDriver.ScrollDirection.DOWN, 0.25, 8);
		driver.clickLocator(loc);
	}

	@Step("Click on Remove link for selected family")
	public void clickRemoveLnk(String familyName) {
		By loc = By.xpath(String.format(removeLnk, familyName));
		driver.clickLocator(loc);
	}

	public boolean isAreYouSurepoUpPresent() {
		return driver.isElementPresent(areYouSurePopUp);
	}

	@Step("Click confirm on are you sure pop up")
	public String clickConfirmRemoveBtn() {
		String text = "";
		driver.clickLocator(confirmRemove);
		if (DriverFactory.platfrom.equalsIgnoreCase("ios")) {
			driver.waitForElementToBeVisible(lblConfirmationMsg);
			text = driver.getText(lblConfirmationMsg, "Fetching confirmation message");
			driver.clickLocator(MobileBy.iOSNsPredicateString("label == \"OK\" AND name == \"OK\" AND value == \"OK\""));
		} else {
			text = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		}
		return text;
	}

	@Step("Click on back button to go to previous page")
	public HomePageWhistle clickBackButton() {
		driver.clickLocator(backButton);
		return this;
	}

	@Step("Is trip notification displayed")
	public boolean isTripNotificationPopUpPresent() {
		driver.pauseExecutionFor(3);
		boolean flag = false;
		flag = driver.isElementPresent(familyTripNotificationNextBtn);
		if (flag) {
			driver.clickLocator(familyTripNotificationNextBtn);
			driver.clickLocator(familyTripNotificationDoneBtn);
		}
		return flag;
	}

	public void dismissEnableBluetoothIfPresent() {
		if (driver.isElementPresent(dismissEnableBluetooth)) {
			driver.clickLocator(dismissEnableBluetooth);
		}
	}

	@Step("Verify if enable Bluetooth Banner is displayed")
	public boolean isEnableBLEBannerPresent() {
		return driver.isElementPresent(enableBluetoothBanner);
	}

	/**
	 * Modified by Murali Jaladurgam on 21 Feb, 2022
	 */

	@Step("Verify Location Icon is Present")
	public boolean isCheckLocationIconDisplayed() {
		return driver.isElementPresent(locationIconTabBar);
	}

	@Step("Verify Location Icon is enabled")
	public boolean isLocationIconEnabled() {
		return driver.isElementEnabled(locationIconTabBar);
	}

	@Step("Verify Location Icon is enabled")
	public boolean isFindMyPetBtnDisplayed() {
		return driver.isElementEnabled(findMyPetBtn);
	}

	@Step("Verify Activity Icon is Present")
	public boolean ischeckActivityIconDisplayed() {
		return driver.isElementPresent(activityIconTabBar);
	}

	@Step("Verify Health Icon is Present")
	public boolean ischeckHealthIconDisplayed() {
		return driver.isElementPresent(healthIconTabBar);
	}

	@Step("Verify Pet Icon is Present")
	public boolean ischeckPetIconDisplayed() {
		return driver.isElementPresent(petIconTabBar);
	}

	@Step("Verify Human Icon is Present")
	public boolean ischeckHumanIconDisplayed() {
		return driver.isElementPresent(humanIconTabBar);
	}

	public boolean isfamilyTripNotificationNextPresent() {
		driver.pauseExecutionFor(2);
		return driver.isElementPresent(familyTripNotificationNextBtn);
	}

	public boolean isfamilyTripNotificationDonePresent() {
		driver.pauseExecutionFor(2);
		return driver.isElementPresent(familyTripNotificationDoneBtn);
	}

	@Step("Click on permission required button")
	public HomePageWhistle clickfamilyTripNotificationNextBtn() {
		driver.waitForElementToBeVisible(familyTripNotificationNextBtn);
		driver.clickLocator(familyTripNotificationNextBtn);
		return this;
	}

	@Step("Click on permission required button")
	public HomePageWhistle clickfamilyTripNotificationDoneBtn() {
		driver.waitForElementToBeVisible(familyTripNotificationDoneBtn);
		driver.clickLocator(familyTripNotificationDoneBtn);
		return this;
	}

	public void handleNotificationBanner() {
		if (isfamilyTripNotificationNextPresent())
			clickfamilyTripNotificationNextBtn();
		if (isfamilyTripNotificationDonePresent())
			clickfamilyTripNotificationDoneBtn();
	}

	public void handleBluetoothBanner() {
		if (isEnableBLEBannerPresent())
			dismissEnableBluetoothIfPresent();
	}

	@Step("Click on Human Icon")
	public HumanStuffPage clickHumanIcon() {
		driver.clickLocator(humanIconTabBar);
		return new HumanStuffPage(driver);
	}
}

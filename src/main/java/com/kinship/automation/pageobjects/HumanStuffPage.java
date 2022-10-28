package com.kinship.automation.pageobjects;

import com.kinship.automation.utils.commonutils.ByT;
import com.kinship.automation.utils.driver.TestDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

/**
 * Created by Murali Jaladurgam on 19 April, 2022
 * */

public class HumanStuffPage extends WhistleBasePage {

	TestDriver driver;

	private By humanTitle=ByT.locator(By.id("Human Stuff"),By.id("main_title_toolbar"));
	private By referAFriend=ByT.locator(By.xpath(""),By.id("human_tab_refer_a_friend_banner"));
	private By settingsTitle=ByT.locator(By.id("Settings"),By.xpath("//android.widget.TextView[@text='Settings']"));
	private By safePlaces=ByT.locator(By.id("Safe Places"),By.id("human_tab_list_item_safe_places"));
	private By whistlePermissions=ByT.locator(By.id("Whistle Permissions"),By.id("human_tab_list_item_permissions"));
	private By pushNotifications=ByT.locator(By.id("Push Notifications"),By.id("human_tab_list_item_push_notifications"));
	private By emailNotifications=ByT.locator(By.id("Email Notifications"),By.id("human_tab_list_item_email_notifications"));
	private By textNotifications=ByT.locator(By.id("Text Notifications"),By.id("human_tab_list_item_sms_notifications"));
	private By familyTrips=ByT.locator(By.xpath(""),By.id("human_tab_list_item_family_trips"));
	private By supportTitle=ByT.locator(By.id("Support"),By.xpath("//android.widget.TextView[@text='Support']"));
	private By whistleHelpCenter=ByT.locator(By.id("Whistle Help Center"),By.id("human_tab_list_item_help_center"));
	private By messageWhistle=ByT.locator(By.id("Message Us"),By.id("human_tab_list_item_message_us_item"));
	private By helpMakeWhistleBetter=ByT.locator(By.id("Help Make Whistle Better"),By.id("human_tab_list_item_make_us_better"));
	private By takeTheTour=ByT.locator(By.id("Take the Tour"),By.id("human_tab_list_item_tour"));
	private By AccountTitle=ByT.locator(By.id("Account"),By.xpath("//android.widget.TextView[@text='Account']"));
	private By Subscription=ByT.locator(By.id("Subscription"),By.xpath("//android.widget.TextView[@text='Subscription']"));
	private By accountInfoBtn = ByT.locator(By.id("Account Information"), By.xpath("//android.widget.TextView[@text='Account Information']"));
	private By DevicesTitle=ByT.locator(By.id("Devices"),By.xpath("//android.widget.TextView[@text='Devices']"));
	private By AddAWhistle=ByT.locator(By.id("Add a Whistle"),By.id("human_tab_list_item_add_device"));
	private By ShopWhistle=ByT.locator(By.id("Shop Whistle"),By.id("human_tab_list_item_shop"));
	private By MadeWithFor=ByT.locator(By.id("Made with ❤️ for \uD83D\uDC36\uD83D\uDC31"),By.id("human_tab_made_with_love_lbl"));

	public HumanStuffPage(TestDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Click on Account Information")
	public AccountInformationPage clickAccountInformation() {
		driver.waitForElementToBeVisible(accountInfoBtn);
		driver.clickLocator(accountInfoBtn);
		return new AccountInformationPage(driver);
	}

	public boolean isHumanStuffTitlePresent() {
		return driver.isElementPresent(humanTitle);
	}

	public boolean isReferAFriendPresent() {
		driver.waitForElementToBeVisible(referAFriend);
		return driver.isElementPresent(referAFriend);
	}

	public boolean isSafePlacesPresent() {
		return driver.isElementPresent(safePlaces);
	}

	public boolean isWhistlePermissionsPresent() {
		return driver.isElementPresent(whistlePermissions);
	}

	public boolean isPushNotificationsPresent() {
		return driver.isElementPresent(pushNotifications);
	}

	public boolean isEmailNotificationsPresent() {
		return driver.isElementPresent(emailNotifications);
	}

	public boolean isTextNotificationsPresent() {
		return driver.isElementPresent(textNotifications);
	}

	public boolean isFamilyTripsPresent() {
		return driver.isElementPresent(familyTrips);
	}

	public boolean isSettingsTitlePresent() {
		return driver.isElementPresent(settingsTitle);
	}

	public boolean isSupportTitlePresent() {
		return driver.isElementPresent(supportTitle);
	}

	public boolean isWhistleHelpCenterPresent() {
		return driver.isElementPresent(whistleHelpCenter);
	}

	public boolean isMessageWhistlePresent() {
		return driver.isElementPresent(messageWhistle);
	}

	public boolean isHelpMakeWhistleBetterPresent() {
		return driver.isElementPresent(helpMakeWhistleBetter);
	}

	public boolean isTakeTheTourPresent() {
		return driver.isElementPresent(takeTheTour);
	}

	public boolean isAccountTitlePresent() {
		return driver.isElementPresent(AccountTitle);
	}

	public boolean isSubscriptionPresent() {
		return driver.isElementPresent(Subscription);
	}

	public boolean isAccountInformationPresent() {
		return driver.isElementPresent(accountInfoBtn);
	}

	public boolean isDevicesTitlePresent() {
		return driver.isElementPresent(DevicesTitle);
	}

	public boolean isAddAWhistlePresent() {
		return driver.isElementPresent(AddAWhistle);
	}

	public boolean isShopWhistlePresent() {
		return driver.isElementPresent(ShopWhistle);
	}

	public boolean isMadeWithForPresent() {
		return driver.isElementPresent(MadeWithFor);
	}
}

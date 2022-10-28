package com.kinship.automation.testcases.mobile;

import com.kinship.automation.BaseTest;
import com.kinship.automation.commontestmanager.TestManager;
import com.kinship.automation.utils.driver.DriverFactory;
import com.kinship.automation.utils.listeners.TestListener;
import com.kinship.automation.utils.testrail.TestRails;
import com.kinship.automation.pageobjects.HomePageWhistle;
import com.kinship.automation.pageobjects.SignInPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.kinship.automation.utils.driver.TestDriver.ScrollDirection.UP;

/**
 * Created by Murali Jaladurgam on 19 April, 2022
 * */

@Listeners(TestListener.class)
public class HumanStuffViewTest extends TestManager {

    @TestRails(id = "1008587")
    @Description("Pet Human View")
    @Test(priority = 0, groups = {"P0"})
    @Feature("Human Stuff View")

    public void testHumanStuffPageView() {

        SignInPage signInPage = new SignInPage(testDriver);
        HomePageWhistle homePageWhistle = new HomePageWhistle(testDriver);
        if (!testDriver.isDisplayedWait(signInPage.signInLink, 3)) {
            whistleBasePage.write2Log4jAllureStep("user is already logged in");
        } else {
            homePageWhistle = signInPage.signInToHomePage();
            homePageWhistle.handleNotificationBanner();
        }

        whistleBasePage.write2Log4jAllureStep("Click on Human Tab");
        if ((DriverFactory.platfrom.equalsIgnoreCase("ios")) || (DriverFactory.platfrom.equalsIgnoreCase("bs-ios")))
            signInPage.clickTab("Human");
        humanStuffPage = signInPage.clickHumanTab();

        whistleBasePage.write2Log4jAllureStep("Verifying Human Stuff Title is Displaying");
        Assert.assertTrue(humanStuffPage.isHumanStuffTitlePresent(), "Human Stuff Title is not Displayed");

        /* Settings Section */
        whistleBasePage.write2Log4jAllureStep("Validating Settings Section");
        Assert.assertTrue(humanStuffPage.isSettingsTitlePresent(), "Settings Title is not Displayed");

        whistleBasePage.write2Log4jAllureStep("Verifying Safe Places element is displayed");
        Assert.assertTrue(humanStuffPage.isSafePlacesPresent(), "Safe Places is not Displayed");

        whistleBasePage.write2Log4jAllureStep("Verifying Whistle Permissions element is displayed");
        Assert.assertTrue(humanStuffPage.isWhistlePermissionsPresent(), "Whistle Permissions is not Displayed");

        whistleBasePage.write2Log4jAllureStep("Verifying Push Notifications element is displayed");
        Assert.assertTrue(humanStuffPage.isPushNotificationsPresent(), "Push Notifications is not Displayed");

        whistleBasePage.write2Log4jAllureStep("Verifying Email Notifications element is displayed");
        Assert.assertTrue(humanStuffPage.isEmailNotificationsPresent(), "Email Notifications is not Displayed");

        whistleBasePage.write2Log4jAllureStep("Verifying Text Notifications element is displayed");
        Assert.assertTrue(humanStuffPage.isTextNotificationsPresent(), "Text Notifications is not Displayed");

        if ((DriverFactory.platfrom.equalsIgnoreCase("android")) || (DriverFactory.platfrom.equalsIgnoreCase("bs-android"))) {
            whistleBasePage.write2Log4jAllureStep("Verifying Family Trips element is displayed");
            Assert.assertTrue(humanStuffPage.isFamilyTripsPresent(), "Family Trips is not Displayed");
        }

        /* Support Section */
        whistleBasePage.write2Log4jAllureStep("Validating Support Section");
        Assert.assertTrue(humanStuffPage.isSupportTitlePresent(), "Settings Title is not Displayed");

        whistleBasePage.write2Log4jAllureStep("Verifying Whistle Help Center element is displayed");
        Assert.assertTrue(humanStuffPage.isWhistleHelpCenterPresent(), "Whistle Help Center is not Displayed");

        whistleBasePage.write2Log4jAllureStep("Verifying Message Whistle element is displayed");
        Assert.assertTrue(humanStuffPage.isMessageWhistlePresent(), "Message Whistle is not Displayed");

        if (DriverFactory.platfrom.equalsIgnoreCase("android")&& DriverFactory.platfrom.equalsIgnoreCase("bs-android")) {
            testDriver.scrollToText((AppiumDriver<MobileElement>) driver, "Shop Whistle");
        }
        else {
            testDriver.swipe(UP);
        }

        whistleBasePage.write2Log4jAllureStep("Verifying Help Make Whistle Better element is displayed");
        Assert.assertTrue(humanStuffPage.isHelpMakeWhistleBetterPresent(), "Help Make Whistle Better is not Displayed");

        whistleBasePage.write2Log4jAllureStep("Verifying Take the Tour element is displayed");
        Assert.assertTrue(humanStuffPage.isTakeTheTourPresent(), "Take the Tour is not Displayed");

        /* Account Section */
        whistleBasePage.write2Log4jAllureStep("Validating Account Section");
        Assert.assertTrue(humanStuffPage.isAccountTitlePresent(), "Account Title is not Displayed");

        whistleBasePage.write2Log4jAllureStep("Verifying Subscription element is displayed");
        Assert.assertTrue(humanStuffPage.isSubscriptionPresent(), "Subscription is not Displayed");

        whistleBasePage.write2Log4jAllureStep("Verifying Account Information element is displayed");
        Assert.assertTrue(humanStuffPage.isAccountInformationPresent(), "Account Information is not Displayed");

        /* Devices Section */
        whistleBasePage.write2Log4jAllureStep("Validating Devices Section");
        Assert.assertTrue(humanStuffPage.isDevicesTitlePresent(), "Devices Title is not Displayed");

        whistleBasePage.write2Log4jAllureStep("Verifying 'Add a Whistle' element is displayed");
        Assert.assertTrue(humanStuffPage.isAddAWhistlePresent(), "Add a Whistle is not Displayed");

        whistleBasePage.write2Log4jAllureStep("Verifying 'Shop Whistle' element is displayed");
        Assert.assertTrue(humanStuffPage.isShopWhistlePresent(), "Shop Whistle is not Displayed");

        whistleBasePage.write2Log4jAllureStep("Verifying 'Made with ❤ for \uD83D\uDC36\uD83D\uDC31' element is displayed");
        Assert.assertTrue(humanStuffPage.isMadeWithForPresent(), "Made With for is not Displayed");
    }
}

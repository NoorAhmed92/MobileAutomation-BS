package com.kinship.automation.testcases.mobile;

import com.kinship.automation.BaseTest;
import com.kinship.automation.commontestmanager.TestManager;
import com.kinship.automation.utils.listeners.Retry;
import com.kinship.automation.utils.driver.DriverFactory;
import com.kinship.automation.utils.listeners.TestListener;
import com.kinship.automation.utils.testrail.TestRails;
import com.kinship.automation.constants.WhistleConstants;
import com.kinship.automation.pageobjects.HomePageWhistle;
import com.kinship.automation.pageobjects.HumanStuffPage;
import com.kinship.automation.pageobjects.LandingPage;
import com.kinship.automation.pageobjects.SignInPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.kinship.automation.utils.driver.TestDriver.ScrollDirection.UP;

@Listeners(TestListener.class)
public class CustomerContactTest extends TestManager {

    @TestRails(id = "1007884")
    @Description("Verify Whistle Customer Contact Link")
    @Test(priority = 0, groups = {"P0"}, retryAnalyzer = Retry.class)
    @Feature("CustomerContactLink")
    public void testCustomerContactLink() {
        SignInPage signInPage = new SignInPage(testDriver);
        HomePageWhistle homePageWhistle = new HomePageWhistle(testDriver);
        HumanStuffPage humanStuffPage = new HumanStuffPage(testDriver);

        // Start
        LandingPage landingPage = new LandingPage(testDriver);

        if (!landingPage.isSignInBtnDisplayed()) {
            log.info("user is already logged in");
        } else {
            log.info("Clicked on Sign in Button");
            signInPage = landingPage.clickSignInLnk();

            log.info("Enter Email Address / User ID " + WhistleConstants.emailMobile);
            signInPage.enterEmail(WhistleConstants.emailMobile);

            log.info("Enter Password " + WhistleConstants.password);
            signInPage.enterPassword(WhistleConstants.password);

            log.info("Click on Login button");
            homePageWhistle = signInPage.clickLoginBtn();

        }

        // End

        log.info("Verifying for Family Trip screen");
        homePageWhistle.handleNotificationBanner();

        log.info("Click on Human Tab");
        if ((DriverFactory.platfrom.equalsIgnoreCase("android")) || (DriverFactory.platfrom.equalsIgnoreCase("ios"))) {
            signInPage.clickHumanTab();
        } else {
            signInPage.clickTab("Human");
        }

        log.info("Scroll up to Account Information");
        if (DriverFactory.platfrom.equalsIgnoreCase("android")) {
            testDriver.scrollToText((AppiumDriver<MobileElement>) driver, "Account Information");
        } else {
            testDriver.swipe(UP);
        }

        accountInformationPage = humanStuffPage.clickAccountInformation();

        accountInformationPage.clickOnCsContact();

        whistleBasePage.VerifyNCloseBrowserWindow();

    }
}

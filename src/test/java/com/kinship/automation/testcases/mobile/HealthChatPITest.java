package com.kinship.automation.testcases.mobile;

import com.kinship.automation.commontestmanager.TestManager;
import com.kinship.automation.pageobjects.HealthPage;
import com.kinship.automation.pageobjects.LandingPage;
import com.kinship.automation.BaseTest;
import com.kinship.automation.utils.listeners.Retry;
import com.kinship.automation.utils.listeners.TestListener;
import com.kinship.automation.utils.testrail.TestRails;
import com.kinship.automation.constants.WhistleConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.kinship.automation.utils.driver.TestDriver.ScrollDirection.UP;

@Listeners(TestListener.class)
public class HealthChatPITest extends TestManager {

    List<String> healthOption = new ArrayList<String>();

    @TestRails(id = "1008246")
    @Description("Verify Chat with Vet Link")
    @Test(priority = 1, groups = {"P0"}, retryAnalyzer = Retry.class)
    @Feature("ChatWithVetLink")
    public void testChatWithVetOpt() {
        LandingPage landingPage = new LandingPage(testDriver);
        HealthPage healthPage = new HealthPage(testDriver);

        healthOption.add("Scratching");
        healthOption.add("Licking");
        healthOption.add("Eating");
        healthOption.add("Drinking");

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

            log.info("Verifying for Family Trip screen");
            homePageWhistle.handleNotificationBanner();
        }

        for(int i=0; i<healthOption.size();i++){
            testDriver.swipe(UP);
            healthPage.verifyLearnAndChat(healthOption.get(i));
        }
    }

    @TestRails(id = "1008247")
    @Description("Verify Contribute to Research Option")
//    @Test(priority = 1, groups = {"P0"}, retryAnalyzer = Retry.class)
    @Feature("Cont2Research")
    public void testContToResearchOpt() {
        LandingPage landingPage = new LandingPage(testDriver);
        HealthPage healthPage = new HealthPage(testDriver);

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

            log.info("Verifying for Family Trip screen");
            homePageWhistle.handleNotificationBanner();

        }

        healthPage.clickStatusTab("Health");
        healthPage.verifyOnHealthPage();

        log.info("Scroll till end of Screen");
        testDriver.swipe(UP);

        log.info("Verifying Contribute to Research is Present");
        healthPage.verifyResearch();

        log.info("Click on Chat to Vet");
        healthPage.clickResearch();

        log.info("Verifying URL and Close the Window");
        whistleBasePage.VerifyNCloseBrowserWindow();
    }
}

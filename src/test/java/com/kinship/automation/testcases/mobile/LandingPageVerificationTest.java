package com.kinship.automation.testcases.mobile;

import com.kinship.automation.BaseTest;
import com.kinship.automation.commontestmanager.TestManager;
import com.kinship.automation.utils.listeners.Retry;
import com.kinship.automation.utils.listeners.TestListener;
import com.kinship.automation.utils.testrail.TestRails;
import com.kinship.automation.pageobjects.LandingPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by Hasmukh patel on 01 Mar, 2022
 */

@Listeners(TestListener.class)
public class LandingPageVerificationTest extends TestManager {
    @TestRails(id="994168")
    @Description("Verify Landing Screen")
    @Test(groups = {"P0", "BAT"}, priority=0, retryAnalyzer = Retry.class)
    @Feature("Verifying Landing Screen")
    public void testLandingScreen() throws InterruptedException {
        LandingPage landingPage = new LandingPage(testDriver);
        log.info("Verify Get Started button is Display");
        landingPage.isGetStartedBtnDisplayed();

        log.info("Verifying Get Started button is Enable");
        landingPage.isGetStartedBtnEnable();

        log.info("Verify Sign in Text Display or Not");
        landingPage.isSignInTxtDisplayed();
    }
}


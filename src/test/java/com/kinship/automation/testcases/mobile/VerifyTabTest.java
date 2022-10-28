package com.kinship.automation.testcases.mobile;

import com.kinship.automation.BaseTest;
import com.kinship.automation.commontestmanager.TestManager;
import com.kinship.automation.utils.listeners.TestListener;
import com.kinship.automation.utils.testrail.TestRails;
import com.kinship.automation.pageobjects.SignInPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Murali Jaladurgam on 17 Feb, 2022
 * Modified by Jyoti Basera on 28 Feb, 2022
 * Modified by Jyoti Basera on 11 Mar, 2022
 * */

@Listeners(TestListener.class)
public class VerifyTabTest extends TestManager {

    @TestRails(id="1008240")
    @Description("Navigation - W04/AM2 Dogs")
    @Test(priority = 0, groups={ "BAT", "P0" })
    @Feature("Navigation")
    public void testDisplayHomeScreenAfterLogin() throws InterruptedException, IOException {
        SignInPage signInPage = new SignInPage(testDriver);
        if( !testDriver.isDisplayedWait(signInPage.signInLink, 3 )){
            log.info("user is already logged in");
        }else {
            homePageWhistle = signInPage.signInToHomePage();
            homePageWhistle.handleNotificationBanner();
        }
        log.info("Verifying if Location Icon is displayed");
        signInPage.assertTab("Location");

        log.info("Verifying if Activity Icon is displayed");
        signInPage.assertTab("Activity");

        log.info("Verifying if Pet Icon is displayed ");
        signInPage.assertTab("Pet");

        log.info("Verifying if Human icon is displayed");
        signInPage.assertTab("Human");
    }
}

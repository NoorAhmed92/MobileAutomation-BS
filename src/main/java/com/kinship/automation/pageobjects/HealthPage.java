package com.kinship.automation.pageobjects;

import com.kinship.automation.utils.commonutils.ByT;
import com.kinship.automation.utils.driver.TestDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;

import static com.kinship.automation.utils.driver.TestDriver.ScrollDirection.UP;

/**
 * Created by hpatel on 29 Mar, 2022
 */
public class HealthPage extends WhistleBasePage {

    TestDriver driver;

    private By wellnessTitleTxt = ByT.locator(By.id(""), By.id("wellness_index_title"));


    private By healthTrendText = ByT.locator(By.id(""), By.id("health_trend_card_title"));

    private By healthTrendTexts = ByT.locator(By.xpath("//XCUIElementTypeStaticText[@name='Gathering Data'] | //XCUIElementTypeStaticText[@name='Wellness Score']"),
                                                By.xpath("//android.widget.ImageView[@content-desc='Null state image']/following-sibling::android.widget.TextView"));


    private By wellnessScoreLabel = ByT.locator(By.id("Gathering Data"), By.id("gathering_data_label"));
    ;
    private By chatWithVet = ByT.locator(By.id("Chat with a Vet"), By.id("health_tab_list_item_chat_with_vet"));
//    private By shareReport = ByT.locator(By.id("Share Health Report"), By.id("health_tab_list_item_share_health_report"));
    private By research = ByT.locator(By.id("Contribute to research"), By.id("health_tab_list_item_join_insights_lab"));

    public HealthPage(TestDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void verifyOnHealthPage() {
        List<MobileElement> searchElement;
        searchElement = driver.findElements(healthTrendTexts);
        log.info("Verifying Wellness Heading Title Text");
        for (int i = 0; i < searchElement.size(); i++) {
            log.info("Found This Text for Wellness = " + searchElement.get(i).getText());
            Assert.assertNotNull(searchElement.get(i).getText());
        }
    }

    public void verifyChatWithVet() {
        Assert.assertTrue(driver.isDisplayedWait(chatWithVet, 3), "Missing Chat with Bet option");
    }

    public void clickChatWithVet() {
        driver.clickLocator(chatWithVet, 3);
    }

    public void verifyResearch() {
        Assert.assertTrue(driver.isDisplayedWait(research, 3), "Missing Research option");
    }

    public void clickResearch() {
        driver.clickLocator(research, 3);
    }

    public void verifyLearnAndChat(String sHealthType) {
        if(!isAndroidBS()) {
            driver.findElement(MobileBy.xpath("//XCUIElementTypeStaticText[contains(@name,'" + sHealthType + "')]")).click();
            driver.pauseExecutionFor(2);
            driver.swipe(UP);
            driver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@name,'Learn about')]")).click();

            log.info("Verifying URL and Close the Window");
            VerifyNCloseBrowserWindow();

            driver.swipe(UP);
            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Chat with a Vet']")).click();

//            String abc = driver.findElement(By.xpath("//h2[text()=\"" + sHealthType + "\"]")).getText();
//            Assert.assertEquals(abc, sHealthType);

            log.info("Verifying URL and Close the Window");
            VerifyNCloseBrowserWindow();

            navigateBackPage();
        }else{
            driver.findElement(MobileBy.xpath("//android.view.View[contains(@text,'" + sHealthType + "')]")).click();
            driver.pauseExecutionFor(2);
            driver.swipe(UP);
            driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Learn about')]")).click();

//            String abc = driver.findElement(By.xpath("//h2[text()='" + sHealthType + "']")).getText();
//            $x("//h2[text()='Scratching']")
//            Assert.assertEquals(abc, sHealthType);

            log.info("Verifying URL and Close the Window");
            VerifyNCloseBrowserWindow();

            driver.swipe(UP);
            driver.findElement(By.xpath("//android.widget.TextView[@text='Chat with a Vet']")).click();

            log.info("Verifying URL and Close the Window");
            VerifyNCloseBrowserWindow();

            navigateBackPage();
        }
    }

//
//        log.info("Scroll till end of Screen");
//        driver.swipe(TestDriver.ScrollDirection.UP);
//
//        assertTrue(driver.isElementPresent(shareReport));
//        log.info(driver.getText(shareReport, "Report Text"));
//
//        log.info("Scroll till end of Screen");
//        driver.swipe(TestDriver.ScrollDirection.UP);
//
//        assertTrue(driver.isElementPresent(research));
//        System.out.println(research);
//
//        log.info("Click on Contribute to research");
//        driver.clickLocator(research, "Contribute to research");
//
//        log.info("Verifying URL and Close the Window");
//        VerifyNCloseBrowserWindow();
//    }
}
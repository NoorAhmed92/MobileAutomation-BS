package com.kinship.automation.testcases.api;

import com.kinship.automation.BaseTest;
import com.kinship.automation.utils.listeners.Retry;
import com.kinship.automation.utils.commonutils.Utils;
import com.kinship.automation.utils.listeners.TestListener;
import com.kinship.automation.utils.testrail.TestRails;
import com.kinship.automation.pageobjects.WhistleBasePage;
import com.kinship.automation.testdata.APIResources;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

@Listeners(TestListener.class)
public class NotificationTokenTest extends BaseTest {
	Response response;
 
	@TestRails(id="993863")
	@Description("[TestRail] Test Notification: token-Create notification token")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("NotificationToken")
    public void testCreateNotificationTokenAPI() throws IOException {
			log.info("********* Test Case Execution started to validate Create Notification Token API ***********");	
			APIResources resourceAPI = APIResources.valueOf("getNotificationToken");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.addNotificationTokenPayload()).header("Authorization", "Bearer " +token).
					when().log().all().post(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 201, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
    }
	
	@TestRails(id="993864")
	@Description("[TestRail] Test Notification: token-Delete")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("NotificationToken")
    public void testDeleteNotificationTokenAPI() throws IOException {
			log.info("********* Test Case Execution started to validate Delete Notification Token API ***********");	
			APIResources resourceAPI = APIResources.valueOf("getNotificationToken");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.addNotificationTokenPayload()).header("Authorization", "Bearer " +token).
					when().log().all().delete(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 204, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
    }
}

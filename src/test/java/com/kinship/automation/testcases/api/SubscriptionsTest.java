package com.kinship.automation.testcases.api;

import com.kinship.automation.BaseTest;
import com.kinship.automation.utils.listeners.Retry;
import com.kinship.automation.utils.commonutils.Utils;
import com.kinship.automation.utils.listeners.TestListener;
import com.kinship.automation.utils.testrail.TestRails;
import com.kinship.automation.constants.WhistleConstants;
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
public class SubscriptionsTest extends BaseTest {
	Response response;
	
	@TestRails(id="1010062")
	@Description("[TestRail] Test Subscription Get Cancellation Reasons-200")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Subscriptions")
	public void testGetSubscriptionCancellationReasons() throws IOException {
		
			log.info("********* Test Case Execution started to validate Subscription Cancellation reasons for 200 ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			APIResources resourceAPI = APIResources.valueOf("getSubscriptions");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("subscriptionId", WhistleConstants.SUBSCRIPTION_ID).header("Authorization", "Bearer " +token).
					when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			String actualcancellationReasonsID = WhistleBasePage.getJsonPath(response, "cancellation_reasons.id");
			String actualcancellationReasonsDesc = WhistleBasePage.getJsonPath(response, "cancellation_reasons.description");
			log.info("Actual cancellation Reason IDs: " +actualcancellationReasonsID+ "Actual Cancellation Reason Descriptions: " +actualcancellationReasonsDesc);
			Utils.assertOutput(actualcancellationReasonsID, WhistleConstants.SUBSCRIPTION_CANCELLATION_REASON_IDs, resourceAPI);
			Utils.assertOutput(actualcancellationReasonsDesc, WhistleConstants.SUBSCRIPTION_CANCELLATION_MESSAGES, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010067")
	@Description("[TestRail] Test Subscription Cancellation Reasons Unauthenticated-401")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Subscriptions")
	public void testSubscriptionCancellationReasonsUnauthenticated() throws IOException {
		
			log.info("********* Test Case Execution started to validate Subscription Cancellation reasons for unauthenticated user ***********");
			APIResources resourceAPI = APIResources.valueOf("getSubscriptions");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("subscriptionId", WhistleConstants.SUBSCRIPTION_ID).
					when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010068")
	@Description("[TestRail] Test Subscription Not Found in Cancellation Reasons-404")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Subscriptions")
	public void testSubscriptionNotFound() throws IOException {
		
			log.info("********* Test Case Execution started to validate Subscription Not Found in Cancellation reasons ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			APIResources resourceAPI = APIResources.valueOf("getSubscriptions");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("subscriptionId", WhistleConstants.NONEXISTING_SUBSCRIPTION_ID).
					header("Authorization", "Bearer " +token).when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 404, resourceAPI);		
			String actualErrorMessage = WhistleBasePage.getJsonPath(response, "errors[0].message");
			Utils.assertOutput(actualErrorMessage, WhistleConstants.SUBSCRIPTION_NOTFOUND_MESSAGE, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010064")
	@Description("[TestRail] Test No Active Subscription Preview Cancellation-422")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Subscriptions")
	public void testNoActiveSubscriptionCancellationPreview() throws IOException {
		
		log.info("********* Test Case Execution started for Subscription Preview Cancellation for 422 ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		APIResources resourceAPI = APIResources.valueOf("getSubscriptionCancellationPreview");
		response = given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.addSubscriptionPreviewCancellation()).header("Authorization", "Bearer " +token).
				pathParams("subscriptionId", WhistleConstants.PREVIEW_SUBSCRIPTION_ID).when().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 422, resourceAPI);
		String actualErrorMessage = WhistleBasePage.getJsonPath(response, "errors[0].message");
		Utils.assertOutput(actualErrorMessage, WhistleConstants.NOACTIVE_SUBSCRIPTION_MESSAGE, resourceAPI);
		log.info("******** Test Case Execution Finished *********");
	}
	
	@TestRails(id="1010065")
	@Description("[TestRail] Test Subscription Preview Cancellation Unauthenticated User-401 ")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Subscriptions")
	public void testSubscriptionCancellationPreviewForUnauthenticatedUser() throws IOException {
		
		log.info("********* Test Case Execution started for Subscription Preview Cancellation for 401 unauthenticated ***********");
		APIResources resourceAPI = APIResources.valueOf("getSubscriptionCancellationPreview");
		response = given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("subscriptionId", WhistleConstants.SUBSCRIPTION_ID).queryParam("reason_id", 12).
				queryParam("immeditate", false).when().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
		log.info("******** Test Case Execution Finished *********");
	}
}
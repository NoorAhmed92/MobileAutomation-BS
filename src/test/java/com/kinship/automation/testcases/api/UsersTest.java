package com.kinship.automation.testcases.api;

import com.kinship.automation.BaseTest;
import com.kinship.automation.utils.listeners.Retry;
import com.kinship.automation.utils.commonutils.Utils;
import com.kinship.automation.utils.listeners.TestListener;
import com.kinship.automation.utils.testrail.TestRails;
import com.kinship.automation.pageobjects.WhistleBasePage;
import com.kinship.automation.testdata.APIResources;
import com.kinship.automation.testdata.TestDataPayload;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;

@Listeners(TestListener.class)
public class UsersTest extends BaseTest {
	Response response;
	protected TestDataPayload data = new TestDataPayload();

	@TestRails(id="993870")
	@Description("[TestRail] User: Post create secondary emails")
	@Test(groups = "P0", priority = 0, retryAnalyzer = Retry.class)
	@Feature("Users")
	public void testCreatePostSecondaryEmail() throws IOException {
		log.info("********* Test Case Execution started to validate Create a phone number ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		APIResources resourceAPI = APIResources.valueOf("getSecondaryEmailcollection");
		LocalDateTime currentDT = LocalDateTime.now(); 
		log.info("Current Date and Time: " +currentDT);
		String email = currentDT.toString().replace("-", "").replace(":", "").replace(".", "").replace("T", "_");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(data.addSecondaryEmailCollection(email)).
				header("Authorization", "Bearer " +token).when().post(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 201, resourceAPI);
		String secondaryEmail = WhistleBasePage.getJsonPath(response, "secondary_email.email");
		log.info("Secondry email: " +secondaryEmail );
		Utils.setValuesInPropertiesFile("environment", "secondaryEmail", secondaryEmail);
		String secondaryEmailId = WhistleBasePage.getJsonPath(response, "secondary_email.id");
		Utils.setValuesInPropertiesFile("environment", "secondaryEmailId", secondaryEmailId);
		log.info("Secondry email id: " + secondaryEmailId);
		log.info("secondaryEmailId is Integer : " + Utils.verifyInteger(secondaryEmailId, secondaryEmailId.length()));
		log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="993872")
	@Description("[TestRail] Test email and id are created_expect200")
	@Test(groups = "P0", dependsOnMethods = "testCreatePostSecondaryEmail", priority = 1, retryAnalyzer = Retry.class)
	@Feature("Users")
	public void testCurrentUser() throws IOException {

		log.info("********* Test Case Execution started to validate Get Current User***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String emailAddress = Utils.getValuesFromPropertiesFile("environment", "emailAddress");
		APIResources resourceAPI = APIResources.valueOf("getCurrentUser");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).header("Authorization", "Bearer " +token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		String secondaryEmailId = WhistleBasePage.getJsonPath(response, "user.notification_settings.secondary_emails[0].id");
		log.info("Secondry email: " +secondaryEmailId );
		String secondSecondaryEmailId = WhistleBasePage.getJsonPath(response, "user.notification_settings.secondary_emails[1].id");
		log.info("Second Secondry email: " +secondSecondaryEmailId );
		Utils.setValuesInPropertiesFile("environment", "secondaryEmailId", secondaryEmailId);
		Utils.setValuesInPropertiesFile("environment", "secondSecondaryEmailId", secondSecondaryEmailId);
		String currentUserEmail = WhistleBasePage.getJsonPath(response, "user.email");
		log.info("CurrentUser email : " + currentUserEmail);
		Utils.assertOutput(currentUserEmail, emailAddress, resourceAPI);
		log.info("******** Test Case Execution Finished *********");

	}

	@TestRails(id="993871")
	@Description("Test User: application_state")
	@Test(groups = "P0", priority = 2,retryAnalyzer = Retry.class)
	@Feature("Users")
	public void testGetAppState() throws IOException {

		log.info("********* Test Case Execution started to validate Get Application state***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		APIResources resourceAPI = APIResources.valueOf("getApplicationState");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).header("Authorization", "Bearer " +token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		log.info("******** Test Case Execution Finished *********");

	}

	@TestRails(id="993876")
	@Description("[TestRail] Test User: referral code")
	@Test(groups = "P0", priority = 3,retryAnalyzer = Retry.class)
	@Feature("Users")
	public void testReferralCode() throws IOException {

		log.info("********* Test Case Execution started to validate Referral code***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		APIResources resourceAPI = APIResources.valueOf("getReferralCode");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).header("Authorization", "Bearer " +token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		log.info("******** Test Case Execution Finished *********");

	}

	@TestRails(id="993875")
	@Description("[TestRail] Test User: credit card_GET")
	@Test(groups = "P0",priority = 4,retryAnalyzer = Retry.class)
	@Feature("Users")
	public void testGetExistingCard() throws IOException {
		log.info("********* Test Case Execution started to validate Existing card***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		APIResources resourceAPI = APIResources.valueOf("getExistingcard");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).header("Authorization", "Bearer " +token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="1010248")
	@Description("[TestRail] User: Put update user secondary emails")
	@Test(groups = "P0", priority = 5,retryAnalyzer = Retry.class)
	@Feature("Users")
	public void testPutSecondaryEmailUpdate() throws IOException {
		log.info("********* Test Case Execution started to validate Create secondary email ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String secondaryEmailId = Utils.getValuesFromPropertiesFile("environment", "secondaryEmailId");			
		APIResources resourceAPI = APIResources.valueOf("getCreateSecondaryEmail");
		LocalDateTime currentDT = LocalDateTime.now(); 
		System.out.println(currentDT);
		String email = currentDT.toString().replace("-", "").replace(":", "").replace(".", "").replace("T", "_");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParam("secondaryEmailId", secondaryEmailId).body(data.addSecondaryEmailCollection(email)).
				header("Authorization", "Bearer " +token).when().put(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		String updatSecondaryEmailId = WhistleBasePage.getJsonPath(response, "secondary_email.id");
		log.info("Secondry email id: " + updatSecondaryEmailId);
		log.info("updated secondaryEmailId is Integer : " + Utils.verifyInteger(updatSecondaryEmailId, updatSecondaryEmailId.length()));
		log.info("******** Test Case Execution Finished *********");
	}

	@TestRails(id="993874")
	@Description("[TestRail] User: Delete newly created secondary emails")
	@Test(groups = "P0", priority = 6,retryAnalyzer = Retry.class)
	@Feature("Users")
	public void testSecondaryEmailDelete() throws IOException {	
		log.info("********* Test Case Execution started to validate delete ssecondary email ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String secondaryEmailId = Utils.getValuesFromPropertiesFile("environment", "secondaryEmailId");	
		APIResources resourceAPI = APIResources.valueOf("getDeleteSecondaryEmail");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParam("secondaryEmailId",secondaryEmailId).
				header("Authorization", "Bearer " +token).when().delete(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 204, resourceAPI);
		log.info("******** Test Case Execution Finished *********");		
	}
}


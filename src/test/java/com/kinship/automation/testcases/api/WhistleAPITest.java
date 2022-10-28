package com.kinship.automation.testcases.api;

import com.kinship.automation.BaseTest;
import com.kinship.automation.utils.commonutils.Utils;
import com.kinship.automation.utils.listeners.TestListener;
import com.kinship.automation.utils.testrail.TestRails;
import com.kinship.automation.constants.WhistleConstants;
import com.kinship.automation.pageobjects.WhistleBasePage;
import com.kinship.automation.testdata.APIResources;
import com.kinship.automation.testdata.TestDataPayload;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;


/**
 * Created by Jyoti Basera on 23 Feb, 2022 to add testGetAuthAndLogin() and testGetPetList()
 * Modified on 25 Feb, 2022 to add testCreatePostSecondaryEmail(), testGetUserEmail() and testSecondaryEmailDelete()
 * */

@Listeners(TestListener.class)
public class WhistleAPITest extends BaseTest {
	Response response;
	protected TestDataPayload data = new TestDataPayload();
	
	@TestRails(id="993830")
	@Description("[TestRail] Test login get auth and verify account detail")
	@Feature("Login")
	@Test(groups = { "BAT"}, priority = 0)
	public void testGetAuthAndLogin() throws IOException {
		
				log.info("********* Test Case Execution started to test login get Auth and verify account details ***********");
				APIResources resourceAPI = APIResources.valueOf("getBearerToken");
				response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.addWhistleLoginPayLoad()).when().log().all().post(resourceAPI.getResource());
				Utils.assertOutput(response.getStatusCode(), 201, resourceAPI);
				log.info("Getting Authorization Token");
				String token = WhistleBasePage.getJsonPath(response, "auth_token");
				String userId = WhistleBasePage.getJsonPath(response, "user.id");
				String actualWhistleEmail = WhistleBasePage.getJsonPath(response, "user.email");
				log.info("Actual Whistle email address : " + actualWhistleEmail);
				Utils.assertOutput(actualWhistleEmail, WhistleConstants.email, resourceAPI);
				log.info("Verify Authentication Token is not null");
				Assert.assertNotNull(token);
				log.info("Verify user ID is not null and is a digit");
				log.info("User Id is "+userId);
				Assert.assertNotNull(userId, "User ID is null");
				Assert.assertTrue(Utils.verifyStringHasOnlyDigits(userId), "User ID is not a digit");
				log.info("******** Test Case Execution Finished *********");
	}
	
	@TestRails(id="1010147")
	@Description("[TestRail] Test Get Pet ID Info")
	@Test(groups = { "BAT" }, priority = 1)
	@Feature("Pets")
	public void testGetPetList() throws IOException {

		log.info("********* Test Case Execution started to get pets ID info ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		APIResources resourceAPI = APIResources.valueOf("getListOfPets");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).header("Authorization", "Bearer " +token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		String actualDeviceSerialNum = WhistleBasePage.getJsonPath(response, "pets[0].device.serial_number");
		String expectedDeviceSerialNum = Utils.getValuesFromPropertiesFile("environment", "deviceSerialNumber");
		String actualSubscriptionStatus = WhistleBasePage.getJsonPath(response, "pets[0].subscription_status");
		log.info("Verifying Subscription status is active");
		Utils.assertOutput(actualSubscriptionStatus, WhistleConstants.SUBSCRIPTION_STATUS, resourceAPI);
		log.info("Verifying Device Serial number is same as actual Device ID");
		Utils.assertOutput(actualDeviceSerialNum, expectedDeviceSerialNum, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="993870")
	@Description("[TestRail] User: Post create secondary emails")
	@Test(groups = { "BAT" }, priority = 2)
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
	@Test(groups = { "BAT" }, dependsOnMethods = "testCreatePostSecondaryEmail", priority = 3)
	@Feature("Users")
	public void testGetUserEmailDetails() throws IOException {
		
			log.info("********* Test Case Execution started to validate Get Current User***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			APIResources resourceAPI = APIResources.valueOf("getCurrentUser");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).header("Authorization", "Bearer " +token).
					when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			//Get actual values from JSON response attributes
			String actualSecondaryEmailId = WhistleBasePage.getJsonPath(response, "user.notification_settings.secondary_emails[0].id");
			String actualSecondaryEmailAddress = WhistleBasePage.getJsonPath(response, "user.notification_settings.secondary_emails[0].email");
			//Assertions
			log.info("Verifying Secondary Email ID consists of only digits");
			Assert.assertTrue(Utils.verifyStringHasOnlyDigits(actualSecondaryEmailId), "Secondary Email ID does not contain only digits");	
			log.info("Verifying Secondry email address is not null" );
			Assert.assertNotNull(actualSecondaryEmailAddress, "Secondary Email address is null");
			log.info("******** Test Case Execution Finished *********");
		
	}
	
	@TestRails(id="993874")
	@Description("[TestRail] User: Delete newly created secondary emails")
	@Test(groups = { "BAT" }, priority = 4)
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

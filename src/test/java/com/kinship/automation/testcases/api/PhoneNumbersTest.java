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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

@Listeners(TestListener.class)
public class PhoneNumbersTest extends BaseTest {
	Response response;
	protected TestDataPayload data = new TestDataPayload();
	
	@TestRails(id="993878")
	@Description("[TestRail] Test Phone Numbers: Create a phone number")
	@BeforeClass
	@Test(groups = "P0", priority = 0, retryAnalyzer = Retry.class)
	@Feature("PhoneNumbers")
    public void testCreatePhoneNumberAPI() throws IOException {
		
			log.info("********* Test Case Execution started to validate Create Phone Number API ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			int newNum1= Utils.getRandomInt(500, 999);
			int newNum2= Utils.getRandomInt(1000, 9999);
			String newPhoneNumber= "628-"+newNum1+"-"+newNum2;
			APIResources resourceAPI = APIResources.valueOf("getCreatePhoneNumber");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(data.addCreatePhoneNumberPayload(newPhoneNumber)).
					header("Authorization", "Bearer " +token).when().log().all().post(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 201, resourceAPI);
			String actualPhoneNumberId = WhistleBasePage.getJsonPath(response, "phone_number.id");
			String actualPhoneNumber = WhistleBasePage.getJsonPath(response, "phone_number.number");
			log.info("New Phone Number is : " +newPhoneNumber);
			log.info("Phone Number Id is : " +actualPhoneNumberId);
			Utils.assertOutputNotEquals(actualPhoneNumberId, null);
			Utils.assertOutputNotEquals(actualPhoneNumber, null);
			String expectedPhoneNumber = String.valueOf(Utils.getValuesFromPropertiesFile("environment", "newPhoneNumber"));
			Utils.assertOutputNotEquals(expectedPhoneNumber, null);
			Utils.setValuesInPropertiesFile("environment", "newPhoneNumber", newPhoneNumber);
			Utils.setValuesInPropertiesFile("environment", "newPhoneNumberId", actualPhoneNumberId);
			log.info("******** Test Case Execution Finished *********");
    }
	
	@TestRails(id="993879")
	@Description("[TestRail] Test Phone Numbers: Verify a phone number")
	@Test(groups = "P0", priority = 1, retryAnalyzer = Retry.class)
	@Feature("PhoneNumbers")
    public void testVerifyPhoneNumberAPI() throws IOException {
		
			log.info("********* Test Case Execution started to validate Verify Phone Number API ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			int phoneVerificationCode= Utils.getRandomInt(10000, 99999);
			String newPhoneNumberId = Utils.getValuesFromPropertiesFile("environment", "newPhoneNumberId");
			APIResources resourceAPI = APIResources.valueOf("getUpdatePhoneNumber");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(data.verifyPhoneNumberPayload(phoneVerificationCode)).
					pathParam("newPhoneNumberId", newPhoneNumberId).header("Authorization", "Bearer " +token).
					when().log().all().post(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 422, resourceAPI);	
			log.info("******** Test Case Execution Finished *********");
    }
	
	@TestRails(id="993880")
	@Description("[TestRail] Test Phone Numbers: Update a phone number")
	@Test(groups = "P0", priority = 2, retryAnalyzer = Retry.class)
	@Feature("PhoneNumbers")
    public void testUpdatePhoneNumberAPI() throws IOException {
		
			log.info("********* Test Case Execution started to validate Create Phone Number API ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String newPhoneNumber = Utils.getValuesFromPropertiesFile("environment", "newPhoneNumber");
			String newPhoneNumberId = Utils.getValuesFromPropertiesFile("environment", "newPhoneNumberId");
			APIResources resourceAPI = APIResources.valueOf("getUpdatePhoneNumber");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(data.addCreatePhoneNumberPayload(newPhoneNumber)).pathParam("newPhoneNumberId", newPhoneNumberId).
					header("Authorization", "Bearer " +token).when().log().all().put(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 204, resourceAPI);
			log.info("******** Test Case Execution Finished *********");
    }
	
	@TestRails(id="993881")
	@Description("[TestRail] Test Phone Numbers: Reset verification code")
	@Test(groups = "P0", priority = 3, retryAnalyzer = Retry.class)
	@Feature("PhoneNumbers")
    public void testResetVerificationCodeInPhoneNumberAPI() throws IOException {
		
			log.info("********* Test Case Execution started to validate Reset Verification Code in Phone Number API ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String newPhoneNumberId = Utils.getValuesFromPropertiesFile("environment", "newPhoneNumberId");
			APIResources resourceAPI = APIResources.valueOf("getUpdatePhoneNumber");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParam("newPhoneNumberId", newPhoneNumberId).
					header("Authorization", "Bearer " +token).when().log().all().put(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 204, resourceAPI);
			log.info("******** Test Case Execution Finished *********");
    }
	
	@TestRails(id="1010136")
	@Description("[TestRail] Test Phone Numbers: Reset verification code : 404")
	@Test(groups = "P1", priority = 3, retryAnalyzer = Retry.class)
	@Feature("PhoneNumbers")
    public void testResetVerificationCodeInPhoneNumberAPINotFound() throws IOException {
		
			log.info("********* Test Case Execution started to validate Reset Verification Code in Phone Number API for 404***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String newPhoneNumberId = Utils.getValuesFromPropertiesFile("environment", "newPhoneNumberId");
			APIResources resourceAPI = APIResources.valueOf("getUpdatePhoneNumber");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParam("newPhoneNumberId", token).
					header("Authorization", "Bearer " +newPhoneNumberId).when().log().all().put(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 404, resourceAPI);
			log.info("******** Test Case Execution Finished *********");		
    }
	
	@TestRails(id="993882")
	@Description("[TestRail] Test Phone Numbers: Delete a phone number")
	@Test(groups = "P0", priority = 4,retryAnalyzer = Retry.class)
	@Feature("PhoneNumbers")
    public void testDeletePhoneNumberAPI() throws IOException {
		
			log.info("********* Test Case Execution started to validate Delete Phone Number API ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String newPhoneNumberId = Utils.getValuesFromPropertiesFile("environment", "newPhoneNumberId");
			APIResources resourceAPI = APIResources.valueOf("getDeletePhoneNumber");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParam("newPhoneNumberId", newPhoneNumberId).
					header("Authorization", "Bearer " +token).when().log().all().delete(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			log.info("******** Test Case Execution Finished *********");			
    }
	
	@TestRails(id="993883")
	@Description("[TestRail] Test Phone Numbers: Delete same phone number expect 404")
	@Test(groups = "P0", priority = 5, retryAnalyzer = Retry.class)
	@Feature("PhoneNumbers")
    public void testDeleteSamePhoneNumberAPI() throws IOException {
		
			log.info("********* Test Case Execution started to validate Delete Same Phone Number expect 404, resourceAPI ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String newPhoneNumberId = Utils.getValuesFromPropertiesFile("environment", "newPhoneNumberId");
			APIResources resourceAPI = APIResources.valueOf("getDeletePhoneNumber");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParam("newPhoneNumberId", newPhoneNumberId).
					header("Authorization", "Bearer " +token).when().log().all().delete(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 404, resourceAPI);
			String actualMessage = WhistleBasePage.getJsonPath(response, "errors[0].message");
			Utils.assertOutput(actualMessage, "Phone number not found", resourceAPI);
			log.info("******** Test Case Execution Finished *********");		
    }
}

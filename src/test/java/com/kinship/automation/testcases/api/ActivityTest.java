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
public class ActivityTest extends BaseTest {
	Response response;

	@TestRails(id="993833")
	@Description("[TestRail] Test List of pets-410")
	@Test(groups = {"P0"}, retryAnalyzer = Retry.class)
	@Feature("Activity")
	public void testGetActivityListOfPets() throws IOException {

		log.info("********* Test Case Execution started to validate Activity list of pets API ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		APIResources resourceAPI = APIResources.valueOf("getListOfPetsActivity");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).header("Authorization", "Bearer " +token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 410, resourceAPI);
		log.info("******** Test Case Execution Finished *********");

	}

	@TestRails(id="993834")
	@Description("[TestRail] Test Activity goals-Set Activity Goal.")
	@Test(groups = {"P0", "BAT"}, retryAnalyzer = Retry.class)
	@Feature("Activity")
	public void testSetActivityGoal() throws IOException {

		log.info("********* Test Case Execution started for Set Activity Goal ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		int minutes = Utils.getRandomInt(15,60);
		String newPetActiveMinutes =  Integer.toString(minutes);
		log.info("Pet activity goals in mm set to = " +newPetActiveMinutes);
		APIResources resourceAPI = APIResources.valueOf("getSetActivityGoal");
		response = given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +token).
				when().body(WhistleBasePage.data.setActivityGoalPayLoad(minutes)).post(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 201, resourceAPI);
		String actualMinutes = WhistleBasePage.getJsonPath(response, "upcoming_activity_goal.minutes");
		log.info("Actual Mintues from response body : " + actualMinutes);
		Utils.assertOutput(actualMinutes, newPetActiveMinutes, resourceAPI);
		log.info("******** Test Case Execution Finished *********");

	}

	@TestRails(id="1010132")
	@Description("[TestRail] Test Activity goals-Set Activity Goal : 401")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Activity")
	public void testSetActivityGoalStatusCode() throws IOException {

		log.info("********* Test Case Execution started for Set Activity Goal for 401 ***********");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		int minutes = Utils.getRandomInt(15,60);
		String newPetActiveMinutes =  Integer.toString(minutes);
		log.info("Pet activity goals in mm set to = " +newPetActiveMinutes);
		APIResources resourceAPI = APIResources.valueOf("getSetActivityGoal");
		response = given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +petId).
				when().body(WhistleBasePage.data.setActivityGoalPayLoad(minutes)).post(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
		log.info("******** Test Case Execution Finished *********");		
	
	}

	@TestRails(id="1010133")
	@Description("[TestRail] Test Activity goals-Set Activity Goal : 404")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Activity")
	public void testSetActivityGoalNotFound() throws IOException {

		log.info("********* Test Case Execution started for Set Activity Goal for 404 ***********");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		int minutes = Utils.getRandomInt(15,60);
		String newPetActiveMinutes =  Integer.toString(minutes);
		log.info("Pet activity goals in mm set to = " +newPetActiveMinutes);
		APIResources resourceAPI = APIResources.valueOf("getSetActivityGoal");
		response = given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",token).header("Authorization", "Bearer " +petId).
				when().body(WhistleBasePage.data.setActivityGoalPayLoad(minutes)).post(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 404, resourceAPI);
		log.info("******** Test Case Execution Finished *********");
	}

	@TestRails(id="993835")
	@Description("[TestRail] Test Overall activity Stats-Get Pet's Activity Stats.")
	@Test(groups = "P0" , retryAnalyzer = Retry.class)
	@Feature("Activity")
	public void testOverallActivityStash() throws IOException {

		log.info("********* Test Case Execution started for response for Overall Activity Statistics ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getOverallActivityStash");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).
				header("Authorization", "Bearer " +token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		log.info("******** Test Case Execution Finished *********");
		
	}

	@TestRails(id="1010130")
	@Description("[TestRail] Test Overall activity Stats-Get Pet's Activity Stats : 401")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Activity")
	public void testOverallActivityStatusCode() throws IOException {

		log.info("********* Test Case Execution started for response for Overall Activity Statistics for 401 ***********");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getOverallActivityStash");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).
				header("Authorization", "Bearer " +petId).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
		log.info("******** Test Case Execution Finished *********");

	}

	@TestRails(id="1010131")
	@Description("[TestRail] Test Overall activity Stats-Get Pet's Activity Stats : 404")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Activity")
	public void testOverallActivityNotFound() throws IOException {

		log.info("********* Test Case Execution started for response for Overall Activity Statistics for 404 ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getOverallActivityStash");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",token).
				header("Authorization", "Bearer " +petId).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 404, resourceAPI);
		log.info("******** Test Case Execution Finished *********");

	}

	@TestRails(id="993836")
	@Description("[TestRail] Test List of daily summaries.")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Activity")
	public void testGetListOfSummary() throws IOException {

		log.info("********* Test Case Execution started to validate list of summary ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getGetListOfSummary");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		log.info("******** Test Case Execution Finished *********");

	}

	@TestRails(id="1010128")
	@Description("[TestRail] Test List of daily summaries : 401")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Activity")
	public void testGetListOfSummaryStatusCode() throws IOException {

		log.info("********* Test Case Execution started to validate list of summary for 401***********");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getGetListOfSummary");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +petId).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
		log.info("******** Test Case Execution Finished *********");

	}

	@TestRails(id="1010129")
	@Description("[TestRail] Test List of daily summaries : 404")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Activity")
	public void testGetListOfSummaryNotFound() throws IOException {

		log.info("********* Test Case Execution started to validate list of summary 404***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getGetListOfSummary");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",token).header("Authorization", "Bearer " +petId).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 404, resourceAPI);
		log.info("******** Test Case Execution Finished *********");

	}

	@TestRails(id="993837")
	@Description("[TestRail] Test Daily activity.")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Activity")
	public void testGetDailyActivity() throws IOException {

		log.info("********* Test Case Execution started for response for daily activity ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getGetDailyActivity");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		log.info("******** Test Case Execution Finished *********");

	}

	@TestRails(id="1010126")
	@Description("[TestRail] Test Daily activity : 401")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Activity")
	public void testGetDailyActivityStatusCode() throws IOException {

		log.info("********* Test Case Execution started for response for daily activity for 401***********");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getGetDailyActivity");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +petId).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
		log.info("******** Test Case Execution Finished *********");

	}

	@TestRails(id="1010127")
	@Description("[TestRail] Test Daily activity : 404")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Activity")
	public void testGetDailyActivityNotFound() throws IOException {

		log.info("********* Test Case Execution started for response for daily activity for 404***********");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		APIResources resourceAPI = APIResources.valueOf("getGetDailyActivity");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",token).header("Authorization", "Bearer " +petId).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 404, resourceAPI);
		log.info("******** Test Case Execution Finished *********");
		
	}

}

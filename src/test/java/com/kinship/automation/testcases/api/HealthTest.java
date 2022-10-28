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
public class HealthTest extends BaseTest {
	Response response;
	
	@TestRails(id="993884")
	@Description("[TestRail] Test Health: Trends: Daily health trends-Retrieve pet daily health trends")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Health")
	public void testDailyhealthTrend() throws IOException {
		
			log.info("********* Test Case Execution started for Daily Health Trends ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
			APIResources resourceAPI = APIResources.valueOf("getDailyhealthTrend");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +token).
					when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="993885")
	@Description("[TestRail] Test Health: Trends: Sleeping-Retrieve pet sleeping graph data")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Health")
	public void testSleepingGraphData() throws IOException {
		
			log.info("********* Test Case Execution started for response sleeping Graph Data ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
			APIResources resourceAPI = APIResources.valueOf("getSleepingGraphData");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +token).
					when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="993886")
	@Description("[TestRail] Test Health: Trends: Scratching")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Health")
	public void testScrattchingGraphData() throws IOException {
		
			log.info("********* Test Case Execution started for response scratching Graph Data ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
			APIResources resourceAPI = APIResources.valueOf("getScratchingGraphData");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +token).
					when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			log.info("******** Test Case Execution Finished *********");
	}

	@TestRails(id="993887")
	@Description("[TestRail] Test Health: Trends: Licking")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Health")
	public void testLickingGraphData() throws IOException {
		
			log.info("********* Test Case Execution started for response pet licking graph data ***********");	
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
			APIResources resourceAPI = APIResources.valueOf("getLickingGraphData");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +token).
					when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
	}

	@Description("Test the response for Drinking Graph Data")
	@Feature("Health")
	public void testDrinkingGraphData() throws IOException {
		
			log.info("********* Test Case Execution started for response pet drinking graph data ***********");	
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
			APIResources resourceAPI = APIResources.valueOf("getDrinkingGraphData");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +token).
					when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
	}
	
	@Description("Test the response for Eating Daily Trend Graph Data")
	@Feature("Health")
	public void testEatingDailyTrendGraphData() throws IOException {
		
			log.info("********* Test Case Execution started for eating daily trends graph data ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
			APIResources resourceAPI = APIResources.valueOf("getEatingDailyTrendGraphData");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +token).when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="993888")
	@Description("[TestRail] Test Health: Trends: Eating_events")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Health")
	public void testEatingEventGraphData() throws IOException {
		
			log.info("********* Test Case Execution started for eating event graph data ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
			APIResources resourceAPI = APIResources.valueOf("getEatingEventGraphData");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +token).
					when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="1010121")
	@Description("[TestRail] Test Health: Toggle visibility of health report url")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Health")
	public void testToggleVisibilityOfHealthReportUrl() throws IOException {
		
			log.info("********* Test Case Execution started for eating event graph data ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String userId = Utils.getValuesFromPropertiesFile("environment", "userId");   
			APIResources resourceAPI = APIResources.valueOf("getToggleVisibilityOfHealthReportUrl");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("userId",userId).header("Authorization", "Bearer " +token).
					when().log().all().post(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 404, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
	}
}

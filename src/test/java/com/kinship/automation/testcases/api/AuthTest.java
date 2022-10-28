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
public class AuthTest extends BaseTest {
	static String token, realtimeUserChannel, auth;
	Response response;
	
	@TestRails(id="993841")
	@Description("[TestRail] Test Auth.")
	@Feature("Auth")
	@Test(groups = {"BAT","P0"}, retryAnalyzer = Retry.class)
    public void testAuthAPI() throws IOException {
		
			log.info("******** Test Case Execution started to validate Auth API  *********");
			token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			realtimeUserChannel= Utils.getValuesFromPropertiesFile("environment", "realtimeUserChannel");
			APIResources resourceAPI = APIResources.valueOf("getRealTimeAuth");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.addAuthPayload(realtimeUserChannel)).header("Authorization", "Bearer " +token).
					when().post(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 201, resourceAPI);
			auth = WhistleBasePage.getJsonPath(response, "auth");
			log.info("Auth value is: "+auth);
			Utils.assertOutputNotEquals(auth, null);
			log.info("******** Test Case Execution Finished *********");
    }
	
	@TestRails(id="993830")
	@Description("[TestRail] Test login.")
	@Feature("Login")
	@Test(groups = { "BAT", "P0" }, retryAnalyzer = Retry.class)
	public void testLoginWhistleAPI() throws IOException {
		
				log.info("********* Test Case Execution started to test login in whistle API ***********");
				APIResources resourceAPI = APIResources.valueOf("getBearerToken");
				response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.addWhistleLoginPayLoad()).when().log().all().post(resourceAPI.getResource());
				Utils.assertOutput(response.getStatusCode(), 201, resourceAPI);
				log.info("******** Test Case Execution Finished *********");
	}
	
	@Description("Test login API for invalid login credentials and verify status code")
	@Feature("Login")
	public void testInvalidLoginWhistleAPI() throws IOException {
		
			log.info("********* Test Case Execution started to test login for invalid credentials in whistle API ***********");
			APIResources resourceAPI = APIResources.valueOf("getBearerToken");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.addWhistleLoginInvalidPayLoad())
					.when().log().all().post(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 422, resourceAPI);
	}
}

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
public class PlacesTest extends BaseTest {
	Response response;
	
	@TestRails(id="993877")
	@Description("[TestRail] Test Places: Places collection_View Places for User")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("API")
	public void testPlacesCollection() throws IOException {
			log.info("********* Test Case Execution started for response GeoCode,Look Up Cordinates ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			APIResources resourceAPI = APIResources.valueOf("getPlacesCollection");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).header("Authorization", "Bearer " +token)
					.when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			String wifiNetworkValue = WhistleBasePage.getJsonPath(response, "wifi_network");
			Utils.setValuesInPropertiesFile("environment", "wifiNetwork", wifiNetworkValue);
			Utils.assertOutputNotEquals(wifiNetworkValue, null);
			log.info("wifiNetworkValue"+wifiNetworkValue);
			log.info("******** Test Case Execution Finished *********");
	}
}
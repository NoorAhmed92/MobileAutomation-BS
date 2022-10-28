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
public class BreedsTest extends BaseTest {
	static String breedsList;
	Response response;

	@TestRails(id="993842")
	@Description("[TestRail] Test Dog breeds-List all dog breeds.")
	@Test(groups = {"P0", "BAT"}, retryAnalyzer = Retry.class)
	@Feature("Breeds")
    public void validateDogBreeds() throws IOException {
		
			log.info("******** Test Case Execution started to validate dogs breed API  *********");
			APIResources resourceAPI = APIResources.valueOf("getDogBreeds");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).when().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			breedsList = WhistleBasePage.getJsonPath(response, "breeds");
			Utils.assertOutputNotEquals(breedsList, null);
			log.info("******** Test Case Execution Finished *********");
    }
	
	@TestRails(id="993843")
	@Description("[TestRail] Test Cat breeds-List all dog breeds.")
	@Test(groups = {"P0", "BAT"}, retryAnalyzer = Retry.class)
	@Feature("Breeds")
    public void validateCatBreeds() throws IOException {
		
			log.info("******** Test Case Execution started to validate cats breed API  *********");
			APIResources resourceAPI = APIResources.valueOf("getCatBreeds");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).when().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			breedsList = WhistleBasePage.getJsonPath(response, "breeds");
			Utils.assertOutputNotEquals(breedsList, null);
			log.info("******** Test Case Execution Finished *********");		
    }
}

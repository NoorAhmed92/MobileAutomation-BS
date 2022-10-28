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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;

@Listeners(TestListener.class)
public class MapTest extends BaseTest {
	Response response;

	@TestRails(id="993855")
	@Description("[TestRail] Test Map: Pets map-List pets and places")
	@BeforeClass
	@Test(groups = "P0", priority = 0, retryAnalyzer = Retry.class)
	@Feature("Map")
	public void testListPetsAndPlaces() throws IOException {		
			log.info("********* Test Case Execution started for response Lists pets and places***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			APIResources resourceAPI = APIResources.valueOf("getListPetsAndPlaces");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).header("Authorization", "Bearer " +token).
					when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			String latitude = WhistleBasePage.getJsonPath(response, "pets[0].last_location.latitude");
			String longitude = WhistleBasePage.getJsonPath(response, "pets[0].last_location.longitude");
			String petAddress = WhistleBasePage.getJsonPath(response, "places[0].address");
			Utils.setValuesInPropertiesFile("environment", "latitude", latitude);
			Utils.setValuesInPropertiesFile("environment", "longitude", longitude);
			Utils.setValuesInPropertiesFile("environment", "petAddress", petAddress);
			log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="993856")
	@Description("[TestRail] Test Map: GeoCode-Look up coordinates")
	@Test(groups = "P0", priority = 1, retryAnalyzer = Retry.class)
	@Feature("Map")
	public void testLookUpCoodrinates() throws IOException {	
			log.info("********* Test Case Execution started for response GeoCode,Look Up Coordinates ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String petAddress = Utils.getValuesFromPropertiesFile("environment", "petAddress");
			APIResources resourceAPI = APIResources.valueOf("getLookUpCoodrinates");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).header("Authorization", "Bearer " +token).
					body(WhistleBasePage.data.addLookupCoordinates(petAddress)).when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			int expectedLatitude = Integer.valueOf(Utils.getValuesFromPropertiesFile("environment", "latitude").substring(0,2));
			int expectedLongitude = Integer.valueOf(Utils.getValuesFromPropertiesFile("environment", "longitude").substring(0,4));
			int actualLatitude= Integer.valueOf(WhistleBasePage.getJsonPath(response,"latitude").substring(0,2));
			int actualLongitude=Integer.valueOf(WhistleBasePage.getJsonPath(response,"longitude").substring(0,4));
			log.info("actualLatitude: "+actualLatitude+ " and expectedLatitude: "+expectedLatitude);
			log.info("actualLongitude: "+actualLongitude+ " and expectedLongitude: "+expectedLongitude);
			Utils.assertOutput(actualLatitude, expectedLatitude, resourceAPI);
			Utils.assertOutput(actualLongitude, expectedLongitude, resourceAPI);				
			log.info("******** Test Case Execution Finished *********");		
	}
	
	@TestRails(id="993857")
	@Description("[TestRail] Test Map: Reverse Geocode-Look up address")
	@Test(groups = "P0", priority = 2, retryAnalyzer = Retry.class)
	@Feature("Map")
    public void testReverseLookUpCoordinates() throws IOException {
			
			log.info("********* Test Case Execution started for response Reverse GeoCode,Look Up Coordinates ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String latitude = Utils.getValuesFromPropertiesFile("environment", "latitude");
			String longitude = Utils.getValuesFromPropertiesFile("environment", "longitude");
			APIResources resourceAPI = APIResources.valueOf("getReverseLookUpCoodrinates");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).header("Authorization", "Bearer " +token).
					body(WhistleBasePage.data.addReverseGeocode(latitude, longitude)).
					when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			String expectedAddress = Utils.getValuesFromPropertiesFile("environment", "petFullAddress");
			String actualAddress = WhistleBasePage.getJsonPath(response, "description.address")+","+ WhistleBasePage.getJsonPath(response, "description.place")+","+ WhistleBasePage.getJsonPath(response, "description.postcode")+","+ WhistleBasePage.getJsonPath(response, "description.region")+","+ WhistleBasePage.getJsonPath(response, "description.country");;
			Utils.assertOutput(actualAddress, expectedAddress, resourceAPI);
			log.info("ActualAddress: "+ actualAddress);
			log.info("******** Test Case Execution Finished *********");		
    }
	
	/*@TestRails(id="993858")
	@Description("[TestRail] Test Map: Locations-List of locations")
	@Test(groups = "P0", priority = 3, retryAnalyzer = Retry.class, enabled=false)
	@Feature("Map")*/
    public void testListOfLocation() throws IOException {	
			log.info("********* Test Case Execution started for response Last known location ***********");	
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
			LocalDateTime currentDT = LocalDateTime.now(); 
			APIResources resourceAPI = APIResources.valueOf("getListOfLocation");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).header("Authorization", "Bearer " +token).
					pathParam("petId", petId).queryParam("start_time", currentDT).queryParam("end_time", currentDT).when().log().all().
					get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
    }

	@TestRails(id="993859")
	@Description("[TestRail] Test Map: Locations-Last known location")
	@Test(groups = "P0", priority = 4, retryAnalyzer = Retry.class)
	@Feature("Map")
	public void testLocationLastKnown() throws IOException {
			log.info("********* Test Case Execution started for response Last known location ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
			APIResources resourceAPI = APIResources.valueOf("getLastKnownlocation");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId", petId).header("Authorization", "Bearer " +token).
					when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="993860")
	@Description("[TestRail] Test Map: Locations-Most recent locations")
	@Test(groups = "P0", priority = 5, retryAnalyzer = Retry.class)
	@Feature("Map")
	public void testMostRecentlocation() throws IOException {
			log.info("********* Test Case Execution started for response Last known location ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
			APIResources resourceAPI = APIResources.valueOf("getMostrecentlocation");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId", petId).header("Authorization", "Bearer " +token).
					when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="993861")
	@Description("[TestRail] Test Map: Locations timelines-Location timeline from past 168 hours / 7 days")
	@Test(groups = "P0", priority = 6, retryAnalyzer = Retry.class)
	@Feature("Map")
	public void testLocationTimelineFromPast() throws IOException {
			log.info("********* Test Case Execution started for response Last known location ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
			APIResources resourceAPI = APIResources.valueOf("getLocationTimelinesFromPast");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId", petId).header("Authorization", "Bearer " +token).
					when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010066")
	@Description("[TestRail] Test Map: Locate Request-- Start locating - 404")
	@Test(groups = "P1", priority = 7, retryAnalyzer = Retry.class)
	@Feature("Map")
	public void testStartLocating() throws IOException {
			log.info("********* Test Case Execution started to validate Start locating ***********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String userId = Utils.getValuesFromPropertiesFile("environment", "userId");
			APIResources resourceAPI = APIResources.valueOf("getStartlocating");
			LocalDateTime currentDT = LocalDateTime.now(); 
			log.info("Current Date and Time: " +currentDT);
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("userId", userId).header("Authorization", "Bearer " +token).when().put(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 404, resourceAPI);
			String errMsg=WhistleBasePage.getJsonPath(response,"errors.message");
			Utils.assertOutput(errMsg, WhistleConstants.ERROR_MSG_DEVICE_NOT_FOUND,resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010079")
	@Description("[TestRail] Test Map: Request to start tracking - 401")
	@Test(groups = "P1", priority = 8, retryAnalyzer = Retry.class)
	@Feature("Map")
	public void testStartTrackingRequestNegative() throws IOException {
			log.info("********* Test Case Execution started to validate Request to start tracking ***********");
			String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
			APIResources resourceAPI = APIResources.valueOf("getRequestToStarTracking");
			LocalDateTime currentDT = LocalDateTime.now(); 
			log.info("Current Date and Time: " +currentDT);
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId", petId).when().put(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010080")
	@Description("[TestRail] Test Map: Request to stop tracking - 401")
	@Test(groups = "P1", priority = 9, retryAnalyzer = Retry.class)
	@Feature("Map")
	public void testStopTrackingRequestNegative() throws IOException {
			log.info("********* Test Case Execution started to validate Request to stop tracking ***********");
			String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
			APIResources resourceAPI = APIResources.valueOf("getRequestToStopTracking");
			LocalDateTime currentDT = LocalDateTime.now(); 
			log.info("Current Date and Time: " +currentDT);
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId", petId).when().put(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
	}
}

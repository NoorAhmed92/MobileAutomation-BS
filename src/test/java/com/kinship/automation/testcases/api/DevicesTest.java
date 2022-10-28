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
public class DevicesTest extends BaseTest {
	Response response;
	
	@TestRails(id="993845")
	@Description("[TestRail] Test Device-Show device info & battery stats.")
	@Test(groups = "P0", priority = 0, retryAnalyzer = Retry.class)
	@Feature("Devices")
    public void testShowDeviceInfo() throws IOException {
		
			log.info("******** Test Case Execution started to validate device info and & battery stats *********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String deviceSerialNumber = Utils.getValuesFromPropertiesFile("environment", "deviceSerialNumber");
			APIResources resourceAPI = APIResources.valueOf("getdeviceInfo");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParam("deviceSerialNumber", deviceSerialNumber).
					header("Authorization", "Bearer " +token).when().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			String actualSerialNumber = WhistleBasePage.getJsonPath(response, "device.serial_number");
			Utils.assertOutput(actualSerialNumber, deviceSerialNumber, resourceAPI);
			log.info("******** Test Case Execution Finished *********");	
    }
	
	@TestRails(id="993853")
	@Description("[TestRail] Test Devices: Device on demand check in.")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Devices")
    public void testDevicesOnDemandAPI() throws IOException {
		
			log.info("******** Test Case Execution started to validate Device on demand check in API  *********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String deviceSerialNumber = Utils.getValuesFromPropertiesFile("environment", "deviceSerialNumber");
			APIResources resourceAPI = APIResources.valueOf("getDeviceOnDemandCheckIn");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParam("deviceSerialNumber", deviceSerialNumber).
					header("Authorization", "Bearer " +token).
					when().post(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 204, resourceAPI);			
			log.info("******** Test Case Execution Finished *********");	
    }
	
	@TestRails(id="993847")
	@Description("[TestRail] Test Devices: Show Device Configs.")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Devices")
    public void testShowDeviceConfigs() throws IOException {
		
			log.info("******** Test Case Execution started to validate Device on demand check in API  *********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String deviceSerialNumber = Utils.getValuesFromPropertiesFile("environment", "deviceSerialNumber");
			APIResources resourceAPI = APIResources.valueOf("getDeviceConfigs");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParam("deviceSerialNumber", deviceSerialNumber).
					header("Authorization", "Bearer " +token).when().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);			
			log.info("******** Test Case Execution Finished *********");
    }
	
	@TestRails(id="993848")
	@Description("[TestRail] Test Devices: Update device's configs.")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Devices")
    public void testUpdateDeviceConfigs() throws IOException {
		
			log.info("******** Test Case Execution started to validate Device on demand check in API  *********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String deviceSerialNumber = Utils.getValuesFromPropertiesFile("environment", "deviceSerialNumber");
			APIResources resourceAPI = APIResources.valueOf("getDeviceConfigs");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.addDeviceConfigsPayload()).pathParam("deviceSerialNumber", deviceSerialNumber).
					header("Authorization", "Bearer " +token).when().put(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 204, resourceAPI);			
			log.info("******** Test Case Execution Finished *********");	
    }
	
	@TestRails(id="993849")
	@Description("[TestRail] Test Devices: Show Activation.")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Devices")
    public void testShowDevicesActivationStatus() throws IOException {
		
			log.info("******** Test Case Execution started to validate Device on demand check in API  *********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String newDeviceId = Utils.getValuesFromPropertiesFile("environment", "newDeviceId");
			APIResources resourceAPI = APIResources.valueOf("getShowDeviceActivation");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParam("deviceSerialNumber", newDeviceId).
					header("Authorization", "Bearer " +token).when().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 204, resourceAPI);			
			log.info("******** Test Case Execution Finished *********");	
    }
	
	@TestRails(id="993850")
	@Description("[TestRail] Test Devices: Plans.")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Devices")
    public void testDevicesPlans() throws IOException {
		
			log.info("******** Test Case Execution started to validate Device on demand check in API  *********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String deviceSerialNumber = Utils.getValuesFromPropertiesFile("environment", "deviceSerialNumber");
			APIResources resourceAPI = APIResources.valueOf("getDevicePlans");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParam("deviceSerialNumber", deviceSerialNumber).
					header("Authorization", "Bearer " +token).when().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);			
			log.info("******** Test Case Execution Finished *********");	
    }
	
	@TestRails(id="993851")
	@Description("[TestRail] Test Devices: Find My Pets.")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Devices")
    public void testDevicesFindMyPet() throws IOException {
		
			log.info("******** Test Case Execution started to validate Device on demand check in API  *********");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String deviceId = Utils.getValuesFromPropertiesFile("environment", "deviceId");
			String userId = Utils.getValuesFromPropertiesFile("environment", "userId");
			APIResources resourceAPI = APIResources.valueOf("getFindMyPet");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.addDeviceFindMyPetPayload(userId)).pathParam("deviceId", deviceId).
					header("Authorization", "Bearer " +token).when().post(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
			log.info("******** Test Case Execution Finished *********");
    }
	
	@TestRails(id="993852")
	@Description("[TestRail] Test Devices: firmware_Device Stream Assignment.")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Devices")
    public void testDeviceStreamAssignmentFirmware() throws IOException {
		
			log.info("******** Test Case Execution started to validate Device on demand check in API  *********");	
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			String deviceId = Utils.getValuesFromPropertiesFile("environment", "deviceId");
			APIResources resourceAPI = APIResources.valueOf("getDeviceFirmware");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParam("deviceId", deviceId).
					header("Authorization", "Bearer " +token).when().put(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 204, resourceAPI);			
			log.info("******** Test Case Execution Finished *********");
    }
}

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
public class PetsTest extends BaseTest {
	Response response;

	@TestRails(id="993865")
	@Description("[TestRail] Test Pets: List-Retrieve pet list")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testGetPetList() throws IOException {

		log.info("********* Test Case Execution started to validate pets list***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		APIResources resourceAPI = APIResources.valueOf("getListOfPets");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).header("Authorization", "Bearer " +token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010112")
	@Description("[TestRail] Test Pets List-Retrieve pet list : 401")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testGetPetListNotFound() throws IOException {

		log.info("********* Test Case Execution started to validate pets list***********");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getListOfPets");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).header("Authorization", "Bearer " +petId).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="993866")
	@Description("[TestRail] Test Pets: Pet Profile")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testPetsProfile() throws IOException {

		log.info("********* Test Case Execution started to validate pets profile ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		String petName = Utils.getValuesFromPropertiesFile("environment", "petName");
		APIResources resourceAPI = APIResources.valueOf("getPetsProfile");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		String actualPetId = WhistleBasePage.getJsonPath(response, "pet.id");
		String actualPetName = WhistleBasePage.getJsonPath(response, "pet.name");
		log.info("Actual PetId from response body : " + actualPetId);
		Utils.assertOutput(actualPetId, petId, resourceAPI);
		log.info("Actual PetName from response body : " + actualPetName);
		Utils.assertOutput(actualPetName, petName, resourceAPI);
		log.info("******** Test Case Execution Finished *********");
	}

	@TestRails(id="993867")
	@Description("[TestRail] Test Pets: Achievements")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testPetAchievementsList() throws IOException {

		log.info("********* Test Case Execution started to validate pets profile ***********");
		APIResources resourceAPI = APIResources.valueOf("getPetAchievementsList");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		log.info("******** Test Case Execution Finished *********");
	}
	
	@TestRails(id="1010113")
	@Description("[TestRail] Test Pets Achievements : 401")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testPetAchievementsListStatusCode() throws IOException {

		log.info("********* Test Case Execution started to validate pets profile ***********");
		APIResources resourceAPI = APIResources.valueOf("getPetAchievementsList");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +petId).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
		log.info("******** Test Case Execution Finished *********");
	}
	
	@TestRails(id="1010114")
	@Description("[TestRail] Test Pets Achievements : 404")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testPetAchievementsListNotFound() throws IOException {

		log.info("********* Test Case Execution started to validate pets profile ***********");
		APIResources resourceAPI = APIResources.valueOf("getPetAchievementsList");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 404, resourceAPI);
		log.info("******** Test Case Execution Finished *********");
	}

	@TestRails(id="993868")
	@Description("[TestRail] Test Pets: Owners")
	@Test(groups = {"P0", "BAT"}, retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testListOwners() throws IOException {

		log.info("********* Test Case Execution started to validate pets profile ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getListOwners");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010115")
	@Description("[TestRail] Test Pets Owners : 401")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testListOwnersStatusCode() throws IOException {

		log.info("********* Test Case Execution started to validate pets profile ***********");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getListOwners");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +petId).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010116")
	@Description("[TestRail] Test Pets Owners : 404")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testListOwnersNotFound() throws IOException {

		log.info("********* Test Case Execution started to validate pets profile ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		APIResources resourceAPI = APIResources.valueOf("getListOwners");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 404, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="993869")
	@Description("[TestRail] Test Pets: Create invitation code")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testCreateInvitationCode() throws IOException {

		log.info("********* Test Case Execution started to validate Create Invitation Code ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getCreateInvitationCode");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +token).
				when().log().all().post(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 201, resourceAPI);
		String invitation_code = WhistleBasePage.getJsonPath(response, "invitation_code");
		String invitation_link = WhistleBasePage.getJsonPath(response, "invitation_link");
		Utils.assertOutputNotEquals(invitation_code, null);
		Utils.assertOutputNotEquals(invitation_link, null);
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010117")
	@Description("[TestRail] Test Pets Create invitation code : 401")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testCreateInvitationCodeStatusCode() throws IOException {

		log.info("********* Test Case Execution started to validate Create Invitation Code ***********");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getCreateInvitationCode");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +petId).
				when().log().all().post(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010118")
	@Description("[TestRail] Test Pets Create invitation code : 404")
	@Test(groups = "P1", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testCreateInvitationCodeNotFound() throws IOException {

		log.info("********* Test Case Execution started to validate Create Invitation Code ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		APIResources resourceAPI = APIResources.valueOf("getCreateInvitationCode");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",token).
				when().log().all().post(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 404, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}
		
	@TestRails(id="993889")
	@Description("[TestRail] Test Nutrition: Retrieve nutrition calculation data")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Nutrition")
	public void testNutritionCalculationData() throws IOException {

		log.info("********* Test Case Execution started for Nutrition calculation data ***********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getNutritionCalculationData");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="993890")
	@Description("[TestRail] Test Pet foods: List pet all pet foods")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("PetFoods")
	public void testPetFoods() throws IOException {

		log.info("********* Test Case Execution started for pet foods ***********");	
		APIResources resourceAPI = APIResources.valueOf("getPetFoods");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="1010071")
	@Description("[TestRail] Test Pets List Transferrable Pets : 200")
	@Test(groups = "apiTests",dependsOnMethods = "testPetsProfile", priority = 1)
	@Feature("Pets")
	public void testListTransferrablePets() throws IOException {

		log.info("********* Test Case Execution started for List Transferrable Pet ***********");	
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String serialNumber = Utils.getValuesFromPropertiesFile("environment", "deviceSerialNumber");
		APIResources resourceAPI = APIResources.valueOf("getListTransferrablePets");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParam("serial_number", serialNumber).
				header("Authorization", "Bearer " +token).when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010084")
	@Description("[TestRail] Test Pets List Transferrable Pets : 401")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testListTransferrablePetsNotFound() throws IOException {

		log.info("********* Test Case Execution started for List Transferrable Pet : 401 ***********");	
		String serialNumber = Utils.getValuesFromPropertiesFile("environment", "deviceSerialNumber");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getListTransferrablePets");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParam("serial_number", serialNumber).
				header("Authorization", "Bearer " +petId).when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="1010091")
	@Description("[TestRail] Test Pets Remove Owner : 401")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testRemoveOwnerNotFound() throws IOException {

		log.info("******** Test Case Execution started to validate Remove Owner *********");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		String userid = Utils.getValuesFromPropertiesFile("environment", "userId");
		APIResources resourceAPI = APIResources.valueOf("getRemoveOwner");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).pathParams("userid",userid).header("Authorization", "Bearer " +petId).
				when().log().all().delete(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="1010092")
	@Description("[TestRail] Test Pets Remove Owner : 422")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testRemoveOwnerStatusCode() throws IOException {

		log.info("******** Test Case Execution started to validate Remove Owner *********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getRemoveOwner");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).pathParams("userid",petId).header("Authorization", "Bearer " +token).
				when().log().all().delete(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 422, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010093")
	@Description("[TestRail] Test Pets Remove Owner : 404")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testRemoveOwnerVerifyStatusCode() throws IOException {

		log.info("******** Test Case Execution started to validate Remove Owner *********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getRemoveOwner");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",token).pathParams("userid",token).header("Authorization", "Bearer " +petId).
				when().log().all().delete(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 404, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010088")
	@Description("[TestRail] Test Pets Create Task : 401")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testCreateTaskNotFound() throws IOException {

		log.info("******** Test Case Execution started to validate Create task  *********");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getCreateTask");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.createTask()).pathParam("petId", petId).
				header("Authorization", "Bearer " +petId).when().post(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);	
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010089")
	@Description("[TestRail] Test Pets Create Task : 422")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testCreateTaskfailedStatusCode() throws IOException {

		log.info("******** Test Case Execution started to validate Create task  *********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getCreateTask");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.createTask()).pathParam("petId", petId).
				header("Authorization", "Bearer " +token).when().post(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 422, resourceAPI);	
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010094")
	@Description("[TestRail] Test Pets Update Task and Its Future  : 401")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testUpdateTaskAndItsFutureNotFound() throws IOException {

		log.info("******** Test Case Execution started to validate Update Task and its future incomplete  *********");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		int randomId = Utils.getRandomInt(1000,9999);
		String id = Integer.toString(randomId);
		APIResources resourceAPI = APIResources.valueOf("getUpdateTaskAndItsFuture");
		response = given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).pathParams("id",id).header("Authorization", "Bearer " +petId).
				when().body(WhistleBasePage.data.updateTastAndFuture()).put(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);	
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010095")
	@Description("[TestRail] Test Pets Update Task and Its Future  : 422")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testUpdateTaskAndItsFutureVerifyStatus() throws IOException {

		log.info("******** Test Case Execution started to validate Update Task and its future incomplete  *********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		int randomId = Utils.getRandomInt(1000,9999);
		String id = Integer.toString(randomId);
		APIResources resourceAPI = APIResources.valueOf("getUpdateTaskAndItsFuture");
		response = given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).pathParams("id",id).header("Authorization", "Bearer " +token).
				when().body(WhistleBasePage.data.updateTastAndFuture()).put(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 422, resourceAPI);	
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010090")
	@Description("[TestRail] Test Pets Destroy Task and its Occurrences : 401")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testDestroyTaskAndItsOccurrenceNotFound() throws IOException {

		log.info("******** Test Case Execution started to validate Transfer Pets to Destroy task and its Occurrence *********");
		int randomId = Utils.getRandomInt(1000,9999);
		String id = Integer.toString(randomId);
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getDestroyTaskAndItsOccurrence");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).pathParams("id",id).header("Authorization", "Bearer " +petId).
				when().log().all().delete(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="1010076")
	@Description("[TestRail] Test Pets Show All Task Occurrence : 200")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testShowAllTaskOccurrences() throws IOException {

		log.info("********* Test Case Execution started for Show All Task Occurrences ***********");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		APIResources resourceAPI = APIResources.valueOf("getShowAllTaskOccurrences");
		response = given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +token).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 200, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010085")
	@Description("[TestRail] Test Pets Show All Task Occurrence : 401")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testShowAllTaskOccurrencesNotFound() throws IOException {

		log.info("********* Test Case Execution started for Show All Task Occurrences ***********");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getShowAllTaskOccurrences");
		response = given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).header("Authorization", "Bearer " +petId).
				when().log().all().get(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010089")
	@Description("[TestRail] Test Pets Destroy Task Occurrence : 401")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testDestroyTaskOccurrenceNotFound() throws IOException {

		log.info("******** Test Case Execution started to validate Transfer Pets to Destroy task Occurrence *********");
		int randomId = Utils.getRandomInt(1000,9999);
		String id = Integer.toString(randomId);
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getDestroyTaskOccurrence");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("petId",petId).pathParams("id",id).header("Authorization", "Bearer " +id).
				when().log().all().delete(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010083")
	@Description("[TestRail] Test Pets Create Pets : 422")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testNotCreatePets() throws IOException {

		log.info("******** Test Case Execution started to validate Create Pets  *********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String deviceSerialNumber = Utils.getValuesFromPropertiesFile("environment", "deviceSerialNumber");
		APIResources resourceAPI = APIResources.valueOf("getListOfPets");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.createPets(deviceSerialNumber)).
				header("Authorization", "Bearer " +token).when().post(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 422, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="1010082")
	@Description("[TestRail] Test Pets Create Pets : 404")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testCreatePetsNotFound() throws IOException {

		log.info("******** Test Case Execution started to validate Create Pets  *********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getListOfPets");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.createPets(petId)).
				header("Authorization", "Bearer " +token).when().post(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 404, resourceAPI);	
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010120")
	@Description("[TestRail] Test Pets Set Profile Attribute  : 404")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testSetProfileAttributeNotFound() throws IOException {

		log.info("******** Test Case Execution started to validate Set Profile Attribute   *********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		int randomId = Utils.getRandomInt(1000,9999);
		String id = Integer.toString(randomId);
		APIResources resourceAPI = APIResources.valueOf("getSetProfileAttributes");
		response = given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("id",id).header("Authorization", "Bearer " +token).
				when().body(WhistleBasePage.data.setProfileAttributePets()).put(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 404, resourceAPI);	
		log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="1010119")
	@Description("[TestRail] Test Pets Set Profile Attribute  : 422")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testSetProfileAttributeStatusCode() throws IOException {

		log.info("******** Test Case Execution started to validate Set Profile Attribute   *********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getSetProfileAttributes");
		response = given().log().all().spec(WhistleBasePage.requestSpecification()).pathParams("id",petId).header("Authorization", "Bearer " +token).
				when().body(WhistleBasePage.data.setProfileAttributePets()).put(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 422, resourceAPI);	
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010123")
	@Description("[TestRail] Test Pets Transfer Pets to device  : 401")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testTransferPetsToDevice() throws IOException {

		log.info("******** Test Case Execution started to validate Transfer Pets to device   *********");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		String deviceSerialNumber = Utils.getValuesFromPropertiesFile("environment", "deviceSerialNumber");
		APIResources resourceAPI = APIResources.valueOf("getTransferPetToDevice");
		response = given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.transferPetToDevice(petId,deviceSerialNumber)).
				header("Authorization", "Bearer " +petId).when().post(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 401, resourceAPI);
		log.info("******** Test Case Execution Finished *********");	
	}

	@TestRails(id="1010122")
	@Description("[TestRail] Test Pets Transfer Pets to device : 404")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testTransferPetsToDeviceNotFound() throws IOException {

		log.info("******** Test Case Execution started to validate Transfer Pets to Device Not Found  *********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		APIResources resourceAPI = APIResources.valueOf("getTransferPetToDevice");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.transferPetToDevice(petId,petId)).
				header("Authorization", "Bearer " +token).when().post(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 404, resourceAPI);	
		log.info("******** Test Case Execution Finished *********");	
	}
	
	@TestRails(id="1010124")
	@Description("[TestRail] Test Pets Transfer Pets to device : 422")
	@Test(groups = "apiTests", retryAnalyzer = Retry.class)
	@Feature("Pets")
	public void testTransferPetsToDeviceStatusCode() throws IOException {

		log.info("******** Test Case Execution started to validate Transfer Pets to Device  *********");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		String petId = Utils.getValuesFromPropertiesFile("environment", "petId");
		String deviceSerialNumber = Utils.getValuesFromPropertiesFile("environment", "deviceSerialNumber");
		APIResources resourceAPI = APIResources.valueOf("getTransferPetToDevice");
		response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.transferPetToDevice(petId,deviceSerialNumber)).
				header("Authorization", "Bearer " +token).when().post(resourceAPI.getResource());
		Utils.assertOutput(response.getStatusCode(), 422, resourceAPI);	
		log.info("******** Test Case Execution Finished *********");	
	}

}

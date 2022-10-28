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
public class CouponsTest extends BaseTest {
	Response response;

	@TestRails(id="993844")
	@Description("[TestRail] Test Show coupon code info.")
	@Test(groups = "P0", retryAnalyzer = Retry.class)
	@Feature("Coupons")
    public void testCouponsAPI() throws IOException {
		
			log.info("********* Test Case Execution started to validate Coupons API ***********");	
			APIResources resourceAPI = APIResources.valueOf("getCoupons");
			String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			response= given().log().all().spec(WhistleBasePage.requestSpecification()).body(WhistleBasePage.data.addCouponsPayload()).header("Authorization", "Bearer " +token).
					when().log().all().get(resourceAPI.getResource());
			Utils.assertOutput(response.getStatusCode(), 422, resourceAPI);
			log.info("******** Test Case Execution Finished *********");
    }
}

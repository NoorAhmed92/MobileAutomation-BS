package com.kinship.automation.pageobjects;

import com.kinship.automation.constants.Constants;
import com.kinship.automation.utils.commonutils.Utils;
import com.kinship.automation.utils.driver.DriverFactory;
import com.kinship.automation.utils.driver.DriverManager;
import com.kinship.automation.utils.driver.TestDriver;
import com.kinship.automation.constants.WhistleConstants;
import com.kinship.automation.testdata.APIResources;
import com.kinship.automation.testdata.TestDataPayload;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.kinship.automation.utils.commonutils.ByT.locator;
import static io.appium.java_client.MobileBy.AccessibilityId;
import static io.restassured.RestAssured.given;

public class WhistleBasePage {

	protected final static Logger log = LogManager.getLogger(WhistleBasePage.class);
	public TestDriver driver;
	public static RequestSpecification req;
	public static TestDataPayload data = new TestDataPayload();
	static String token, userId, realtimeUserChannel, deviceSerialNumber, emailAddress;
	static Response response;

	public By signInLink = locator(By.id("welcome - sign in label"), By.id("welcome_screen_sign_in_btn"));
	private By getStartedBtn = locator(By.id("Get started"), By.xpath("//android.widget.Button[@text='Get started']"));

	private By whistleIcon= locator(By.id("Welcome to"), By.id("welcome_screen_logo"));
	private By enableBluetoothBanner = locator(By.xpath("//*[@id='“Whistle-Beta” Would Like to Use Bluetooth']"), By.id("alert_banner_title"));
	private By oKBluetoothBtn = locator(By.xpath("//*[@text='OK']"), By.id("alert_banner_action_primary"));
	private By navigateUpBackButton= locator(By.id("ChubbyBackArrow"), By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"));
	private By closeBrowser = locator(By.id(""), By.id("com.android.chrome:id/close_button"));
	private By helpUrl = locator(By.id(""), By.id("com.sec.android.app.sbrowser:id/url_bar_text"));



	public WhistleBasePage(){

	}

	public WhistleBasePage(TestDriver driver) {
		this.driver = driver;
	}

	@Step("Setting Base URI, Content Type and Accept Header Values In API Test")
	public static RequestSpecification requestSpecification() throws IOException
	{		
		if(req==null)
		{
			String fileName = "logs/apiLogging-" +new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss'.txt'").format(new Date());
			PrintStream log =new PrintStream(new FileOutputStream(fileName));
			token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
			req = new RequestSpecBuilder().setBaseUri(Constants.baseURLAPI).setAccept(WhistleConstants.acceptHeader)
					.setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			return req;
		}
		return req;// If not null return older req
	}

	@Step("Getting key value from Response")
	public static String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}

	@Description("Pre-requisite is to login and get authorization code and set values in property file")
	public static void getAuthorizationToken() throws IOException {

		log.info("Pre-requisite is to get authorization token and user's realtime channel event");
		APIResources resourceAPI = APIResources.valueOf("getBearerToken");
		response = given().log().all().spec(requestSpecification()).body(data.addWhistleLoginPayLoad()).when().log()
				.all().post(resourceAPI.getResource());
		Assert.assertEquals(response.getStatusCode(), 201);
		log.info("Getting Authorization Token");
		token = getJsonPath(response, "auth_token");
		userId = getJsonPath(response, "user.id");
		emailAddress = getJsonPath(response, "user.email");
		realtimeUserChannel = getJsonPath(response, "user.realtime_channel.channel");
		deviceSerialNumber = getJsonPath(response, "user.user_activations.device_serial").substring(1, 12);
		log.info("User's realtime channel value is:" + realtimeUserChannel);
		log.info("Device's serial number is:" + deviceSerialNumber);
		log.info("Authentication token is : " + token);
		log.info("User's id is : " + userId);
		Utils.setValuesInPropertiesFile("environment", "auth_token", token);
		Utils.setValuesInPropertiesFile("environment", "userId", userId);
		Utils.setValuesInPropertiesFile("environment", "realtimeUserChannel", realtimeUserChannel);
		Utils.setValuesInPropertiesFile("environment", "deviceSerialNumber", deviceSerialNumber);
		Utils.setValuesInPropertiesFile("environment", "emailAddress", emailAddress);
	}

	@Description("Pre condition to Get Pet ID Info and set it in property file")
	public static void getPetID() throws IOException {

		log.info("Pre-requisite is to get Pet ID info");
		String token = Utils.getValuesFromPropertiesFile("environment", "auth_token");
		APIResources resourceAPI = APIResources.valueOf("getListOfPets");
		response = given().log().all().spec(requestSpecification()).header("Authorization", "Bearer " + token).when()
				.log().all().get(resourceAPI.getResource());
		Assert.assertEquals(response.getStatusCode(), 200);
		String petId = getJsonPath(response, "pets[0].id");
		String petName = getJsonPath(response, "pets[0].name");
		String petFullAddress = getJsonPath(response, "pets[0].last_location.description.address") + ","
				+ getJsonPath(response, "pets[0].last_location.description.place") + ","
				+ getJsonPath(response, "pets[0].last_location.description.postcode") + ","
				+ getJsonPath(response, "pets[0].last_location.description.region") + ","
				+ getJsonPath(response, "pets[0].last_location.description.country");
		Assert.assertNotEquals(petId, null);
		log.info("Pet ID id : " + petId);
		Utils.setValuesInPropertiesFile("environment", "petId", petId);
		Utils.setValuesInPropertiesFile("environment", "petName", petName);
		Utils.setValuesInPropertiesFile("environment", "petFullAddress", petFullAddress);
		log.info("******** Test Case Execution Finished *********");
	}

	@Step("Click on sign in link")
	public SignInPage clickSignInLnk() {
		driver.clickLocator(signInLink);
		return new SignInPage(driver);
	}

	@Step("Click on get started button")
	public SelectDevicePage clickGetStartedButton() throws InterruptedException {
		handleBluetoothBannerIfPresent();
		driver.clickLocator(getStartedBtn);
		return new SelectDevicePage(driver);
	}

	public boolean isGetStartedButtonISDisplayed() {
		return driver.isElementPresent(getStartedBtn);
	}

	@Step("Verify Logo on Welcome Screen")
	public boolean isWhistleLogoScreenDisplayed() {
		return driver.isElementPresent(whistleIcon);
	}

	@Step("Clicking on {0}")
	public void clickTab(String tabName) {
		driver.pauseExecutionFor(2);
		driver.tapElementAt(locator(AccessibilityId(tabName)), 0.5, 0.5);
	}

	@Step("Asserting Tab")
	public void assertTab(String tabName) {
		Assert.assertTrue(driver.isElemneVisibleOnScreen(locator(AccessibilityId(tabName))), tabName + " icon is not found");
	}

	@Step("Android Platform")
	public boolean isAndroidBS() {
		return ( DriverFactory.platfrom.equalsIgnoreCase("android") || DriverFactory.platfrom.equalsIgnoreCase("bs-android"));
	}

	public void handleBluetoothBannerIfPresent() {
		if (isEnableBluetoothBannerPresent())
			acceptEnableBluetoothIfPresent();
	}

	@Step("Verify if enable Bluetooth Banner is displayed")
	public boolean isEnableBluetoothBannerPresent() {
		return driver.isElementPresent(enableBluetoothBanner);
	}

	public void acceptEnableBluetoothIfPresent() {
		if (driver.isElementPresent(oKBluetoothBtn)) {
			driver.clickLocator(oKBluetoothBtn);
		}
	}

	@Step("Comparing the value")
	public void assertValue(String sPetPreviousGender, String sUpdatePetGender, String sErrMsg) {
		Assert.assertEquals(sPetPreviousGender, sUpdatePetGender,sErrMsg);
	}

	@Step("Navigate back")
	public ViewPetProfilePage navigateBackPage() {
		driver.clickLocator(navigateUpBackButton);
		return  new ViewPetProfilePage(driver);
	}

	@Step("{0}")
	public void write2Log4jAllureStep(String sStep){
		log.info(sStep);
	}

	@Step("Verify and close the browser")
	public void VerifyNCloseBrowserWindow() {
		if (DriverFactory.platfrom.equalsIgnoreCase("android")) {
			driver.pauseExecutionFor(2);
			String chatToVerUrlTxt = driver.findElement(By.id("com.android.chrome:id/url_bar")).getText();

			log.info("Found URL for Chat to Vet " + chatToVerUrlTxt);

			if (driver.isElementPresent(closeBrowser)) {
				driver.tapElementAt(closeBrowser, 0.9, 0.5);
			}
		} else {
			getHelpUrl();
		}
	}

	@Step("Get the url for help make Whistle better")
	public String getHelpUrl() {
		String url = "";
		if (DriverFactory.platfrom.equalsIgnoreCase("ios")) {
			((AppiumDriver) DriverManager.getDriver()).activateApp("com.apple.mobilesafari");
			log.info(((AppiumDriver) DriverManager.getDriver()).queryAppState("com.apple.mobilesafari"));
			WebElement urlTextField = ((AppiumDriver) DriverManager.getDriver()).findElement(MobileBy.xpath("//XCUIElementTypeOther[@name='URL']"));
			url = urlTextField.getText();
			log.info("URL " + url);
			((AppiumDriver) DriverManager.getDriver()).activateApp("com.whistle.winston-ent");
		} else {
			driver.pauseExecutionFor(5);
			url = driver.getText(helpUrl, "fetching web view url");
		}
		return url;
	}

	@Step("Click on {0}")
	public void clickStatusTab(String tabName) {
		driver.tapElementAt(locator(AccessibilityId(tabName)), 0.5, 0.5);
	}
}

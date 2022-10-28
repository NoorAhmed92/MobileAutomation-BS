package com.kinship.automation.testdata;

import com.kinship.automation.constants.WhistleConstants;
import io.qameta.allure.Step;

import java.io.IOException;

public class TestDataPayload {
	
	@Step("Whistle Login Test Data Body Payload")
	public LoginEntity addWhistleLoginPayLoad() throws IOException
	{
		LoginEntity w =new LoginEntity();
		w.setEmail(WhistleConstants.email);
		w.setPassword(WhistleConstants.password);
		return w;
	}
	
	@Step("Whistle Login Test Invalid Data Body Payload")
	public Object addWhistleLoginInvalidPayLoad() throws IOException{
		LoginEntity w =new LoginEntity();
		w.setEmail(WhistleConstants.invalidEmail);
		w.setPassword(WhistleConstants.password);
		return w;
	}
	
	@Step("Auth Body Payload")
	public String addAuthPayload(String realtimeChannelName)
	{
		return "{\r\n"
				+ "  \"realtime_channel\": {\r\n"
				+ "    \"name\": \""+realtimeChannelName+"\",\r\n"
				+ "    \"socket_id\": \"123.456\"\r\n"
				+ "  }\r\n"
				+ "}";
	}
	
	@Step("Device Configs Body Payload")
	public String addDeviceConfigsPayload()
	{
		return "{\r\n"
				+ "  \"device_configs\": {\r\n"
				+ "    \"gps_scan_interval\": 6,\r\n"
				+ "    \"smart_gps_scan\": false,\r\n"
				+ "    \"version\": 1\r\n"
				+ "  }\r\n"
				+ "}";
	}
	
	@Step("Device Find My Pet Body Payload")
	public String addDeviceFindMyPetPayload(String userId)
	{
		return "{\r\n"
				+ "    \"find_my_pet\": {\r\n"
				+ "        \"power_save_disabled\": true,\r\n"
				+ "        \"tracking_state\": true,\r\n"
				+ "        \"power_save_disabled_timeout\": 2400,\r\n"
				+ "        \"tracking_timeout\": 1800,\r\n"
				+ "        \"user_id\":\""+userId+"\" ,\r\n"
				+ "        \"bluetooth\": true\r\n"
				+ "    }\r\n"
				+ "}";
	}
	
	@Step("Coupons Body Payload")
	public String addCouponsPayload()
	{
		return "{\r\n"
				+ "    \"plan_id\": \"12MonthFree\"\r\n"
				+ "}";
	}
	
	@Step("Notification Token Body Payload")
	public String addNotificationTokenPayload()
	{
		return "{\r\n"
				+ "  \"notification_token\": {\r\n"
				+ "    \"notification_token_type\": \"apns\",\r\n"
				+ "    \"notification_token\": \"sometoken\",\r\n"
				+ "    \"mobile_device\": \"mobile1\",\r\n"
				+ "    \"app_id\": \"com.whistle.WhistleAppEnt\"\r\n"
				+ "  }\r\n"
				+ "}";
	}
	
	@Step("Create Phone Number Body Payload")
	public String addCreatePhoneNumberPayload(String newPhoneNumber)
	{
		return "{\r\n"
				+ "  \"phone_number\": {\r\n"
				+ "    \"number\": \""+newPhoneNumber+"\",\r\n"
				+ "    \"primary_number\": false\r\n"
				+ "  }\r\n"
				+ "}";
	}
	
	@Step("Verify Phone Number Body Payload")
	public String verifyPhoneNumberPayload(int phoneVerificationCode)
	{
		return "{\r\n"
				+ "  \"verification\": {\r\n"
				+ "    \"code\": \""+phoneVerificationCode+"\"\r\n"
				+ "  }\r\n"
				+ "}";
	}
	
	@Step("Set Activity Goal Body Payload")
	public String setActivityGoalPayLoad(int minutes) 
	{
		return "{\r\n"
				+ "  \"activity_goal\": {\r\n"
				+ "    \"minutes\": \""+minutes+"\"\r\n"
				+ "  }\r\n"
				+ "}"; 
	
	}
	
	@Step("Reverse Geocode Body Payload")
	public String addReverseGeocode(String latitude, String longitude)
	{
		return "{\r\n"
				+ "    \"latitude\": \""+latitude+"\",\r\n"
				+ "    \"longitude\": \""+longitude+"\"\r\n"
				+ "}";
	}
	
	@Step("Add Lookup Coordinates Body Payload")
	public String addLookupCoordinates(String currentAddress)
	{
		return "{\r\n"
				+ "  \"address\": \""+currentAddress+"\"\r\n"
				+ "}";
	}
	
	@Step("Secondary Email Collection Body Payload")
	public String addSecondaryEmailCollection(String email) {
		return "{\r\n"
				+ "  \"secondary_email\": {\r\n"
				+ "    \"email\": \"test_"+email+"@example.com\"\r\n"
				+ "  }\r\n"
				+ "}";
	}

	
	public String setProfileAttributePets() {
		return "{\r\n"
				+ "  \"pet\": {\r\n"
				+ "    \"profile\": {\r\n"
				+ "      \"breed\": {\r\n"
				+ "        \"id\": 633\r\n"
				+ "      },\r\n"
				+ "      \"gender\": \"f\",\r\n"
				+ "      \"pet_food\": {\r\n"
				+ "        \"id\": 3\r\n"
				+ "      }\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}";
		
	}
	
	public String updateTastAndFuture() {
		return "{\r\n"
				+ "  \"task\": {\r\n"
				+ "    \"title\": \"Bordetella Vaccine\",\r\n"
				+ "    \"type\": \"medical\",\r\n"
				+ "    \"note\": \"I need to give my dog the vaccine\",\r\n"
				+ "    \"schedule\": {\r\n"
				+ "      \"start_at\": \"2022-02-08T02:00:00-08:00\",\r\n"
				+ "      \"occurrence_interval\": 1,\r\n"
				+ "      \"occurrence_frequency\": \"daily\"\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}";
		
	}
	
	public String createTask() {
		return "{\r\n"
				+ "  \"task\": {\r\n"
				+ "    \"title\": \"Bordetella Vaccine\",\r\n"
				+ "    \"type\": \"medical\",\r\n"
				+ "    \"note\": \"I need to give my dog the vaccine\",\r\n"
				+ "    \"schedule\": {\r\n"
				+ "      \"start_at\": \"2022-02-08T02:00:00-08:00\",\r\n"
				+ "      \"time_zone\": \"America/Los_Angeles\",\r\n"
				+ "      \"occurrence_interval\": 1,\r\n"
				+ "      \"occurrence_frequency\": \"daily\"\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}";
		
	}
	
	public String createPets (String deviceSerialNumber) {
		return "{\r\n"
				+ "\"pet\": {\r\n"
				+ "  \"gender\": \"f\",\r\n"
				+ "  \"name\": \"Bailey\",\r\n"
				+ "  \"partner_record\": {\r\n"
				+ "    \"id\": \"50a81f59-15fc-e111-8c61-00215e6e082b\",\r\n"
				+ "    \"partner\": \"banfield\",\r\n"
				+ "    \"type\": \"pet\"\r\n"
				+ "  },\r\n"
				+ "  \"profile\": {\r\n"
				+ "    \"breed\": {\r\n"
				+ "      \"id\": 1\r\n"
				+ "    },\r\n"
				+ "    \"age_in_months\": 4,\r\n"
				+ "    \"age_in_years\": 2,\r\n"
				+ "    \"species\": \"dog\",\r\n"
				+ "    \"time_zone_name\": \"America/Los_Angeles\",\r\n"
				+ "    \"weight\": 20.1,\r\n"
				+ "    \"weight_type\": \"pounds\",\r\n"
				+ "\"is_fixed\": true,\r\n"
				+ "    \"body_condition_score\": \"ideal\",\r\n"
				+ "    \"pet_food\": {\r\n"
				+ "      \"id\": 3\r\n"
				+ "    }\r\n"
				+ "  },\r\n"
				+ "  \"device\": {\r\n"
				+ "    \"serial_number\":\""+deviceSerialNumber+"\"\r\n"
				+ "  }\r\n"
				+ "}\r\n"
				+ "}";	
	}
	
	public String transferPetToDevice(String petId,String deviceSerialNumber) {
		return "{\r\n"
				+ "  \"transfer\": {\r\n"
				+ "    \"pet_id\": "+petId+",\r\n"
				+ "    \"device_serial_number\": \""+deviceSerialNumber+"\"\r\n"
				+ "  }\r\n"
				+ "}";
	}

	@Step("Subscription Preview Cancellation Body Payload")
	public String addSubscriptionPreviewCancellation() {
		return "{\r\n"
				+ "	    \"reason_id\": \"12\",\r\n"
				+ "	    \"immeditate\": \"false\"\r\n"
				+ "	}";
	}
	

}

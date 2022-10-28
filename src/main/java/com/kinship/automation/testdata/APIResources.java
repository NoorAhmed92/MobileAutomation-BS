package com.kinship.automation.testdata;

import io.qameta.allure.Step;

public enum APIResources {
	
	//Activity
	getListOfPets("/api/pets"),
	getListOfPetsActivity("/api/activity/pets"),
	getSetActivityGoal("/api/pets/{petId}/activity_goals"),
	getOverallActivityStash("/api/pets/{petId}/stats"),
	getGetDailyActivity("/api/pets/{petId}/dailies/3"),
	getGetListOfSummary("/api/pets/{petId}/dailies"),
	//Auth
	getRealTimeAuth("/api/auth/realtime_channels"),
	getBearerToken("/api/login"),
	//Breeds
	getDogBreeds("/api/breeds/dogs"),
	getCatBreeds("/api/breeds/cats"),
	//Coupons
	getCoupons("/api/coupons/LifetimeFree"),
	//Devices
	getdeviceInfo("/api/devices/{deviceSerialNumber}"),
	getDeviceOnDemandCheckIn("/api/devices/{deviceSerialNumber}/check_in"),
	getDeviceConfigs("/api/devices/{deviceSerialNumber}/device_configs"),
	getShowDeviceActivation("/api/devices/{deviceSerialNumber}/activation"),
	getDevicePlans("/api/devices/{deviceSerialNumber}/plans"),
	getFindMyPet("/api/devices/{deviceId}/find_my_pet"),
	getDeviceFirmware("/api/devices/{deviceId}/firmware"),
	//Health
	getEatingEventGraphData("/api/pets/{petId}/health/graphs/eating_events"),
	getEatingDailyTrendGraphData("/api/pets/{petId}/health/graphs/eating_daily_trends"),
	getDrinkingGraphData("/api/pets/{petId}/health/graphs/drinking"),
	getLickingGraphData("/api/pets/{petId}/health/graphs/licking"),
	getScratchingGraphData("/api/pets/{petId}/health/graphs/scratching"),
	getSleepingGraphData("/api/pets/{petId}/health/graphs/sleeping"),
	getDailyhealthTrend("/api/pets/{petId}/health/trends"),
	getToggleVisibilityOfHealthReportUrl("/api/pets/{userId}/health/report_url"),
	getRetrievePetWellnessIndexGraphData("/api/pets/{petId}/health/graphs/wellness_index"),
	getSleeping("/api/pets/{petId}/health/graphs/sleeping"),
	//Map
	getLookUpCoodrinates("/api/geocode"),
	getReverseLookUpCoodrinates("/api/reverse_geocode"),
	getListPetsAndPlaces("/api/map/pets"),
	getListOfLocation("/api/pets/{petId}/locations"),
	getLocationTimelinesFromPast("/api/pets/{petId}/timelines/location"),
	getMostrecentlocation("/api/pets/{petId}/locations/recent_trackings"),
	getLastKnownlocation("/api/pets/{petId}/locations/last"),
	getStartlocating("/api/pets/{userId}/locate_request"),
	getRequestToStarTracking("/api/pets/{petId}/start_tracking_request"),
	getRequestToStopTracking("/api/pets/{petId}/stop_tracking_request"),
	//Notification
	getNotificationToken("/api/notification_token"),
	getListNotifications("/api/notifications"),
	getUpdateNotification("/api/notifications/{petId}"),
	//PerformanceSettings
	getPerformanceSettings("/api/performance_settings"),
	//Pets
	getPetFoods("/api/pet_foods"),
	getPetsProfile("/api/pets/{petId}"),
	getPetAchievementsList("/api/pets/{petId}/achievements"),
	getListOwners("/api/pets/{petId}/owners"),
	getCreateInvitationCode("/api/pets/{petId}/invitation_code"),
	getReferralCode("/api/users/referral_code"),
	getExistingcard("/api/users/credit_card"),
	getListTransferrablePets("/api/pets/transfers?device_serial_number={serial_number}"),
	getShowAllTaskOccurrences("/api/pets/{petId}/task_occurrences"),
	getSetProfileAttributes("/api/pets/{id}"),
	getUpdateTaskAndItsFuture("/api/pets/{petId}/tasks/{id}"),
	getCreateTask("/api/pets/{petId}/tasks"),
	getTransferPetToDevice("/api/pets/transfers"),
	getDestroyTaskOccurrence("/api/pets/{petId}/task_occurrences/{id}"),
	getDestroyTaskAndItsOccurrence("/api/pets/{petId}/tasks/{id}"),
	getRemoveOwner("/api/pets/{petId}/owners/{userid}"),
	getUpdateTaskOccurance("/api/pets/{petId}/task_occurrences/{id}"),
	getNutritionCalculationData("/api/pets/{petId}/nutrition/suggested_portions"),
	//PhoneNumbers
	getCreatePhoneNumber("/api/phone_numbers"),
	getUpdatePhoneNumber("/api/phone_numbers/{newPhoneNumberId}/verification"),
	getDeletePhoneNumber("/api/phone_numbers/{newPhoneNumberId}"),
	//Places
	getPlacesCollection("/api/places"),
	//Subscriptions
	getSubscriptions("/api/subscriptions/{subscriptionId}/cancellation/reasons"),
	getSubscriptionCancellationPreview("/api/subscriptions/{subscriptionId}/cancellation/preview"),
	postSubscriptionCancellation("/api/subscriptions/{subscriptionId}/cancellation"),
	//Users
	getApplicationState("/api/users/application_state"),
	getSecondaryEmailcollection("/api/users/secondary_emails"),
	getCurrentUser("/api/users/me"),
	getCreateSecondaryEmail("/api/users/secondary_emails/{secondaryEmailId}/"),
	getDeleteSecondaryEmail("/api/users/secondary_emails/{secondaryEmailId}");
	
	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	@Step("Get API Resource")
	public String getResource()
	{
		return resource;
	}
}

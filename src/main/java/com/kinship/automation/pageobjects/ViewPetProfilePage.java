package com.kinship.automation.pageobjects;

import com.kinship.automation.utils.commonutils.ByT;
import com.kinship.automation.utils.driver.DriverFactory;
import com.kinship.automation.utils.driver.TestDriver;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ViewPetProfilePage extends WhistleBasePage {
	TestDriver driver;
	private By petEditIconBtn = ByT.locator(By.id("edit"), By.id("pet_tab_edit_icon"));
	private By petEditProfileLabel= ByT.locator(By.id("Edit Profile"), By.xpath("//android.widget.TextView[@text='Edit Profile']"));
	private By petPhotoLabel= ByT.locator(MobileBy.AccessibilityId("pet edit - photo clicked"), By.id("pet_profile_pet_name"));
	private By petEditPhotoBtn= ByT.locator(MobileBy.AccessibilityId("pet edit - photo clicked"), By.id("pet_profile_photo"));
	private By petBreedText= ByT.locator(MobileBy.AccessibilityId("edit pet profile - breed"), By.xpath("//android.widget.TextView[@text='Breed']"));
	private By petSexText= ByT.locator(MobileBy.AccessibilityId("edit pet profile - gender"),By.xpath("//android.widget.TextView[@text='Sex']"));
	private By PetNeuteredSpayedText= ByT.locator(MobileBy.AccessibilityId("edit pet profile - spay/neuter"),By.xpath("//android.widget.TextView[@text='Neutered Status']"));
	private By petAgeText= ByT.locator(MobileBy.AccessibilityId("edit pet profile - age"),By.xpath("//android.widget.TextView[@text='Age']"));
	private By petWeightText= ByT.locator(MobileBy.AccessibilityId("edit pet profile - weight"),By.xpath("//android.widget.TextView[@text='Weight']"));
	private By petTimeZoneText= ByT.locator(MobileBy.AccessibilityId("edit pet profile - timezone"),By.xpath("//android.widget.TextView[@text='Timezone']"));
	private By petFoodText= ByT.locator(MobileBy.AccessibilityId("edit pet profile - pet food"),By.xpath("//android.widget.TextView[@text='Pet Food']"));
	private By petProfileBreedItem=ByT.locator(By.xpath("//XCUIElementTypeStaticText[contains(@text,'Breed')]"),By.id("pet_tab_breed"));
	private By petBreedName = ByT.locator(MobileBy.AccessibilityId("edit pet profile - breed"), By.xpath("//android.widget.TextView[@text='Breed']"));
	private By petBreedEditBar = ByT.locator(By.xpath("//*[@class='UIATextField' and ./parent::*[@accessibilityLabel='edit pet profile - breed']]"), By.id("edit_breed_search_bar"));
	private By petBreedNameList=ByT.locator(By.xpath(""), By.id("breeds_list_item_text"));
	private By petPageBackBtn=ByT.locator(By.id("ChubbyBackArrow"),MobileBy.AccessibilityId("Navigate up"));
	private By petNameLabel = ByT.locator(By.xpath("//XCUIElementTypeTextField[@placeholder='Name']"), By.id("pet_profile_pet_name"));
	private By petNameEditInput = ByT.locator(By.xpath("//*[@placeholder='Name']"), By.id("android:id/input"));
	private By petNameSaveBtn = ByT.locator(By.id("Human - tab - 5 of 5"), By.id("md_buttonDefaultPositive"));
	//*[@placeholder='Name' and @XCElementType='XCUIElementTypeTextField']
	public ViewPetProfilePage(TestDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickEditIcon() {
		driver.clickLocator(petEditIconBtn);
	}

	public void editPetNameValue(String name) {
		driver.clickLocator(petNameLabel);
		driver.type(petNameEditInput, name);

		if (DriverFactory.platfrom.equalsIgnoreCase("andriod")||DriverFactory.platfrom.equalsIgnoreCase("bs-android"))
		{
			driver.clickLocator(petNameSaveBtn);
		}
	}

	public boolean ischeckPetNameDisplayed() {
		if(isAndroidBS()) {
			return driver.isElementPresent(petNameLabel);
		}
		else
		return driver.findElement(By.xpath("//*[@x='60' and @y='137' and @width='658' and @height='68']")).isDisplayed();
	}

	public boolean isEditIconPresent() {
		driver.pauseExecutionFor(2);
		return driver.isElementPresent(petEditIconBtn);
	}

	public boolean ischeckPetEditProfileLabelDisplayed() {
		return driver.isElementPresent(petEditProfileLabel);
	}

	public boolean ischeckPetEditPhotoDisplayed() {
		return driver.isElementPresent(petEditPhotoBtn);
	}

	public boolean ischeckPetBreedDisplayed() {
		return driver.isElementPresent(petBreedText);
	}

	public boolean ischeckPetGenderDisplayed() {
		return driver.isElementPresent(petSexText);
	}

	public boolean ischeckPetNeuteredSpayedStatusDisplayed() {
		return driver.isElementPresent(PetNeuteredSpayedText);
	}

	public boolean ischeckAgeDisplayed() {
		return driver.isElementPresent(petAgeText);
	}

	public boolean ischeckWeightDisplayed() {
		return driver.isElementPresent(petWeightText);
	}

	public boolean ischeckTimeZoneDisplayed() {
		driver.pauseExecutionFor(3);
		return driver.isElementPresent(petTimeZoneText);
	}

	public boolean ischeckPetFoodDisplayed() {
		return driver.isElementPresent(petFoodText);
	}

	public String getProfilePageBreedValue() {
		String value;
		if (DriverFactory.platfrom.equalsIgnoreCase("ios")||DriverFactory.platfrom.equalsIgnoreCase("bs-ios"))
			value=driver.findElement(By.xpath("//XCUIElementTypeStaticText[contains(@id,'Breed')]")).getText();
		else
	 		value=driver.findElement(petProfileBreedItem).getText();
		return value;
	}

	public boolean isCheckBreedFieldDisplayed() {
		return driver.isElementPresent(petBreedName);
	}

	public void editBreedField(String name, int index) {
		if (DriverFactory.platfrom.equalsIgnoreCase("ios")||DriverFactory.platfrom.equalsIgnoreCase("bs-ios")) {
			//insert pick  wheeler code
			driver.clickLocator(petBreedName);
			driver.type(petBreedEditBar, name);
			driver.findElementsById(petBreedNameList, index).click();
		}
		else {
			driver.clickLocator(petBreedName);
			driver.type(petBreedEditBar, name);
			driver.findElementsById(petBreedNameList, index).click();
		}
	}

	public void clickBackBtnToPetProfilePage() {
		driver.clickLocator(petPageBackBtn);
	}

	public boolean selectPetBreedEdit() {
		return driver.isElementPresent(petBreedName);
	}

	@Step("Click on Edit icon of Pet")
	public PetEditProfilePage selectEditIcon() {

		driver.clickLocator(petEditIconBtn);
		return new PetEditProfilePage(driver);
	}
}

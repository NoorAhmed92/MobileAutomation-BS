package com.kinship.automation.pageobjects;

import com.kinship.automation.utils.commonutils.ByT;
import com.kinship.automation.utils.driver.DriverFactory;
import com.kinship.automation.utils.driver.TestDriver;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PetEditProfilePage  extends WhistleBasePage{

    TestDriver driver;
    protected final Logger log = LogManager.getLogger(WhistleBasePage.class);

    private By petEditIconBtn = ByT.locator(By.id("edit"), By.id("pet_tab_edit_icon"));
    private By petSexValue = ByT.locator(By.id("edit pet profile - gender"), By.id("pet_profile_list_item_sex"));
    private By petSexUpdatedValue = ByT.locator(By.xpath("//XCUIElementTypeOther[@name='edit pet profile - gender']/XCUIElementTypeTextField"),
                                                By.xpath("//android.widget.LinearLayout/android.view.ViewGroup[3]/android.view.ViewGroup/android.widget.ImageView/following-sibling::android.widget.TextView[2]"));

    private By petStatusValue = ByT.locator(By.xpath("//XCUIElementTypeOther[@name='edit pet profile - spay/neuter']/XCUIElementTypeTextField"),
            By.xpath("//android.widget.LinearLayout/android.view.ViewGroup[4]/android.view.ViewGroup/android.widget.ImageView/following-sibling::android.widget.TextView[2]"));

    private By petSexMaleBtn = ByT.locator(By.xpath("//*[@class='UIAPickerWheel' and @label='Male']"), By.xpath("//android.widget.TextView[@text='Male']"));
    private By petSexFemaleBtn = ByT.locator(By.xpath("//*[@class='UIAPickerWheel' and @label='Female']"), By.xpath("//android.widget.TextView[@text='Female']"));
    private By petNameSaveBtn = ByT.locator(By.xpath(""), By.id("md_buttonDefaultPositive"));
    private By navigateUpBackButton= ByT.locator(By.id("ChubbyBackArrow"), By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"));
    private By maleGender = ByT.locator(By.xpath("//XCUIElementTypePicker/XCUIElementTypePickerWheel"),
            By.xpath("//android.widget.TextView[contains(@text,'Male')]/preceding-sibling::android.widget.RadioButton"));

    private By petMaleStatus = ByT.locator(By.xpath("//XCUIElementTypePicker/XCUIElementTypePickerWheel"),
            By.xpath("//android.widget.TextView[@text='Neutered']/preceding-sibling::android.widget.RadioButton"));
    private By petFemaleStatus = ByT.locator(By.xpath("//XCUIElementTypePicker/XCUIElementTypePickerWheel"),
            By.xpath("//android.widget.TextView[@text='Spayed']/preceding-sibling::android.widget.RadioButton"));

    private By petGenderPickerWheel = ByT.locator(By.xpath("//*[@class='UIAPickerWheel']"), By.xpath(""));
    private By petNameLabel = ByT.locator(By.xpath("//*[@x='60' and @y='194']"), By.id("pet_profile_pet_name"));
    private By petNameEditInput = ByT.locator(By.xpath("//*[@placeholder='Name']"), By.id("android:id/input"));
    private By okPopUpPetName = ByT.locator(By.id("OK"), By.xpath(""));
    private By nullErrorPopUpPetName = ByT.locator(By.id("Validation failed: Name cannot be blank.\\n  (422)"), By.xpath(""));
    private By sizeErrorPopUpPetName = ByT.locator(By.id("Name is too long (maximum is 255 characters)\\n  (422)"), By.xpath(""));
    private By petBreedName = ByT.locator(By.xpath("//*[@class='UIATextField' and ./parent::*[@accessibilityLabel='edit pet profile - breed']]"), By.id("two_line_list_item_value"));
    private By petBreedEditBar = ByT.locator(By.xpath("//*[@class='UIATextField' and ./parent::*[@accessibilityLabel='edit pet profile - breed']]"), By.id("edit_breed_search_bar"));
    private By petBreedNameList=ByT.locator(By.id("Human"),By.id("breeds_list_item_text"));
    private By petProfileBreedItem=ByT.locator(By.xpath(""),By.id("pet_tab_breed"));
    private By petTimeZone= ByT.locator(MobileBy.AccessibilityId("edit pet profile - timezone"),By.id("pet_profile_list_item_time_zone"));
    private By petTimeZoneEditBar = ByT.locator(By.xpath(""), By.id("edit_time_zone_search_bar"));
    private By petTimeZoneNameList=ByT.locator(By.id("Human"),By.id("breeds_list_item_text"));
    private By petWeight = ByT.locator(By.xpath(""), By.id("pet_profile_list_item_weight"));
    private By petWeightInput = ByT.locator(By.xpath(""), By.id("weight_input_field_type"));
    private By petWeightInputKg = ByT.locator(By.xpath(""), By.id("weight_dialog_kilograms_field"));
    private By petWeightInputField  = ByT.locator(By.xpath(""), By.id("weight_input_field"));
    private By petSaveBtn = ByT.locator(By.xpath(""), By.id("md_buttonDefaultPositive"));
    private By petWeightValue = ByT.locator(By.xpath(""), By.xpath("//android.widget.LinearLayout/android.view.ViewGroup[6]/android.view.ViewGroup/android.widget.ImageView/following-sibling::android.widget.TextView[2]"));
    private By petFood = ByT.locator(By.id("edit pet profile - pet food"), By.id("pet_profile_list_item_pet_food"));

    public PetEditProfilePage(TestDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public PetEditProfilePage clickEditIcon() {
        driver.clickLocator(petEditIconBtn);
        return this;
    }


    public boolean isEditIconPresent() {
        driver.pauseExecutionFor(2);
        return driver.isElementPresent(petEditIconBtn);
    }

    public boolean isCheckPetSexValueDisplayed() {
        return driver.isElementPresent(petSexValue);
    }


    @Step("Getting the current value of Pet Gender")
    public String getPetGenderValue() {
        log.info("Getting the current value of Pet Gender");
        return driver.getText(petSexUpdatedValue,"Getting Pet's Gender value");
    }

    @Step("Getting the current value of Pet Gender")
    public String getPetStatusValue() {
        log.info("Getting the current value of Pet Gender");
        return driver.getText(petStatusValue,"Getting Pet's Gender value");
    }


    public void selectAndUpdatePetSexValue(String petSex) {
        driver.clickLocator(petSexValue);
        WebElement element;
        if ((DriverFactory.platfrom.equalsIgnoreCase("android")) || (DriverFactory.platfrom.equalsIgnoreCase("bs-android"))) {
            if(petSex.equalsIgnoreCase("Male"))
                element=driver.findElement(petSexMaleBtn);
            else
                element=driver.findElement(petSexFemaleBtn);
            element.click();
            driver.clickLocator(petNameSaveBtn);
        }
        else
            driver.findElement(By.xpath("//XCUIElementTypePicker/XCUIElementTypePickerWheel")).sendKeys("Female");

    }

    public ViewPetProfilePage navigateBackPage()
    {
        driver.clickLocator(navigateUpBackButton);
        return  new ViewPetProfilePage(driver);
    }

    public boolean ischeckPetNameDisplayed() {
        return driver.isElementPresent(petNameLabel);
    }

    public void editPetNameValue(String name) {
        driver.clickLocator(petNameLabel);
        driver.type(petNameEditInput, name);

        if (DriverFactory.platfrom.equalsIgnoreCase("andriod")||DriverFactory.platfrom.equalsIgnoreCase("bs-android"))
        {
            driver.clickLocator(petNameSaveBtn);
        }
    }

    @Step("Clicked on Sex to change the value")
    public void clickToChangePetGender() {
        log.info("Clicked on Sex to change the value");
        driver.clickLocator(petSexUpdatedValue);
    }

    @Step("Clicked on Sex to change the value")
    public void clickToChangePetStatus() {
        log.info("Clicked on Sex to change the value");
        driver.clickLocator(petStatusValue);
    }


    @Step("Selecting & Updating Pet Gender")
    public String selectPetGender(String sPetGender) throws InterruptedException {
        log.info("Selecting & Updating Pet Gender");
        if(isAndroidBS()) {
            driver.clickLocator(maleGender);
            driver.findElement(By.id("md_buttonDefaultPositive")).click();
        }else{
            driver.findElement(By.xpath("//XCUIElementTypePicker/XCUIElementTypePickerWheel")).sendKeys(sPetGender);
            driver.clickLocator(petSexUpdatedValue);
        }
        driver.pauseExecutionFor(2);
        return getPetGenderValue();
    }

    @Step("Selecting & Updating Pet Gender")
    public String selectPetStatus(String sPetStatus) throws InterruptedException {
        log.info("Selecting & Updating Pet Gender");
        System.out.println("sPetStatus ==> "+ sPetStatus);
        if(isAndroidBS()) {
            driver.findElement(By.xpath("//android.widget.TextView[@text= '" + sPetStatus + "']//preceding-sibling::android.widget.RadioButton")).click();
            driver.findElement(By.id("md_buttonDefaultPositive")).click();
        }else{
            driver.findElement(By.xpath("//XCUIElementTypePicker/XCUIElementTypePickerWheel")).sendKeys(sPetStatus);
            driver.clickLocator(petStatusValue);
        }
        driver.pauseExecutionFor(2);
        return getPetStatusValue();
    }

    private boolean isNullErrorPopUpPresent() {
        boolean flag = false;
        flag = driver.isElementPresent(nullErrorPopUpPetName);
        return flag;
    }

    public boolean editPetNameNullValue() {
        boolean flag = false;
        driver.clickLocator(petNameLabel);
        driver.type(petNameEditInput, "");
        if (DriverFactory.platfrom.equalsIgnoreCase("bs-ios") || DriverFactory.platfrom.equalsIgnoreCase("ios")) {
            driver.clickLocator(petNameSaveBtn);
            if (isNullErrorPopUpPresent()) {
                driver.clickLocator(okPopUpPetName);}
        }
        else {
            flag=driver.isElementEnabled(petNameSaveBtn);
        }
        return flag;
    }
    
    private boolean isSizeErrorPopUpPresent() {
        boolean flag = false;
        flag = driver.isElementPresent(sizeErrorPopUpPetName);
        return flag;
    }
    
    public boolean editLongPetNameValue(String name) {
        boolean flag = false;
        driver.clickLocator(petNameLabel);
        driver.type(petNameEditInput, name);
        if (DriverFactory.platfrom.equalsIgnoreCase("bs-ios") || DriverFactory.platfrom.equalsIgnoreCase("ios")) {
            driver.clickLocator(petNameSaveBtn);
            if (isSizeErrorPopUpPresent()) {
                driver.clickLocator(okPopUpPetName);}
        }
        else {
            flag=driver.isElementEnabled(petNameSaveBtn);
        }
        return flag;
    }

    public boolean isCheckBreedFieldDisplayed() {
        boolean flag = false;
        flag = driver.isElementPresent(petBreedName);
        return flag;
    }

    public void editBreedField(String name,int index) {
        driver.clickLocator(petBreedName);
        driver.type(petBreedEditBar, name);
        if (DriverFactory.platfrom.equalsIgnoreCase("bs-android") || DriverFactory.platfrom.equalsIgnoreCase("android"))
        driver.findElementsById(petBreedNameList,index).click();
        else
            driver.clickLocator(petBreedNameList);

    }

    public String getTimezoneValue() {
        return driver.findElement(petTimeZone).getText();
    }


    public void editTimezoneField(String name, String index) {
        if (isAndroidBS()) {
            driver.clickLocator(petTimeZone);
            driver.type(petTimeZoneEditBar, name);
            driver.findElementsById(petTimeZoneNameList, Integer.parseInt(index)).click();
        }
        else
        {
            driver.findElement(By.id("edit pet profile - timezone")).click();
            driver.findElement(By.id("edit pet profile - timezone")).clear();
            driver.findElement(By.xpath("//XCUIElementTypePickerWheel[@class='UIAPickerWheel']")).sendKeys(name);
            driver.findElement(By.xpath("//XCUIElementTypePickerWheel[@class='UIAPickerWheel']")).click();
            driver.findElement(By.id("Human")).click();
        }
    }
    @Step("Selecting Pet's Weight in Edit Pet Profile")
    public void selectPetWeight() throws InterruptedException {
        log.info("Selecting Pet's Weight");
        driver.clickLocator(petWeight);
    }
    @Step("Selecting Pet's Weight in Lbs")
    public void selectPetWeightLbs() throws InterruptedException {
        log.info("Selecting Pet's Weight");
            driver.clickLocator(petWeightInput);
        }
    @Step("Selecting Pet's Weight in Kgs")
    public void selectPetWeightKgs() throws InterruptedException {
        driver.clickLocator(petWeightInputKg);
    }

    @Step("Updating Pet's Weight")
    public void updatePetWeight(String sPetWeight) throws InterruptedException {
        log.info("Updating Pet's Weight");
        if(isAndroidBS()) {
            driver.findElement(petWeightInputField).clear();
            driver.findElement(petWeightInputField).sendKeys(sPetWeight);
        }else{
            driver.findElement(By.xpath("//XCUIElementTypePicker/XCUIElementTypePickerWheel")).sendKeys(sPetWeight);
        }
    }

    @Step("Click Save Pet's Weight")
    public void clickSaveInPetWeightPopUp() throws InterruptedException {
        log.info("Click on Save Weight Btn");
            driver.clickLocator(petSaveBtn);
    }

    @Step("Getting the current value of Pet's Weight")
    public String getPetWeightValue() {
        log.info("Getting the current value of Pet Weight");
        String sPetUpdatedWeight = driver.getText(petWeightValue,"Getting Pet's Weight value").split(" ")[0];
        log.info("Pet Current Weight: " +sPetUpdatedWeight);
        return sPetUpdatedWeight;
    }

    @Step("Verify if Pet's Save Button is enabled")
    public boolean isPetSaveBtnEnabled() {
        return driver.isElementEnabled(petSaveBtn);
    }

    @Step("Clicking on Pet Food field")
    public PetFoodPage clickPetFoodField() {
        driver.clickLocator(petFood);
        return new PetFoodPage(driver);

    }
}

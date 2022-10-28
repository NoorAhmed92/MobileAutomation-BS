package com.kinship.automation.pageobjects;

import com.kinship.automation.utils.commonutils.ByT;
import com.kinship.automation.utils.driver.TestDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class PetFoodCantFindPage extends WhistleBasePage {

    TestDriver driver;
    protected final Logger log = LogManager.getLogger(WhistleBasePage.class);

    private By navigateUpBackButton= ByT.locator(By.id("ChubbyBackArrow"), By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"));
    private By assetIcon= ByT.locator(By.id("foodBowl"), By.id("pet_food_not_found_icon"));
    private By assetHeader= ByT.locator(By.id("Suggest a Dog Food"), By.id("pet_food_not_found_header"));
    private By subCopy = ByT.locator(By.id("We’re building our dog food database and appreciate your input! Please include brand and flavor."), By.id("pet_food_not_found_details"));
    private By customFoodName = ByT.locator(By.xpath("//*[@placeholder='Custom food name']"), By.id("pet_food_not_found_edit_text"));
    private By petFoodCTAContinue= ByT.locator(By.id("nutrition - pet food suggestion continue button"),By.id("pet_food_not_found_continue_button"));

    public PetFoodCantFindPage(TestDriver driver) {
        this.driver=driver;
    }

    public boolean isBackButtonDisplayed() {
        return driver.isElementPresent(navigateUpBackButton);
    }

    public boolean isAssetIconDisplayed() {
        return driver.isElementPresent(assetIcon);
    }

    public boolean isAssetHeaderDisplayed() {
        return driver.isElementPresent(assetHeader);
    }

    public boolean isSubCopyDisplayed() {
        return driver.isElementPresent(subCopy);
    }

    public boolean isCustomFoodNameDisplayed() {
        return driver.isElementPresent(customFoodName);
    }

    public boolean isPetFoodContinueCTAEnabled() {
        return driver.isElementEnabled(petFoodCTAContinue);
    }

    public PetFoodPage navigateBackPagetoPetFoodPage()
    {
        driver.clickLocator(navigateUpBackButton);
        return  new PetFoodPage(driver);
    }

    public void enterPetFoodCantFindSearchBox(String food) {
        driver.type(customFoodName,food);
    }
}

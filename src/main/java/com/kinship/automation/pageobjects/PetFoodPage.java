package com.kinship.automation.pageobjects;

import com.kinship.automation.utils.commonutils.ByT;
import com.kinship.automation.utils.driver.TestDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class PetFoodPage extends WhistleBasePage {

    TestDriver driver;
    protected final Logger log = LogManager.getLogger(WhistleBasePage.class);

    private By navigateUpBackButton= ByT.locator(By.id("ChubbyBackArrow"), By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"));
    private By dogFoodText= ByT.locator(By.xpath("//*[@text='Dog Food']"), By.xpath("//android.widget.TextView[@text=\"Dog Food\"]"));
    private By cantFindFood= ByT.locator(By.id("nutrition - food not found button"), By.id("menu_item_pet_food_not_found"));
    private By petFoodHeader= ByT.locator(By.id("What food does he eat?"), By.id("pet_food_header"));
    private By petFoodField= ByT.locator(By.xpath("//*[@placeholder='Search dog foods']"), By.id("pet_food_field"));
    private By petFoodTextField = ByT.locator(By.xpath("//*[@placeholder='Search dog foods']"), By.id("textinput_placeholder"));
    private By petFoodListField= ByT.locator(By.xpath("//XCUIElementTypeTable[@class='UIATable']"), By.id("pet_food_list"));
    private By foodRemoveBtn= ByT.locator(By.xpath(""),By.xpath("//android.view.View[contains(@content-desc,'Remove')]"));
    private By petFoodCTAContinue= ByT.locator(By.id("nutrition - pet food continue button"),By.id("pet_food_continue_button"));

    public PetFoodPage(TestDriver driver) {
        this.driver=driver;
    }

    public PetEditProfilePage navigateBackPagetoPetPage()
    {
        driver.clickLocator(navigateUpBackButton);
        return  new PetEditProfilePage(driver);
    }

    public boolean isDogFoodDisplayed() {
        if(isAndroidBS())
        return driver.isElementPresent(dogFoodText);
        else
            return true;
    }

    public boolean isCantFindFoodDisplayed() {
        return driver.isElemneVisibleOnScreen(cantFindFood);
    }

    public boolean isPetFoodHeaderDisplayed() {
        return driver.isElementPresent(petFoodHeader);
    }
    public boolean isPetFoodFieldDisplayed() {
        return driver.isElementPresent(petFoodField);
    }
    public boolean isPetFoodTextInputFieldDisplayed() {
        return driver.isElementPresent(petFoodTextField);
    }

    public boolean isPetFoodListFieldDisplayed() {
        return driver.isElementPresent(petFoodListField);
    }

    public void clearfood() {
        driver.pauseExecutionFor(3);
        driver.clickLocator(foodRemoveBtn);
    }

    public boolean isPetFoodContinueCTADisabled() {
        return driver.isElementEnabled(petFoodCTAContinue);
    }

    public PetFoodCantFindPage clickPetFoodCantFind() {
        driver.clickLocator(cantFindFood);
        return new PetFoodCantFindPage(driver);
    }
}

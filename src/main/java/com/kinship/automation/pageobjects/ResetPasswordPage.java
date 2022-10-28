package com.kinship.automation.pageobjects;

import com.kinship.automation.utils.commonutils.ByT;
import com.kinship.automation.utils.driver.TestDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.io.IOException;

public class ResetPasswordPage  extends WhistleBasePage {

	TestDriver driver;

	private By emailAddress= ByT.locator(By.name("reset password - email text field"),By.id("sign_in_email_field"));
	private By emailTextInput= ByT.locator(By.id("reset password - email text field"),By.id("reset_password_email_field"));
	private By resetPasswordBtn= ByT.locator(By.id("reset password - reset password button"), By.id("reset_password_reset_password_btn"));
	private By toastMessageText= ByT.locator(By.xpath("//*[@text='There was a problem resetting your password, please try again.\\n  (422)']"), By.xpath("//android.widget.Toast[1]"));
	private By clickOKBtn= ByT.locator(By.id("OK"), By.xpath(""));

	public ResetPasswordPage(TestDriver driver){
		super(driver);
		this.driver=driver;
	}

	@Step("Enter email")
	public ResetPasswordPage enterEmail(String email) {
		driver.isElementPresent(emailAddress);
		driver.clickLocator(emailTextInput);
		driver.type(emailTextInput, email);
		return this;
	}

	@Step("Get Email from API Test")
	public ResetPasswordPage getEmailFromAPITest() throws IOException {
		driver.isElementPresent(emailAddress);
		driver.clickLocator(emailTextInput);
		getAuthorizationToken();
		driver.type(emailTextInput, WhistleBasePage.emailAddress);
		return this;
	}

	@Step("Verify if reset Password button is Enabled")
	public boolean isResetPasswordBtnEnabled() {
		driver.pauseExecutionFor(2);
		return driver.isElementEnabled(resetPasswordBtn);
	}

	@Step("Verify if reset Password is displayed")
	public boolean isResetPasswordBtnDisplayed() {
		driver.pauseExecutionFor(2);
		return driver.isElementPresent(resetPasswordBtn);
	}

	@Step("Click reset Password Button")
	public SignInPage clickResetPasswordBtn() {
		driver.clickLocator(resetPasswordBtn);
		return new SignInPage(driver);
	}

	@Step ("Getting Reset Password Toast message")
	public String getToastMessage() throws InterruptedException {
		driver.pauseExecutionFor(2);
		String ele = driver.getText(toastMessageText,"Get the Reset Password toast Message");
		return ele;
	}

	@Step ("Verify Toast message display")
	public boolean isToastMsgDisplayed() {
		driver.pauseExecutionFor(2);
		return driver.isElementPresent(toastMessageText);
	}

	@Step ("Verify Toast message display")
	public ResetPasswordPage clickOKBtn() {
		driver.pauseExecutionFor(2);
		driver.clickLocator(clickOKBtn);
		return this;
	}
}
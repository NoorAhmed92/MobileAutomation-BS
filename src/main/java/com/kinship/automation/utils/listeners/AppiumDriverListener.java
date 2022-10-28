package com.kinship.automation.utils.listeners;

import com.kinship.automation.utils.driver.DriverFactory;
import com.kinship.automation.utils.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.IOException;

public class AppiumDriverListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            AppiumDriver<MobileElement> driver = null;
            try {
                driver = (AppiumDriver<MobileElement>) DriverFactory.createInstance();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DriverManager.setDriver(driver);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            AppiumDriver<MobileElement> driver =(AppiumDriver<MobileElement>) DriverManager.getDriver();
            if (driver != null) {
                driver.quit();
            }
        }
    }
}

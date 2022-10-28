package com.kinship.automation.utils.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
    public static ThreadLocal<Boolean> isAndroid = new ThreadLocal<Boolean>();

    public static WebDriver getDriver() {
        return dr.get();

    }

    public static void setDriver(WebDriver driver) {
        dr.set(driver);
    }

}

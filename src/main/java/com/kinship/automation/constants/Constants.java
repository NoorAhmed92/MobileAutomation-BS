package com.kinship.automation.constants;

import java.util.HashMap;

public class Constants {
    public static String testRailUserName = "";
    public static String testRailPassword = "";
    public static String platform = "";
    public static String browser = "";
    public static String baseURLAPI = "";
    public static String deviceType = "real";
    public static String testrailUrl= "https://whistle.testrail.com/";
    public static String testrailProjectId= "14";
    public static String updateTestRailFlag = "";
    public static String existingRunId = "";
    public static String createNewTestRun = "";
    public static String slackWebhookUrl = "";
    public static HashMap<String, String> testCaseIds = new HashMap<String, String>();
    public static Long newRunId;
    public static String APPIUM_SERVER_URL = "http://localhost:4723/wd/hub";
    public static String BS_SERVER_URL = "http://hub-cloud.browserstack.com/wd/hub";
}

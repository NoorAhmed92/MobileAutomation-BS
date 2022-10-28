package com.kinship.automation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;
import com.kinship.automation.constants.Constants;
import com.kinship.automation.pageobjects.*;
import com.kinship.automation.utils.allure.AllureUtils;
import com.kinship.automation.utils.commonutils.Utils;
import com.kinship.automation.utils.driver.DriverFactory;
import com.kinship.automation.utils.driver.DriverManager;
import com.kinship.automation.utils.driver.TestDriver;
import com.kinship.automation.utils.slack.SlackUtils;
import com.kinship.automation.utils.testrail.APIException;
import com.kinship.automation.utils.testrail.TestRail;
import com.kinship.automation.utils.testrail.TestRails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;

public class BaseTest {
    protected final Logger log = LogManager.getLogger(BaseTest.class);
    public static String testCaseId;
    static int pass = 0;
    static int fail = 0;
    static int skip = 0;
    public WebDriver driver = null;
    protected TestDriver testDriver;
    protected ExtentReports extent;
    protected ExtentTest test;
    protected Faker faker;
    protected TestRail testRail;
    protected WhistleBasePage whistleBasePage;
    ReentrantLock lock = new ReentrantLock();
    long lTestStartTime;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext testContext) throws IOException, APIException {
        log.info("Before Suite initiated");
        //get variables form pom
        getProperties();
        DriverFactory.setPlatform();
        AllureUtils.copyAllureHistory();
        //Create a new test run if specified else update the results to existing test run
        if (Constants.updateTestRailFlag.equals("true")) {
            if (Constants.createNewTestRun.equals("true"))
                TestRail.createTestRunSuiteId(testContext);
            else {
                testContext.setAttribute("suiteId", Long.valueOf(Constants.existingRunId));
            }
        }
        if(Constants.platform.equalsIgnoreCase("api")) {
            WhistleBasePage.getAuthorizationToken();
            WhistleBasePage.getPetID();
        }
        lTestStartTime = System.currentTimeMillis();
        log.info("Current Date and Time before initiating Suite: " + lTestStartTime);
    }

    @BeforeMethod
    public void beforeMethod(Method method, final ITestContext testContext) throws Exception {
        log.info("Before Method initiated");
        driver = DriverFactory.createInstance();
        DriverManager.setDriver(driver);
        testDriver = new TestDriver(DriverManager.getDriver());
        whistleBasePage = new WhistleBasePage(testDriver);
        faker = new Faker();
    }

    @AfterMethod
    public void afterMethod(Method method, ITestResult result, ITestContext testContext) throws Exception {
        log.info("After Method initiated");
        lock.lock();
        try {
            int status = 1;
            String message = "Test case passed via automation";

            if (result.getStatus() == ITestResult.SUCCESS) {
                status = 1;
                pass++;
            } else if (result.getStatus() == ITestResult.SKIP) {
                status = 6;
                skip++;
            } else {
                message = result.getThrowable().toString();
                status = 5;
                fail++;
            }
            testCaseId = method.getAnnotation(TestRails.class).id();
            Constants.testCaseIds.put(testCaseId, status + "|" + message);
        } finally {
            lock.unlock();
        }

        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterTest
    public void cleanUp() {
        log.info("After Test initiated");
    }

    @AfterClass
    public void tearDown() {
        log.info("After Class initiated");
    }

    @AfterSuite
    public void resultSummary() throws Exception {
        long endTime = System.currentTimeMillis();
        log.info("End Date and Time after running Suite: " +endTime);
        String timeTaken = Utils.getTimeDifference(endTime, lTestStartTime);
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
        }
        TestRail.addResultsInTestRail(Constants.testCaseIds);
        SlackUtils.sendMessage("KinshipAutomation", "Automation Execution Summary: " + pass +
                " passed | " + fail + " failed | " + skip + " skipped." + timeTaken +
                "\n" + "Automation Suite ran From " + System.getProperty("user.name") +
                "\n" + "Web / Mobile (BrowserStack-Android / BrowserStack-iOS) / API = " + Constants.platform.toUpperCase() +
                "\n" + "Click on the link to view the report :  https://whistlelabs.github.io/kinship_qa_auto/");
        AllureUtils.generateReport();
    }

    private void getProperties() throws IOException {
        java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("my.properties");
        java.util.Properties properties = new Properties();
        properties.load(inputStream);
        log.info("setting properties in constant file");
        Constants.testRailUserName = properties.getProperty("testRailUserName");
        Constants.testRailPassword = properties.getProperty("testRailPassword");
        Constants.updateTestRailFlag = properties.getProperty("testrailExecution");
        Constants.createNewTestRun = properties.getProperty("createNewTestRun");
        Constants.existingRunId = properties.getProperty("existingTestRunId");
        Constants.platform = properties.getProperty("platform");
        Constants.browser = properties.getProperty("browser");
        Constants.baseURLAPI = properties.getProperty("baseURLAPI");
        Constants.slackWebhookUrl = properties.getProperty("slackWebhookUrl");
    }
}
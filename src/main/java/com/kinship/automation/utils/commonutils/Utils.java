package com.kinship.automation.utils.commonutils;

import com.kinship.automation.testdata.APIResources;
import io.qameta.allure.Step;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

public class Utils {

	public static boolean isNull(String s) {
		return s == null || s.length() == 0 || s.equals("")|| s.equals("null");
	}
	@Step("Setting Values In Properties File")
	public static void setValuesInPropertiesFile(String fileName, String key, String value) {
		String filePath = "./src/test/resources/" + fileName + ".properties";
		try {
			PropertiesConfiguration log4jProperties = new PropertiesConfiguration(filePath);
			log4jProperties.setProperty(key, value);
			log4jProperties.save();
			System.out.println("Saving " + key + " value in " + fileName + " properties file : " +value);
		} catch (ConfigurationException e) {
			e.printStackTrace();
			Assert.fail("Error occurred while storing TestData in properties file");
		}
	}
	
	@Step("Getting Values From Properties File")
	public static String getValuesFromPropertiesFile(String fileName, String key) {
		Properties prop;
		String value = null;
		String filePath = "./src/test/resources/"+ fileName + ".properties";
		try {
			InputStream input = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(input);
			value = prop.getProperty(key);
			System.out.println("Getting " + key + " value from " + fileName + " properties file");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	@Step("Getting random integer")
	public static int getRandomInt(int iStart, int iEnd) {
		Random random=new Random();
		return random.nextInt(iEnd - iStart + 1) + iStart;
	}

	@Step("Time taken in running Test Suite")
	public static String getTimeDifference(long endTime, long currentstartTime) {
		long diff=endTime-currentstartTime;
		long minutes = (diff / 1000) / 60;
		long seconds = (diff / 1000) % 60;
		String timeTaken = "Time taken in running TestSuite : " +diff + " Milliseconds = " + minutes + " minutes and " + seconds + " seconds.";
		return timeTaken ;
	}	

	@Step("Assert actual and expected integer values")
	public static void assertOutput(int actual, int expected, APIResources resourceAPI) {
		Assert.assertEquals(actual, expected, "Validate status code for " +resourceAPI);	
	}

	@Step("Assert actual and expected String values")
	public static void assertOutput(String actual, String expected, APIResources resourceAPI) {
		Assert.assertEquals(actual, expected, "Validate actual and expected values for " +resourceAPI);	
	}

	@Step("Assert that actual values should not match with expected values ")
	public static void assertOutputNotEquals(String actual, String expected) {
		Assert.assertNotEquals(actual, expected);	
		 }
	
	@Step("Assert actual and expected double values ")
	public static void assertOutputEquals(double actual, double expected) {
		Assert.assertEquals(actual, expected);
		}
	
	@Step("Verifying Integer value")
	public static boolean verifyInteger(String str, int n)
	{
		for (int i = 0; i < n; i++) {
			if (str.charAt(i) >= '0'
					&& str.charAt(i) <= '9') {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	@Step("Verifying given string contains only digits")
	public static boolean verifyStringHasOnlyDigits(String str) {
		boolean flag = false;
		if(str.matches("[0-9]+")) {
		flag= true;	
		}
		return flag;	
		}
}

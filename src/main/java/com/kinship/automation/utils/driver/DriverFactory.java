package com.kinship.automation.utils.driver;

import com.kinship.automation.constants.Constants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class DriverFactory {
	 	private static WebDriver driver;
	    private static AppiumServiceBuilder builder = null;
	    public static String platfrom;//android, iOS, web, api
	    private static String deviceType;//real, simulator
	    private static String target;
	    private static DesiredCapabilities capability = new DesiredCapabilities();
	    static DesiredCapabilities moreCap = new DesiredCapabilities();
	    static DesiredCapabilities moreCap1 = new DesiredCapabilities();
	    static String operatingSystem = System.getProperty("os.name");

	    public static void setPlatform() throws IOException {
	    	DriverFactory.platfrom = Constants.platform;
	    	DriverFactory.target = Constants.browser;
	    	DriverFactory.deviceType = Constants.deviceType;
	    }

    @SuppressWarnings("unchecked")
	public static WebDriver createInstance() throws IOException {

        if(!target.equalsIgnoreCase("web")) {
            builder = new AppiumServiceBuilder();
            builder.withIPAddress("127.0.0.1");
            builder.usingAnyFreePort();
            builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
            builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        }
        capability= new DesiredCapabilities();
        setCapabilities();
        
        if(platfrom.equalsIgnoreCase("android")){
            builder.withCapabilities(capability);
            AppiumDriverLocalService server = AppiumDriverLocalService.buildService(builder);
            server.start();
            String appiumServiceUrl = server.getUrl().toString();
            driver= new AndroidDriver<MobileElement>(new URL(appiumServiceUrl),capability);
        }
        else if(platfrom.equalsIgnoreCase("ios")) {
                builder.withCapabilities(capability);
                AppiumDriverLocalService server = AppiumDriverLocalService.buildService(builder);
                server.start();
                driver = new IOSDriver<>(new URL(Constants.APPIUM_SERVER_URL), capability);
        }
        else if (platfrom.equalsIgnoreCase("bs-android")) {
            driver = new AndroidDriver<>(new URL(Constants.BS_SERVER_URL), capability);
        }
        else if (platfrom.equalsIgnoreCase("bs-ios")) {
            driver = new IOSDriver<>(new URL(Constants.BS_SERVER_URL), capability);
        }
        else if(platfrom.equalsIgnoreCase("api")){
            return null;
        }
        else {
        	setDriver();
        	driver.manage().window().setSize(new Dimension(2072, 1220));
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        
        if(!platfrom.equalsIgnoreCase("web"))
        	DriverManager.setDriver((AppiumDriver<MobileElement>)driver);

        return driver;
    }

    private static void setDriver() {
    	switch(DriverFactory.target) {
    	case "chrome":{
    		if(operatingSystem.contains("Windows"))
    			System.setProperty("webdriver.chrome.driver", "src/test/resources/config/chromedriver.exe");
    		else if(operatingSystem.contains("Mac"))
    			System.setProperty("webdriver.chrome.driver", "src/test/resources/config/chromedriver_mac");
    		else
    			System.setProperty("webdriver.chrome.driver", "src/test/resources/config/chromedriver_linux");

//    		driver = new ChromeDriver();
//            WebDriverManager.chromedriver().setup();  		
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
    		break;
    	}
    	case "firefox":{
    		if(operatingSystem.contains("Windows"))
    			System.setProperty("webdriver.gecko.driver", "src/test/resources/config/geckodriver.exe");
    		else if(operatingSystem.contains("Mac"))
    			System.setProperty("webdriver.gecko.driver", "src/test/resources/config/geckodriver_mac");
    		else
    			System.setProperty("webdriver.gecko.driver", "src/test/resources/config/geckodriver_linux");

//    		driver = new FirefoxDriver();
    		FirefoxOptions options = new FirefoxOptions();
    		options.addArguments("--headless");
    		driver = new FirefoxDriver(options);
    		break;
    	}
    	default:{

    	}
    	}
    }

    private static void setCapabilities() { 	
    	//Read the capabilities from file and set them
    	Map<String, Object> capabilities = readCapabilitiesFromJson();
    	int counter=0;
    	//Add capabilities to desired capabilities
    	for (Map.Entry<String, Object> entry : capabilities.entrySet()) {
    	    if(counter>0)
    		    capability.setCapability(entry.getKey(), entry.getValue());
    	    counter++;
		}
    	
    }
    
    /*
     * platform : ios / Android
     * target : mWeb / Native app
     * deviceType : real device / simulator
     * */
    private static  Map<String, Object> readCapabilitiesFromJson() {
          Map<String, Object>  capMap = new HashMap<String, Object>();
          //JSON parser object to parse read file
          JSONParser jsonParser = new JSONParser();
          JSONObject jsonObj = new JSONObject();
          Object obj = null;
          try (FileReader reader = new FileReader(System.getProperty("user.dir")+"/src/test/resources/config/capability.json")) {
              //Read JSON file
              obj = jsonParser.parse(reader);
              jsonObj = (JSONObject) obj;

              if(!jsonObj.isEmpty()) {
                  capMap = toMap(jsonObj);
              }
              capMap.forEach((K,V)->{
              });
          }
          catch(Exception e) {e.printStackTrace();}
          return capMap;
    }
    
    public static Map<String, Object> toMap(JSONObject object){
    	 Map<String, Object> map = new HashMap<String, Object>();
         Iterator<String> keysItr = object.keySet().iterator();

         while(keysItr.hasNext()) {
             String key = keysItr.next();
             Object value = object.get(key);
             if(value instanceof JSONObject) {
                 if(key.equalsIgnoreCase(platfrom)||key.equalsIgnoreCase(deviceType)|| key.equalsIgnoreCase(target)){
                     value = toMap((JSONObject) value);
                 }
                 else
                     continue;}
             map.put(key, value);
             if(key.contains(target)){
                 Iterator<String>itr = ((HashMap)value).keySet().iterator();
                 while(itr.hasNext()) {
                     String nkey = itr.next();
                     Object nvalue = object.get(nkey);
                     capability.setCapability(nkey, nvalue);
                 }
             }
             if(key.contains(deviceType)){
                 Iterator<String>itr = ((HashMap)value).keySet().iterator();
                 while(itr.hasNext()) {
                     String nkey = itr.next();
                     Object nvalue = object.get(nkey);
                     moreCap.setCapability(nkey, nvalue);
                 }
             }
             else
                 if(!key.equalsIgnoreCase(platfrom))
                     moreCap1.setCapability(key, value);
         }
         capability.merge(moreCap).merge(moreCap1);
         return map;
    }

  
}

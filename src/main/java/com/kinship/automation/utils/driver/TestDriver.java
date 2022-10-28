package com.kinship.automation.utils.driver;

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class TestDriver {

    private WebDriver driver;
    private WebDriverWait wait;
    private static Duration SCROLL_DUR = Duration.ofMillis(1000);
    private static double SCROLL_RATIO = 0.8;
    private static int ANDROID_SCROLL_DIVISOR = 3;
    private Dimension windowSize;
    private long explicitWait = 10;
    private static String operatingSystem = System.getProperty("os.name");

    public TestDriver (WebDriver driver)
    {
        this.driver = driver;
    }

    private WebElement _findElement(By by){
        return this.driver.findElement(by);
    }

    public void clickLocator(By by, int timeOutInSecods){
        wait = new WebDriverWait(this.driver,timeOutInSecods);
        wait.until(elementFoundAndClicked(by));
    }

    public void clickLocator(By by){
//        sleepFor(500);
//        wait = new WebDriverWait(this.driver,cfg.getExplicitWait());
//        wait.until(elementFoundAndClicked(by));
    	clickLocator(by, by.toString());
    }

    public List findElements(By by) {
        return driver.findElements(by);
    }

    public WebElement waitForElementToBeClickable(final By by, int timeOutInSeconds) {
        WebElement element=new WebDriverWait(this.driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(by));
        return element;
    }

    public WebElement waitForElementToBeClickable(final By by) {
        WebElement element=new WebDriverWait(this.driver, explicitWait)
                .until(ExpectedConditions.elementToBeClickable(by));
        return element;
    }

    public void waitforElementStaleness(final WebElement element, int timeOutInSeconds) {
        new WebDriverWait(this.driver, timeOutInSeconds)
                .until(ExpectedConditions.stalenessOf(element));
    }

    public void waitforElementStaleness(final WebElement element) {
        new WebDriverWait(this.driver, explicitWait)
                .until(ExpectedConditions.stalenessOf(element));
    }


    public WebElement waitForElementToBePresent(final By by, int timeOutInSeconds) {
        WebElement element= new WebDriverWait(this.driver, timeOutInSeconds)
                .until(ExpectedConditions.presenceOfElementLocated(by));
        return element;
    }

    public WebElement waitForElementToBePresent(final By by) {
        WebElement element=  new WebDriverWait(driver, explicitWait)
                .until(ExpectedConditions.presenceOfElementLocated(by));
        return element;
    }


    public WebElement waitForElementToBeVisible(final By by, int timeOutInSeconds) {
        WebElement element= new WebDriverWait(this.driver, timeOutInSeconds)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
        return element;
    }

    public WebElement waitForElementToBeVisible(final By by) {
        WebElement element=  new WebDriverWait(driver, explicitWait)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
        return element;
    }


    public void waitForElementToBeInvisible(final By locator, int timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementToBeInvisible(final By locator) {
        new WebDriverWait(driver, explicitWait)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void type(final By by, String text){
        WebElement element= waitForElementToBeVisible(by);
        element.clear();
        element.sendKeys(text);
    }

    public void clickByCoordinates(final By by) {
        WebElement element= waitForElementToBePresent(by);
        TouchAction touchAction = new TouchAction((AppiumDriver<MobileElement>)this.driver);
        touchAction.tap(PointOption.point(element.getLocation().getX(),element.getLocation().getY())).release().perform();
    }

    public String getAttribute(By by, String attribute, String desc) {
        String text="";
        try {
            text = _findElement(by).getAttribute(attribute);
        }catch(Exception e) {}
        return text;
    }

    public String getText(By by, String desc) {
    	String text="";
    	try {
    	    text =_findElement(by).getText();
    	}
    	catch(Exception e) {}
        return text;
    }


    public void swipeFromLeft(By by, double differenceX, String plusOrMinus) {
        WebElement element = _findElement(by);
        TouchAction touchAction = new TouchAction((AppiumDriver<MobileElement>)this.driver);
        int startX = element.getLocation().getX();
        Integer endX = plusOrMinus.equalsIgnoreCase("+") ? startX + (int) differenceX : startX - (int) differenceX;

        touchAction.press(PointOption.point(startX, element.getLocation().getY())).waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                .moveTo(PointOption.point(endX, element.getLocation().getY())).release().perform();

        //touchAction.press(startX, element.getLocation().getY()).waitAction(Duration.ofMillis(500))
        //.moveTo(endX, element.getLocation().getY()).release().perform();
//        Logger.info(
//                "Left swiping done from X-Coordinate: " + Integer.toString(startX) + " to " + Integer.toString(endX));
//    }
    }

    public void dismissAlert() {
        this.driver.switchTo().alert().dismiss();
    }

    private Dimension getWindowSize() {
        if (windowSize == null) {
            windowSize = this.driver.manage().window().getSize();
        }
        return windowSize;
    }

    protected void swipe(Point start, Point end, Duration duration) {
        AppiumDriver<MobileElement> d =(AppiumDriver<MobileElement>) this.driver;
        boolean isAndroid = d instanceof AndroidDriver<?>;

        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        if (isAndroid) {
            duration = duration.dividedBy(ANDROID_SCROLL_DIVISOR);
        } else {
            swipe.addAction(new Pause(input, duration));
            duration = Duration.ZERO;
        }
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        d.perform(ImmutableList.of(swipe));
    }

    protected void swipe(double startXPct, double startYPct, double endXPct, double endYPct, Duration duration) {
        Dimension size = getWindowSize();
        Point start = new Point((int)(size.width * startXPct), (int)(size.height * startYPct));
        Point end = new Point((int)(size.width * endXPct), (int)(size.height * endYPct));
        swipe(start, end, duration);
    }

    public WebElement findElementById(By by) {
        WebElement element= driver.findElement(by);
        return element;
    }

    public enum ScrollDirection {
        UP, DOWN, LEFT, RIGHT
    }

    public void scroll(ScrollDirection dir, double distance) {
        if (distance < 0 || distance > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }
        Dimension size = getWindowSize();
        Point midPoint = new Point((int)(size.width * 0.5), (int)(size.height * 0.5));
        int top = midPoint.y - (int)((size.height * distance) * 0.5);
        int bottom = midPoint.y + (int)((size.height * distance) * 0.5);
        int left = midPoint.x - (int)((size.width * distance) * 0.5);
        int right = midPoint.x + (int)((size.width * distance) * 0.5);
        if (dir == ScrollDirection.UP) {
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
        } else if (dir == ScrollDirection.DOWN) {
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
        } else if (dir == ScrollDirection.LEFT) {
            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);
        } else {
            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);
        }
    }

    public void scroll(ScrollDirection dir) {
        scroll(dir, SCROLL_RATIO);
    }

    public void scroll() {
        scroll(ScrollDirection.DOWN, SCROLL_RATIO);
    }

    private ExpectedCondition<Boolean> elementFoundAndClicked(By locator) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                WebElement el = driver.findElement(locator);
                el.click();
                return true;
            }
        };
    }

    public void scrollAndClick(String visibleText) {
        AndroidDriver driver2 = (AndroidDriver)this.driver;
        driver2.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))").click();
    }


//    public void scrollToMobileElement(String elementName, String direction) {
//        final int maximumScrolls = 5;
//        for (int i = 0; i < maximumScrolls; i++) {
//            try {
//                if (findElementsByPredicateString("label CONTAINS \"" + elementName + "\"").size() > 0)
//                    // PredicateString & label is the locator strategy that I used. It can be changed to others as needed for your app.
//                    break;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            scroll(direction);
//        }
//    }

//    private void scroll(String direction) {
//        final HashMap<String, String> scrollObject = new HashMap<String, String>();
//        scrollObject.put("direction", direction);
//        driver.executeScript("mobile:scroll", scrollObject);
//    }
//
//    public List<MobileElement> findElementsByPredicateString(String predicateString) {
//        return driver.findElements(MobileBy.iOSNsPredicateString(predicateString));
//    }


    public  void scrollToText(AppiumDriver<MobileElement> driver, String text) {
        AndroidDriver driver2 = (AndroidDriver)this.driver;
        MobileElement el = (MobileElement) driver2.findElementByAndroidUIAutomator("new UiScrollable("
                + "new UiSelector().scrollable(true)).scrollIntoView(" + "new UiSelector().text(\"" + text + "\"));");
    }

    public  void scrollToId(AppiumDriver<MobileElement> driver, String id) {
        AndroidDriver driver2 = (AndroidDriver)this.driver;
        MobileElement el = (MobileElement) driver2.findElementByAndroidUIAutomator(
                "new UiScrollable(" + "new UiSelector().scrollable(true)).scrollIntoView("
                        + "new UiSelector().resourceIdMatches(\"" + id + "\"));");
    }

    public void scrollPageUPOrDown(ScrollDirection direction, int num, int end){
        try {
            Dimension dimensions = this.driver.manage().window().getSize();
//            Double screenHeightStart = dimensions.getHeight() * start;
//            int scrollStart = screenHeightStart.intValue();
//            Double screenHeightEnd = dimensions.getHeight() * end;
//            int scrollEnd = screenHeightEnd.intValue();
            int scrollStart = dimensions.getHeight() / 2;
            int scrollEnd;
            if(direction == ScrollDirection.DOWN)
            	scrollEnd = (dimensions.getHeight() /2 ) + end;
            else
            	scrollEnd = (dimensions.getHeight() / 2 ) - end;
            
            TouchAction swipe = new TouchAction<>((AppiumDriver<MobileElement>)this.driver);
            for (int i = 0; i < num; i++) {
                //logger.info("Swiping page in " + direction + " direction for '" + (i + 1) + "' times from offset '" + Integer.toString(scrollStart) + "' to offset '" + Integer.toString(scrollEnd) + "'");
                // getDriver().swipe(0, scrollStart, 0, scrollEnd, 1000);
                swipe.longPress(PointOption.point(0, scrollStart)).waitAction(new WaitOptions().withDuration(Duration.ofMillis(350))).moveTo(PointOption.point(0, scrollEnd)).release().perform();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void scrollToLocator(By locator) throws Exception {
//        for (int i = 0; i < 4; i++) {
//            if (isElementPresent(locator)) {
//                break;
//            } else {
//                scrollPageUPOrDown(DOWN, 1, 0.5, 0.7, 1000);
//            }
//        }
//        for (int i = 0; i < 5; i++) {
//            if (isElementPresent(locator)) {
//                break;
//            } else {
//                scrollPageUPOrDown(UP, 1, 0.5, 0.3, 1000);
//            }
//        }
//    }

    public boolean isElementPresent(By by){
        boolean isPresent=false;
        try{
           
            isPresent= (this.findElements(by).size() > 0) ? true:false;
        }catch(Exception e){
            e.printStackTrace();
        }
        return isPresent;
    }

    public void pauseExecutionFor(int timeInSeconds){
        Uninterruptibles.sleepUninterruptibly(timeInSeconds, TimeUnit.SECONDS);
    }

    public void sleepFor(int timeInMilliSeconds){
        Uninterruptibles.sleepUninterruptibly(timeInMilliSeconds, TimeUnit.MILLISECONDS);
    }

    public boolean ScrollToElement(By by) {
        int numberOfTimes = 10;
        final int ANIMATION_TIME = 400; // ms
        final int PRESS_TIME = 200; // ms
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width / 2);
        // Swipe up to scroll down
        int startPoint = (int) (size.height - 10);
        int endPoint = 150;
        int height = size.height-200;
        int reduceBy = 100;
        PointOption pointOptionStart, pointOptionEnd;
        pointOptionStart = PointOption.point(size.width / 2,height);
        pointOptionEnd = PointOption.point(size.width / 2, endPoint);

        for (int i = 0; i < numberOfTimes; i++) {
            try {
                if(this._findElement(by).isDisplayed())
                    return true;
                    System.out.println(String.format("Element not available. Scrolling (%s) times...", i + 1));
                    new TouchAction((AppiumDriver<MobileElement>)driver)
                            .longPress(pointOptionStart)
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                            .moveTo(pointOptionEnd)
                            .release()
                            .perform();
                    try {
                        Thread.sleep(ANIMATION_TIME);
                    } catch (InterruptedException e) {
                        System.out.println("scrolling was not performed");
                    }
                    pointOptionStart = PointOption.point(size.width / 2,height-reduceBy);
                    reduceBy = reduceBy+100;
            } catch (NoSuchElementException ex) {
                System.out.println(String.format("Element not available. Scrolling (%s) times...", i + 1));
                new TouchAction((AppiumDriver<MobileElement>)driver)
                .longPress(pointOptionStart)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release()
                .perform();
	    try {
	        Thread.sleep(ANIMATION_TIME);
	    } catch (InterruptedException e) {
	       System.out.println("scrolling was not performed");
	    }
                pointOptionStart = PointOption.point(size.width / 2,height-reduceBy);
                reduceBy = reduceBy+50;
            }
        }
        return false;
    }

    public void  scrollTo(int x1, int y1, int x2, int y2) {
        TouchAction touchAction = new TouchAction(((AppiumDriver<MobileElement>)this.driver));
        touchAction.press(PointOption.point(x1, y1)).waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                .moveTo(PointOption.point(x2, (y2))).release().perform();
    }

    public void swipe(ScrollDirection direction){
        pauseExecutionFor(2);
    	final int ANIMATION_TIME = 200; // ms
        final int PRESS_TIME = 200; // ms
        int edgeBorder = 10; // better avoid edges
        Dimension dims = driver.manage().window().getSize();
    	PointOption pointOptionStart, pointOptionEnd;
    	pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

    	switch (direction) {
        case DOWN: // center of footer
            pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
            break;
        case UP: // center of header
            pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
            break;
        case LEFT: // center of left side
            pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
            break;
        case RIGHT: // center of right side
            pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
            break;
        default:
            throw new IllegalArgumentException("swipeScreen(): dir: '" + direction + "' NOT supported");
    }
    	 try {
    	        new TouchAction((AppiumDriver<MobileElement>)driver)
    	                .press(pointOptionStart)
    	                // a bit more reliable when we add small wait
    	                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
    	                .moveTo(pointOptionEnd)
    	                .release().perform();
    	    } catch (Exception e) {
    	        System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
    	        return;
    	    }

    	    // always allow swipe action to complete
    	    try {
    	        Thread.sleep(ANIMATION_TIME);
    	    } catch (InterruptedException e) {
    	        // ignore
    	    }
    }

    @Attachment(value="Page screenshot", type="image/png")
    public String getScreenshot(String msg) throws IOException {
        String path="target/TestReport/Screenshots_"+ (msg.replace(" ", "_").trim()+".jpg");
        System.out.println(path);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(path));
        return path+scrFile;
    }
    
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }
    public WebElement findElementsById(final By by,int index) {
        WebElement element= driver.findElements(by).get(index);
        return element;
    }
    
    public void switchContext() {
        //"NATIVE_APP" , WEBVIEW_chrome, WEBVIEW_com.gspann.torrid
        Set<String> contextNames = ((AppiumDriver<MobileElement>)driver).getContextHandles();
        String[] arr = new String[contextNames.size()];
        contextNames.toArray(arr);
        for (String c_arr : arr) {
        	if(c_arr.trim().equalsIgnoreCase(""))
        		((AppiumDriver<MobileElement>)driver).context("WEBVIEW_Terrace");
		}
    }

//    public void executeScript(String action){
//        driver.executeScript("mobile:performEditorAction", ImmutableMap.of("action", action)); //"Search",Done
//    }
//
//    public void hideKeyboard(){
//    	driver.hideKeyboard();
//    }
    
    public boolean isElemneVisibleOnScreen(By locator) {
        boolean isElementFound = false;
    	String nature= DriverFactory.platfrom;
        if(!isElementPresent(locator)) {
            swipe(ScrollDirection.DOWN);//moving it inside if, as its failing tests where screen is set to some point before finding element
        }
    	if(nature.equalsIgnoreCase("ios"))
    	{
    		if(isElementPresent(locator)) {
    			if(findElement(locator).isDisplayed()) {
    				isElementFound = true;
    			}
    		}
    		if(!isElementFound) {
    			isElementFound = ScrollToElement(locator);
    		}
    		
    	}
    	else {
            if (!isElementPresent(locator))
                isElementFound = ScrollToElement(locator);
            else
                isElementFound = true;
        }
    	return isElementFound;
    }

    public void clickByCoordinates(MobileElement element){
        TouchAction touchAction = new TouchAction((AppiumDriver<MobileElement>)this.driver);
        touchAction.tap(PointOption.point(element.getLocation().getX(),element.getLocation().getY())).release().perform();
    }

    public void selectAndClickiOS(String visibleText) {
        // find pickerWheel
        MobileElement pickerWheel =
                (MobileElement) driver.findElement(MobileBy.className("XCUIElementTypePickerWheel"));
        pickerWheel.sendKeys(visibleText);

    }
    
    
    public boolean scrollToElement(By scrollTo, By scrollTill, ScrollDirection direction, double distance, int trail) {
    	if (distance < 0 || distance > 1 || trail < 0) {
    		throw new Error("Scroll distance must be between 0 and 1 or trail > 0");
    	}
    	boolean isFound = false;

    	Dimension size = getWindowSize();
    	Point midPoint = new Point((int)(size.width * 0.5), (int)(size.height * 0.5));
    	int top = midPoint.y - (int)((midPoint.y * distance) );
    	int bottom = midPoint.y + (int)((midPoint.y * distance) );
    	int left = midPoint.x - (int)((midPoint.x * distance) );
    	int right = midPoint.x + (int)((midPoint.x * distance));
    	int starty, releasey;
    	int startx = midPoint.x, releasex =  midPoint.x;
    	if(direction == ScrollDirection.UP) {
    		starty = top;
    		releasey = bottom;
    	} else if (direction == ScrollDirection.DOWN) {
    		starty = bottom;
    		releasey = top;
    	} else {
    		starty = midPoint.y;
    		releasey = midPoint.y;
    		if(direction == ScrollDirection.LEFT) {
    			startx = left;
    			releasex = right;
    		} else if(direction == ScrollDirection.RIGHT) {
    			startx = right;
    			releasex = left;
    		}
    	}
    	boolean tillLast = false;
    	for(int i = 1; i <= trail;	i++) {
    		if(driver.findElements(scrollTo).size() > 0) {
    			if(driver.findElement(scrollTo).isDisplayed()) {
    				isFound = true;
    				break;
    			}
    		}
    		if(!tillLast) {
    			scrollTo(startx,starty, releasex, releasey);
    			if(driver.findElements(scrollTill).size() > 0) {
    				if(driver.findElement(scrollTill).isDisplayed()) {
    					i =  trail - 1;
    					tillLast = true;
    				}
    			}
    		}
    	}

    	return isFound;
    }

    public void clickLocator(By by, String desc){
        sleepFor(500);
        try {
        wait = new WebDriverWait(this.driver,explicitWait);
        wait.until(elementFoundAndClicked(by));
        }
        catch(Exception e) {
//        	Assert.fail("Failed due to : "+desc); //if click action fails no use proceeding further with the test case
        	
        }
    }
    
    public void navigateTo(String url) {
        System.out.println(url);
    	driver.navigate().to(url);
        System.out.println(driver.getTitle());

    }

    public void switchFrame(int index){
        driver.switchTo().frame(index);
    }

    public void tapAtPoint(Point point) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(input, 0);
        tap.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), point.x, point.y));
        tap.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(input, Duration.ofMillis(200)));
        tap.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        ((AppiumDriver<MobileElement>)this.driver).perform(ImmutableList.of(tap));
    }

    public void tapElementAt(By by, double xPct, double yPct) {
        WebElement el = _findElement(by);
        Rectangle elRect = el.getRect();
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        touchAction.tap(PointOption.point(
                elRect.x + (int)(elRect.getWidth() * xPct),
                elRect.y + (int)(elRect.getHeight() * yPct))).perform();
    }

	public void clearField(By field) {
		if(operatingSystem.contains("Mac")) 
			findElement(field).sendKeys(Keys.chord(Keys.COMMAND, "a"));
		else
			findElement(field).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		findElement(field).sendKeys(Keys.DELETE);
	}
	
    public void pressTab() {
   	  Keys.chord(Keys.TAB);
   }
    
    public boolean isElementEnabled(By by){
    	boolean flag = false;
    	waitForElementToBeVisible(by);
        flag = findElement(by).isEnabled();
        return flag;
    }

    public String getElementAttributeValue(By by, String attribute){
        String attributeValue = findElement(by).getAttribute(attribute);
        return attributeValue;
    }

    public boolean isDisplayedWait(By by, int iTimeOut) {
        boolean isDisplayed = false;

        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, iTimeOut);
            wait.until(ExpectedConditions.visibilityOf(findElement(by)));
            isDisplayed = true;
        } catch (Exception e) {
            isDisplayed = false;
        }
        return isDisplayed;
    }
}

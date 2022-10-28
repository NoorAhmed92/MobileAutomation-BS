package com.kinship.automation.utils.commonutils;

import com.kinship.automation.utils.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ByT extends By {
    private enum Strategy {CLASSNAME, CSSSELECTOR, ID, LINKTEXT, NAME, PARTIALLINKTEXT, TAGNAME, XPATH, JSLOCATOR};

    private String template;
    private Strategy strategy;

    private ByT(String t, Strategy s){
        template = t;
        strategy = s;
    }

    public static ByT className(String className){
        return new ByT(className, Strategy.CLASSNAME);
    }

    public static ByT cssSelector(String selector){
        return new ByT(selector, Strategy.CSSSELECTOR);
    }



    public static ByT id(String id){
        return new ByT(id, Strategy.ID);
    }

    public static ByT linkText(String linkText){
        return new ByT(linkText, Strategy.LINKTEXT);
    }

    public static ByT name(String name){
        return new ByT(name, Strategy.NAME);
    }

    public static ByT partialLinkText(String linkText){
        return new ByT(linkText, Strategy.PARTIALLINKTEXT);
    }

    public static ByT tagName(String tagName){
        return new ByT(tagName, Strategy.TAGNAME);
    }

    public static ByT xpath(String xpathExpression){
        return new ByT(xpathExpression, Strategy.XPATH);
    }

    public static ByT jsLocator(String jsLocator){
        return new ByT(jsLocator, Strategy.JSLOCATOR);
    }

    /**
     * Generate locator instance from template
     * @param args - arguments for template formatting (see {@link String#format(String, Object...))
     * @return - instance of By class
     */
    public By format(Object...args){
        String locator = String.format(template, args);
        switch(strategy){
            case CLASSNAME:
                return new ByClassName(locator);
            case CSSSELECTOR:
                return new ByCssSelector(locator);
            case ID:
                return new ById(locator);
            case LINKTEXT:
                return new ByLinkText(locator);
            case NAME:
                return new ByName(locator);
            case PARTIALLINKTEXT:
                return new ByPartialLinkText(locator);
            case TAGNAME:
                return new ByTagName(locator);
            case XPATH:
                return new ByXPath(locator);
            default:
                return null;
        }
    }

    @Override
    public List<WebElement> findElements(SearchContext context) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static By locator(By ios, By android) {
    	if( (DriverFactory.platfrom.equalsIgnoreCase("ios")) || (DriverFactory.platfrom.equalsIgnoreCase("bs-ios")))
    		return ios;
    	else
    		return android;
    	
    }
    
    public static String locator(String ios, String android) {
        if( (DriverFactory.platfrom.equalsIgnoreCase("ios")) || (DriverFactory.platfrom.equalsIgnoreCase("bs-ios")))
    		return ios;
    	else
    		return android;
    	
    }
    
    public static By locator(By ios, By android, By web) {
        if( (DriverFactory.platfrom.equalsIgnoreCase("ios")) || (DriverFactory.platfrom.equalsIgnoreCase("bs-ios")))
    		return ios;
    	else if( (DriverFactory.platfrom.equalsIgnoreCase("android")) || (DriverFactory.platfrom.equalsIgnoreCase("bs-android")))
    		return android;
    	else
    		return web;
    	
    }
    
    public static By locator(By locator) {
    		return locator;
    	
    }
}

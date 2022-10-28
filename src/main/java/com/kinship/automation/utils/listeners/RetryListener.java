package com.kinship.automation.utils.listeners;

import com.kinship.automation.utils.listeners.Retry;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by Hasmukh Patel on 15 Feb, 2022
 */

public class RetryListener implements IAnnotationTransformer {
    public void transform(ITestAnnotation annotation, Class testClass,
            Constructor testConstructor, Method testMethod) {
        IRetryAnalyzer retry = annotation.getRetryAnalyzer();
        if (retry == null) {
            annotation.setRetryAnalyzer(Retry.class);
        }
    }
}
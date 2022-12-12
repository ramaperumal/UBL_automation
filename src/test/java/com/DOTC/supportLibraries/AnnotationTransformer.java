package com.DOTC.supportLibraries;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformer implements IAnnotationTransformer {

	@SuppressWarnings("rawtypes")
	public void transform(ITestAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) {
		//annotation.setRetryAnalyzer(RetryAnalyzer.class);
		//new code start
		IRetryAnalyzer retry= annotation.getRetryAnalyzer();
		if(retry == null) {
			annotation.setRetryAnalyzer(RetryAnalyzer.class);
		}
		//new code end
	}
}
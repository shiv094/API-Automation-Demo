package com.apidemo.automation.framework.config;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.apidemo.automation.framework.logging.LoggerHelper;
import com.apidemo.automation.framework.utils.PropertyFileHelper;

import io.restassured.RestAssured;

public class BaseClass {
	public static final String BASE_URL = System.getProperty("url");
	private static final LoggerHelper LOG = new LoggerHelper(BaseClass.class);
	public static String accessToken;

	@BeforeSuite
	public void beforeSuite() {
		LOG.info("==================== Test suite started ====================");
		PropertyFileHelper.loadPropertyFile("config");
		accessToken = PropertyFileHelper.getProperty("access.token");
		RestAssured.baseURI = BASE_URL;
		LOG.info("Base URL : " + BASE_URL);
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		System.out.println();
		LOG.info("==================== " + method.getName() + " Test Case Started ====================");

	}

	@AfterMethod
	public void afterMethod(Method method) {
		LOG.info("==================== " + method.getName() + " Test Case Finished ====================");
		System.out.println();
	}

	@AfterSuite
	public void afterSuite() {
		LOG.info("==================== Test suite Finished ====================");
	}
}

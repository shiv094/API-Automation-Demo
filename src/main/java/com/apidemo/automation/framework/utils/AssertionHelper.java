package com.apidemo.automation.framework.utils;

import org.testng.Assert;

import com.apidemo.automation.framework.logging.LoggerHelper;

public class AssertionHelper {

	private static final LoggerHelper LOG = new LoggerHelper(AssertionHelper.class);

	public static void assertEquals(Object actual, Object expected, String failedMessage) {
		LOG.info("VERIFICATION :: Actual : " + actual + " | Expected : " + expected);
		Assert.assertEquals(actual, expected, failedMessage);
	}

}

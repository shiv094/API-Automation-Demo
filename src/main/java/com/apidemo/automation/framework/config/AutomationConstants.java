package com.apidemo.automation.framework.config;

import java.io.File;

public class AutomationConstants {

	public static final String USER_DIR = System.getProperty("user.dir");
	public static final String PATH_RESOURCES = "resources";
	public static final String PATH_PROPERTY_FILES = "propertyfiles";

	public static String getPropertyFileDir() {
		return USER_DIR + File.separator + PATH_RESOURCES + File.separator + PATH_PROPERTY_FILES + File.separator;
	}

}

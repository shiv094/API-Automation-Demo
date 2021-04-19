package com.apidemo.automation.framework.utils;

import java.io.FileInputStream;
import java.util.Properties;

import com.apidemo.automation.framework.config.AutomationConstants;

public class PropertyFileHelper {

	static Properties properties = new Properties();

	public static void loadPropertyFile(String propertyfile) {
		try {
			properties
					.load(new FileInputStream(AutomationConstants.getPropertyFileDir() + propertyfile + ".properties"));

		} catch (Exception e) {
			throw new IllegalStateException("Couldn't load the property");
		}
	}

	public static String getProperty(String propertyName) {
		return properties.getProperty(propertyName);
	}

}

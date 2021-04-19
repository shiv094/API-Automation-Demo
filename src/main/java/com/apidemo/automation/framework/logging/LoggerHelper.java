package com.apidemo.automation.framework.logging;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.apidemo.automation.framework.config.AutomationConstants;

public class LoggerHelper {

	private org.apache.log4j.Logger log;

	static {
		PropertyConfigurator.configure(AutomationConstants.getPropertyFileDir() + "log4j.properties");
	}

	public LoggerHelper(Class<?> clazz) {
		log = Logger.getLogger(clazz);
	}

	public void info(String message) {
		log.log(LoggerHelper.class.getCanonicalName(), Level.INFO, message, null);
	}

	public void error(String message) {
		log.log(LoggerHelper.class.getCanonicalName(), Level.ERROR, message, null);
	}

	public void warn(String message) {
		log.log(LoggerHelper.class.getCanonicalName(), Level.WARN, message, null);
	}

}

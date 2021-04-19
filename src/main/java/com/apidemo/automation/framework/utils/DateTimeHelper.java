package com.apidemo.automation.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHelper {

	public static String currentdate(String dateFormat) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		return (formatter.format(date));
	}

}

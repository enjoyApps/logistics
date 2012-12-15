package com.example.logistics_ui.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhenggangji
 *
 */
public class DateUtil {
	
	public static final String yyyyMMddFormat = "yyyy-MM-dd";
	
	public static String getFormatDateTime(Date date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
}

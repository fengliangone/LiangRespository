package com.ailibaba.crm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
	/**
	 * 转换当前时间
	 * @return
	 */
	public static String getSysTime(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date = new Date();
		String dateStr = sdf.format(date);
		
		return dateStr;
		
	}
	
}

package com.csit.javaweb_02.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
public static String getString(Date date){
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd HH:MM:ss");
	return sf.format(date);
}
}

package it.unibz.mysasa.util;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Tools {

	public static String getFormatMin(String c){
		String ret = "";
		if(c!=null){
			ret = c+"'";
		}
		return ret;
	}
	
	public static String getFormatSec(String c){
		String ret = "";
		if(c!=null){
			Integer t = getInt(c);
			if(t>60){
				ret += t/60+"' ";
				t = t%60;
			}
			ret += t+"''";
		}
		return ret;
	}
	
	public static Integer getHour(String t){
		Integer ret = null;
		if(t!=null){
			t = t.substring(0, t.indexOf(":"));
			ret = getInt(t);
		}		
		return ret;
	}
	
	public static Integer getHourNow(){
		GregorianCalendar c = new GregorianCalendar();
		return c.get(Calendar.HOUR_OF_DAY);
	}
	
	public static Integer getInt(String val) {
		Integer ret = null;
		try {
			ret = Integer.valueOf(val.trim());
		} catch (Exception e) {
			e.printStackTrace();
			ret = null;
		}
		return ret;
	}

	public static Long getLong(String val) {
		Long ret = null;
		try {
			ret = Long.valueOf(val.trim());
		} catch (Exception e) {
			ret = null;
		}
		return ret;
	}

	public static Double getDouble(String val) {
		Double ret = null;
		try {
			ret = Double.valueOf(val.trim());
		} catch (Exception e) {
			ret = null;
		}
		return ret;
	}

	public static long day = 86400000; // 1000*60*60*24

	public static Timestamp getToday() {
		long t = System.currentTimeMillis() - System.currentTimeMillis() % day;
		return new Timestamp(t);
	}

	public static Timestamp getYesterday() {
		return new Timestamp(getToday().getTime() - day);
	}

	public static Timestamp getPast(int ndays) {
		return new Timestamp(getToday().getTime() - ndays * day);
	}

	public static Timestamp getTS(String gps_date) {
		Timestamp ret = null;
		try {
			//2015-01-25 18:01:58+01
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSX");
			Date d = sdf.parse(gps_date);
			ret = new Timestamp(d.getTime());
		} catch (ParseException e) { ; }
		return ret;
	}

}

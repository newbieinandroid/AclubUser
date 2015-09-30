/**
 * 
 */
package aclub.users.android.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class DateUtils {
	
	public static Date getDateBeforeTime(Date date, int time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -time); // 2 weeks
		return calendar.getTime();
	}

	public static Date getDateFromString(String str) {
		Date theDate = null;
		try {
			theDate = new SimpleDateFormat("yyyy MM dd", Locale.ENGLISH)
					.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return theDate;
	}

	public static String getCurrentDate() {
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(currentTime);

		return sdf.format(date);
	}

	public static String getCurrentTime() {
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
		Date date = new Date(currentTime);

		return sdf.format(date);
	}

	public static String getTimeString(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(time);
		return sdf.format(date);
	}
}

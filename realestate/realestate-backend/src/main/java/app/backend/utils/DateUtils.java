package app.backend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

public class DateUtils {
	
	private static final FastDateFormat RFC3339_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss'Z'");
	
	public static Date addSecondsToDate(final Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime();
	}
	
	public static Date addMinutesToDate(final Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}	
	
	public static Date addHoursToDate(final Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTime();
	}	

	public static Date addDaysToDate(final Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	
	public static Date addMonthsToDate(final Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
	
	public static Date addYearsToDate(final Date date, int years) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}
	
	public static Date getFirstMomentOfHour(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		return calendar.getTime();
	}
	
	public static Date getFirstMomentOfDay(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}
	
	public static Date getLastMomentOfHour(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 999);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MINUTE, 59);
		return calendar.getTime();
	}

	public static Date getLastMomentOfDay(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 999);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		return calendar.getTime();
	}
	
	public static Date getLastMomentOfMonth(final Date date) {
		Date nextMonth = addMonthsToDate(date, 1);
		Date firstMomentOfNextMonth = getFirstMomentOfMonth(nextMonth);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstMomentOfNextMonth);
		calendar.add(Calendar.SECOND, -1);
		calendar.add(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}	

	public static Date getLastMomentOfYear(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 999);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.DATE, 31);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		return calendar.getTime();
	}
	
	public static long getDaysBetweenDates(final Date dateFrom, final Date dateTo) {
	    long diff = dateTo.getTime() - dateFrom.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public static long getHoursBetweenDates(final Date dateFrom, final Date dateTo) {
	    long diff = dateTo.getTime() - dateFrom.getTime();
	    return TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public static long getMinutesBetweenDates(final Date dateFrom, final Date dateTo) {
	    long diff = dateTo.getTime() - dateFrom.getTime();
	    return TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public static long getSecondsBetweenDates(final Date dateFrom, final Date dateTo) {
	    long diff = dateTo.getTime() - dateFrom.getTime();
	    return TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public static long getMonthsBetweenDates(final Date dateFrom, final Date dateTo) {
		LocalDate from = dateFrom.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
		LocalDate today = dateTo.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
		
		Period age = Period.between(from, today);
		return age.getYears() * 12 + age.getMonths();
	}

	public static Date getFirstMomentOfMonth(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	public static Date getFirstMomentOfYear(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		return calendar.getTime();
	}

	public static boolean hasSameMonthAndYear(final Date date1, final Date date2) {
		if (date1 == null || date2 == null)
			return false;
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		
		return (calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) && 
				calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR));

	}
	
	public static Date removeMilliseconds(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date now() {
		return new Date();
	}
	
	public static Date createDate(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second, int millisecond) {
		Calendar calendar = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second);
		calendar.set(Calendar.MILLISECOND, millisecond);
		return calendar.getTime();
	}
	
	public static boolean isWithinRange(final Date date, final Date start, final Date end) {
		return !(date.before(start) || date.after(end));
	}	

	/**
	 * Retorna el lunes de la semana anterior
	 * 
	 * @return
	 */
	public static Calendar getFirstDayLastWeek() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		int matchedCount = 0;
		while (matchedCount<2) {
			if (date.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				matchedCount++;
			}
			if (matchedCount < 2)
				date.add(Calendar.DAY_OF_YEAR, -1);;
		}
		return date;
	}

	/**
	 * Retorna el domingo de la semana anterior
	 * 
	 * @return
	 */
	public static Calendar getLastDayLastWeek() {
		Calendar date = getFirstDayLastWeek();
		date.set(Calendar.HOUR_OF_DAY, 23);
		date.set(Calendar.MINUTE, 59);
		date.set(Calendar.SECOND, 59);
		date.set(Calendar.MILLISECOND, 999);
		date.add(Calendar.DAY_OF_YEAR, 6);
		return date;
	}

	public static Calendar getFirstDayOfMonth(int monthsBack) {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		date.add(Calendar.DAY_OF_MONTH, 1);
		date.add(Calendar.MONTH, date.get(Calendar.MONTH)-monthsBack);
		return date;
	}

	public static Calendar getLastDayOfMonth(int monthsBack) {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.HOUR_OF_DAY, 23);
		date.set(Calendar.MINUTE, 59);
		date.set(Calendar.SECOND, 59);
		date.set(Calendar.MILLISECOND, 999);
		date.add(Calendar.MONTH, date.get(Calendar.MONTH)-monthsBack);
		YearMonth yearMonth = YearMonth.of(date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1);
		date.add(Calendar.DAY_OF_MONTH, yearMonth.lengthOfMonth());
		return date;
	}

	public static String getDayName(int dayOfWeek) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		return new SimpleDateFormat("EEEEE")
				.format(calendar.getTime())
				.toLowerCase();
	}
	
	public static String formatRFC3339(Date date) {
		return RFC3339_DATE_FORMAT.format(date);
	}
	
	public static int getMonth(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}
	
	public static int getDayOfMonth(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * @param dateFrom
	 * @return una fecha X dado que la distancia entre X y dateFrom, es la misma que entre dateFrom y dateTo
	 */
	public static Date getPreviousDateWithSameDifference(Date dateFrom, Date dateTo) {
		long difference = dateTo.getTime() - dateFrom.getTime();
		long newDateInMs = dateFrom.getTime() - difference;
		return new Date(newDateInMs);
	}

	public static Date createDate(String string) throws ParseException {
		if(StringUtils.isBlank(string))
			return null;
		return new SimpleDateFormat("yyyy-MM-ddThh:mm:ss.SSSZ").parse(string);
	}
	
}

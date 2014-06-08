package matrak.misc.date_time;

import static matrak.misc.Util.log;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Years;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class DateTimeStuff {

	public static void main(String[] args) 
	{
		
		log("----------- Instantiate Calendar -----------");
		
		Calendar cal1 = Calendar.getInstance();
		log("Calendar.getInstance()", cal1);
		
		Calendar cal2 = new GregorianCalendar();
		log("new GregorianCalendar()", cal2);
		
		Calendar cal1_1 = Calendar.getInstance(Locale.US);
		log("Calendar.getInstance(Locale.US)", cal1_1);
		
		Calendar cal2_1 = new GregorianCalendar(TimeZone.getTimeZone("GMT-5"));
		log("new GregorianCalendar(TimeZone.getTimeZone(\"GMT-5\")", cal2_1);
		
		
		log("----------- util.Date vs sql.Timestamp -----------");
		
		Date date = new Date();
		log("new Date()", date);
		
		Timestamp timestamp = new Timestamp(date.getTime());
		log("new Timestamp(date.getTime())", timestamp);
		
		log("timestamp equals date", timestamp.equals(date), " --> false");
		log("date equals timestamp", date.equals(timestamp), " --> true");
		
		
		log("----------- Joda Time (UTC) -----------");
		/*
		http://stackoverflow.com/questions/2653567/joda-time-whats-the-difference-between-period-interval-and-duration
		
		Interval:
		---------
		An interval in Joda-Time represents an interval of time from one millisecond instant to another instant. 
		Both instants are fully specified instants in the datetime continuum, complete with time zone. 
		Specific times are defined e.g. this might be the interval between 20:00:00GMT and yesterday and 09:00:00GMT 
		this morning.
		
		Duration:
		---------
		A duration in Joda-Time represents a duration of time measured in milliseconds. 
		The duration is often obtained from an interval. i.e. 
		we can subtract start from end of an interval to derive a duration

		Period:
		-------
		A period in Joda-Time represents a period of time defined in terms of fields, 
		for example, 3 years 5 months 2 days and 7 hours. 
		This differs from a duration in that it is inexact in terms of milliseconds. 
		A period can only be resolved to an exact number of milliseconds by specifying the instant 
		(including chronology and time zone) it is relative to. e.g. 
		consider the period of 1 year, if we add this to January 1st we will always arrive at the next January 1st
		but the duration will depend on whether the intervening year is a leap year or not. 
		Similarly if we add 1 month to the 1st of a month then we will arrive at the 1st of the next month 
		but the duration (in milliseconds) will vary based on the month in question
		 */
		
		DateTime start = new DateTime(2012, 12, 12, 0, 0, 0);
		DateTime end   = new DateTime();
		Interval startEndInterval = new Interval(start, end);
		Duration startEndDuration = startEndInterval.toDuration();
		log("Years, days, minutes since 2012.12.12", 
				Years.yearsIn(startEndInterval).getYears(),
				startEndDuration.getStandardDays(), 
				startEndDuration.getStandardMinutes());
		
		Period startEndPeriod = new Period(start, end, PeriodType.days());
		PeriodFormatter daysSinceStartFormatter = new PeriodFormatterBuilder().appendDays().appendSuffix(" days").toFormatter();
		log("Days since 2012.12.12", 
				daysSinceStartFormatter.print(startEndPeriod), 
				startEndPeriod.getDays(), 
				Days.daysBetween(start.toLocalDate(), end.toLocalDate()).getDays());
		
		log("In 12 days", end.plusDays(12).toString("YYYY.MM.dd"));
		
		BuddhistChronology buddhistChronology = BuddhistChronology.getInstance();
		DateTime dateTimeBuddhist = DateTime.now(buddhistChronology);
		log("BuddhistChronology Year", dateTimeBuddhist.getYear());
		
	}

}

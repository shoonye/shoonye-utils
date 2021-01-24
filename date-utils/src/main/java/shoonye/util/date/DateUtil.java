package shoonye.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import shoonye.util.StringUtil;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public class DateUtil {
	
    public static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static String JUST_DATE_FORMAT = "EEE, MMM d";
    private static final DateTimeFormatter FORMAT_MM_DD_YYYY_HYPHEN = DateTimeFormat.forPattern("MM-dd-yyyy");
    private static final DateTimeFormatter FORMAT_MM_DD_YYYY_SLASH = DateTimeFormat.forPattern("MM/dd/yyyy");
    public static String DDMM_FORMATE = "dd/MM";
    
    public static final DateFormat _FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static DateTime beginingOfTheDay(DateTime date) {
        if (date == null)
            return null;
        date = date.withTime(0, 0, 0, 0);
        return date;
    }

    public static DateTime convertToJoda(String date, String srcTz) {
        DateTimeZone dz;
        DateTime srcDateTime = null;
        if (srcTz.equalsIgnoreCase("PST")) {
            dz = DateTimeZone.forID("PST8PDT");
            logger.warn("Time Zone supplied was PST, defaulting to PST8PDT");
        } else {
            dz = DateTimeZone.forID(srcTz);
        }
        DateTimeParser[] parsers = 
            {   DateTimeFormat.forPattern("yyyy-MM-dd").getParser(),
                DateTimeFormat.forPattern("yyyy/MM/dd").getParser(),
                DateTimeFormat.forPattern("MM-dd-yyyy").getParser(),
                DateTimeFormat.forPattern("MM/dd/yyyy").getParser() 
            };
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().append(null, parsers).toFormatter();
        srcDateTime = formatter.withZone(dz).parseDateTime(date);

        return srcDateTime.toDateTime();
    }
    
    public static String transform(String dateTime){
        if(StringUtil.isBlank(dateTime)) return null;
    	DateFormat sourceFormat = new SimpleDateFormat("dd-MMM-yyyy");
    	DateFormat targetFormat = new SimpleDateFormat("MM/dd/yyyy");
        String dateStr =null;
		try {
			Date date = sourceFormat.parse(dateTime);
			dateStr = targetFormat.format(date);
		} catch (ParseException e) {
		    logger.warn(e.getMessage(),e);
		}
		return dateStr;
    }
   
    public static DateTime convertToTimeZone(DateTime date, String dest) {
        DateTime dstDateTime = date.withZone(DateTimeZone.forID(dest));
        return dstDateTime.toDateTime();
    }

    public static DateTime beginingOfTheWeek(DateTime day) {
        if (day == null)
            return null;
        day = day.withDayOfWeek(1);
        return day;
    }

    public static DateTime beginingOfTheMonth(DateTime day) {
        if (day == null)
            return null;
        day = day.withDayOfMonth(1);
        return day;
    }

    public static DateTime endOfTheDay(DateTime date) {
        if (date == null)
            return null;
        date = date.withTime(23, 59, 59, 999);
        return date;
    }

    public static DateTime beginingOfToday() {
        return beginingOfTheDay(new DateTime(DateTimeZone.UTC));
    }

    public static DateTime endOfToday() {
        return endOfTheDay(new DateTime(DateTimeZone.UTC));
    }

    public static String formatDateTime(DateTime date) {
        DateTimeFormatter format = DateTimeFormat.forPattern(DATE_TIME_FORMAT);
        return format.print(date);
    }

    public static String formatJustDate(DateTime date) {
        return FORMAT_MM_DD_YYYY_SLASH.print(date);
    }
    
    public static String ddmmFromat(DateTime date) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(DDMM_FORMATE);
        return fmt.print(date);
    }

    public static DateTime dateSevenDaysAgo() {
        DateTime today = new DateTime(DateTimeZone.UTC);
        DateTime lastWeek = today.minus(Days.SEVEN);
        DateTime date = DateUtil.endOfTheDay(lastWeek);
        return date;
    }

    public static DateTime aMonthAgo() {
        DateTime today = new DateTime(DateTimeZone.UTC);
        DateTime lastMonth = today.minusMonths(1);
        DateTime date = endOfTheDay(lastMonth);
        return date;
    }

    public static Date _parse(String date) {
        try {
            return _FORMAT.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String _format(Date date) {
        return _FORMAT.format(date);
    }

    public static DateTime nowUTC() {
        DateTime dt = new DateTime(DateTimeZone.UTC);
        return dt;
    }
    
    public static DateTime now() {
        DateTime dt = new DateTime();
        return dt;
    }

    public static DateTime parse(String dateTime) throws Exception{
        if(StringUtil.isBlank(dateTime)) return null;
    	DateTimeFormatter df = null;
    	if(dateTime.contains("/")){
            df = FORMAT_MM_DD_YYYY_SLASH;
        }else if(dateTime.contains("-")){
            df = FORMAT_MM_DD_YYYY_HYPHEN;
        }else{
            throw new Exception("Unsupported format: " +dateTime);
        }
    	
    	DateTime date =null;
		try {
			date = df.parseDateTime(dateTime);
		} catch (Exception e) {
           throw e;
		}
		return date;
    }
    
    public static void main(String[] args)
    {
//    	DateTime date = DateTime.now();
//    	DateTimeFormatter FORMAT_MM_DD_YYYY_SLASH = DateTimeFormat.forPattern("MM/dd/yyyy");
//            System.out.println(FORMAT_MM_DD_YYYY_SLASH.print(date));
//            
//            
//            System.out.println(formatJustDate(date));
    }
}

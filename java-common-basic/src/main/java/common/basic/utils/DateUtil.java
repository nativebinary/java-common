package common.basic.utils;

import common.basic.logs.Logger;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static final long MilliSecond = 1;
    public static final long Second = 1000 * MilliSecond;
    public static final long Minute = Second * 60;
    public static final long Hour = Minute * 60;
    public static final long Day = Hour * 24;
    public static final long Week = Day * 7;


    static SimpleDateFormat formatter_yyyyMMddHHmmss = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
    static SimpleDateFormat formatter_yyyyMMddHHmmssSSS = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS", Locale.KOREA );
    static SimpleDateFormat formatter_yyyyMMddHHmmssForFile = new SimpleDateFormat( "yyyyMMdd_HHmmss", Locale.KOREA );
    static SimpleDateFormat formatter_yyyyMMdd = new SimpleDateFormat( "yyyyMMdd", Locale.KOREA );
    static SimpleDateFormat formatter_yyyy_MM_dd = new SimpleDateFormat( "yyyy-MM-dd", Locale.KOREA );
    static SimpleDateFormat formatter_yyyyMM = new SimpleDateFormat( "yyyyMM", Locale.KOREA );
    static SimpleDateFormat formatter_yyyy = new SimpleDateFormat( "yyyy", Locale.KOREA );
    static SimpleDateFormat formatter_MM = new SimpleDateFormat( "MM", Locale.KOREA );

    static SimpleDateFormat formatter_nginxlog = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z");


    public static String yyyyMMddHHmmss(Date date)
    {
        return formatter_yyyyMMddHHmmss.format ( date );
    }


    public static String yyyyMMddHHmmssSSS(Date date)
    {
        return formatter_yyyyMMddHHmmssSSS.format ( date );
    }


    public static String yyyyMMddHHmmssForFile(Date date) {
        return formatter_yyyyMMddHHmmssForFile.format ( date );
    }

    public static String yyyyMMdd(Date date) {
        return formatter_yyyyMMdd.format( date );
    }

    public static String yyyy_MM_dd(Date date) {
        return formatter_yyyy_MM_dd.format( date );
    }

    public static String yyyyMM(Date date) {
        return formatter_yyyyMM.format( date );
    }

    public static String yyyy(Date date) {
        return formatter_yyyy.format( date );
    }

    public static String MM(Date date) {
        return formatter_MM.format( date );
    }

    public static Date parse(String s) {
        if(s == null || s.isEmpty())
            return null;

        try{
            SimpleDateFormat inDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return inDateFormat.parse(s);
        } catch(ParseException e){
            Logger.e(e);
            return null;
        }
    }

    public static Date getDateRandom(Date dateFrom, Date dateTo){
        Calendar cal = Calendar.getInstance();

        cal.setTime(dateFrom);
        BigDecimal decFrom = new BigDecimal(cal.getTimeInMillis());

        cal.setTime(dateTo);
        BigDecimal decTo = new BigDecimal(cal.getTimeInMillis());

        BigDecimal diff = decTo.subtract(decFrom);
        BigDecimal factor = new BigDecimal(Math.random()).multiply(diff);
        return new Date((factor.add(decFrom)).longValue());
    }

    public static long nowTime() {
        return new Date().getTime();
    }

}

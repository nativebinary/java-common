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



    final static SimpleDateFormat formatter_EEE = new SimpleDateFormat("EEE", Locale.US);
    final static SimpleDateFormat formatter_HHmm = new SimpleDateFormat("HHmm", Locale.KOREA);
    final static SimpleDateFormat formatter_Korean = new SimpleDateFormat("yyyy년MM월dd일", Locale.KOREA);
    final static SimpleDateFormat formatter_MM = new SimpleDateFormat("MM", Locale.KOREA);
    final static SimpleDateFormat formatter_nginxlog = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z");
    final static SimpleDateFormat formatter_yyMMdd = new SimpleDateFormat("yyMMdd", Locale.KOREA);
    final static SimpleDateFormat formatter_yyyy = new SimpleDateFormat("yyyy", Locale.KOREA);
    final static SimpleDateFormat formatter_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    final static SimpleDateFormat formatter_yyyyMM = new SimpleDateFormat("yyyyMM", Locale.KOREA);
    final static SimpleDateFormat formatter_yyyyMMdd = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
    final static SimpleDateFormat formatter_yyyyMMdd_HHmm = new SimpleDateFormat("yyyyMMdd HHmm", Locale.KOREA);
    final static SimpleDateFormat formatter_yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
    final static SimpleDateFormat formatter_yyyyMMddHHmmssForFile = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA);
    final static SimpleDateFormat formatter_yyyyMMddHHmmssSSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.KOREA);

    public static String EEE(Date date) { synchronized (formatter_EEE) { return formatter_EEE.format(date); } }
    public static String HHmm(Date date) { synchronized (formatter_HHmm) { return formatter_HHmm.format(date); } }
    public static String korean(Date date) { synchronized (formatter_Korean) { return formatter_Korean.format(date); } }
    public static String MM(Date date) { synchronized (formatter_MM) { return formatter_MM.format(date); } }
    public static String nginxlog(Date date) { synchronized (formatter_nginxlog) { return formatter_nginxlog.format(date); } }
    public static String yyMMdd(Date date) { synchronized (formatter_yyMMdd) { return formatter_yyMMdd.format(date); } }
    public static String yyyy(Date date) { synchronized (formatter_yyyy) { return formatter_yyyy.format(date); } }
    public static String yyyy_MM_dd(Date date) { synchronized (formatter_yyyy_MM_dd) { return formatter_yyyy_MM_dd.format(date); } }
    public static String yyyyMM(Date date) { synchronized (formatter_yyyyMM) { return formatter_yyyyMM.format(date); } }
    public static String yyyyMMdd(Date date) { synchronized (formatter_yyyyMMdd) { return formatter_yyyyMMdd.format(date); } }
    public static String yyyyMMdd_HHmm(Date date) { synchronized (formatter_yyyyMMdd_HHmm) { return formatter_yyyyMMdd_HHmm.format(date); } }
    public static String yyyyMMddHHmmss(Date date) { synchronized(formatter_yyyyMMddHHmmss) { return formatter_yyyyMMddHHmmss.format(date); } }
    public static String yyyyMMddHHmmssForFile(Date date) { synchronized (formatter_yyyyMMddHHmmssForFile) { return formatter_yyyyMMddHHmmssForFile.format(date); } }
    public static String yyyyMMddHHmmssSSS(Date date) { synchronized (formatter_yyyyMMddHHmmssSSS) { return formatter_yyyyMMddHHmmssSSS.format(date); } }



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

    public static Date parse_yyyyMMdd_HHmm(String s) {
        if (s == null || s.isEmpty())
            return null;

        try {
            return formatter_yyyyMMdd_HHmm.parse(s);
        } catch (ParseException e) {
            Logger.e(e);
            return null;
        }
    }

    public static Date getDateRandom(Date dateFrom, Date dateTo) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(dateFrom);
        BigDecimal decFrom = new BigDecimal(cal.getTimeInMillis());

        cal.setTime(dateTo);
        BigDecimal decTo = new BigDecimal(cal.getTimeInMillis());

        BigDecimal diff = decTo.subtract(decFrom);
        BigDecimal factor = new BigDecimal(Math.random()).multiply(diff);
        return new Date(factor.add(decFrom).longValue());
    }

    public static long nowTime() {
        return new Date().getTime();
    }

}

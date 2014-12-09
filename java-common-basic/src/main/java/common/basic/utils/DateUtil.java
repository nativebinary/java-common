package common.basic.utils;

import common.basic.logs.Logger;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
    public static final long MilliSecond = 1;
    public static final long Second = 1000 * MilliSecond;
    public static final long Minute = Second * 60;
    public static final long Hour = Minute * 60;
    public static final long Day = Hour * 24;
    public static final long Week = Day * 7;

    private final static SimpleDateFormat EEE = new SimpleDateFormat("EEE", Locale.US);
    private final static SimpleDateFormat HHmm = new SimpleDateFormat("HHmm", Locale.KOREA);
    private final static SimpleDateFormat HH_mm = new SimpleDateFormat("HH:mm", Locale.KOREA);
    private final static SimpleDateFormat Korean = new SimpleDateFormat("yyyy년MM월dd일", Locale.KOREA);
    private final static SimpleDateFormat MM = new SimpleDateFormat("MM", Locale.KOREA);
    private final static SimpleDateFormat nginxlog = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z");
    private final static SimpleDateFormat yyMMdd = new SimpleDateFormat("yyMMdd", Locale.KOREA);
    private final static SimpleDateFormat yyyy = new SimpleDateFormat("yyyy", Locale.KOREA);
    private final static SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    private final static SimpleDateFormat yyyy_MM_dd_E = new SimpleDateFormat("yyyy-MM-dd (E)", Locale.KOREA);
    private final static SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyyMM", Locale.KOREA);
    private final static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
    private final static SimpleDateFormat yyyyMMdd_HHmm = new SimpleDateFormat("yyyyMMdd HHmm", Locale.KOREA);
    private final static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
    private final static SimpleDateFormat yyyyMMddHHmmssForFile = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA);
    private final static SimpleDateFormat yyyyMMddHHmmssSSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.KOREA);


    //<editor-fold desc="generated code : convert Date -> String">

    // s/private final static SimpleDateFormat (\w+) = .+/public static String $1(Date date) { synchronized ($1) { return $1.format(date); } }/

    public static String EEE(Date date) { synchronized (EEE) { return EEE.format(date); } }
    public static String HHmm(Date date) { synchronized (HHmm) { return HHmm.format(date); } }
    public static String HH_mm(Date date) { synchronized (HH_mm) { return HH_mm.format(date); } }
    public static String Korean(Date date) { synchronized (Korean) { return Korean.format(date); } }
    public static String MM(Date date) { synchronized (MM) { return MM.format(date); } }
    public static String nginxlog(Date date) { synchronized (nginxlog) { return nginxlog.format(date); } }
    public static String yyMMdd(Date date) { synchronized (yyMMdd) { return yyMMdd.format(date); } }
    public static String yyyy(Date date) { synchronized (yyyy) { return yyyy.format(date); } }
    public static String yyyy_MM_dd(Date date) { synchronized (yyyy_MM_dd) { return yyyy_MM_dd.format(date); } }
    public static String yyyy_MM_dd_E(Date date) { synchronized (yyyy_MM_dd_E) { return yyyy_MM_dd_E.format(date); } }
    public static String yyyyMM(Date date) { synchronized (yyyyMM) { return yyyyMM.format(date); } }
    public static String yyyyMMdd(Date date) { synchronized (yyyyMMdd) { return yyyyMMdd.format(date); } }
    public static String yyyyMMdd_HHmm(Date date) { synchronized (yyyyMMdd_HHmm) { return yyyyMMdd_HHmm.format(date); } }
    public static String yyyyMMddHHmmss(Date date) { synchronized (yyyyMMddHHmmss) { return yyyyMMddHHmmss.format(date); } }
    public static String yyyyMMddHHmmssForFile(Date date) { synchronized (yyyyMMddHHmmssForFile) { return yyyyMMddHHmmssForFile.format(date); } }
    public static String yyyyMMddHHmmssSSS(Date date) { synchronized (yyyyMMddHHmmssSSS) { return yyyyMMddHHmmssSSS.format(date); } }
    //</editor-fold>

    //<editor-fold desc="generated code : convert String -> Date">

    // s/private final static SimpleDateFormat (\w+) = .+/public static Date $1(String s, Date dateDefault) { synchronized ($1) { try { return $1.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }/

    public static Date EEE(String s, Date dateDefault) { synchronized (EEE) { try { return EEE.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static Date HHmm(String s, Date dateDefault) { synchronized (HHmm) { try { return HHmm.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static Date Korean(String s, Date dateDefault) { synchronized (Korean) { try { return Korean.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static Date MM(String s, Date dateDefault) { synchronized (MM) { try { return MM.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static Date nginxlog(String s, Date dateDefault) { synchronized (nginxlog) { try { return nginxlog.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static Date yyMMdd(String s, Date dateDefault) { synchronized (yyMMdd) { try { return yyMMdd.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static Date yyyy(String s, Date dateDefault) { synchronized (yyyy) { try { return yyyy.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static Date yyyy_MM_dd(String s, Date dateDefault) { synchronized (yyyy_MM_dd) { try { return yyyy_MM_dd.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static Date yyyyMM(String s, Date dateDefault) { synchronized (yyyyMM) { try { return yyyyMM.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static Date yyyyMMdd(String s, Date dateDefault) { synchronized (yyyyMMdd) { try { return yyyyMMdd.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static Date yyyyMMdd_HHmm(String s, Date dateDefault) { synchronized (yyyyMMdd_HHmm) { try { return yyyyMMdd_HHmm.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static Date yyyyMMddHHmmss(String s, Date dateDefault) { synchronized (yyyyMMddHHmmss) { try { return yyyyMMddHHmmss.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static Date yyyyMMddHHmmssForFile(String s, Date dateDefault) { synchronized (yyyyMMddHHmmssForFile) { try { return yyyyMMddHHmmssForFile.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static Date yyyyMMddHHmmssSSS(String s, Date dateDefault) { synchronized (yyyyMMddHHmmssSSS) { try { return yyyyMMddHHmmssSSS.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    //</editor-fold>

    public static String yyyyMMdd2yyyy_MM_dd(String YYYYMMDD) {
        return yyyy_MM_dd(yyyyMMdd(YYYYMMDD, new Date()));
    }

    public static String yyyyMMdd2yyyy_MM_dd_E(String YYYYMMDD) {
        return yyyy_MM_dd_E(yyyyMMdd(YYYYMMDD, new Date()));
    }

    public static String HHmm2HH_mm(String HHmm) {
        return HH_mm(HHmm(HHmm, new Date()));
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

    public static Date parse_yyyyMMdd_HHmm(String s) {
        if (s == null || s.isEmpty())
            return null;

        try {
            return yyyyMMdd_HHmm.parse(s);
        } catch (ParseException e) {
            Logger.e(e);
            return null;
        }
    }

    public static Date parse_yyyyMMdd(String s) {
        if (s == null || s.isEmpty())
            return null;

        try {
            return yyyyMMdd.parse(s);
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



    static Pattern patternTimeSpan = Pattern.compile("(\\d+)\\s*(second|sec|s|ss|minute|min|mi|mm|hour|h|hh|day|d|dd|month|m|MM|year|y|yy|yyyy)");
    public static Date dateAddHumanReadableTimeSpan(Date date, String timeSpan) {

        final Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(date);

        timeSpan = timeSpan.trim();
        Matcher matcher = patternTimeSpan.matcher(timeSpan);
        if (!matcher.find())
            return calendarNow.getTime();


        int number = IntUtil.parseInt(matcher.group(1), 0);
        String unit = matcher.group(2);
        if ("second".equals(unit) || "sec".equals(unit) || "s".equals(unit) || "ss".equals(unit)) {
            calendarNow.add(Calendar.SECOND, number);
            return calendarNow.getTime();
        }

        if ("minute".equals(unit) || "min".equals(unit)|| "mi".equals(unit) || "mm".equals(unit)) {
            calendarNow.add(Calendar.MINUTE, number);
            return calendarNow.getTime();
        }

        if ("hour".equals(unit) || "h".equals(unit) || "hh".equals(unit)) {
            calendarNow.add(Calendar.HOUR, number);
            return calendarNow.getTime();
        }

        if ("day".equals(unit) || "d".equals(unit) || "dd".equals(unit)) {
            calendarNow.add(Calendar.DATE, number);
            return calendarNow.getTime();
        }

        if ("month".equals(unit) || "m".equals(unit) || "MM".equals(unit)) {
            calendarNow.add(Calendar.MONTH, number);
            return calendarNow.getTime();
        }

        if ("year".equals(unit) || "y".equals(unit) || "yy".equals(unit) || "yyyy".equals(unit)) {
            calendarNow.add(Calendar.YEAR, number);
            return calendarNow.getTime();
        }

        return calendarNow.getTime();
    }

    public static Date dateNowAfterHumanReadableTimeSpan(String timeSpan) {
        return dateAddHumanReadableTimeSpan(new Date(), timeSpan);
    }

    public static String YYYYMMDD2YYYY_MM_DD(String YYYYMMDD) {
        return String.format("%s-%s-%s", YYYYMMDD.substring(0, 4), YYYYMMDD.substring(4, 6), YYYYMMDD.substring(6, 8));
    }
}

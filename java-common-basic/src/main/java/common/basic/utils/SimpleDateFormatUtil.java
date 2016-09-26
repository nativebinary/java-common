package common.basic.utils;

import common.basic.logs.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SimpleDateFormatUtil {
    private final static SimpleDateFormat HH_colon_mm = new SimpleDateFormat("HH:mm", Locale.KOREA);
    private final static SimpleDateFormat HHmm = new SimpleDateFormat("HHmm", Locale.KOREA);
    private final static SimpleDateFormat MM = new SimpleDateFormat("MM", Locale.KOREA);
    private final static SimpleDateFormat US_EEE = new SimpleDateFormat("EEE", Locale.US);
    private final static SimpleDateFormat yyMMdd = new SimpleDateFormat("yyMMdd", Locale.KOREA);
    private final static SimpleDateFormat yyyy = new SimpleDateFormat("yyyy", Locale.KOREA);
    private final static SimpleDateFormat yyyy_dash_MM_dash_dd = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    private final static SimpleDateFormat yyyy_dash_MM_dash_dd_space_HH = new SimpleDateFormat("yyyy-MM-dd HH", Locale.KOREA);
    private final static SimpleDateFormat yyyy_dash_MM_dash_dd_space_HH_colon_mm = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
    private final static SimpleDateFormat yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
    private final static SimpleDateFormat yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss_dot_SSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.KOREA);
    private final static SimpleDateFormat yyyy_dash_MM_dash_dd_space_HH_colon_mm_a_space_z = new SimpleDateFormat("yyyy-MM-dd HH:mma z", Locale.KOREA);
    private final static SimpleDateFormat yyyy_dash_MM_dash_dd_space_leftParenthesis_E_rightParenthesis = new SimpleDateFormat("yyyy-MM-dd (E)", Locale.KOREA);
    private final static SimpleDateFormat yyyy_nyeon_MM_wol_dd_Il = new SimpleDateFormat("yyyy년MM월dd일", Locale.KOREA);
    private final static SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyyMM", Locale.KOREA);
    private final static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
    private final static SimpleDateFormat yyyyMMdd_dot_HHmmss = new SimpleDateFormat("yyyyMMdd.HHmmss", Locale.KOREA);
    private final static SimpleDateFormat yyyyMMdd_space_HH = new SimpleDateFormat("yyyyMMdd HH", Locale.KOREA);
    private final static SimpleDateFormat yyyyMMdd_space_HHmm = new SimpleDateFormat("yyyyMMdd HHmm", Locale.KOREA);


    //<editor-fold desc="generated code : convert Date -> String">
    //pattern: private final static SimpleDateFormat (\w+) = .+
    //replace: public static String $1(Date date) { synchronized ($1) { return $1.format(date); } }\npublic static Date $1(String s, Date dateDefault) { synchronized ($1) { try { return $1.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }

    public static String HH_colon_mm(Date date) { synchronized (HH_colon_mm) { return HH_colon_mm.format(date); } }
    public static Date HH_colon_mm(String s, Date dateDefault) { synchronized (HH_colon_mm) { try { return HH_colon_mm.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String HHmm(Date date) { synchronized (HHmm) { return HHmm.format(date); } }
    public static Date HHmm(String s, Date dateDefault) { synchronized (HHmm) { try { return HHmm.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String MM(Date date) { synchronized (MM) { return MM.format(date); } }
    public static Date MM(String s, Date dateDefault) { synchronized (MM) { try { return MM.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String US_EEE(Date date) { synchronized (US_EEE) { return US_EEE.format(date); } }
    public static Date US_EEE(String s, Date dateDefault) { synchronized (US_EEE) { try { return US_EEE.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String yyMMdd(Date date) { synchronized (yyMMdd) { return yyMMdd.format(date); } }
    public static Date yyMMdd(String s, Date dateDefault) { synchronized (yyMMdd) { try { return yyMMdd.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String yyyy(Date date) { synchronized (yyyy) { return yyyy.format(date); } }
    public static Date yyyy(String s, Date dateDefault) { synchronized (yyyy) { try { return yyyy.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String yyyy_dash_MM_dash_dd(Date date) { synchronized (yyyy_dash_MM_dash_dd) { return yyyy_dash_MM_dash_dd.format(date); } }
    public static Date yyyy_dash_MM_dash_dd(String s, Date dateDefault) { synchronized (yyyy_dash_MM_dash_dd) { try { return yyyy_dash_MM_dash_dd.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String yyyy_dash_MM_dash_dd_space_HH(Date date) { synchronized (yyyy_dash_MM_dash_dd_space_HH) { return yyyy_dash_MM_dash_dd_space_HH.format(date); } }
    public static Date yyyy_dash_MM_dash_dd_space_HH(String s, Date dateDefault) { synchronized (yyyy_dash_MM_dash_dd_space_HH) { try { return yyyy_dash_MM_dash_dd_space_HH.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String yyyy_dash_MM_dash_dd_space_HH_colon_mm(Date date) { synchronized (yyyy_dash_MM_dash_dd_space_HH_colon_mm) { return yyyy_dash_MM_dash_dd_space_HH_colon_mm.format(date); } }
    public static Date yyyy_dash_MM_dash_dd_space_HH_colon_mm(String s, Date dateDefault) { synchronized (yyyy_dash_MM_dash_dd_space_HH_colon_mm) { try { return yyyy_dash_MM_dash_dd_space_HH_colon_mm.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss(Date date) { synchronized (yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss) { return yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss.format(date); } }
    public static Date yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss(String s, Date dateDefault) { synchronized (yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss) { try { return yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss_dot_SSS(Date date) { synchronized (yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss_dot_SSS) { return yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss_dot_SSS.format(date); } }
    public static Date yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss_dot_SSS(String s, Date dateDefault) { synchronized (yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss_dot_SSS) { try { return yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss_dot_SSS.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String yyyy_dash_MM_dash_dd_space_HH_colon_mm_a_space_z(Date date) { synchronized (yyyy_dash_MM_dash_dd_space_HH_colon_mm_a_space_z) { return yyyy_dash_MM_dash_dd_space_HH_colon_mm_a_space_z.format(date); } }
    public static Date yyyy_dash_MM_dash_dd_space_HH_colon_mm_a_space_z(String s, Date dateDefault) { synchronized (yyyy_dash_MM_dash_dd_space_HH_colon_mm_a_space_z) { try { return yyyy_dash_MM_dash_dd_space_HH_colon_mm_a_space_z.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String yyyy_dash_MM_dash_dd_space_leftParenthesis_E_rightParenthesis(Date date) { synchronized (yyyy_dash_MM_dash_dd_space_leftParenthesis_E_rightParenthesis) { return yyyy_dash_MM_dash_dd_space_leftParenthesis_E_rightParenthesis.format(date); } }
    public static Date yyyy_dash_MM_dash_dd_space_leftParenthesis_E_rightParenthesis(String s, Date dateDefault) { synchronized (yyyy_dash_MM_dash_dd_space_leftParenthesis_E_rightParenthesis) { try { return yyyy_dash_MM_dash_dd_space_leftParenthesis_E_rightParenthesis.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String yyyy_nyeon_MM_wol_dd_Il(Date date) { synchronized (yyyy_nyeon_MM_wol_dd_Il) { return yyyy_nyeon_MM_wol_dd_Il.format(date); } }
    public static Date yyyy_nyeon_MM_wol_dd_Il(String s, Date dateDefault) { synchronized (yyyy_nyeon_MM_wol_dd_Il) { try { return yyyy_nyeon_MM_wol_dd_Il.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String yyyyMM(Date date) { synchronized (yyyyMM) { return yyyyMM.format(date); } }
    public static Date yyyyMM(String s, Date dateDefault) { synchronized (yyyyMM) { try { return yyyyMM.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String yyyyMMdd(Date date) { synchronized (yyyyMMdd) { return yyyyMMdd.format(date); } }
    public static Date yyyyMMdd(String s, Date dateDefault) { synchronized (yyyyMMdd) { try { return yyyyMMdd.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String yyyyMMdd_dot_HHmmss(Date date) { synchronized (yyyyMMdd_dot_HHmmss) { return yyyyMMdd_dot_HHmmss.format(date); } }
    public static Date yyyyMMdd_dot_HHmmss(String s, Date dateDefault) { synchronized (yyyyMMdd_dot_HHmmss) { try { return yyyyMMdd_dot_HHmmss.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String yyyyMMdd_space_HH(Date date) { synchronized (yyyyMMdd_space_HH) { return yyyyMMdd_space_HH.format(date); } }
    public static Date yyyyMMdd_space_HH(String s, Date dateDefault) { synchronized (yyyyMMdd_space_HH) { try { return yyyyMMdd_space_HH.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }
    public static String yyyyMMdd_space_HHmm(Date date) { synchronized (yyyyMMdd_space_HHmm) { return yyyyMMdd_space_HHmm.format(date); } }
    public static Date yyyyMMdd_space_HHmm(String s, Date dateDefault) { synchronized (yyyyMMdd_space_HHmm) { try { return yyyyMMdd_space_HHmm.parse(s); } catch (ParseException e) { Logger.e(e); return dateDefault; } } }

    //</editor-fold>
}

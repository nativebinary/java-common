package common.basic.utils
import spock.lang.Specification

import java.text.SimpleDateFormat

class SimpleDateFormatUtilTest extends Specification {
    def "yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss_dot_SSS"() {
        expect:
        SimpleDateFormatUtil.yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss_dot_SSS(SimpleDateFormatUtil.yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss_dot_SSS("1982-10-23 10:23:10.234", null)) == "1982-10-23 10:23:10.234";
        SimpleDateFormatUtil.yyyy_dash_MM_dash_dd_space_HH(SimpleDateFormatUtil.yyyy_dash_MM_dash_dd_space_HH("1982-10-23 10", null)) == "1982-10-23 10";

    }


    public static String dateFormat = "yyyyMMdd";
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
    public static int count = 10/* * 1000 * 1000*/;

    public static synchronized Date parseUsingStatic(String s) {
        return simpleDateFormat.parse(s);
    }

    public static Date parseUsingLocal(String s) {
        return new SimpleDateFormat(dateFormat).parse(s);
    }

    def "staticInstanceWithLock"() {
        // about 18sec
        for (int i = 0; i < count; i++) {
            parseUsingStatic("20150101")
        }
        expect:
        true;
    }

    def "localInstanceWithoutLock"() {
        // about 7sec
        for (int i = 0; i < count; i++) {
            parseUsingLocal("20150101")
        }

        expect:
        true;
    }
}

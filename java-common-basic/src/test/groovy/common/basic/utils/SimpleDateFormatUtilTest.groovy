package common.basic.utils

import spock.lang.Specification

class SimpleDateFormatUtilTest extends Specification {
    def "yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss_dot_SSS"() {
        expect:
        SimpleDateFormatUtil.yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss_dot_SSS(SimpleDateFormatUtil.yyyy_dash_MM_dash_dd_space_HH_colon_mm_colon_ss_dot_SSS("1982-10-23 10:23:10.234", null)) == "1982-10-23 10:23:10.234";
        SimpleDateFormatUtil.yyyy_dash_MM_dash_dd_space_HH(SimpleDateFormatUtil.yyyy_dash_MM_dash_dd_space_HH("1982-10-23 10", null)) == "1982-10-23 10";

    }
}

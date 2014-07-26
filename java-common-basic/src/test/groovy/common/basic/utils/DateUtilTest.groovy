package common.basic.utils

import spock.lang.Specification

class DateUtilTest extends Specification {
    def "yyMMdd"() {
        expect:
        DateUtil.yyMMdd(DateUtil.yyyyMMdd("20140725", null)) == "140725";
        DateUtil.yyyyMMdd(DateUtil.yyMMdd("140725", null)) == "20140725";
    }
}

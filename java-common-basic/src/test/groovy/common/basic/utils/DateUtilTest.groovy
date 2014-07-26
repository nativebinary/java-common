package common.basic.utils

import spock.lang.Specification

class DateUtilTest extends Specification {
    def "yyMMdd"() {
        expect:
        DateUtil.yyMMdd(DateUtil.parse("2014-07-25")) == "140725";
    }
}

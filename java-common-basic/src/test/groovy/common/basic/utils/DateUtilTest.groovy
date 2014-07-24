package common.basic.utils

import spock.lang.Specification

class DateUtilTest extends Specification {
    def "yyMMdd"() {
        expect:
        DateUtil.yyMMdd(new Date(1406239188787)) == "140725";
    }
}

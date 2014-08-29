package common.basic.utils

import spock.lang.Specification

class DateUtilTest extends Specification {
    def "yyMMdd"() {
        expect:
        DateUtil.yyMMdd(DateUtil.yyyyMMdd("20140725", null)) == "140725";
        DateUtil.yyyyMMdd(DateUtil.yyMMdd("140725", null)) == "20140725";
        DateUtil.MM(DateUtil.yyyyMMdd("20140725", null)) == "07";
    }


    def "humanReadable"() {
        def date = DateUtil.parse("2014-07-25")
        expect:
        DateUtil.dateAddHumanReadableTimeSpan(date, "1 sec").getTime() == (date.getTime() + DateUtil.Second);
        DateUtil.dateAddHumanReadableTimeSpan(date, "1minute").getTime() == (date.getTime() + DateUtil.Minute);
        DateUtil.dateAddHumanReadableTimeSpan(date, "1hour").getTime() == (date.getTime() + DateUtil.Hour);
        DateUtil.dateAddHumanReadableTimeSpan(date, "1day").getTime() == (date.getTime() + DateUtil.Day);
        DateUtil.dateAddHumanReadableTimeSpan(date, "1month").getTime() == (date.getTime() + DateUtil.Day * 31);
        DateUtil.dateAddHumanReadableTimeSpan(date, "1year").getTime() == (date.getTime() + DateUtil.Day * 365);
    }
}

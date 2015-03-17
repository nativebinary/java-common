package common.basic.utils

import spock.lang.Specification

class DateUtilTest extends Specification {
    def "EEEString"() {
        expect:
        result == DateUtil.EEE(date)

        where:
        result      ||  date
        "Fri"       ||  DateUtil.yyyyMMdd("20141212", null)
        "Sat"       ||  DateUtil.yyyyMMdd("20141213", null)
        "Thu"       ||  DateUtil.yyyyMMdd("20150101", null)
        "Sun"       ||  DateUtil.yyyyMMdd("20150201", null)
    }

    def "gmtFormatString"() {
        expect:
        result == DateUtil.gmtFormat(date)

        where:
        result                              ||  date
        "Thu, 11 Dec 2014 15:00:00 GMT"     ||  DateUtil.yyyyMMdd("20141212", null)
        "Fri, 12 Dec 2014 15:00:00 GMT"     ||  DateUtil.yyyyMMdd("20141213", null)
        "Wed, 31 Dec 2014 15:00:00 GMT"     ||  DateUtil.yyyyMMdd("20150101", null)
        "Sat, 31 Jan 2015 15:00:00 GMT"     ||  DateUtil.yyyyMMdd("20150201", null)
    }

    def "HH_mmString"() {
        expect:
        result == DateUtil.HH_mm(date)

        where:
        result      ||  date
        "00:00"     ||  DateUtil.yyyyMMdd("20141212", null)
        "12:30"     ||  DateUtil.yyyyMMddHHmmss("2014-12-12 12:30:24", null)
        "08:45"     ||  DateUtil.yyyyMMddHHmmss("2014-12-12 08:45:11", null)
        "08:45"     ||  DateUtil.yyyyMMdd_HHmm("20141212 0845", null)
        "08:45"     ||  DateUtil.yyyyMMddHHmmssForFile("20141212_084512", null)
        "08:45"     ||  DateUtil.yyyyMMddHHmmssSSS("2014-12-12 08:45:12.111", null)
    }

    def "HHmmString"() {
        expect:
        result == DateUtil.HHmm(date)

        where:
        result      ||  date
        "0000"      ||  DateUtil.yyyyMMdd("20141212", null)
        "1230"      ||  DateUtil.yyyyMMddHHmmss("2014-12-12 12:30:24", null)
        "0845"      ||  DateUtil.yyyyMMddHHmmss("2014-12-12 08:45:11", null)
        "0845"      ||  DateUtil.yyyyMMdd_HHmm("20141212 0845", null)
        "0845"      ||  DateUtil.yyyyMMddHHmmssForFile("20141212_084512", null)
        "0845"      ||  DateUtil.yyyyMMddHHmmssSSS("2014-12-12 08:45:12.111", null)
    }
}

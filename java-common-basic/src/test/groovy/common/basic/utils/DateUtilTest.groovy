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

//    def "gmtFormatString"() {
//        expect:
//        result == DateUtil.gmtFormat(date)
//
//        where:
//        result                              ||  date
//        "Sun, 30 Nov 2014 03:30:24 GMT"     ||  DateUtil.yyyyMMddHHmmss("2014-11-30 12:30:24", null)
//        "Sat, 13 Dec 2014 06:22:32 GMT"     ||  DateUtil.yyyyMMddHHmmss("2014-12-13 15:22:32", null)
//        "Wed, 31 Dec 2014 23:34:11 GMT"     ||  DateUtil.yyyyMMddHHmmss("2015-01-01 08:34:11", null)
//        "Sun, 1 Feb 2015 13:22:22 GMT"      ||  DateUtil.yyyyMMddHHmmss("2015-02-01 22:22:22", null)
//    }

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

    def "KoreanString"() {
        expect:
        result == DateUtil.Korean(date)

        where:
        result              ||  date
        "2014년12월12일"      ||  DateUtil.yyyyMMdd("20141212", null)
        "2014년11월30일"      ||  DateUtil.yyyyMMddHHmmss("2014-11-30 12:30:24", null)
        "2014년12월25일"      ||  DateUtil.yyyyMMddHHmmss("2014-12-25 08:45:11", null)
        "2015년03월17일"      ||  DateUtil.yyyyMMdd_HHmm("20150317 0845", null)
        "2015년01월01일"      ||  DateUtil.yyyyMMddHHmmssForFile("20150101_084512", null)
        "2015년02월27일"      ||  DateUtil.yyyyMMddHHmmssSSS("2015-02-27 08:45:12.111", null)
    }

    def "MMString"() {
        expect:
        result == DateUtil.MM(date)

        where:
        result      ||  date
        "12"        ||  DateUtil.yyyyMMdd("20141212", null)
        "11"        ||  DateUtil.yyyyMMddHHmmss("2014-11-30 12:30:24", null)
        "12"        ||  DateUtil.yyyyMMddHHmmss("2014-12-25 08:45:11", null)
        "03"        ||  DateUtil.yyyyMMdd_HHmm("20150317 0845", null)
        "01"        ||  DateUtil.yyyyMMddHHmmssForFile("20150101_084512", null)
        "02"        ||  DateUtil.yyyyMMddHHmmssSSS("2015-02-27 08:45:12.111", null)
    }

//    def "nginxlogString"() {
//        expect:
//        result == DateUtil.nginxlog(date)
//
//        where:
//        result                          ||  date
//        "12/Dec/2014:00:00:00 +0900"    ||  DateUtil.yyyyMMdd("20141212", null)
//        "30/Nov/2014:12:30:24 +0900"    ||  DateUtil.yyyyMMddHHmmss("2014-11-30 12:30:24", null)
//        "25/Dec/2014:08:45:11 +0900"    ||  DateUtil.yyyyMMddHHmmss("2014-12-25 08:45:11", null)
//        "17/Mar/2015:08:45:00 +0900"    ||  DateUtil.yyyyMMdd_HHmm("20150317 0845", null)
//        "01/Jan/2015:08:45:12 +0900"    ||  DateUtil.yyyyMMddHHmmssForFile("20150101_084512", null)
//        "27/Feb/2015:08:45:12 +0900"    ||  DateUtil.yyyyMMddHHmmssSSS("2015-02-27 08:45:12.111", null)
//    }

    def "yyMMddString"() {
        expect:
        result == DateUtil.yyMMdd(date)

        where:
        result      ||  date
        "141212"    ||  DateUtil.yyyyMMdd("20141212", null)
        "141212"    ||  DateUtil.yyyyMMdd("20141212", null)
        "141130"    ||  DateUtil.yyyyMMddHHmmss("2014-11-30 12:30:24", null)
        "141225"    ||  DateUtil.yyyyMMddHHmmss("2014-12-25 08:45:11", null)
        "150317"    ||  DateUtil.yyyyMMdd_HHmm("20150317 0845", null)
        "150101"    ||  DateUtil.yyyyMMddHHmmssForFile("20150101_084512", null)
        "150227"    ||  DateUtil.yyyyMMddHHmmssSSS("2015-02-27 08:45:12.111", null)
    }

    def "yyyyString"() {
        expect:
        result == DateUtil.yyyy(date)

        where:
        result      ||  date
        "2014"      ||  DateUtil.yyMMdd("141212", null)
        "2014"      ||  DateUtil.yyyyMMdd("20141212", null)
        "2014"      ||  DateUtil.yyyyMMdd("20141212", null)
        "2014"      ||  DateUtil.yyyyMMddHHmmss("2014-11-30 12:30:24", null)
        "2014"      ||  DateUtil.yyyyMMddHHmmss("2014-12-25 08:45:11", null)
        "2015"      ||  DateUtil.yyyyMMdd_HHmm("20150317 0845", null)
        "2015"      ||  DateUtil.yyyyMMddHHmmssForFile("20150101_084512", null)
        "2015"      ||  DateUtil.yyyyMMddHHmmssSSS("2015-02-27 08:45:12.111", null)
    }

    def "yyyy_MM_ddString"() {
        expect:
        result == DateUtil.yyyy_MM_dd(date)

        where:
        result          ||  date
        "2014-12-12"    ||  DateUtil.yyMMdd("141212", null)
        "2014-12-12"    ||  DateUtil.yyyyMMdd("20141212", null)
        "2014-12-12"    ||  DateUtil.yyyyMMdd("20141212", null)
        "2014-11-30"    ||  DateUtil.yyyyMMddHHmmss("2014-11-30 12:30:24", null)
        "2014-12-25"    ||  DateUtil.yyyyMMddHHmmss("2014-12-25 08:45:11", null)
        "2015-03-17"    ||  DateUtil.yyyyMMdd_HHmm("20150317 0845", null)
        "2015-01-01"    ||  DateUtil.yyyyMMddHHmmssForFile("20150101_084512", null)
        "2015-02-27"    ||  DateUtil.yyyyMMddHHmmssSSS("2015-02-27 08:45:12.111", null)
    }

    def "yyyy_MM_dd_EString"() {
        expect:
        result == DateUtil.yyyy_MM_dd_E(date)

        where:
        result              ||  date
        "2014-12-12 (금)"    ||  DateUtil.yyMMdd("141212", null)
        "2014-12-12 (금)"    ||  DateUtil.yyyyMMdd("20141212", null)
        "2014-12-12 (금)"    ||  DateUtil.yyyyMMdd("20141212", null)
        "2014-11-30 (일)"    ||  DateUtil.yyyyMMddHHmmss("2014-11-30 12:30:24", null)
        "2014-12-25 (목)"    ||  DateUtil.yyyyMMddHHmmss("2014-12-25 08:45:11", null)
        "2015-03-17 (화)"    ||  DateUtil.yyyyMMdd_HHmm("20150317 0845", null)
        "2015-01-01 (목)"    ||  DateUtil.yyyyMMddHHmmssForFile("20150101_084512", null)
        "2015-02-27 (금)"    ||  DateUtil.yyyyMMddHHmmssSSS("2015-02-27 08:45:12.111", null)
    }

    def "yyyyMMString"() {
        expect:
        result == DateUtil.yyyyMM(date)

        where:
        result          ||  date
        "201412"    ||  DateUtil.yyMMdd("141212", null)
        "201412"    ||  DateUtil.yyyyMMdd("20141212", null)
        "201412"    ||  DateUtil.yyyyMMdd("20141212", null)
        "201411"    ||  DateUtil.yyyyMMddHHmmss("2014-11-30 12:30:24", null)
        "201412"    ||  DateUtil.yyyyMMddHHmmss("2014-12-25 08:45:11", null)
        "201503"    ||  DateUtil.yyyyMMdd_HHmm("20150317 0845", null)
        "201501"    ||  DateUtil.yyyyMMddHHmmssForFile("20150101_084512", null)
        "201502"    ||  DateUtil.yyyyMMddHHmmssSSS("2015-02-27 08:45:12.111", null)
    }

    def "yyyyMMddString"() {
        expect:
        result == DateUtil.yyyyMMdd(date)

        where:
        result      ||  date
        "20141212"  ||  DateUtil.yyMMdd("141212", null)
        "20141212"  ||  DateUtil.yyyyMMdd("20141212", null)
        "20141212"  ||  DateUtil.yyyyMMdd("20141212", null)
        "20141130"  ||  DateUtil.yyyyMMddHHmmss("2014-11-30 12:30:24", null)
        "20141225"  ||  DateUtil.yyyyMMddHHmmss("2014-12-25 08:45:11", null)
        "20150317"  ||  DateUtil.yyyyMMdd_HHmm("20150317 0845", null)
        "20150101"  ||  DateUtil.yyyyMMddHHmmssForFile("20150101_084512", null)
        "20150227"  ||  DateUtil.yyyyMMddHHmmssSSS("2015-02-27 08:45:12.111", null)
    }

    def "yyyyMMdd_HHmmString"() {
        expect:
        result == DateUtil.yyyyMMdd_HHmm(date)

        where:
        result              ||  date
        "20141212 0000"     ||  DateUtil.yyMMdd("141212", null)
        "20141212 0000"     ||  DateUtil.yyyyMMdd("20141212", null)
        "20141212 0000"     ||  DateUtil.yyyyMMdd("20141212", null)
        "20141130 1230"     ||  DateUtil.yyyyMMddHHmmss("2014-11-30 12:30:24", null)
        "20141225 0845"     ||  DateUtil.yyyyMMddHHmmss("2014-12-25 08:45:11", null)
        "20150317 0845"     ||  DateUtil.yyyyMMdd_HHmm("20150317 0845", null)
        "20150101 0845"     ||  DateUtil.yyyyMMddHHmmssForFile("20150101_084512", null)
        "20150227 0845"     ||  DateUtil.yyyyMMddHHmmssSSS("2015-02-27 08:45:12.111", null)
    }

    def "yyyyMMddHHmmssString"() {
        expect:
        result == DateUtil.yyyyMMddHHmmss(date)

        where:
        result                  ||  date
        "2014-12-12 00:00:00"   ||  DateUtil.yyMMdd("141212", null)
        "2014-12-12 00:00:00"   ||  DateUtil.yyyyMMdd("20141212", null)
        "2014-12-12 00:00:00"   ||  DateUtil.yyyyMMdd("20141212", null)
        "2014-11-30 12:30:24"   ||  DateUtil.yyyyMMddHHmmss("2014-11-30 12:30:24", null)
        "2014-12-25 08:45:11"   ||  DateUtil.yyyyMMddHHmmss("2014-12-25 08:45:11", null)
        "2015-03-17 08:45:00"   ||  DateUtil.yyyyMMdd_HHmm("20150317 0845", null)
        "2015-01-01 08:45:12"   ||  DateUtil.yyyyMMddHHmmssForFile("20150101_084512", null)
        "2015-02-27 08:45:12"   ||  DateUtil.yyyyMMddHHmmssSSS("2015-02-27 08:45:12.111", null)
    }

    def "yyyyMMddHHmmssForFileString"() {
        expect:
        result == DateUtil.yyyyMMddHHmmssForFile(date)

        where:
        result              ||  date
        "20141212_000000"   ||  DateUtil.yyMMdd("141212", null)
        "20141212_000000"   ||  DateUtil.yyyyMMdd("20141212", null)
        "20141212_000000"   ||  DateUtil.yyyyMMdd("20141212", null)
        "20141130_123024"   ||  DateUtil.yyyyMMddHHmmss("2014-11-30 12:30:24", null)
        "20141225_084511"   ||  DateUtil.yyyyMMddHHmmss("2014-12-25 08:45:11", null)
        "20150317_084500"   ||  DateUtil.yyyyMMdd_HHmm("20150317 0845", null)
        "20150101_084512"   ||  DateUtil.yyyyMMddHHmmssForFile("20150101_084512", null)
        "20150227_084512"   ||  DateUtil.yyyyMMddHHmmssSSS("2015-02-27 08:45:12.111", null)
    }

    def "yyyyMMddHHmmssSSSString"() {
        expect:
        result == DateUtil.yyyyMMddHHmmssSSS(date)

        where:
        result                      ||  date
        "2014-12-12 00:00:00.000"   ||  DateUtil.yyMMdd("141212", null)
        "2014-12-12 00:00:00.000"   ||  DateUtil.yyyyMMdd("20141212", null)
        "2014-12-12 00:00:00.000"   ||  DateUtil.yyyyMMdd("20141212", null)
        "2014-11-30 12:30:24.000"   ||  DateUtil.yyyyMMddHHmmss("2014-11-30 12:30:24", null)
        "2014-12-25 08:45:11.000"   ||  DateUtil.yyyyMMddHHmmss("2014-12-25 08:45:11", null)
        "2015-03-17 08:45:00.000"   ||  DateUtil.yyyyMMdd_HHmm("20150317 0845", null)
        "2015-01-01 08:45:12.000"   ||  DateUtil.yyyyMMddHHmmssForFile("20150101_084512", null)
        "2015-02-27 08:45:12.111"   ||  DateUtil.yyyyMMddHHmmssSSS("2015-02-27 08:45:12.111", null)
    }

    def "EEEDate"() {
        expect:
        result == DateUtil.EEE(stringDate, dateDefault)

        where:
        result                                                  ||  stringDate                          ||  dateDefault
        null                                                    ||  "2014-12-25 08:45:11"               ||  null
        null                                                    ||  ""                                  ||  null
        new Date()                                              ||  ""                                  ||  new Date()
        new Date()                                              ||  "2014-05-27"                        ||  new Date()
        DateUtil.yyyyMMdd("20140527", null)                     ||  "2014-05-27"                        ||  DateUtil.yyyyMMdd("20140527", null)
        DateUtil.yyyyMMdd("20140527", null)                     ||  "2014-12-25 08:45:11"               ||  DateUtil.yyyyMMdd("20140527", null)
        DateUtil.yyyyMMdd("19700106", null)                     ||  "Tue May 27 00:00:00 KST 2014"      ||  null
        DateUtil.yyyyMMdd("19700106", null)                     ||  "Tue May 27 00:00:00 KST 2014"      ||  new Date()
        DateUtil.yyyyMMdd("19700105", null)                     ||  "Mon Nov 11 00:00:00 KST 2013"      ||  DateUtil.yyyyMMdd("20131111", null)
        DateUtil.yyyyMMdd("19700101", null)                     ||  "Thu Dec 25 08:24:11 KST 2014"      ||  DateUtil.yyyyMMddHHmmss("2014-12-25 08:24:11", null)
    }
}

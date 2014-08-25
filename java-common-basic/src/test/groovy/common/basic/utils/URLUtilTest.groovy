package common.basic.utils

import spock.lang.Specification

class URLUtilTest extends Specification {

    def "Escape"() {

        expect:
            URLUtil.escape(url) == result

        where:
            url                                 ||  result
            "http://www.test.com"               ||  "http://www.test.com"
            "http://www.test.com?a=13"          ||  "http://www.test.com?a=13"
            "http://www.test.com?a=|"           ||  "http://www.test.com?a=%7c"
            "http://www.test.com?a=13&b=|"      ||  "http://www.test.com?a=13&b=%7c"
            "http://www.test.com?a = tp"        ||  "http://www.test.com?a%20=%20tp"
            "http://www.test.com?a = tp&b=|"    ||  "http://www.test.com?a%20=%20tp&b=%7c"
    }

    def "EscapePipe"() {

        expect:
            URLUtil.escapePipe(url) == result

        where:
            url                 ||  result
            "|"                 ||  "%7c"
            "||"                ||  "%7c%7c"
            "|test|"            ||  "%7ctest%7c"
            "| |"               ||  "%7c %7c"
            "~!@#\$%^&*()_+|"   ||  "~!@#\$%^&*()_+%7c"
    }

    def "EscapeSpace"() {

        expect:
            URLUtil.escapeSpace(url) == result

        where:
            url                 ||  result
            " "                 ||  "%20"
            "  "                ||  "%20%20"
            " test "            ||  "%20test%20"
            " | "               ||  "%20|%20"
            "~ ! @ # \$"        ||  "~%20!%20@%20#%20\$"
    }

    def "Encode"() {

        expect:
            URLUtil.encode(str) == result

        where:
            str                         ||  result
            "test"                      ||  "test"
            "test input url code"       ||  "test+input+url+code"
            "동해물과 백두산이 마르고 닳도록"   ||  "%EB%8F%99%ED%95%B4%EB%AC%BC%EA%B3%BC+%EB%B0%B1%EB%91%90%EC%82%B0%EC%9D%B4+%EB%A7%88%EB%A5%B4%EA%B3%A0+%EB%8B%B3%EB%8F%84%EB%A1%9D"
            ""                          ||  ""
            null                        ||  ""
    }

    def "DecodeURIComponentIfEncoded"() {

    }

    def "Decode"() {

    }

    def "GetMapListParameter"() {

    }

    def "PrependHttpIfNoProtocol"() {

    }

    def "IsHttpOrHttps"() {

    }

    def "IsHttps"() {

    }

    def "IsHttp"() {

    }

    def "MakeQueryString"() {

    }

    def "Create"() {

    }

    def "ToFilename"() {

    }

    def "ToUriString"() {

    }
}

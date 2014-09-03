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
            str                                 ||  result
            "test"                              ||  "test"
            "test input url code"               ||  "test+input+url+code"
            "http://www.test.com"               ||  "http%3A%2F%2Fwww.test.com"
            "http://www.test.com?param=test"    ||  "http%3A%2F%2Fwww.test.com%3Fparam%3Dtest"
            "동해물과 백두산이 마르고 닳도록"           ||  "%EB%8F%99%ED%95%B4%EB%AC%BC%EA%B3%BC+%EB%B0%B1%EB%91%90%EC%82%B0%EC%9D%B4+%EB%A7%88%EB%A5%B4%EA%B3%A0+%EB%8B%B3%EB%8F%84%EB%A1%9D"
            "https://www.애국가.com"              ||  "https%3A%2F%2Fwww.%EC%95%A0%EA%B5%AD%EA%B0%80.com"
            ""                                  ||  ""
    }

    def "DecodeURIComponentIfEncoded"() {

        expect:
            URLUtil.decodeURIComponentIfEncoded(url) == result

        where:
            url                                                     ||  result
            "http://www.test.com"                                   ||  "http://www.test.com"
            "http://www.test.com?a%20=%20tp&b=%7c"                  ||  "http://www.test.com?a%20=%20tp&b=%7c"
            "http%3A%2F%2Fwww.test.com?a%20=%20tp&b=%7c"            ||  "http://www.test.com?a = tp&b=|"
            "http%3A%2F%2Fwww.test.com"                             ||  "http://www.test.com"
            "https%3A%2F%2Fwww.애국가.com"                            ||  "https://www.애국가.com"
            "https%3A%2F%2Fwww.%EC%95%A0%EA%B5%AD%EA%B0%80.com"     ||  "https://www.애국가.com"
    }

    def "Decode"() {

        expect:
            URLUtil.decode(url) == result

        where:
            url                                                     ||  result
            "http://www.test.com"                                   ||  "http://www.test.com"
            "http://www.test.com?a%20=%20tp&b=%7c"                  ||  "http://www.test.com?a = tp&b=|"
            "http%3A%2F%2Fwww.test.com?a%20=%20tp&b=%7c"            ||  "http://www.test.com?a = tp&b=|"
            "http%3A%2F%2Fwww.test.com"                             ||  "http://www.test.com"
            "https%3A%2F%2Fwww.애국가.com"                            ||  "https://www.애국가.com"
            "https%3A%2F%2Fwww.%EC%95%A0%EA%B5%AD%EA%B0%80.com"     ||  "https://www.애국가.com"
    }

    def "GetMapListParameter"() {

    }

    def "PrependHttpIfNoProtocol"() {

        expect:
            URLUtil.prependHttpIfNoProtocol(url) == result

        where:
            url                     ||  result
            ""                      ||  ""
            " "                     ||  " "
            "|"                     ||  "http://|"
            "http://www.test.com"   ||  "http://www.test.com"
            "https://www.test.com"  ||  "https://www.test.com"
            "test"                  ||  "http://test"
            "test.com"              ||  "http://test.com"
    }

    def "IsHttpOrHttps"() {

        expect:
            URLUtil.isHttpOrHttps(url) == result

        where:
            url                     ||  result
            ""                      ||  false
            " "                     ||  false
            "|"                     ||  false
            "http://www.test.com"   ||  true
            "https://www.test.com"  ||  true
            " http://www.test.com"  ||  false
            "test"                  ||  false
            "test.com"              ||  false
    }

    def "IsHttps"() {

        expect:
            URLUtil.isHttps(url) == result

        where:
            url                     ||  result
            ""                      ||  false
            " "                     ||  false
            "|"                     ||  false
            "http://www.test.com"   ||  false
            "https://www.test.com"  ||  true
            " http://www.test.com"  ||  false
            "test"                  ||  false
            "test.com"              ||  false
    }

    def "IsHttp"() {

        expect:
            URLUtil.isHttp(url) == result

        where:
            url                     ||  result
            ""                      ||  false
            " "                     ||  false
            "|"                     ||  false
            "http://www.test.com"   ||  true
            "https://www.test.com"  ||  false
            " http://www.test.com"  ||  false
            "test"                  ||  false
            "test.com"              ||  false
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

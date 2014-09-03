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

    @SuppressWarnings("GroovyPointlessBoolean")
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

    @SuppressWarnings("GroovyPointlessBoolean")
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

    @SuppressWarnings("GroovyPointlessBoolean")
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

        expect:
            URLUtil.makeQueryString(map) == result

        where:
        map                                                                               ||  result
        new HashMapStringObject().and("a", 3).and("b", 2).and("c", 1)                     ||  "b=2&c=1&a=3"
        new HashMapStringObject().and("A", 1).and("B", 2).and("C", 3)                     ||  "A=1&B=2&C=3"
        new HashMapStringObject().and("song", "애국가").and("country", "대한민국")            ||  "song=%EC%95%A0%EA%B5%AD%EA%B0%80&country=%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD"
        new HashMapStringObject().and("song", "The Reason").and("singer", "hoobas|tank")  ||  "song=The+Reason&singer=hoobas%7Ctank"
        new HashMapStringObject().and("search name", "이재범").and("search age", "28살")    ||  "search+name=%EC%9D%B4%EC%9E%AC%EB%B2%94&search+age=28%EC%82%B4"
    }

    def "Create"() {

        expect:
            URLUtil.create(url) == result

        where:
            url                     ||  result
            "http://www.test.com"   ||  new URL("http://www.test.com")
            "test"                  ||  null
            "www.test.com"          ||  null
            "http://test.com"       ||  new URL("http://test.com")
            "http://test"           ||  new URL("http://test")
            "http://"               ||  new URL("http://")
            "https://"              ||  new URL("https://")
    }

    def "ToFilename"() {

        expect:
        URLUtil.toFilename(url) == result

        where:
        url                             ||  result
        "http://www.test.com"           ||  "http：／／www.test.com"
        "fileName=<\\test file\\>"      ||  "fileName=〈＼test file＼〉"
        '"test.pds"'                    ||  "〃test.pds〃"

        '\\, /, :, *, ?, ", <, >, |'    ||  "＼, ／, ：, ∗, ？, 〃, 〈, 〉, ｜"

        '", ~, !, @, #, $, %, ^, &, *, (, ), _, +, -, =, `, < , >, ?, /, ;, :, [, ], {, }, \\, |"'  ||
        "〃, ~, !, @, #, \$, %, ^, &, ∗, (, ), _, +, -, =, `, 〈 , 〉, ？, ／, ;, ：, [, ], {, }, ＼, ｜〃"
    }

    def "ToUriString"() {

        expect:
            URLUtil.toUriString(url) == result

        where:
        url                             ||  result
        "http：／／www.test.com"          ||  "http://www.test.com"
        "fileName=〈＼test file＼〉"        ||  "fileName=<\\test file\\>"
        "〃test.pds〃"                   ||  '"test.pds"'

        "＼, ／, ：, ∗, ？, 〃, 〈, 〉, ｜"    ||  '\\, /, :, *, ?, \", <, >, |'

        "〃, ~, !, @, #, \$, %, ^, &, ∗, (, ), _, +, -, =, `, 〈 , 〉, ？, ／, ;, ：, [, ], {, }, ＼, ｜〃"  ||
        '", ~, !, @, #, $, %, ^, &, *, (, ), _, +, -, =, `, < , >, ?, /, ;, :, [, ], {, }, \\, |"'
    }
}

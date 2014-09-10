package common.basic.utils

import spock.lang.Specification

class URLUtilTest extends Specification {

    def "ctor"() {

        when: new URLUtil()
        then: thrown(InstantiationException)
    }

    def "Escape"() {

        expect:
        URLUtil.escape(url) == result

        where:
        url                                ||  result
        "http://example.com"               ||  "http://example.com"
        "http://example.com?a=13"          ||  "http://example.com?a=13"
        "http://example.com?a=|"           ||  "http://example.com?a=%7c"
        "http://example.com?a=13&b=|"      ||  "http://example.com?a=13&b=%7c"
        "http://example.com?a = tp"        ||  "http://example.com?a%20=%20tp"
        "http://example.com?a = tp&b=|"    ||  "http://example.com?a%20=%20tp&b=%7c"
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
        ""                                  ||  ""
        null                                ||  ""
        "test"                              ||  "test"
        "test input url code"               ||  "test+input+url+code"
        "보고있나"                            ||  "%EB%B3%B4%EA%B3%A0%EC%9E%88%EB%82%98"
        "http://example.com"                ||  "http%3A%2F%2Fexample.com"
        "http://example.com?param=test"     ||  "http%3A%2F%2Fexample.com%3Fparam%3Dtest"
        "https://example.com"               ||  "https%3A%2F%2Fexample.com"
        "https://보고있나.com"                ||  "https%3A%2F%2F%EB%B3%B4%EA%B3%A0%EC%9E%88%EB%82%98.com"
    }

    def "DecodeURIComponentIfEncoded"() {

        expect:
        URLUtil.decodeURIComponentIfEncoded(url) == result

        where:
        url                                                              ||  result
        "http://example.com"                                             ||  "http://example.com"
        "http://example.com?a%20=%20tp&b=%7c"                            ||  "http://example.com?a%20=%20tp&b=%7c"
        "http%3A%2F%2Fexample.com?a%20=%20tp&b=%7c"                      ||  "http://example.com?a = tp&b=|"
        "http%3A%2F%2Fexample.com"                                       ||  "http://example.com"
        "https%3A%2F%2Fwww.보고있나.com"                                   ||  "https://www.보고있나.com"
        "https%3A%2F%2Fwww.%EB%B3%B4%EA%B3%A0%EC%9E%88%EB%82%98.com"     ||  "https://www.보고있나.com"
    }

    def "Decode"() {
        expect:
        URLUtil.decode(url) == result

        where:
        url                                                          ||  result
        null                                                         ||  ""
        "http://example.com"                                         ||  "http://example.com"
        "http://example.com?a%20=%20tp&b=%7c"                        ||  "http://example.com?a = tp&b=|"
        "http%3A%2F%2Fexample.com?a%20=%20tp&b=%7c"                  ||  "http://example.com?a = tp&b=|"
        "http%3A%2F%2Fexample.com"                                   ||  "http://example.com"
        "https%3A%2F%2F%EB%B3%B4%EA%B3%A0%EC%9E%88%EB%82%98.com"     ||  "https://보고있나.com"
        "https%3A%2F%2F%EB%B3%B4%EA%B3%A0%EC%9E%88%EB%82%98.com"     ||  "https://보고있나.com"
    }

    def "GetMapListParameter"() {
        expect:
        result == URLUtil.getMapListParameter(url)

        where:
        result                                                ||  url
        [:]                                                   ||  "djakhdsjkdlsjf"
        [:]                                                   ||  "http://example.com"
        [:]                                                   ||  "http://example.com?"
        ["a" : ["1"], "b" : ["2"]]                            ||  "http://example.com?a=1&b=2"
        ["a" : ["1"], "b" : ["2", "3"]]                       ||  "http://example.com?a=1&b=2&b=3"
    }

    def "PrependHttpIfNoProtocol"() {

        expect:
        URLUtil.prependHttpIfNoProtocol(url) == result

        where:
        url                     ||  result
        ""                      ||  ""
        " "                     ||  " "
        "|"                     ||  "http://|"
        "http://example.com"    ||  "http://example.com"
        "https://example.com"   ||  "https://example.com"
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
        "http://example.com"    ||  true
        "https://example.com"   ||  true
        " http://example.com"   ||  false
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
        "http://example.com"    ||  false
        "https://example.com"   ||  true
        " http://example.com"   ||  false
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
        "http://example.com"    ||  true
        "https://example.com"   ||  false
        " http://example.com"   ||  false
        "test"                  ||  false
        "test.com"              ||  false
    }

    def "MakeQueryString"() {

        expect:
        URLUtil.makeQueryString(map) == result

        where:
        map                                            ||  result
        ["a":3, "b":2, "c":1]                          ||  "a=3&b=2&c=1"
        ["A":1, "B":2, "C":3]                          ||  "A=1&B=2&C=3"
        ["song":"보고있나", "country":"대한민국"]           ||  "song=%EB%B3%B4%EA%B3%A0%EC%9E%88%EB%82%98&country=%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD"
        ["song":"The Reason", "singer":"hoobas|tank"]  ||  "song=The+Reason&singer=hoobas%7Ctank"
        ["search name":"이순신", "search age":"28살"]    ||  "search+name=%EC%9D%B4%EC%88%9C%EC%8B%A0&search+age=28%EC%82%B4"
    }

    def "Create"() {

        expect:
        URLUtil.create(url) == result

        where:
        url                     ||  result
        "http://example.com"    ||  new URL("http://example.com")
        "test"                  ||  null
        "example.com"           ||  null
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
        "http://example.com"            ||  "http：／／example.com"
        "fileName=<\\test file\\>"      ||  "fileName=〈＼test file＼〉"
        '"test.pds"'                    ||  "〃test.pds〃"

        '\\, /, :, *, ?, ", <, >, |'    ||
        "＼, ／, ：, ∗, ？, 〃, 〈, 〉, ｜"

        '", ~, !, @, #, $, %, ^, &, *, (, ), _, +, -, =, `, < , >, ?, /, ;, :, [, ], {, }, \\, |"'  ||
        "〃, ~, !, @, #, \$, %, ^, &, ∗, (, ), _, +, -, =, `, 〈 , 〉, ？, ／, ;, ：, [, ], {, }, ＼, ｜〃"
    }

    def "ToUriString"() {

        expect:
        URLUtil.toUriString(url) == result

        where:
        url                             ||  result
        "http：／／example.com"           ||  "http://example.com"
        "fileName=〈＼test file＼〉"        ||  "fileName=<\\test file\\>"
        "〃test.pds〃"                   ||  '"test.pds"'

        "＼, ／, ：, ∗, ？, 〃, 〈, 〉, ｜"    ||
        '\\, /, :, *, ?, \", <, >, |'

        "〃, ~, !, @, #, \$, %, ^, &, ∗, (, ), _, +, -, =, `, 〈 , 〉, ？, ／, ;, ：, [, ], {, }, ＼, ｜〃"  ||
        '", ~, !, @, #, $, %, ^, &, *, (, ), _, +, -, =, `, < , >, ?, /, ;, :, [, ], {, }, \\, |"'
    }
}

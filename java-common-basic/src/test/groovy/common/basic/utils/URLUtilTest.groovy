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

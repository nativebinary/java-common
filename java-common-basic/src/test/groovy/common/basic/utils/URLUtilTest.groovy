package common.basic.utils

import spock.lang.Specification

class URLUtilTest extends Specification {

    def "Escape"() {

        expect:
            URLUtil.escape(url) == i

        where:
            url                             ||  i
            "http://www.test.com"           ||  "http://www.test.com"
            "http://www.test.com?a=|"       ||  "http://www.test.com?a=%7c"
            "http://www.test.com?a=13"      ||  "http://www.test.com?a=13"
            "http://www.test.com?a = 13"    ||  "http://www.test.com?a%20=%2013"
            "http://www.test.com?a = || 13" ||  "http://www.test.com?a%20=%20%7c%7c%2013"
    }

    def "EscapePipe"() {

        expect:
            URLUtil.escapePipe(url) == i

        where:
            url                             ||  i
            "|"                             ||  "%7c"
            "||"                            ||  "%7c%7c"
            " "                             ||  " "
            " | "                           ||  " %7c "
            "~!@#%^&*()_+\$"                ||  "~!@#%^&*()_+\$"
            "http://www.naver.com?a = |"    ||  "http://www.naver.com?a = %7c"
    }

    def "EscapeSpace"() {

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

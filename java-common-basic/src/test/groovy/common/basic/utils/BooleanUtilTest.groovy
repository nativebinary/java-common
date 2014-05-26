package common.basic.utils

import spock.lang.Specification

class BooleanUtilTest extends Specification {
    def "ctor"() {
        when: new BooleanUtil()
        then: thrown(InstantiationException)
    }

    def "Parse"() {
        expect:
        b == BooleanUtil.parse(s)

        where:
        s       | b
        ""      | false
        "false" | false
        "true"  | true
    }

    def "ToString"() {
        expect:
        s == BooleanUtil.toString(b)

        where:
        s       | b
        "false" | false
        "true"  | true
    }

    def "Convert01"() {
        expect:
        BooleanUtil.convert01(s) == b
        BooleanUtil.convert01(b) == s

        where:
        s     | b
        "0"   | false
        "1"   | true
    }

    def "ConvertYN"() {
        expect:
        BooleanUtil.convertYN(s) == b
        BooleanUtil.convertYN(b) == s

        where:
        s     | b
        "Y"   | true
        "N"   | false
    }
}

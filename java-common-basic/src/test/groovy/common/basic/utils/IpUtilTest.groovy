package common.basic.utils

import spock.lang.Specification

class IpUtilTest extends Specification {
    def "ctor"() {
        when: new IpUtil()
        then: thrown(InstantiationException)
    }

    def "toArrayByte"() {
        expect:
        i == IpUtil.toArrayByte(s)

        where:
        i              || s
        null           || "abc"
        [0, 0, 0, 0]   || "0.0.0.0"
        [127, 0, 0, 1] || "127.0.0.1"
    }

    def "toInt"() {
        expect:
        i == IpUtil.toInt(s)

        where:
        i           || s
        0           || "0.0.0.0"
        2130706433  || "127.0.0.1"
    }

    def "cidrToMask"() {
        expect:
        i == IpUtil.cidrToMask(cidr)

        where:
        i           || cidr
        2147483648  || 1
        3221225472  || 2
        3758096384  || 3
        4026531840  || 4
        4160749568  || 5
        4227858432  || 6
        4261412864  || 7
        4278190080  || 8
        4294901760  || 16
        4294967295  || 32
    }

}

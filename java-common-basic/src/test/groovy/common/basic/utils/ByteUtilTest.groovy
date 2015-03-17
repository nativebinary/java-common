package common.basic.utils

import spock.lang.Specification

@SuppressWarnings("GroovyPointlessBoolean")
class ByteUtilTest extends Specification {
    def "ctor"() {
        when: new ByteUtil()
        then: thrown(InstantiationException)
    }

    def "ToInt"() {
        expect:
        i == ByteUtil.toInt(array)

        where:
        i           || array
        0           || [0, 0, 0, 0] as byte[]
        2130706433  || [127, 0, 0, 1] as byte[]
        2134922241  || [127, 64, 84, 1] as byte[]
        -1          || [255, 255, 255, 255] as byte[]
        -1          || [] as byte[]
        -1          || [0] as byte[]
        -1          || [0, 0] as byte[]
        -1          || [0, 0, 0] as byte[]
    }

}

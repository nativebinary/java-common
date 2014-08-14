package common.basic.utils

import spock.lang.Specification

@SuppressWarnings("GroovyPointlessBoolean")
class IntUtilTest extends Specification {
    def "ctor"() {
        when: new IntUtil()
        then: thrown(InstantiationException)
    }


    def "ParseInt"() {
        expect:
        i == IntUtil.parseInt(s, 0)

        where:
        i           || s
        0           || null
        0           || ""
        0           || "aa"
        1           || "1"
        100         || "100"
        -1000       || "-1000"
        11          || "011"
        11          || "0011"
        11          || "00011"
    }

    def "GenerateListRange"() {
        expect:
        list.size() == i;
        list == IntUtil.generateListRange(i);

        where:
        list                      || i
        []                        || 0
        [0]                       || 1
        [0, 1, 2, 3, 4]           || 5
    }
}

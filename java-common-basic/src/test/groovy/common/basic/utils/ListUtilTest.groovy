package common.basic.utils

import spock.lang.Specification

class ListUtilTest extends Specification {
    def "ctor"() {
        when: new IntUtil()
        then: thrown(InstantiationException)
    }

    def "Swap"() {
        def range = IntUtil.generateListRange(5)
        ListUtil.swap(range, i1, i2)

        expect:
        list == range

        where:
        list                      || i1 || i2
        [1, 0, 2, 3, 4]           || 0  || 1
        [0, 3, 2, 1, 4]           || 1  || 3
        [0, 4, 2, 3, 1]           || 4  || 1
        [4, 1, 2, 3, 0]           || 0  || 4
    }
}

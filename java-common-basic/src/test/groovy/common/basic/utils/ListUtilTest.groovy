package common.basic.utils

import spock.lang.Specification

class ListUtilTest extends Specification {
    def "ctor"() {
        when: new IntUtil()
        then: thrown(InstantiationException)
    }

    def "get"() {
        expect:
        ListUtil.getByInfiniteIndex([1, 2, 3, 4, 5], i) == v

        where:
        i       | v
        -10     | 1
        -9      | 2
        -8      | 3
        -7      | 4
        -6      | 5
        -5      | 1
        -4      | 2
        -3      | 3
        -2      | 4
        -1      | 5
        0       | 1
        1       | 2
        2       | 3
        3       | 4
        4       | 5
        5       | 1
        6       | 2
        7       | 3
        8       | 4
        9       | 5
        10      | 1
        11      | 2
        12      | 3
        13      | 4
        14      | 5
        15      | 1
        16      | 2
        17      | 3
        18      | 4
        19      | 5
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

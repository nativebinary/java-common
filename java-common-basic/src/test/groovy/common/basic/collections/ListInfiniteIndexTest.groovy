package common.basic.collections

import spock.lang.Specification

class ListInfiniteIndexTest extends Specification {
    def "get"() {
        ListInfiniteIndex<Integer> list = new ListInfiniteIndex<>();
        list.addAll([1, 2, 3, 4, 5]);
        expect:
        list.get(i) == v
        -5 % 3 == -2

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
}

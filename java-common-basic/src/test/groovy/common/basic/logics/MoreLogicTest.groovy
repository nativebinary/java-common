package common.basic.logics

import spock.lang.Specification

class MoreLogicTest extends Specification {
    def "hasMore"() {
        def list = src;
        def result = MoreLogic.hasMore(list, 5)
        expect:
        result == b;
        list == expect


        where:
        src                               | b     | expect
        [1]                                | false | [1]
        [1, 2]                             | false | [1, 2]
        [1, 2, 3]                          | false | [1, 2, 3]
        [1, 2, 3, 4]                       | false | [1, 2, 3, 4]
        [1, 2, 3, 4, 5]                    | false | [1, 2, 3, 4, 5]
        [1, 2, 3, 4, 5, 6]                 | true  | [1, 2, 3, 4, 5]
        [1, 2, 3, 4, 5, 6, 7]              | true  | [1, 2, 3, 4, 5, 6]
    }
}

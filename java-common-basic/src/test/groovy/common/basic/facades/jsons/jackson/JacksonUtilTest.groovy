package common.basic.facades.jsons.jackson

import spock.lang.Specification

class JacksonUtilTest extends Specification {
    def "toMap"() {
        expect:
        JacksonUtil.toMap(input) == expect;

        where:
        input                                                                                || expect
        "{\"a\": { \"b\": { \"c\": \"d\", \"E\": \"F\", \"G\": [10] } }, \"H\": [1, 2, 3] }" || [a: [b: [c: "d", E: "F", G: [10]]], H: [1, 2, 3]]
    }

    def "toListMap"() {
        expect:
        JacksonUtil.toListMap(input) == expect;

        where:
        input                            || expect
        "[ { \"a\": 1}, { \"b\": 2 }, { \"c\": \"d\"} ]" || [[a:1], [b:2], [c:"d"]]


    }
}

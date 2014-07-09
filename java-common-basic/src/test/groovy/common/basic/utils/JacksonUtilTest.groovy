package common.basic.utils

import common.basic.jsons.JacksonUtil
import spock.lang.Specification

class JacksonUtilTest extends Specification {
    def "toMap"() {
        expect:
        JacksonUtil.toMap(input) == expect;

        where:
        input                                                                                || expect
        "{\"a\": { \"b\": { \"c\": \"d\", \"E\": \"F\", \"G\": [10] } }, \"H\": [1, 2, 3] }" || [a: [b: [c: "d", E: "F", G: [10]]], H: [1, 2, 3]]
    }
}

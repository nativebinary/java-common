package common.basic.jsons

import common.basic.geometiries.Point
import spock.lang.Specification

class JsonUtilTest extends Specification {
    def "ToJson"() {
        expect:
        JsonUtil.toJson(o) == s

        where:
        o       || s
        1       || "1"
    }

    def "FromJson"() {
        expect:
        JsonUtil.fromJson(s, c) == o

        where:
         s              || c            || o
         "{x:10,y:20}"  || Point.class  || new Point(10, 20)
    }
}

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

    def "FromJsonWithGson"() {
        setup:
        def old = JsonUtil.setJsonEngine(new JsonEngineGson());

        expect:
        JsonUtil.fromJson(s, c) == o

        cleanup:
        JsonUtil.setJsonEngine(old);

        where:
        s                      || c            || o
        "{\"x\":10,\"y\":20}"  || Point.class  || new Point(10, 20)

    }

    def "FromJsonWithJackson"() {

        setup:
        def old = JsonUtil.setJsonEngine(new JsonEngineJackson());

        expect:
        JsonUtil.fromJson(s, c) == o

        cleanup:
        JsonUtil.setJsonEngine(old);

        where:
        s                      || c            || o
        "{\"x\":10,\"y\":20}"  || Point.class  || new Point(10, 20)
    }
}

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
        def original = JsonUtil.json;
        JsonUtil.json = new IJsonGson();

        expect:
        JsonUtil.fromJson(s, c) == o

        cleanup:
        JsonUtil.json = original;

        where:
        s                      || c            || o
        "{\"x\":10,\"y\":20}"  || Point.class  || new Point(10, 20)

    }

    def "FromJsonWithJackson"() {

        setup:
        def original = JsonUtil.json;
        JsonUtil.json = new IJsonJackson();

        expect:
        JsonUtil.fromJson(s, c) == o

        cleanup:
        JsonUtil.json = original;

        where:
        s                      || c            || o
        "{\"x\":10,\"y\":20}"  || Point.class  || new Point(10, 20)
    }
}

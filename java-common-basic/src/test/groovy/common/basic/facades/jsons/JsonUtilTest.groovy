package common.basic.facades.jsons
import common.basic.geometiries.Point
import common.basic.utils.TestClass
import spock.lang.Specification

abstract class JsonUtilTest extends Specification {
    abstract IJsonEngine createJsonEngine()

    def old;
    def setup() {
        old = JsonUtil.setJsonEngine(createJsonEngine());
    }

    def cleanup() {
        JsonUtil.setJsonEngine(old);
    }

    def "ctor"() {
        when: new JsonUtil()
        then: thrown(InstantiationException)
    }

    def "toJson"() {
        expect:
        result == JsonUtil.toJson(param);

        where:
        param             || result
        1                 || "1"
        new Point(10, 20) || "{\"x\":10,\"y\":20}"
    }

    def "fromJson"() {
        expect:
        result == JsonUtil.fromJson(paramJson, paramClass);

        where:
        paramJson                      || paramClass            || result
        "{\"x\":10,\"y\":20}"          || Point.class           || new Point(10, 20)
    }


    def "toList"() {
        expect:
        result == JsonUtil.toList(param);

        where:
        param       || result
        "[1, 2]"    || [1, 2]
    }

    def "toMap"() {
        expect:
        result == JsonUtil.toMap(param);

        where:
        param           || result
        "{\"A\":10}"    || ["A":10]
    }

    def "toListMap"() {
        expect:
        result == JsonUtil.toListMap(param);


        cleanup:
        JsonUtil.setJsonEngine(old);

        where:

        param                                     || result
        "[{\"A\":10}, {\"B\":20}, {\"C\":30}]"    || [["A":10.0], ["B":20.0], ["C":30.0]]
    }

    class TempClass {
        String aaa;
        String bbb;
    }

}

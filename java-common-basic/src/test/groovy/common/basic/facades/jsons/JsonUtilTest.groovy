package common.basic.facades.jsons

import common.basic.facades.jsons.gson.JsonEngineGson
import common.basic.facades.jsons.jackson.JsonEngineJackson
import common.basic.geometiries.Point
import common.basic.utils.MapUtil
import spock.lang.Specification

class JsonUtilTest extends Specification {
    def "toJson"() {
        setup:
        def func = { p -> JsonUtil.toJson(p); }

        def old = JsonUtil.setJsonEngine(new JsonEngineGson());
        def gson = func(param);
        def old2 = JsonUtil.setJsonEngine(new JsonEngineJackson());
        def jackson = func(param);

        expect:
        result == gson; gson == jackson; jackson == result

        cleanup:
        JsonUtil.setJsonEngine(old);

        where:
        param       || result
        1           || "1"
    }


    def "fromJson"() {
        setup:
        def func = { p1, p2 ->  JsonUtil.fromJson(p1, p2); }

        def old = JsonUtil.setJsonEngine(new JsonEngineGson());
        def gson = func(paramJson, paramClass);
        def old2 = JsonUtil.setJsonEngine(new JsonEngineJackson());
        def jackson = func(paramJson, paramClass);

        expect:
        result == gson; gson == jackson; jackson == result

        cleanup:
        JsonUtil.setJsonEngine(old);

        where:
        paramJson                      || paramClass            || result
        "{\"x\":10,\"y\":20}"          || Point.class           || new Point(10, 20)

    }


    def "toList"() {
        setup:
        def func = { p -> JsonUtil.toList(p); }

        def old = JsonUtil.setJsonEngine(new JsonEngineGson());
        def gson = func(param);
        def old2 = JsonUtil.setJsonEngine(new JsonEngineJackson());
        def jackson = func(param);

        expect:
        result == gson; gson == jackson; jackson == result

        cleanup:
        JsonUtil.setJsonEngine(old);

        where:
        param       || result
        "[1, 2]"    || [1, 2]
    }


    def "toMap"() {
        setup:
        def func = { p -> JsonUtil.toMap(p); }

        def old = JsonUtil.setJsonEngine(new JsonEngineGson());
        def gson = func(param);
        def old2 = JsonUtil.setJsonEngine(new JsonEngineJackson());
        def jackson = func(param);

        expect:
        MapUtil.equals(result, gson); MapUtil.equals(gson, jackson); jackson == result


        cleanup:
        JsonUtil.setJsonEngine(old);

        where:

        param           || result
        "{\"A\":10}"    || ["A":10]
    }


    def "toListMap"() {
        setup:
        def func = { p -> JsonUtil.toListMap(p); }

        def old = JsonUtil.setJsonEngine(new JsonEngineGson());
        def gson = func(param);
        def old2 = JsonUtil.setJsonEngine(new JsonEngineJackson());
        def jackson = func(param);

        expect:
        result == gson; gson == jackson; jackson == result


        cleanup:
        JsonUtil.setJsonEngine(old);

        where:

        param                                     || result
        "[{\"A\":10}, {\"B\":20}, {\"C\":30}]"    || [["A":10.0], ["B":20.0], ["C":30.0]]
    }
}

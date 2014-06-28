package common.basic.utils

import spock.lang.Specification

@SuppressWarnings("GroovyPointlessBoolean")
class MapUtilTest extends Specification {
    def "ctor"() {
        when: new MapUtil()
        then: thrown(InstantiationException)
    }

    def "IsNullOrEmpty"() {
        expect:
        b == MapUtil.isNullOrEmpty(m)

        where:
        b           || m
        true        || null
        true        || new HashMap<String, String>()
        false       || new MapBuilderT<String, String>().and("A", "B")
    }

    def "convertYNStringToBoolean"() {
        def map = new MapBuilderT<String, Object>()
                .and("Y", "Y")
                .and("y", "y")
                .and("N", "N")
                .and("N", "n")

        MapUtil.convertYNStringToBoolean(map, "Y", "y", "N", "n")

        expect:
        (boolean)map.get("Y")
        (boolean)map.get("y")
        !(boolean)map.get("N")
        !(boolean)map.get("n")
    }

    def "convertBigDecimalToInt"() {
        def map = new MapBuilderT<String, Object>()
                .and("i", 1)
                .and("bd", new BigDecimal(1))

        MapUtil.convertBigDecimalToInt(map)

        expect:
        1 == (int)map.get("i")
        1 == (int)map.get("bd")
    }

    def ""(){
        def map = new MapBuilderT<String, Object>()
                .and("a", "a")
                .and("b", "b")

        def result1 = MapUtil.transformKey(map, { _ -> _ } as MapUtil.ITransform<String>);
        def result2 = MapUtil.transformKey(map, { _ -> "K" + _ } as MapUtil.ITransform<String>);

        expect:
        "a" == result1.get("a")
        "b" == result1.get("b")
        "a" == result2.get("Ka")
        "b" == result2.get("Kb")
    }
}

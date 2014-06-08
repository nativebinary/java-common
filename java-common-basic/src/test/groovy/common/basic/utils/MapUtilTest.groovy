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
}

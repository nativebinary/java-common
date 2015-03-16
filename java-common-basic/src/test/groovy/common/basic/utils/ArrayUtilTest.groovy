package common.basic.utils

import spock.lang.Specification

@SuppressWarnings("GroovyPointlessBoolean")
class ArrayUtilTest extends Specification {
    def "ctor"() {
        when: new ArrayUtil()
        then: thrown(InstantiationException)
    }

    def "isNullOrEmpty"() {
        expect:
        i == ArrayUtil.isNullOrEmpty(a)

        where:
        i       ||  a
        true    ||  null
        false   ||  "String"
        false   ||  10
        true    ||  new String[0]
        false   ||  new String[10]
        false   ||  new ArrayList<String>()
        false   ||  new ArrayList<String>().add("1")
    }
}

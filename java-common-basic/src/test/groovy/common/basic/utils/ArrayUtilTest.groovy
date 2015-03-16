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
        true    ||  new String[0]
        false   ||  new String[10]
        false   ||  new ArrayList<String>()
        false   ||  new ArrayList<String>().add("1")
    }

    def "startsWith"() {
        expect:
        i == ArrayUtil.startsWith(array1 as String[], array2 as String[])

        where:
        i       ||  array1                  ||  array2
        true    ||  ["1"]                   ||  ["1"]
        false   ||  ["1"]                   ||  ["2"]
        true    ||  ["1", "2"]              ||  ["1"]
        true    ||  ["1", "2"]              ||  ["1", "2"]
        false   ||  ["1", "2"]              ||  ["2", "1"]
        false   ||  ["1", "2"]              ||  ["1", "1"]
        true    ||  ["1", "2", "3"]         ||  ["1"]
        true    ||  ["1", "2", "3"]         ||  ["1", "2"]
        true    ||  ["1", "2", "3"]         ||  ["1", "2", "3"]
        false   ||  ["1", "2", "3"]         ||  ["3", "2", "1"]
        false   ||  ["1", "2", "3"]         ||  ["1", "3", "2"]
        false   ||  ["1", "2", "3"]         ||  ["1", "3"]
    }
}

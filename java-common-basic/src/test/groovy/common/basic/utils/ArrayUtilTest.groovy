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
        false   ||  ["10", "20"] as String[]
        true    ||  [] as String[]
        true    ||  [] as Object[]
        true    ||  [] as Integer[]
        false   ||  [1, 2, 3, 4, 5] as Integer[]
        false   ||  [true, true, false] as boolean[]
        false   ||  [null, null, null] as String[]
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

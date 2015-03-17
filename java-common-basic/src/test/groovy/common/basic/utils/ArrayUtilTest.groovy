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
        b == ArrayUtil.isNullOrEmpty(array)

        where:
        b       ||  array
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
        b == ArrayUtil.startsWith(array1 as String[], array2 as String[])

        where:
        b       ||  array1                  ||  array2
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

    def "isNull"() {
        expect:
        result == ArrayUtil.isNull(array, arrayWhenNull)

        where:
        result      ||  array                   ||  arrayWhenNull
        ["2"]       ||  null                    ||  ["2"] as String[]
        null        ||  null                    ||  null
        [1]         ||  [1] as Integer[]        ||  null
        ["1"]       ||  ["1"] as String[]       ||  ["2"] as String[]
        [1]         ||  [1] as Integer[]        ||  ["2"] as String[]
        []          ||  [] as Integer[]         ||  ["2"] as String[]
        ["1", "2"]  ||  ["1", "2"] as String[]  ||  [1, 2] as String[]
    }

    def "slice"() {
        expect:
        result == ArrayUtil.slice(byteArray as byte[], i, length)

        where:
        result          ||  byteArray           ||  i       ||  length
        []              ||  [1, 2, 3]           ||  0       ||  0
        [1]             ||  [1, 2, 3]           ||  0       ||  1
        [1, 2]          ||  [1, 2, 3]           ||  0       ||  2
        [1, 2, 3]       ||  [1, 2, 3]           ||  0       ||  3
        []              ||  [1, 2, 3]           ||  1       ||  0
        [2]             ||  [1, 2, 3]           ||  1       ||  1
        [2, 3]          ||  [1, 2, 3]           ||  1       ||  2
        [2, 3, 0]       ||  [1, 2, 3]           ||  1       ||  3
        [2, 3, 0, 0]    ||  [1, 2, 3]           ||  1       ||  4
        [3]             ||  [1, 2, 3]           ||  2       ||  1
        [3, 0]          ||  [1, 2, 3]           ||  2       ||  2
        [3, 0, 0]       ||  [1, 2, 3]           ||  2       ||  3
    }
}

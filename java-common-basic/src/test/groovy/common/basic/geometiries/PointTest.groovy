package common.basic.geometiries

import spock.lang.Specification

class PointTest extends Specification {

    def "Absolute"() {

        expect:
        point.absolute() == result

        where:
        point            ||  result
        new Point(10, 10)   ||  new Point(10, 10)
        new Point(15, 20)   ||  new Point(15, 20)
        new Point(47, 8)    ||  new Point(47, 8)
    }

    def "Negate"() {


        expect:
        point.negate() == result

        where:
        point                   ||  result
        new Point(10, 10)       ||  new Point(-10, -10)
        new Point(15, 20)       ||  new Point(-15, -20)
        new Point(47, 8)        ||  new Point(-47, -8)
        new Point(30, 0)        ||  new Point(-30, 0)
        new Point(-82, -51)     ||  new Point(82, 51)
        new Point(11, -72)      ||  new Point(-11, 72)
        new Point(-39, 29)      ||  new Point(39, -29)
        new Point(100, 200)     ||  new Point(-100, -200)
        new Point(3678, -98)    ||  new Point(-3678, 98)
        new Point(-9834, -9999) ||  new Point(9834, 9999)
    }

    def "Multiply"() {

        expect:
        point.multiply(param) == result

        where:
        point                   ||  param   ||  result
        new Point(10, 10)       ||  5       ||  new Point(10 * 5, 10 * 5)
        new Point(15, 20)       ||  13      ||  new Point(15 * 13, 20 * 13)
        new Point(47, 8)        ||  -6      ||  new Point(47 * (-6), 8 * (-6))
        new Point(30, 0)        ||  -9      ||  new Point(30 * (-9), 0)
        new Point(-82, -51)     ||  76      ||  new Point(-82 * 76, -51 * 76)
        new Point(11, -72)      ||  11      ||  new Point(11 * 11, -72 * 11)
        new Point(-39, 29)      ||  35      ||  new Point(-39 * 35, 29 * 35)
        new Point(100, 200)     ||  -96     ||  new Point(100 * (-96), 200 * (-96))
        new Point(3678, -98)    ||  27      ||  new Point(3678 * 27, -98 * 27)
        new Point(-9834, -9999) || -8       ||  new Point(-9834 * (-8), -9999 * (-8))
    }

    def "DivideBy"() {

    }

    def "Delta"() {

    }

    def "Median"() {

    }

    def "Offset"() {

    }

    def "Advance"() {

    }

    def "Differ"() {

    }

    def "Equals"() {

    }

    def "HashCode"() {

    }

    def "ToString"() {

    }
}

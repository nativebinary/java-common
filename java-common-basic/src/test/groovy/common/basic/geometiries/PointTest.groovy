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
        point            ||  result
        new Point(10, 10)   ||  new Point(-10, -10)
        new Point(15, 20)   ||  new Point(-15, -20)
        new Point(47, 8)    ||  new Point(-47, -8)
        new Point(-10, -10) ||  new Point(10, 10)
        new Point(-15, -20) ||  new Point(15, 20)
        new Point(-47, -8)  ||  new Point(47, 8)
        new Point(10, -10)  ||  new Point(-10, 10)
        new Point(-15, 20)  ||  new Point(15, -20)
    }

    def "Multiply"() {

        expect:
        point.multiply(param) == result

        where:
        point               ||  param   ||  result
        new Point(10, 10)   ||  5       ||  new Point(10 * 5, 10 * 5)
        new Point(15, 20)   ||  13      ||  new Point(15 * 13, 20 * 13)
        new Point(47, 8)    ||  -6      ||  new Point(47 * -6, 8 * -6)
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

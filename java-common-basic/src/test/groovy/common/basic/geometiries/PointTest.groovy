package common.basic.geometiries

import spock.lang.Specification

class PointTest extends Specification {

    def "Absolute"() {

        expect:
        pointObj.absolute() == result

        where:
        pointObj            ||  result
        new Point(10, 10)   ||  new Point(10, 10)
        new Point(15, 20)   ||  new Point(15, 20)
        new Point(47, 8)    ||  new Point(47, 8)
    }

    def "Negate"() {

    }

    def "Multiply"() {

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

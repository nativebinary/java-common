package common.basic.geometiries

import spock.lang.Specification

class PointDTest extends Specification {

    def "Absolute"() {

        expect:
        point.absolute() == result

        where:
        point                       ||  result
        new PointD(10, 10)          ||  new PointD(10, 10)
        new PointD(10.0, 10.0)      ||  new PointD(10.0, 10.0)
        new PointD(10.0, 10)        ||  new PointD(10, 10.0)
        new PointD(-10, 10.0)       ||  new PointD(10, 10.0)
        new PointD(22.225, 16.204)  ||  new PointD(22.225, 16.204)
        new PointD(-56.29, -962.2)  ||  new PointD(56.29, 962.2)
        new PointD(-129.0, -77.0)   ||  new PointD(129, 77)
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

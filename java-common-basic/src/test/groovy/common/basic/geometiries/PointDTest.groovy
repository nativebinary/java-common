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

        expect:
        point.negate() == result

        where:
        point                       ||  result
        new PointD(10, 10)          ||  new PointD(-10, -10)
        new PointD(10.0, 10.0)      ||  new PointD(-10.0, -10.0)
        new PointD(10.0, 10)        ||  new PointD(-10, -10.0)
        new PointD(-10, 10.0)       ||  new PointD(10, -10.0)
        new PointD(22.225, 16.204)  ||  new PointD(-22.225, -16.204)
        new PointD(-56.29, -962.2)  ||  new PointD(56.29, 962.2)
        new PointD(-129.0, -77.0)   ||  new PointD(129, 77)
    }

    def "Multiply"() {

        expect:
        point.multiply(param) == result

        where:
        point                       ||  param       ||  result
        new PointD(10, 10)          ||  10.0        ||  new PointD(100, 100)
        new PointD(10.0, 10.0)      ||  10.0        ||  new PointD(100, 100)
        new PointD(26.98, 6.89)     ||  7.62        ||  new PointD(205.5876, 52.501799999999996)
        new PointD(-56.29, -962.2)  ||  9.6         ||  new PointD(-540.384, -9237.12)
        new PointD(22.225, -16.204) ||  -15.25      ||  new PointD(-338.93125000000003, 247.11100000000002)
        new PointD(-129.0, -77.0)   ||  23.58       ||  new PointD(-3041.8199999999997, -1815.6599999999999)
        new PointD(86.25, -98.2)    ||  3.5         ||  new PointD(301.875, -343.7)
    }

    def "DivideBy"() {

        expect:
        point.divideBy(param) == result

        where:
        point                       ||  param       ||  result
        new PointD(10, 10)          ||  10.0        ||  new PointD(1.0, 1.0)
        new PointD(10.0, 10.0)      ||  10.0        ||  new PointD(1.0, 1.0)
        new PointD(26.98, 6.89)     ||  7.62        ||  new PointD(3.540682414698163, 0.9041994750656167)
        new PointD(-56.29, -962.2)  ||  9.6         ||  new PointD(-5.863541666666666, -100.22916666666667)
        new PointD(22.225, -16.204) ||  -15.25      ||  new PointD(-1.457377049180328, 1.0625573770491803)
        new PointD(-129.0, -77.0)   ||  23.58       ||  new PointD(-5.470737913486006, -3.2654792196776934)
        new PointD(86.25, -98.2)    ||  3.5         ||  new PointD(24.642857142857142, -28.057142857142857)
    }

    def "Delta"() {

        expect:
        point.delta(currentPoint) == result

        where:
        point                       ||  currentPoint                ||  result
        new PointD(10, 10)          ||  new PointD(10, 10)          ||  new SizeD(0, 0)
        new PointD(15.5, 9.1)       ||  new PointD(3.7, 2.8)        ||  new SizeD(11.8, 6.3)
        new PointD(26.98, 6.89)     ||  new PointD(13.9, 9.56)      ||  new SizeD(13.08, 2.670000000000001)
        new PointD(-56.29, -962.2)  ||  new PointD(26.5, 167.24)    ||  new SizeD(82.78999999999999, 1129.44)
        new PointD(22.225, -16.204) ||  new PointD(-96.2, 43.87)    ||  new SizeD(118.42500000000001, 60.074)
        new PointD(-129.0, -77.0)   ||  new PointD(62.5, 33.26)     ||  new SizeD(191.5, 110.25999999999999)
        new PointD(86.25, -98.2)    ||  new PointD(18.9, -32.9)     ||  new SizeD(67.35, 65.30000000000001)
    }

    def "Median"() {

        expect:
        point.median(currentPoint) == result

        where:
        point                       ||  currentPoint                ||  result
        new PointD(10, 10)          ||  new PointD(10, 10)          ||  new PointD(10.0, 10.0)
        new PointD(15.5, 9.1)       ||  new PointD(3.7, 2.8)        ||  new PointD(21.4, 12.25)
        new PointD(26.98, 6.89)     ||  new PointD(13.9, 9.56)      ||  new PointD(33.52, 8.225)
        new PointD(-56.29, -962.2)  ||  new PointD(26.5, 167.24)    ||  new PointD(-14.895000000000003, -397.48)
        new PointD(22.225, -16.204) ||  new PointD(-96.2, 43.87)    ||  new PointD(81.4375, 13.832999999999998)
        new PointD(-129.0, -77.0)   ||  new PointD(62.5, 33.26)     ||  new PointD(-33.25, -21.870000000000005)
        new PointD(86.25, -98.2)    ||  new PointD(18.9, -32.9)     ||  new PointD(119.925, -65.55)

    }

    def "Offset"() {

        expect:
        point.offset(size) == result

        where:
        point                       ||  size                        ||  result
        new PointD(10, 10)          ||  new SizeD(10, 10)           ||  new PointD(20.0, 20.0)
        new PointD(15.5, 9.1)       ||  new SizeD(3.7, 2.8)         ||  new PointD(19.2, 11.899999999999999)
        new PointD(26.98, 6.89)     ||  new SizeD(13.9, 9.56)       ||  new PointD(40.88, 16.45)
        new PointD(-56.29, -962.2)  ||  new SizeD(26.5, 167.24)     ||  new PointD(-29.79, -794.96)
        new PointD(22.225, -16.204) ||  new SizeD(-96.2, 43.87)     ||  new PointD(-73.975, 27.665999999999997)
        new PointD(-129.0, -77.0)   ||  new SizeD(62.5, 33.26)      ||  new PointD(-66.5, -43.74)
        new PointD(86.25, -98.2)    ||  new SizeD(18.9, -32.9)      ||  new PointD(105.15, -131.1)
    }

    def "Advance"() {

        expect:
        point.advance(currentPoint) == result

        where:
        point                       ||  currentPoint                 ||  result
        new PointD(10, 10)          ||  new PointD(10, 10)           ||  new PointD(20.0, 20.0)
        new PointD(15.5, 9.1)       ||  new PointD(3.7, 2.8)         ||  new PointD(19.2, 11.899999999999999)
        new PointD(26.98, 6.89)     ||  new PointD(13.9, 9.56)       ||  new PointD(40.88, 16.45)
        new PointD(-56.29, -962.2)  ||  new PointD(26.5, 167.24)     ||  new PointD(-29.79, -794.96)
        new PointD(22.225, -16.204) ||  new PointD(-96.2, 43.87)     ||  new PointD(-73.975, 27.665999999999997)
        new PointD(-129.0, -77.0)   ||  new PointD(62.5, 33.26)      ||  new PointD(-66.5, -43.74)
        new PointD(86.25, -98.2)    ||  new PointD(18.9, -32.9)      ||  new PointD(105.15, -131.1)
    }

    def "Differ"() {

        expect:
        point.differ(currentPoint) == result

        where:
        point                       ||  currentPoint                ||  result
        new PointD(10, 10)          ||  new PointD(10, 10)          ||  new SizeD(0, 0)
        new PointD(15.5, 9.1)       ||  new PointD(3.7, 2.8)        ||  new SizeD(11.8, 6.3)
        new PointD(26.98, 6.89)     ||  new PointD(13.9, 9.56)      ||  new SizeD(13.08, -2.670000000000001)
        new PointD(-56.29, -962.2)  ||  new PointD(26.5, 167.24)    ||  new SizeD(-82.78999999999999, -1129.44)
        new PointD(22.225, -16.204) ||  new PointD(-96.2, 43.87)    ||  new SizeD(118.42500000000001, -60.074)
        new PointD(-129.0, -77.0)   ||  new PointD(62.5, 33.26)     ||  new SizeD(-191.5, -110.25999999999999)
        new PointD(86.25, -98.2)    ||  new PointD(18.9, -32.9)     ||  new SizeD(67.35, -65.30000000000001)
    }

    @SuppressWarnings("GroovyPointlessBoolean")
    def "Equals"() {

        expect:
        pointLhs.equals(pointRhs) == result

        where:
        pointLhs                    ||  pointRhs                    ||  result
        new PointD(10, 10)          ||  new PointD(10, 10)          ||  true
        new PointD(10.0, 10.0)      ||  new PointD(10, 10)          ||  true
        new PointD(10, 10.0)        ||  new PointD(10.0, 10)        ||  true
        new PointD(15.5, 9.1)       ||  new PointD(3.7, 2.8)        ||  false
        new PointD(26.98, 6.89)     ||  new PointD(13.9, 9.56)      ||  false
        new PointD(-56.29, -962.2)  ||  new PointD(26.5, 167.24)    ||  false
        new PointD(22.225, -16.204) ||  new PointD(-96.2, 43.87)    ||  false
        new PointD(-129.0, -77.0)   ||  new PointD(62.5, 33.26)     ||  false
        new PointD(86.25, -98.2)    ||  new PointD(18.9, -32.9)     ||  false

    }

    def "HashCode"() {

        expect:
        point.hashCode() == result

        where:
        point                       ||  result
        new PointD(10, 10)          ||  75497472
        new PointD(10.0, 10.0)      ||  75497472
        new PointD(10, 10.0)        ||  75497472
        new PointD(15.5, 9.1)       ||  952238080
        new PointD(26.98, 6.89)     ||  1408709241
        new PointD(-56.29, -962.2)  ||  -457995064
        new PointD(22.225, -16.204) ||  -194726188
        new PointD(-129.0, -77.0)   ||  200744960
        new PointD(86.25, -98.2)    ||  -688869375
    }

    def "ToString"() {

        expect:
        point.toString() == result

        where:
        point                       ||  result
        new PointD(10, 10)          ||  '{"x":10.0,"y":10.0}'
        new PointD(10.0, 10.0)      ||  '{"x":10.0,"y":10.0}'
        new PointD(10, 10.0)        ||  '{"x":10.0,"y":10.0}'
        new PointD(15.5, 9.1)       ||  '{"x":15.5,"y":9.1}'
        new PointD(26.98, 6.89)     ||  '{"x":26.98,"y":6.89}'
        new PointD(-56.29, -962.2)  ||  '{"x":-56.29,"y":-962.2}'
        new PointD(22.225, -16.204) ||  '{"x":22.225,"y":-16.204}'
        new PointD(-129.0, -77.0)   ||  '{"x":-129.0,"y":-77.0}'
        new PointD(86.25, -98.2)    ||  '{"x":86.25,"y":-98.2}'
    }
}

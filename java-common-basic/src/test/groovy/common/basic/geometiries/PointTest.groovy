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

        expect:
        point.divideBy(param) == result

        where:
        point                   ||  param   ||  result
        new Point(10, 10)       ||  5       ||  new Point((int)(10 / 5), (int)(10 / 5))
        new Point(15, 20)       ||  13      ||  new Point((int)(15 / 13), (int)(20 / 13))
        new Point(47, 8)        ||  -6      ||  new Point((int)(47 / (-6)), (int)(8 / (-6)))
        new Point(30, 0)        ||  -9      ||  new Point((int)(30 / (-9)), 0)
        new Point(-82, -51)     ||  76      ||  new Point((int)(-82 / 76), (int)(-51 / 76))
        new Point(11, -72)      ||  11      ||  new Point((int)(11 / 11), (int)(-72 / 11))
        new Point(-39, 29)      ||  35      ||  new Point((int)(-39 / 35), (int)(29 / 35))
        new Point(100, 200)     ||  -96     ||  new Point((int)(100 / (-96)), (int)(200 / (-96)))
        new Point(3678, -98)    ||  27      ||  new Point((int)(3678 / 27), (int)(-98 / 27))
        new Point(-9834, -9999) || -8       ||  new Point((int)(-9834 / (-8)), (int)(-9999 / (-8)))
    }

    def "Delta"() {

        expect:
        point.delta(pointCurrent) == result

        where:
        point                   ||  pointCurrent            ||  result
        new Point(10, 10)       ||  new Point(5, 5)         ||  new Size(5, 5)
        new Point(15, 20)       ||  new Point(25, 32)       ||  new Size(10, 12)
        new Point(47, 8)        ||  new Point(-35, 17)      ||  new Size(82, 9)
        new Point(30, 0)        ||  new Point(11, 8)        ||  new Size(19, 8)
        new Point(-82, -51)     ||  new Point(-22, 23)      ||  new Size(60, 74)
        new Point(11, -72)      ||  new Point(18, 55)       ||  new Size(7, 127)
        new Point(-39, 29)      ||  new Point(-66, -27)     ||  new Size(27, 56)
        new Point(100, 200)     ||  new Point(32, 76)       ||  new Size(68, 124)
        new Point(3678, -98)    ||  new Point(1625, -168)   ||  new Size(2053, 70)
        new Point(-9834, -9999) ||  new Point(8006, 7624)   ||  new Size(17840, 17623)
    }

    def "Median"() {

        expect:
        point.median(pointCurrent) == result

        where:
        point                   ||  pointCurrent            ||  result
        new Point(10, 10)       ||  new Point(5, 5)         ||  new Point(12, 12)
        new Point(15, 20)       ||  new Point(25, 32)       ||  new Point(20, 26)
        new Point(47, 8)        ||  new Point(-35, 17)      ||  new Point(88, 12)
        new Point(30, 0)        ||  new Point(11, 8)        ||  new Point(39, 4)
        new Point(-82, -51)     ||  new Point(-22, 23)      ||  new Point(-52, -14)
        new Point(11, -72)      ||  new Point(18, 55)       ||  new Point(14, -9)
        new Point(-39, 29)      ||  new Point(-66, -27)     ||  new Point(-26, 57)
        new Point(100, 200)     ||  new Point(32, 76)       ||  new Point(134, 262)
        new Point(3678, -98)    ||  new Point(1625, -168)   ||  new Point(4704, -63)
        new Point(-9834, -9999) ||  new Point(8006, 7624)   ||  new Point(-914, -1188)
    }

    def "Offset"() {

        expect:
        point.offset(size) == result

        where:
        point                   ||  size                    ||  result
        new Point(10, 10)       ||  new Size(5, 5)          ||  new Point(15, 15)
        new Point(15, 20)       ||  new Size(10, 12)        ||  new Point(25, 32)
        new Point(47, 8)        ||  new Size(82, 9)         ||  new Point(129, 17)
        new Point(30, 0)        ||  new Size(19, 8)         ||  new Point(49, 8)
        new Point(-82, -51)     ||  new Size(60, 74)        ||  new Point(-22, 23)
        new Point(11, -72)      ||  new Size(7, 127)        ||  new Point(18, 55)
        new Point(-39, 29)      ||  new Size(27, 56)        ||  new Point(-12, 85)
        new Point(100, 200)     ||  new Size(68, 124)       ||  new Point(168, 324)
        new Point(3678, -98)    ||  new Size(2053, 70)      ||  new Point(5731, -28)
        new Point(-9834, -9999) ||  new Size(17840, 17623)  ||  new Point(8006, 7624)
    }

    def "Advance"() {

        expect:
        point.advance(pointCurrent) == result

        where:
        point                   ||  pointCurrent            ||  result
        new Point(10, 10)       ||  new Point(5, 5)         ||  new Point(15, 15)
        new Point(15, 20)       ||  new Point(25, 32)       ||  new Point(40, 52)
        new Point(47, 8)        ||  new Point(-35, 17)      ||  new Point(12, 25)
        new Point(30, 0)        ||  new Point(11, 8)        ||  new Point(41, 8)
        new Point(-82, -51)     ||  new Point(-22, 23)      ||  new Point(-104, -28)
        new Point(11, -72)      ||  new Point(18, 55)       ||  new Point(29, -17)
        new Point(-39, 29)      ||  new Point(-66, -27)     ||  new Point(-105, 2)
        new Point(100, 200)     ||  new Point(32, 76)       ||  new Point(132, 276)
        new Point(3678, -98)    ||  new Point(1625, -168)   ||  new Point(5303, -266)
        new Point(-9834, -9999) ||  new Point(8006, 7624)   ||  new Point(-1828, -2375)
    }

    def "Differ"() {

        expect:
        point.differ(pointCurrent) == result

        where:
        point                   ||  pointCurrent            || result
        new Point(10, 10)       ||  new Point(5, 5)         ||  new Size(5, 5)
        new Point(15, 20)       ||  new Point(25, 32)       ||  new Size(-10, -12)
        new Point(47, 8)        ||  new Point(-35, 17)      ||  new Size(82, -9)
        new Point(30, 0)        ||  new Point(11, 8)        ||  new Size(19, -8)
        new Point(-82, -51)     ||  new Point(-22, 23)      ||  new Size(-60, -74)
        new Point(11, -72)      ||  new Point(18, 55)       ||  new Size(-7, -127)
        new Point(-39, 29)      ||  new Point(-66, -27)     ||  new Size(27, 56)
        new Point(100, 200)     ||  new Point(32, 76)       ||  new Size(68, 124)
        new Point(3678, -98)    ||  new Point(1625, -168)   ||  new Size(2053, 70)
        new Point(-9834, -9999) ||  new Point(8006, 7624)   ||  new Size(-17840, -17623)
    }

    @SuppressWarnings("GroovyPointlessBoolean")
    def "Equals"() {

        expect:
        pointLhs.equals(pointRhs) == result

        where:
        pointLhs                    ||  pointRhs                ||  result
        new Point(10, 10)           ||  new Point(10, 10)       ||  true
        new Point(22, 45)           ||  new Point(10, 10)       ||  false
        new Point(-581, 10)         ||  new Point(-581, 10)     ||  true
        new Point(-52, 84)          ||  new Point(52, -84)      ||  false
        new Point(-68, -92)         ||  new Point(-68, -92)     ||  true
    }

    def "HashCode"() {

        expect:
        point.hashCode() == result

        where:
        point                   ||  result
        new Point(10, 10)       ||  320
        new Point(22, 45)       ||  727
        new Point(-581, 10)     ||  -18001
        new Point(-52, 84)      ||  -1528
        new Point(-68, -92)     ||  -2200
    }

    def "ToString"() {

        expect:
        point.toString() == result

        where:
        point                   ||  result
        new Point(10, 10)       ||  '{"x":10,"y":10}'
        new Point(22, 45)       ||  '{"x":22,"y":45}'
        new Point(-581, 10)     ||  '{"x":-581,"y":10}'
        new Point(-52, 84)      ||  '{"x":-52,"y":84}'
        new Point(-68, -92)     ||  '{"x":-68,"y":-92}'
    }
}

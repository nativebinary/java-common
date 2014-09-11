package common.basic.geometiries

import spock.lang.Specification

class RectTest extends Specification {

    def "FromLeftTopRightBottom"() {

        expect:
        Rect.fromLeftTopRightBottom(left, top, right, bottom) == result

        where:
        left    ||  top ||  right   ||  bottom  ||  result
        10      ||  10  ||  10      ||  10      ||  new Rect(new Point(10, 10), new Size(0, 0))
        15      ||  52  ||  49      ||  -12     ||  new Rect(new Point(15, 52), new Size(34, -64))
        23      ||  -18 ||  27      ||  36      ||  new Rect(new Point(23, -18), new Size(4, 54))
        -126    ||  -92 ||  39      ||  67      ||  new Rect(new Point(-126, -92), new Size(165, 159))
        88      ||  27  ||  2       ||  27      ||  new Rect(new Point(88, 27), new Size(-86, 0))
        -96     ||  42  ||  14      ||  33      ||  new Rect(new Point(-96, 42), new Size(110, -9))
    }

    def "Width"() {

        expect:
        rect.width() == result

        where:
        rect                                                ||  result
        new Rect(new Point(10, 10), new Size(0, 0))         ||  0
        new Rect(new Point(15, 52), new Size(34, -64))      ||  34
        new Rect(new Point(23, -18), new Size(4, 54))       ||  4
        new Rect(new Point(-126, -92), new Size(165, 159))  ||  165
        new Rect(new Point(88, 27), new Size(-86, 0))       ||  -86
        new Rect(new Point(-96, 42), new Size(110, -9))     ||  110
    }

    def "Height"() {

        expect:
        rect.height() == result

        where:
        rect                                                ||  result
        new Rect(new Point(10, 10), new Size(0, 0))         ||  0
        new Rect(new Point(15, 52), new Size(34, -64))      ||  -64
        new Rect(new Point(23, -18), new Size(4, 54))       ||  54
        new Rect(new Point(-126, -92), new Size(165, 159))  ||  159
        new Rect(new Point(88, 27), new Size(-86, 72))      ||  72
        new Rect(new Point(-96, 42), new Size(110, -9))     ||  -9
    }

    def "Left"() {

        expect:
        rect.left() == result

        where:
        rect                                                ||  result
        new Rect(new Point(10, 10), new Size(0, 0))         ||  10
        new Rect(new Point(15, 52), new Size(34, -64))      ||  15
        new Rect(new Point(23, -18), new Size(4, 54))       ||  23
        new Rect(new Point(-126, -92), new Size(165, 159))  ||  -126
        new Rect(new Point(88, 27), new Size(-86, 72))      ||  88
        new Rect(new Point(-96, 42), new Size(110, -9))     ||  -96
    }

    def "Top"() {

        expect:
        rect.top() == result

        where:
        rect                                                ||  result
        new Rect(new Point(10, 10), new Size(0, 0))         ||  10
        new Rect(new Point(15, 52), new Size(34, -64))      ||  52
        new Rect(new Point(23, -18), new Size(4, 54))       ||  -18
        new Rect(new Point(-126, -92), new Size(165, 159))  ||  -92
        new Rect(new Point(88, 27), new Size(-86, 72))      ||  27
        new Rect(new Point(-96, 42), new Size(110, -9))     ||  42
    }

    def "Right"() {

        expect:
        rect.right() == result

        where:
        rect                                                ||  result
        new Rect(new Point(10, 10), new Size(0, 0))         ||  10
        new Rect(new Point(15, 52), new Size(34, -64))      ||  49
        new Rect(new Point(23, -18), new Size(4, 54))       ||  27
        new Rect(new Point(-126, -92), new Size(165, 159))  ||  39
        new Rect(new Point(88, 27), new Size(-86, 72))      ||  2
        new Rect(new Point(-96, 42), new Size(110, -9))     ||  14
    }

    def "Bottom"() {

        expect:
        rect.bottom() == result

        where:
        rect                                                ||  result
        new Rect(new Point(10, 10), new Size(0, 0))         ||  10
        new Rect(new Point(15, 52), new Size(34, -64))      ||  -12
        new Rect(new Point(23, -18), new Size(4, 54))       ||  36
        new Rect(new Point(-126, -92), new Size(165, 159))  ||  67
        new Rect(new Point(88, 27), new Size(-86, 72))      ||  99
        new Rect(new Point(-96, 42), new Size(110, -9))     ||  33
    }

    def "Center"() {

        expect:
        rect.center() == result

        where:
        rect                                                ||  result
        new Rect(new Point(10, 10), new Size(0, 0))         ||  new Point(10, 10)
        new Rect(new Point(15, 52), new Size(34, -64))      ||  new Point(32, 20)
        new Rect(new Point(23, -18), new Size(4, 54))       ||  new Point(25, 9)
        new Rect(new Point(-126, -92), new Size(165, 159))  ||  new Point(-44, -13)
        new Rect(new Point(88, 27), new Size(-86, 72))      ||  new Point(45, 63)
        new Rect(new Point(-96, 42), new Size(110, -9))     ||  new Point(-41, 38)
    }

    def "Deflate"() {

        expect:
        rect.deflate(param) == result

        where:
        rect                                                ||  param   ||  result
        new Rect(new Point(10, 10), new Size(0, 0))         ||  10      ||  new Rect(new Point(15, 15), new Size(-10, -10))
        new Rect(new Point(15, 52), new Size(34, -64))      ||  10      ||  new Rect(new Point(20, 57), new Size(24, -74))
        new Rect(new Point(23, -18), new Size(4, 54))       ||  10      ||  new Rect(new Point(28, -13), new Size(-6, 44))
        new Rect(new Point(-126, -92), new Size(165, 159))  ||  10      ||  new Rect(new Point(-121, -87), new Size(155, 149))
        new Rect(new Point(88, 27), new Size(-86, 72))      ||  10      ||  new Rect(new Point(93, 32), new Size(-96, 62))
        new Rect(new Point(-96, 42), new Size(110, -9))     ||  10      ||  new Rect(new Point(-91, 47), new Size(100, -19))
    }

    @SuppressWarnings("GroovyPointlessBoolean")
    def "Contains"() {

        expect:
        rect.contains(point) == result

        where:
        rect                                            ||  point                   ||  result
        new Rect(new Point(10, 10), new Size(0, 0))     ||  new PointF(10, 10)      ||  true
        new Rect(new Point(10, 10), new Size(0, 0))     ||  new PointF(9, 9)        ||  false
        new Rect(new Point(10, 10), new Size(0, 0))     ||  new PointF(11, 11)      ||  false
        new Rect(new Point(10, 10), new Size(10, 10))   ||  new PointF(10, 10)      ||  true
        new Rect(new Point(10, 10), new Size(10, 10))   ||  new PointF(9, 9)        ||  false
        new Rect(new Point(10, 10), new Size(10, 10))   ||  new PointF(20, 20)      ||  true
        new Rect(new Point(10, 10), new Size(10, 10))   ||  new PointF(21, 21)      ||  false

        new Rect(new Point(15, 52), new Size(34, 64))   ||  new PointF(15, 52)      ||  true
        new Rect(new Point(15, 52), new Size(34, 64))   ||  new PointF(14, 52)      ||  false
        new Rect(new Point(15, 52), new Size(34, 64))   ||  new PointF(15, 51)      ||  false
        new Rect(new Point(15, 52), new Size(34, 64))   ||  new PointF(20, 80)      ||  true
        new Rect(new Point(15, 52), new Size(34, 64))   ||  new PointF(36, 99)      ||  true
        new Rect(new Point(15, 52), new Size(34, 64))   ||  new PointF(45, 110)     ||  true
        new Rect(new Point(15, 52), new Size(34, 64))   ||  new PointF(49, 116)     ||  true
        new Rect(new Point(15, 52), new Size(34, 64))   ||  new PointF(50, 116)     ||  false
        new Rect(new Point(15, 52), new Size(34, 64))   ||  new PointF(49, 117)     ||  false
    }

    def "Offset"() {

        expect:
        rect.offset(size) == result

        where:
        rect                                                ||  size                    ||  result
        new Rect(new Point(10, 10), new Size(0, 0))         ||  new Size(5, 5)          ||  new Rect(new Point(15, 15), new Size(0, 0))
        new Rect(new Point(15, 52), new Size(34, -64))      ||  new Size(25, 34)        ||  new Rect(new Point(40, 86), new Size(34, -64))
        new Rect(new Point(23, -18), new Size(4, 54))       ||  new Size(1, 9)          ||  new Rect(new Point(24, -9), new Size(4, 54))
        new Rect(new Point(-126, -92), new Size(165, 159))  ||  new Size(78, 125)       ||  new Rect(new Point(-48, 33), new Size(165, 159))
        new Rect(new Point(88, 27), new Size(-86, 72))      ||  new Size(63, 97)        ||  new Rect(new Point(151, 124), new Size(-86, 72))
        new Rect(new Point(-96, 42), new Size(110, -9))     ||  new Size(274, 81)       ||  new Rect(new Point(178, 123), new Size(110, -9))
    }

    @SuppressWarnings("GroovyPointlessBoolean")
    def "Equals"() {

        expect:
        rectLhs.equals(rectRhs) == result

        where:
        rectLhs                                             ||  rectRhs                                                 ||  result
        new Rect(new Point(10, 10), new Size(10, 10))       ||  new Rect(new Point(10, 10), new Size(10, 10))           ||  true
        new Rect(new Point(10, 10), new Size(10, 10))       ||  new Rect(new Point(10, 10), new Size(0, 0))             ||  false
        new Rect(new Point(10, 10), new Size(0, 0))         ||  new Rect(new Point(10, 10), new Size(0, 0))             ||  true
        new Rect(new Point(10, 10), new Size(0, 0))         ||  new Rect(new Point(10, 10), new Size(10, 10))           ||  false
        new Rect(new Point(78, 45), new Size(12, 64))       ||  new Rect(new Point(78, 45), new Size(12, 64))           ||  true
        new Rect(new Point(78, 45), new Size(12, 64))       ||  new Rect(new Point(-78, -45), new Size(-12, -64))       ||  false
    }

    def "HashCode"() {

        expect:
        rect.hashCode() == result

        where:
        rect                                                ||  result
        new Rect(new Point(10, 10), new Size(0, 0))         ||  9920
        new Rect(new Point(-10, -10), new Size(0, 0))       ||  -9920
        new Rect(new Point(15, 52), new Size(34, -64))      ||  17017
        new Rect(new Point(23, -18), new Size(4, 54))       ||  21723
        new Rect(new Point(-126, -92), new Size(165, 159))  ||  -118664
        new Rect(new Point(88, 27), new Size(-86, 72))      ||  82811
        new Rect(new Point(-96, 42), new Size(110, -9))     ||  -87553
    }

    def "ToString"() {

    }
}

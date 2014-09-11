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

    def "Contains"() {

    }

    def "Offset"() {

    }

    def "ToString"() {

    }
}

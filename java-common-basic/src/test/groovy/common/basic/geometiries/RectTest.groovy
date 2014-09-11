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

    }

    def "Right"() {

    }

    def "Bottom"() {

    }

    def "Center"() {

    }

    def "Deflate"() {

    }

    def "Contains"() {

    }

    def "Offset"() {

    }

    def "ToString"() {

    }
}

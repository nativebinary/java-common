package common.basic.geometiries

import spock.lang.Specification

class RectSectionLtrbTest extends Specification {
    def "GetTouchArea"() {
        expect:
        RectSectionLtrb.getTouchArea(size, point) == result

        where:
        size                                ||  point                                   ||  result
        new SizeF(30, 36)                   ||  new PointF(35, 39)                      ||  RectSectionLtrb.None
        new SizeF(30, 36)                   ||  new PointF(30, 36)                      ||  RectSectionLtrb.None
        new SizeF(30, 36)                   ||  new PointF(27, -1)                      ||  RectSectionLtrb.None
        new SizeF(30, 36)                   ||  new PointF(-16, -25)                    ||  RectSectionLtrb.None
        new SizeF(75, 27)                   ||  new PointF(80, 30)                      ||  RectSectionLtrb.None
        new SizeF(75, 27)                   ||  new PointF(75, 27)                      ||  RectSectionLtrb.None
        new SizeF(75, 27)                   ||  new PointF(67, -1)                      ||  RectSectionLtrb.None
        new SizeF(75, 27)                   ||  new PointF(-16, -25)                    ||  RectSectionLtrb.None

        new SizeF(30, 36)                   ||  new PointF(28, 30)                      ||  RectSectionLtrb.Bottom
        new SizeF(18, 27)                   ||  new PointF(12, 13)                      ||  RectSectionLtrb.Right
        new SizeF(26, 52)                   ||  new PointF(25, 8)                       ||  RectSectionLtrb.Top
        new SizeF(48, 69)                   ||  new PointF(22, 34)                      ||  RectSectionLtrb.Left
        new SizeF(54, 77)                   ||  new PointF(26, 15)                      ||  RectSectionLtrb.Top
        new SizeF(86, 94)                   ||  new PointF(42, 47)                      ||  RectSectionLtrb.Left
        new SizeF(67, 82)                   ||  new PointF(35, 41)                      ||  RectSectionLtrb.Right
        new SizeF(78, 104)                  ||  new PointF(26, 82)                      ||  RectSectionLtrb.Bottom

        new SizeF(78, 26)                   ||  new PointF(20, 14)                      ||  RectSectionLtrb.Bottom
        new SizeF(85, 35)                   ||  new PointF(43, 18)                      ||  RectSectionLtrb.Bottom
        new SizeF(98, 42)                   ||  new PointF(75, 19)                      ||  RectSectionLtrb.Right
        new SizeF(69, 14)                   ||  new PointF(55, 11)                      ||  RectSectionLtrb.Right
        new SizeF(58, 48)                   ||  new PointF(13, 22)                      ||  RectSectionLtrb.Left
        new SizeF(27, 12)                   ||  new PointF(5, 11)                       ||  RectSectionLtrb.Left
        new SizeF(49, 33)                   ||  new PointF(13, 15)                      ||  RectSectionLtrb.Top
        new SizeF(89, 76)                   ||  new PointF(45, 35)                      ||  RectSectionLtrb.Top
    }

    def "IsHorizontal"() {

    }

    def "IsBefore"() {

    }

    def "IsAfter"() {

    }

    def "GetViewIndex"() {

    }
}

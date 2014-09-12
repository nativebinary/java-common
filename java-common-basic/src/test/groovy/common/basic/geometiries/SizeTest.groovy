package common.basic.geometiries

import spock.lang.Specification

class SizeTest extends Specification {
    def "DivideBy"() {
        expect:
        size.divideBy(param) == result

        where:
        size                                ||  param   ||  result
        new Size(10, 10)                    ||  5       ||  new Size(2, 2)
        new Size(522, 128)                  ||  2       ||  new Size(261, 64)
        new Size(963, 31)                   ||  17      ||  new Size(56, 1)
        new Size(-85, 72)                   ||  23      ||  new Size(-3, 3)
        new Size(53, 49)                    ||  -6      ||  new Size(-8, -8)
        new Size(999999999, 999999999)      ||  9       ||  new Size(111111111, 111111111)
        new Size(2147483647, 2147483647)    ||  9       ||  new Size(238609294, 238609294)
        new Size(-2147483648, -2147483648)  ||  9       ||  new Size(-238609294, -238609294)
    }

    def "GetWidthAspectRatioForHeight"() {
        expect:
        size.getWidthAspectRatioForHeight(height) == result

        where:
        size                                ||  height  ||  result
        new Size(10, 10)                    ||  5       ||  5
        new Size(522, 128)                  ||  2       ||  8
        new Size(963, 31)                   ||  17      ||  528
        new Size(-85, 72)                   ||  23      ||  -27
        new Size(53, 49)                    ||  -6      ||  -6
        new Size(999999999, 999999999)      ||  9       ||  0
        new Size(2147483647, 2147483647)    ||  9       ||  0
        new Size(-2147483648, -2147483648)  ||  9       ||  1
    }

    def "GetHeightAspectRatioForWidth"() {
        expect:
        size.getHeightAspectRatioForWidth(width) == result

        where:
        size                                ||  width   ||  result
        new Size(10, 10)                    ||  5       ||  5
        new Size(522, 128)                  ||  2       ||  0
        new Size(963, 31)                   ||  17      ||  0
        new Size(-85, 72)                   ||  23      ||  -19
        new Size(53, 49)                    ||  -6      ||  -5
        new Size(999999999, 999999999)      ||  9       ||  0
        new Size(2147483647, 2147483647)    ||  9       ||  0
        new Size(-2147483648, -2147483648)  ||  9       ||  1
    }

    def "Equals"() {

    }

    def "HashCode"() {

    }

    def "ToString"() {

    }
}

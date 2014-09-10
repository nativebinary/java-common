package common.basic.geometiries

import spock.lang.Specification

class MarginTest extends Specification {

    def "MarginOneParameter"() {

        expect:
        new Margin(param) == result

        where:
        param       ||  result
        10          ||  new Margin(10)
        10          ||  new Margin(10, 10, 10, 10)
        22          ||  new Margin(22)
        22          ||  new Margin(22, 22, 22, 22)
        54          ||  new Margin(54)
        54          ||  new Margin(54, 54, 54, 54)
        85          ||  new Margin(85)
        85          ||  new Margin(85, 85, 85, 85)
    }

    def "MarginParameters"() {

        expect:
        new Margin(param1, param2, param3, param4) == result

        where:
        param1  ||  param2  ||  param3  ||  param4  ||  result
        10      ||  10      ||  10      ||  10      ||  new Margin(10, 10, 10, 10)
        10      ||  10      ||  10      ||  10      ||  new Margin(10)
        10      ||  22      ||  58      ||  77      ||  new Margin(10, 22, 58, 77)
        43      ||  0       ||  7       ||  65      ||  new Margin(43, 0, 7, 65)
    }
}

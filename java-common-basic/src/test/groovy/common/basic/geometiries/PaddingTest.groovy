package common.basic.geometiries

import spock.lang.Specification

class PaddingTest extends Specification {

    def "PaddingOneParameter"() {

        expect:
        new Padding(param) == result

        where:
        param       ||  result
        10          ||  new Padding(10)
        10          ||  new Padding(10, 10, 10, 10)
        22          ||  new Padding(22)
        22          ||  new Padding(22, 22, 22, 22)
        54          ||  new Padding(54)
        54          ||  new Padding(54, 54, 54, 54)
        85          ||  new Padding(85)
        85          ||  new Padding(85, 85, 85, 85)
    }

    def "PaddingParameters"() {

        expect:
        new Padding(param1, param2, param3, param4) == result

        where:
        param1  ||  param2  ||  param3  ||  param4  ||  result
        10      ||  10      ||  10      ||  10      ||  new Padding(10, 10, 10, 10)
        10      ||  10      ||  10      ||  10      ||  new Padding(10)
        10      ||  22      ||  58      ||  77      ||  new Padding(10, 22, 58, 77)
        43      ||  0       ||  7       ||  65      ||  new Padding(43, 0, 7, 65)
    }
}

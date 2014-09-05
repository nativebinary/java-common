package common.basic.geometiries

import spock.lang.Specification

class LeftTopRightBottomTest extends Specification {

    @SuppressWarnings("GroovyPointlessBoolean")
    def "Equals"() {

        expect:
        new LeftTopRightBottom(10).equals(obj) == result

        where:
        obj                                     ||  result
        new LeftTopRightBottom(10)              ||  true
        new LeftTopRightBottom(10, 10, 10, 10)  ||  true
        "str"                                   ||  false
        10                                      ||  false
        new LeftTopRightBottom(11)              ||  false
        new LeftTopRightBottom(10, 10, 10, 11)  ||  false
        new LeftTopRightBottom(10.0)            ||  false
        new LeftTopRightBottom(10.0F)           ||  false
        new LeftTopRightBottom(10L)             ||  false
        new LeftTopRightBottom("10")            ||  false
        new LeftTopRightBottom('10')            ||  false
    }

    def "HashCode"() {
        expect: true;
    }

    def "ToString"() {
        expect: true;
    }
}

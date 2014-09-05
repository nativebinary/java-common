package common.basic.geometiries

import spock.lang.Specification

class LeftTopRightBottomTest extends Specification {

    @SuppressWarnings("GroovyPointlessBoolean")
    def "Equals"() {

        expect:
        ltrbLhs.equals(ltrbRhs) == result

        where:
        ltrbLhs                     ||  ltrbRhs                                 ||   result
        new LeftTopRightBottom(10)  ||  new LeftTopRightBottom(10)              ||   true
        new LeftTopRightBottom(10)  ||  new LeftTopRightBottom(10, 10, 10, 10)  ||   true
        new LeftTopRightBottom(10)  ||  new LeftTopRightBottom(11)              ||   false
        new LeftTopRightBottom(10)  ||  new LeftTopRightBottom(10, 10, 20, 10)  ||   false

        new LeftTopRightBottom(22)  ||  new LeftTopRightBottom(22)              ||   true
        new LeftTopRightBottom(22)  ||  new LeftTopRightBottom(22, 22, 22, 22)  ||   true
        new LeftTopRightBottom(22)  ||  new LeftTopRightBottom(22, 11, 22, 22)  ||   false

        new LeftTopRightBottom(10)  ||  new LeftTopRightBottom(10.0)            ||   false
        new LeftTopRightBottom(10)  ||  new LeftTopRightBottom(10.0F)           ||   false
        new LeftTopRightBottom(10)  ||  new LeftTopRightBottom(10L)             ||   false
        new LeftTopRightBottom(10)  ||  new LeftTopRightBottom("10")            ||   false
        new LeftTopRightBottom(10)  ||  new LeftTopRightBottom('10')            ||   false
    }

    def "HashCode"() {
        expect:
        hashCodeMethod == result

        where:
        hashCodeMethod                          ||  result
        new LeftTopRightBottom(10).hashCode()   ||  307840
        new LeftTopRightBottom(11).hashCode()   ||  338624
    }

    def "ToString"() {
        expect: true;
    }
}

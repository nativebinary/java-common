package common.basic.geometiries

import spock.lang.Specification

class LeftTopRightBottomTest extends Specification {

    @SuppressWarnings("GroovyPointlessBoolean")
    def "Equals"() {

        expect:
        ltrbLhs.equals(ltrbRhs) == result

        where:
        ltrbLhs                                     ||  ltrbRhs                                     ||  result
        new LeftTopRightBottom(10)                  ||  new LeftTopRightBottom(10)                  ||  true
        new LeftTopRightBottom(10)                  ||  new LeftTopRightBottom(10, 10, 10, 10)      ||  true
        new LeftTopRightBottom(10)                  ||  new LeftTopRightBottom(11)                  ||  false
        new LeftTopRightBottom(10)                  ||  new LeftTopRightBottom(10, 10, 20, 10)      ||  false

        new LeftTopRightBottom(22)                  ||  new LeftTopRightBottom(22)                  ||  true
        new LeftTopRightBottom(22)                  ||  new LeftTopRightBottom(22, 22, 22, 22)      ||  true
        new LeftTopRightBottom(22)                  ||  new LeftTopRightBottom(22, 11, 22, 22)      ||  false

        new LeftTopRightBottom(10)                  ||  new LeftTopRightBottom(10.0)                ||  false
        new LeftTopRightBottom(10)                  ||  new LeftTopRightBottom(10.0F)               ||  false
        new LeftTopRightBottom(10)                  ||  new LeftTopRightBottom(10L)                 ||  false
        new LeftTopRightBottom(10)                  ||  null                                        ||  false
    }

    def "HashCode"() {

        expect:
        ltrbLhs.hashCode() == result

        where:
        ltrbLhs                                     ||  result
        new LeftTopRightBottom(10)                  ||  307840
        new LeftTopRightBottom(10, 10, 10, 10)      ||  307840
        new LeftTopRightBottom(10, 15, 34, 87)      ||  313466
        new LeftTopRightBottom(22)                  ||  677248
    }

    def "ToString"() {

        expect:
        ltrbLhs.toString() == result

        where:
        ltrbLhs                                     ||  result
        new LeftTopRightBottom(10)                  ||  "LeftTopRightBottom{left=10, top=10, right=10, bottom=10}"
        new LeftTopRightBottom(10, 10, 10, 10)      ||  "LeftTopRightBottom{left=10, top=10, right=10, bottom=10}"
        new LeftTopRightBottom(10, 15, 34, 87)      ||  "LeftTopRightBottom{left=10, top=15, right=34, bottom=87}"
        new LeftTopRightBottom(22)                  ||  "LeftTopRightBottom{left=22, top=22, right=22, bottom=22}"
    }
}

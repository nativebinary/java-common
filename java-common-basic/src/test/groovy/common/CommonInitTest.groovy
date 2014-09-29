package common

import spock.lang.Specification

class CommonInitTest extends Specification {
    def "ctor"() {
        when: new CommonInit()
        then: thrown(InstantiationException)
    }
}

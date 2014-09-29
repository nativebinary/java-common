package common

import spock.lang.Specification

/**
 * Created by roadster on 2014. 9. 29..
 */
class CommonInitTest extends Specification {
    def "ctor"() {
        when: new CommonInit()
        then: thrown(InstantiationException)
    }
}

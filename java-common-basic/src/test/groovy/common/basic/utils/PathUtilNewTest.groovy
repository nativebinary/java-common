package common.basic.utils

import spock.lang.Specification

class PathUtilNewTest extends Specification {

    def "ctor"() {
        when: new RandomUtil();
        then: thrown(InstantiationException);
    }

    def "AppendSlashSafe"() {

    }

    def "Combine"() {

    }

    def "RemoveLeadingSlash"() {

    }
}

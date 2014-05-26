package common.basic.utils

import spock.lang.Specification

import java.util.regex.Pattern

class UuidUtilTest extends Specification {
    def "ctor"() {
        when: new UuidUtil()
        then: thrown(InstantiationException)
    }

    def "Generate"() {
        expect:
        UuidUtil.generate() != UuidUtil.generate()
        UuidUtil.generate().length() == 36
        Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}").matcher(UuidUtil.generate()).matches()
    }

    def "GenerateWithoutDash"() {
        expect:
        UuidUtil.generateWithoutDash() != UuidUtil.generateWithoutDash()
        UuidUtil.generateWithoutDash().length() == 32
        Pattern.compile("[0-9a-f]{32}").matcher(UuidUtil.generateWithoutDash()).matches()
    }
}

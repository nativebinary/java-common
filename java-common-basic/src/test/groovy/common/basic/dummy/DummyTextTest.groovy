package common.basic.dummy

import common.basic.utils.StringUtil
import spock.lang.Specification

class DummyTextTest extends Specification {
    def "ctor"() {
        when: new DummyText()
        then: thrown(InstantiationException)
    }

    def "Get"() {
        expect: !StringUtil.isNullOrEmpty(DummyText.get());
    }

    def "GetAtLeast"() {
        expect: DummyText.getAtLeast(100).length() >= 100;
    }
}

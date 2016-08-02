package common.basic

import spock.lang.Specification

class HelloWorldTest extends Specification {
    def "Say"() {
        expect: new HelloWorld().say() == "Hello, World!"
    }
}

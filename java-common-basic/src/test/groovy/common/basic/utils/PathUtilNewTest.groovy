package common.basic.utils

import spock.lang.Specification

class PathUtilNewTest extends Specification {

    def "ctor"() {
        when: new PathUtilNew();
        then: thrown(InstantiationException);
    }

    def "AppendSlashSafe"() {
        expect:
        PathUtilNew.appendSlashSafe(path) == result

        where:
        path                                    ||  result
        null                                    ||  "/"
        ""                                      ||  "/"
        "/"                                     ||  "/"
        "."                                     ||  "./"
        "./"                                    ||  "./"
        "../"                                   ||  "../"
        "./path"                                ||  "./path/"
        "./path/"                               ||  "./path/"
        "../test/path/url"                      ||  "../test/path/url/"
    }

    def "Combine"() {

    }

    def "RemoveLeadingSlash"() {

    }
}

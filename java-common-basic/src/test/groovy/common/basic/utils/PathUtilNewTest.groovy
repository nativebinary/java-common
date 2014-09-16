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
        expect:
        PathUtilNew.combine(path, fileName) == result

        where:
        path            ||  fileName        ||  result
        null            ||  "empty.txt"     ||  "/empty.txt"
        ""              ||  "empty.txt"     ||  "/empty.txt"
        "./"            ||  "empty.txt"     ||  "./empty.txt"
        "../"           ||  "empty.txt"     ||  "../empty.txt"
        "./path"        ||  "empty.txt"     ||  "./path/empty.txt"
        "./path/"       ||  "empty.txt"     ||  "./path/empty.txt"
        "./path/url/"   ||  "empty.txt"     ||  "./path/url/empty.txt"

    }

    def "RemoveLeadingSlash"() {
        expect:
        PathUtilNew.removeLeadingSlash(path) == result

        where:
        path            ||  result
        ""              ||  ""
        "/"             ||  ""
        "./"            ||  "./"
        "../"           ||  "../"
        "./path"        ||  "./path"
        "./path/url/"   ||  "./path/url/"
        "/path"         ||  "path"
        "/path/url/"    ||  "path/url/"
        "/path/url"     ||  "path/url"
    }
}

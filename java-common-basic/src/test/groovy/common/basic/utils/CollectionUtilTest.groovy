package common.basic.utils

import common.basic.interfaces.IPredicator
import spock.lang.Specification

class CollectionUtilTest extends Specification {
    def "ctor"() {
        when: new CollectionUtil()
        then: thrown(InstantiationException)
    }

    def "IsSubsetOf"() {
        expect:
        expect == CollectionUtil.isSubsetOf(subset, superset)

        where:
        expect || subset | superset
        true   || [1, 2] | [1, 2]
        true   || [1, 2] | [1, 2, 3]
        true   || [1, 2] | [1, 2, 3, 4]
        false  || [1, 2] | [2, 3, 4]
    }


    def "has"() {
        def list = ["A", "B", "C", "D", "E"]

        expect:
        final String capture = toFind;
        result == CollectionUtil.has(list, new IPredicator<String>() {
            @Override
            boolean predicate(String s) {
                return StringUtil.equals(s, capture);
            }
        })

        where:
        toFind | result
        "A"    | true
        "B"    | true
        "C"    | true
        "D"    | true
        "E"    | true
        "F"    | false
        "G"    | false
    }

    def "find"() {
        def list = ["A", "B", "C", "D", "E"]

        expect:
        final String capture = toFind;
        result == CollectionUtil.find(list, new IPredicator<String>() {
            @Override
            boolean predicate(String s) {
                return StringUtil.equals(s, capture);
            }
        })

        where:
        toFind | result
        "A"    | "A"
        "B"    | "B"
        "C"    | "C"
        "D"    | "D"
        "E"    | "E"
        "F"    | null
        "G"    | null
    }



}

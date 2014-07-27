package common.basic.utils

import spock.lang.Specification

class EnumerationUtilTest extends Specification {
    def "ctor"() {
        when: new EnumerationUtil()
        then: thrown(InstantiationException)
    }

    def "ToIterable"() {
        def array = [ 1, 2, 3, 4 ];
        final Enumeration<Integer> enumeration = new Enumeration<Integer>() {
            int i = 0;

            @Override
            public boolean hasMoreElements() {
                return i < array.size();
            }

            @Override
            public Integer nextElement() {
                return array[i++];
            }
        };


        final Iterable<Integer> iterable = EnumerationUtil.toIterable(enumeration);
        final List<Integer> list = IterableUtil.toList(iterable);

        expect:
        list == array
    }
}

package common.basic.utils

import common.basic.interfaces.ICallbackTransform
import spock.lang.Specification

import java.lang.reflect.Field

class ReflectionUtilTest extends Specification {

    static class Test1 {

        @AnnotationForTest
        public String s;

        @AnnotationForTest
        public int i;

        public long l;

        public float f;

        public double d;

        Test1() {
        }

        Test1(String s, int i, long l, float f, double d) {
            this.s = s
            this.i = i
            this.l = l
            this.f = f;
            this.d = d;
        }

        boolean equals(o) {
            if (this.is(o)) return true
            if (!(o instanceof Test1)) return false

            Test1 test1 = (Test1) o

            if (i != test1.i) return false
            if (l != test1.l) return false
            if (s != test1.s) return false
            if (f != test1.f) return false
            if (d != test1.d) return false

            return true
        }

        int hashCode() {
            int result
            result = (s != null ? s.hashCode() : 0)
            result = 31 * result + i
            result = 31 * result + (int) (l ^ (l >>> 32))
            return result
        }
    }

    static class Test1Child extends Test1 {
        public int ii;
        public int ll;
    }

    def "ctor"() {
        when: new ReflectionUtil()
        then: thrown(InstantiationException)
    }

    def "GetAnnotatedField"() {
        List<Field> list = ReflectionUtil.getAnnotatedField(Test1.class, AnnotationForTest.class)

        expect:
        list.size() == 2
        list[0].name == "s"
        list[1].name == "i"
    }

    def "GetAnnotatedFieldFirst"() {
        def field = ReflectionUtil.getAnnotatedFieldFirst(Test1.class, AnnotationForTest.class)

        expect:
        field.name == "s"
    }

    def "GetAnnotatedFieldNameFirst"() {
        def name = ReflectionUtil.getAnnotatedFieldNameFirst(Test1.class, AnnotationForTest.class)
        expect:
        name == "s"
    }

    def "GetAnnotatedFieldValueFirst"() {
        expect:
        "s" == ReflectionUtil.getAnnotatedFieldValueFirst(new Test1("s", 1, 10, 3f, 4d), AnnotationForTest.class)
    }

    def "GetMapField"() {
        Map<String, Field> map = ReflectionUtil.getMapField(Test1.class);

        expect:
        map.keySet().containsAll(["s", "i", "l"])
    }

    def "GetValue fail"() {
        expect:
        null == ReflectionUtil.getValue(new Test1("aaa", 1, 2, 3f, 4d), ReflectionUtil.getListFieldDeclaredRecursive(String.class)[0])
    }

    def "FromListMap"() {
        expect:
        [new Test1("s1", 11, 1000l, 3f, 4d), new Test1("s2", 12, 2000l, 3f, 4d)] == ReflectionUtil.fromListMap(Test1.class, [["s":"s1", "i":11, "l":1000l, "f":3f, "d":4d], ["s":"s2", "i":12, "l":2000l, "f":3f, "d":4d]])
    }

    def "FromMap"() {
        expect:
        new Test1("s", 10, 1000l, 3f, 4d) == ReflectionUtil.fromMap(Test1.class, ["s":"s", "i":10, "l":1000l, "f":3f, "d":4d])
    }

    def "FromMap2"() {
        expect:
        new Test1("s", 10, 1000l, 3f, 4d) == ReflectionUtil.fromMap(Test1.class, ["s":"s", "i":new Long(10), "l":1000l, "f":new Double(3f), "d":4d])
    }

    def "ToMap"() {
        def map = ReflectionUtil.toMap(new Test1("s", 10, 1000l, 3f, 4d))
        expect:
        map["s"] == "s";
        map["i"] == 10;
        map["l"] == 1000l;
        map["f"] == 3f;
        map["d"] == 4d;
    }

    def "GetListFieldDeclaredRecursive"() {
        expect:
        ListUtil.transform(ReflectionUtil.getListFieldDeclaredRecursive(Test1Child.class), new ICallbackTransform<Field, String>() {
            @Override
            String transform(Field field) {
                return field.name;
            }
        }).containsAll(["s", "i", "l", "ii", "ll"])
    }

    def "GetMapFieldDeclaredRecursive"() {
        expect:
        ReflectionUtil.getMapFieldDeclaredRecursive(Test1Child.class).keySet().containsAll(["s", "i", "l", "ii", "ll"])
    }

    def "GetFieldDeclaredRecursive"() {
        expect:
        ReflectionUtil.getFieldDeclaredRecursive(Test1Child.class, "s").name == "s"
    }
}

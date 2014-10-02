package common.basic.utils

import spock.lang.Specification

import java.lang.reflect.Field

class ReflectionUtilTest extends Specification {

    static class Test1 {
        @AnnotationForTest
        @Deprecated
        String s;

        @AnnotationForTest
        int i;

        long l;


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

    def "GetAnnotatedKeyFieldName"() {
        def name = ReflectionUtil.getAnnotatedKeyFieldName(Test1.class, AnnotationForTest.class)
        expect:
        name == "s"
    }

    def "GetAnnotatedKeyFieldValue"() {

    }

    def "GetMapField"() {

    }

    def "GetValue"() {

    }

    def "FromListMap"() {

    }

    def "FromListMap1"() {

    }

    def "FromMap"() {

    }

    def "FromMap1"() {

    }

    def "ToMap"() {

    }

    def "GetListFieldDeclaredRecursive"() {

    }

    def "GetMapFieldDeclaredRecursive"() {

    }

    def "GetFieldDeclaredRecursive"() {

    }
}

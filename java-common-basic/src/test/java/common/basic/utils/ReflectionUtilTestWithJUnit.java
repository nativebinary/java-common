package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class ReflectionUtilTestWithJUnit extends Assert {

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new ReflectionUtil();
    }

    @Test
    public void testFromMap() throws Exception {

        final TestClass testClass = ReflectionUtil.fromMap(TestClass.class, new HashMapStringObject("s", "a").and("i", 10));
        assertEquals("a", testClass.s);
        assertEquals(10, testClass.i);

    }

    @Test
    public void testToMap() throws Exception {

        TestClass testClass = new TestClass();
        testClass.s = "a";
        testClass.i = 1;
        final Map<String, Object> map = ReflectionUtil.toMap(testClass);
        assertEquals("a", map.get("s"));
        assertEquals(1, map.get("i"));

    }

    @Test
    public void testObjectToMapToObject() throws Exception {

        TestClass testClass = new TestClass();
        testClass.s = "a";
        testClass.i = 1;
        final Map<String, Object> map = ReflectionUtil.toMap(testClass);

        TestClass testClass2 = ReflectionUtil.fromMap(TestClass.class, map);
        assertEquals(testClass.s, testClass2.s);
        assertEquals(testClass.i, testClass2.i);

    }
}


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

    public static class TestTest {
        public int i;
        public boolean b;
        public Integer integer;
        public Long long_;
    }

    @Test
    public void setFieldValue() throws Exception {

        TestTest testTest = new TestTest();

        ReflectionUtil.setFieldValue(testTest, ReflectionUtil.getFieldDeclaredRecursive(TestTest.class, "i"), 10, null);
        assertEquals(10, testTest.i);

        ReflectionUtil.setFieldValue(testTest, ReflectionUtil.getFieldDeclaredRecursive(TestTest.class, "i"), 99, null);
        assertEquals(99, testTest.i);

        ReflectionUtil.setFieldValue(testTest, ReflectionUtil.getFieldDeclaredRecursive(TestTest.class, "b"), 1, null);
        assertEquals(true, testTest.b);

        ReflectionUtil.setFieldValue(testTest, ReflectionUtil.getFieldDeclaredRecursive(TestTest.class, "b"), 0, null);
        assertEquals(false, testTest.b);

        ReflectionUtil.setFieldValue(testTest, ReflectionUtil.getFieldDeclaredRecursive(TestTest.class, "integer"), null, null);
        assertEquals(null, testTest.integer);

        ReflectionUtil.setFieldValue(testTest, ReflectionUtil.getFieldDeclaredRecursive(TestTest.class, "integer"), "", null);
        assertEquals(null, testTest.integer);

        ReflectionUtil.setFieldValue(testTest, ReflectionUtil.getFieldDeclaredRecursive(TestTest.class, "integer"), "2", null);
        assertEquals(new Integer(2), testTest.integer);

        ReflectionUtil.setFieldValue(testTest, ReflectionUtil.getFieldDeclaredRecursive(TestTest.class, "long_"), null, null);
        assertEquals(null, testTest.long_);

        ReflectionUtil.setFieldValue(testTest, ReflectionUtil.getFieldDeclaredRecursive(TestTest.class, "long_"), "1", null);
        assertEquals(new Long(1), testTest.long_);

        ReflectionUtil.setFieldValue(testTest, ReflectionUtil.getFieldDeclaredRecursive(TestTest.class, "long_"), "2", null);
        assertEquals(new Long(2), testTest.long_);
    }
}


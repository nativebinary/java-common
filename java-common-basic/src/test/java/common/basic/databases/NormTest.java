package common.basic.databases;

import common.basic.utils.HashMapStringObject;
import common.basic.utils.ReflectionUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class NormTest extends Assert {


    @Test
    public void testFromMap() throws Exception {

        final TestMock testMock = ReflectionUtil.fromMap(TestMock.class, new HashMapStringObject("s", "a").and("i", 10));
        assertEquals("a", testMock.s);
        assertEquals(10, testMock.i);

    }

    @Test
    public void testToMap() throws Exception {

        TestMock testMock = new TestMock();
        testMock.s = "a";
        testMock.i = 1;
        final Map<String, Object> map = ReflectionUtil.toMap(testMock);
        assertEquals("a", map.get("s"));
        assertEquals(1, map.get("i"));

    }

    @Test
    public void testObjectToMapToObject() throws Exception {

        TestMock testMock = new TestMock();
        testMock.s = "a";
        testMock.i = 1;
        final Map<String, Object> map = ReflectionUtil.toMap(testMock);

        TestMock testMock2 = ReflectionUtil.fromMap(TestMock.class, map);
        assertEquals(testMock.s, testMock2.s);
        assertEquals(testMock.i, testMock2.i);

    }

}

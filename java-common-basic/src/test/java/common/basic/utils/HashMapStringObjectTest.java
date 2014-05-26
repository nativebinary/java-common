package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

public class HashMapStringObjectTest extends Assert {

    @Test
    public void testHashMap() throws Exception {

        final HashMapStringObject hashMap = new HashMapStringObject();
        hashMap.and("testKey", "testValue");

        assertEquals("testValue", hashMap.get("testKey"));
        assertTrue(hashMap.containsKey("testKey"));

        final HashMapStringObject hashMap2 = new HashMapStringObject("key", "test");
        assertEquals("test", hashMap2.get("key"));
        assertTrue(hashMap2.containsKey("key"));
    }
}

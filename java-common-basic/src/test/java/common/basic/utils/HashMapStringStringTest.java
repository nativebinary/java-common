package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

public class HashMapStringStringTest extends Assert {

    @Test
    public void testHashMap() throws Exception {

        final HashMapStringString hashMap = new HashMapStringString();
        hashMap.and("testKey", "testValue");

        assertEquals("testValue", hashMap.get("testKey"));
        assertTrue(hashMap.containsKey("testKey"));

        final HashMapStringString hashMap2 = new HashMapStringString("key", "test");
        assertEquals("test", hashMap2.get("key"));
        assertTrue(hashMap2.containsKey("key"));
    }
}

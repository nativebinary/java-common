package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

public class MapBuilderTest extends Assert {

    @Test
    public void testHashMap() throws Exception {

        final MapBuilderT hashMap = new MapBuilderT();
        hashMap.and("testKey", "testValue");
        assertEquals("testValue", hashMap.get("testKey"));
        assertTrue(hashMap.containsKey("testKey"));

        final MapBuilderT hashMapStringString = new MapBuilderT("key", "test");
        assertEquals("test", hashMapStringString.get("key"));
        assertTrue(hashMapStringString.containsKey("key"));

        final MapBuilderT hashMapObjectObject = new MapBuilderT();
        hashMapObjectObject.and(1,19);
        assertEquals(19, hashMapObjectObject.get(1));
        assertTrue(hashMapObjectObject.containsKey(1));
    }
}

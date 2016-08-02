package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class PairTest extends Assert {

    String dummyKey = "key";
    final String dummyValue = "dummyValue";

    public Pair testPair(){
        final HashMap<String, Object> map = new HashMap<String, Object>();

        map.put(dummyKey, dummyValue);

        return new Pair<String, Object>(dummyKey, map.get(dummyKey));
    }

    @Test
    public void testGetKey(){
        final Pair pair = testPair();
        assertEquals(dummyKey,pair.getKey());
    }

    @Test
    public void testGetValue(){
        final Pair pair = testPair();
        assertEquals(dummyValue, pair.getValue());
    }

    @Test
    public void testSetValue(){
        String tempValue = "temp";

        final Pair pair = testPair();
        assertEquals(tempValue, pair.setValue(tempValue));
    }


}

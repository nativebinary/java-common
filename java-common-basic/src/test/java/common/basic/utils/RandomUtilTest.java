package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

public class RandomUtilTest extends Assert {

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new RandomUtil();
    }

    @Test
    public void testNextLeadingZeroInt() throws Exception {
        assertEquals(1, RandomUtil.nextLeadingZeroInt(9).length());
        assertEquals(5, RandomUtil.nextLeadingZeroInt(99999).length());
        assertEquals(10, RandomUtil.nextLeadingZeroInt(2000000000).length());
    }
    @Test
    public void testNextString() throws Exception {
        assertEquals(0, RandomUtil.nextString(0).length());
        assertEquals(1, RandomUtil.nextString(1).length());
        assertEquals(2, RandomUtil.nextString(2).length());
        assertEquals(100, RandomUtil.nextString(100).length());
        assertEquals(1024, RandomUtil.nextString(1024).length());
    }



}

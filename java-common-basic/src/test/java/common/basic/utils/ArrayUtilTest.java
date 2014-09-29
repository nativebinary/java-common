package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilTest extends Assert{

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new ArrayUtil();
    }

    @Test
    public void testIsNullOrEmpty(){
        assertTrue(ArrayUtil.isNullOrEmpty(null));

        assertTrue(ArrayUtil.isNullOrEmpty(new String[0]));

        assertFalse(ArrayUtil.isNullOrEmpty(new String[]{"", "", "", "", ""}));
    }
}

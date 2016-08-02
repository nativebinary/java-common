package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

public class CloseableUtilTest extends Assert{

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new CloseableUtil();
    }

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @Test
    public void testClose() throws Exception {
        assertFalse(CloseableUtil.close(null));
    }
}

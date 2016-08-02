package common.basic.utils;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CrcUtilTest extends Assert {

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new CrcUtil();
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCalculate() throws Exception {
        assertEquals(852952723, CrcUtil.calculate(new byte[] { 10 }));
    }
}

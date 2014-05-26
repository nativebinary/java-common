package common.basic;

import common.basic.logs.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PrepareTest extends Assert {
    @Before
    public void setUp() throws Exception {
        Logger.setDebug(true);
        assertTrue(Logger.isDebug());
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void testPrepare() throws Exception {


    }
}

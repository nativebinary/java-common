package common.basic;

import common.CommonInit;
import common.basic.facades.jsons.gson.JsonEngineGson;
import common.basic.logs.Logger;
import common.basic.logs.LoggerStandardOut;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PrepareTest extends Assert {
    @Before
    public void setUp() throws Exception {
        CommonInit.init(new LoggerStandardOut(), true, new JsonEngineGson());
        assertTrue(Logger.isDebug());
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void testPrepare() throws Exception {


    }
}

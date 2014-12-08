package common.basic.facades.jsons;

import common.basic.geometiries.Point;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class JsonUtilTest extends Assert {


    IJsonEngine jsonEngineOld;
    @Before
    public void setUp() throws Exception {
        jsonEngineOld = JsonUtil.setJsonEngine(createJsonEngine());
    }

    protected abstract IJsonEngine createJsonEngine();

    @After
    public void tearDown() throws Exception {

        JsonUtil.setJsonEngine(jsonEngineOld);
    }


    @Test
    public void testToJsonString() throws Exception {
        assertEquals("null", JsonUtil.toJsonString(null));
        assertEquals("true", JsonUtil.toJsonString(true));
        assertEquals("10", JsonUtil.toJsonString(10));
        assertEquals("\"ss\"", JsonUtil.toJsonString("ss"));
        assertEquals("{\"x\":1,\"y\":10}", JsonUtil.toJsonString(new Point(1, 10)));
    }

    @Test
    public void testGson() throws Exception {
        assertEquals(new Point(1, 10), JsonUtil.parse("{\"x\":1,\"y\":10}", Point.class));
    }
}

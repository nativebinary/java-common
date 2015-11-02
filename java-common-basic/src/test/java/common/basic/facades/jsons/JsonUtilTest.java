package common.basic.facades.jsons;

import common.basic.geometiries.Point;
import common.basic.utils.HashMapStringObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        assertEquals("null", JsonUtil.stringify(null));
        assertEquals("true", JsonUtil.stringify(true));
        assertEquals("10", JsonUtil.stringify(10));
        assertEquals("\"ss\"", JsonUtil.stringify("ss"));
        assertEquals("{\"x\":1,\"y\":10}", JsonUtil.stringify(new Point(1, 10)));
    }

    @Test
    public void testParse() throws Exception {
        assertEquals(new Point(1, 10), JsonUtil.parse("{\"x\":1,\"y\":10}", Point.class));
    }

    @Test
    public void testParseList() throws Exception {
        final List<Point> list = JsonUtil.parseList("[{\"x\":1,\"y\":10}]", Point.class);
        assertEquals(1, list.size());
        assertEquals(Point.class, list.get(0).getClass());
        assertEquals(1, list.get(0).x);
        assertEquals(10, list.get(0).y);
    }

    @Test
    public void testConvertStringMapToJson() {
        assertEquals("{\"test1\":\"1\",\"test2\":\"2\"}", JsonUtil.convertStringMapToJson(new HashMapStringObject().and("test1","1").and("test2", "2")));
    }


}

package common.basic.facades.jsons;

import common.basic.facades.jsons.gson.JsonEngineGson;
import common.basic.facades.jsons.jackson.JacksonUtil;
import common.basic.facades.jsons.jackson.JsonEngineJackson;
import common.basic.logs.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JsonUtilTest2 extends Assert {

    public static class TempClass {
        public String stringField;
        public int intField;

    }

    @Before
    public void before() throws Exception {
    }

    @Test
    public void testJackson() throws Exception {
        JsonUtil.setJsonEngine(new JsonEngineJackson());
        List<TempClass> tempClasses = JsonUtil.toListT("[{\"stringField\":\"a\", \"intField\":2}]", TempClass.class);

        assertEquals(1, tempClasses.size());
        assertEquals(TempClass.class, tempClasses.get(0).getClass());
    }

    @Test
    public void testGson() throws Exception {
        JsonUtil.setJsonEngine(new JsonEngineGson());
        List<TempClass> tempClasses = JsonUtil.toListT("[{\"stringField\":\"a\", \"intField\":2}]", TempClass.class);

        assertEquals(1, tempClasses.size());
        assertEquals(TempClass.class, tempClasses.get(0).getClass());
    }
}

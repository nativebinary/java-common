package common.basic.facades.jsons;

import common.basic.facades.jsons.gson.JsonEngineGson;

public class JsonUtilTestWithGson extends JsonUtilTest {

    @Override
    protected IJsonEngine createJsonEngine() {
        return new JsonEngineGson();
    }
}

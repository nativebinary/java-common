package common.basic.facades.jsons;

import common.basic.facades.jsons.jackson.JsonEngineJackson;

public class JsonUtilTestWithJackson extends JsonUtilTest {

    @Override
    protected IJsonEngine createJsonEngine() {
        return new JsonEngineJackson();
    }
}

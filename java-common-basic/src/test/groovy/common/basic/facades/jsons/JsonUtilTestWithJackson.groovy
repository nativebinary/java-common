package common.basic.facades.jsons

import common.basic.facades.jsons.jackson.JsonEngineJackson

class JsonUtilTestWithJackson extends JsonUtilTest {
    @Override
    IJsonEngine createJsonEngine() {
        return new JsonEngineJackson();
    }
}

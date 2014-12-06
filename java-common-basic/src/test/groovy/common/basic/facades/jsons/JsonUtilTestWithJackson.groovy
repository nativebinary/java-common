package common.basic.facades.jsons

import common.basic.facades.jsons.jackson.JsonEngineJackson

class JsonUtilTestWithJackson extends JsonUtilTest2 {
    @Override
    IJsonEngine createJsonEngine() {
        return new JsonEngineJackson();
    }
}

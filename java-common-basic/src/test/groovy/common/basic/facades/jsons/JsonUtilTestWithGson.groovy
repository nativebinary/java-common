package common.basic.facades.jsons

import common.basic.facades.jsons.gson.JsonEngineGson

class JsonUtilTestWithGson extends JsonUtilTest2 {
    @Override
    IJsonEngine createJsonEngine() {
        return new JsonEngineGson();
    }
}

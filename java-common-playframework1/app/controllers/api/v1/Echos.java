package controllers.api.v1;

import controllers.ControllerBase;
import logics.Json;

import java.util.Map;

@Json
public class Echos extends ControllerBase {
    public static void index(){ // void 장비를 등록합니다.
        final Map<String, String> stringStringMap = request.params.allSimple();
        renderJsonTrue(stringStringMap);
    }
}


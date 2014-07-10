package common;

import common.basic.jsons.IJsonEngine;
import common.basic.jsons.JsonUtil;
import common.basic.logs.ILogger;
import common.basic.logs.Logger;

public class CommonInit {
    public static void init(ILogger logger, boolean debug, IJsonEngine jsonEngine){
        Logger.setLogger(logger);
        Logger.setDebug(debug);
        JsonUtil.setJsonEngine(jsonEngine);
    }
}

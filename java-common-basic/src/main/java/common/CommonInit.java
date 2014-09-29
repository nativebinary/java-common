package common;

import common.basic.facades.jsons.IJsonEngine;
import common.basic.facades.jsons.JsonUtil;
import common.basic.logs.ILogger;
import common.basic.logs.Logger;

public class CommonInit {

    public CommonInit() throws InstantiationException {
        throw new InstantiationException();
    }

    public static void init(ILogger logger, boolean debug, IJsonEngine jsonEngine){
        Logger.setLogger(logger);
        Logger.setDebug(debug);
        JsonUtil.setJsonEngine(jsonEngine);
    }
}

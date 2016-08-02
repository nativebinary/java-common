package common.basic.genModels;


import common.CommonInit;
import common.basic.interfaces.RunnableThrowsException;
import common.basic.facades.jsons.gson.JsonEngineGson;
import common.basic.logs.Logger;
import common.basic.logs.LoggerStandardOut;
import common.basic.utils.FileUtil;

public class Main {

    public static void main(String[] args) {
        CommonInit.init(new LoggerStandardOut(), true, new JsonEngineGson());

        for (RunnableThrowsException runnable : new RunnableThrowsException[]{
                new ConvertModels(FileUtil.getFile("java-common-playframework1", "app", "models"), FileUtil.getFile("java-common-android", "src", "models")),
                new GenerateSelectorXmls(FileUtil.getFile("java-common-android", "res", "drawable")),
        }) {
            try {
                runnable.run();
            } catch (Exception e) {
                Logger.e(e);
            }
        }
    }
}

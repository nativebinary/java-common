package common.basic.externals.zip4j;

import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class ZipParametersUtil {
    public ZipParametersUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static ZipParameters createStore() {
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setCompressionMethod(Zip4jConstants.COMP_STORE);
        return zipParameters;
    }

    public static ZipParameters createDeflateUltra() {
        ZipParameters zipParametersMimeType = new ZipParameters();
        zipParametersMimeType.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        zipParametersMimeType.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);
        return zipParametersMimeType;
    }
}

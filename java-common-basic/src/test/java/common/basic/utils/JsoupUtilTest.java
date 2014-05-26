package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class JsoupUtilTest extends Assert{

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new JsoupUtil();
    }

    @Test
    public void testGetTitle() throws Exception {

        final String defaultPath = PathUtil.removeTrailingSlash(PathUtil.getPathOfTempDirectory());

        FileUtil.mkdirsThrows(new File(defaultPath + "/JsoupUtilTest/"), true);

//        assertEquals("temp.html", JsoupUtil.getTitle(new File(defaultPath + "/JsoupUtilTest/temp.html")));

        FileUtil.deleteRecursiveThrows(new File(defaultPath + "/JsoupUtilTest/"), true);

    }
}

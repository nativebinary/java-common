package common.basic.logs;

import org.junit.Assert;
import org.junit.Test;

public class LogUtilTest extends Assert {
    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new LogUtil();
    }
}

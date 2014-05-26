package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

public class StreamUtilTest extends Assert {

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new StreamUtil();
    }

    @Test
    public void testCopyThrows() throws Exception{
        //Todo
    }
}

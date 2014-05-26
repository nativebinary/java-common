package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

public class ResultSetUtilTest extends Assert {

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new ResultSetUtil();
    }

}

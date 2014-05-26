package common.basic.utils;

import common.basic.managers.HolderTempFile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class GoogleHttpClientUtilTest extends Assert {

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new GoogleHttpClientUtil();
    }

    HolderTempFile holderTempFile;
    @Before
    public void setUp() throws IOException {
        holderTempFile = new HolderTempFile();
    }

    @After
    public void tearDown() throws IOException {
        CloseableUtil.close(holderTempFile);
    }

}

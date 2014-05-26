package common.basic.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TempDirectoryTest extends Assert {

    TempDirectory tempDirectory;

    @Before
    public void setUp() throws Exception {
        tempDirectory = new TempDirectory();
    }

    @After
    public void tearDown() throws Exception {
        FileUtil.deleteThrows(tempDirectory.file);
    }

    @Test
    public void testCombine() throws Exception {
        String filePath = PathUtil.removeTrailingSlash(tempDirectory.file.getAbsolutePath());
        filePath += "/TempDirectory/";

        assertEquals(filePath, tempDirectory.combine("/TempDirectory/"));
    }


}

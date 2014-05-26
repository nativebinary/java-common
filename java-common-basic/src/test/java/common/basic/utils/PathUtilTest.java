package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class PathUtilTest extends Assert {

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new PathUtil();
    }

   @Test
    public void testAppendSlashSafe(){
       assertEquals("/", PathUtil.appendSlashSafe(""));
       assertEquals("/", PathUtil.appendSlashSafe(null));
       assertEquals("/", PathUtil.appendSlashSafe("/"));
       assertEquals("/test/", PathUtil.appendSlashSafe("/test"));
       assertEquals("/test/", PathUtil.appendSlashSafe("/test/"));
    }

    @Test
    public void testDirectoryPath(){
        assertEquals("/", PathUtil.directoryPath("/test.txt"));
        assertEquals("/test/", PathUtil.directoryPath("/test/test.txt"));
        assertEquals("/test/test2/", PathUtil.directoryPath("/test/test2/test.txt"));
    }

    @Test
    public void testCombine(){
        assertEquals("/root/test.txt", PathUtil.combine("/root/","test.txt"));
        assertEquals("/root/test.txt", PathUtil.combine("/root/","/test.txt"));
        assertEquals("/root/testRoot/test.txt", PathUtil.combine("/root/", "testRoot/test.txt"));
    }

    @Test
    public void testRemoveLeadingSlash(){
        assertEquals("test.txt", PathUtil.removeLeadingSlash("test.txt"));
        assertEquals("test.txt", PathUtil.removeLeadingSlash("/test.txt"));
        assertEquals("testRoot/test.txt", PathUtil.removeLeadingSlash("testRoot/test.txt"));
        assertEquals("testRoot/test.txt", PathUtil.removeLeadingSlash("/testRoot/test.txt"));
    }

    @Test
    public void testRemoveTrailingSlash(){
        assertEquals("test.txt", PathUtil.removeTrailingSlash("test.txt"));
        assertEquals("test", PathUtil.removeTrailingSlash("test/"));
        assertEquals("/test.txt", PathUtil.removeTrailingSlash("/test.txt"));
        assertEquals("testRoot/test", PathUtil.removeTrailingSlash("testRoot/test/"));
    }

    @Test
    public void testCalculateDepth(){
        assertEquals(0,PathUtil.calculateDepth("temp.txt"));
        assertEquals(1,PathUtil.calculateDepth("depth1/temp.txt"));
        assertEquals(2,PathUtil.calculateDepth("/depth2/temp.txt"));

        assertEquals(3,PathUtil.calculateDepth("/depth2/depth3/temp.txt"));
        assertEquals(3,PathUtil.calculateDepth("depth1/depth2/depth3/temp.txt"));

        assertEquals(4,PathUtil.calculateDepth("/depth2/depth3/depth4/temp.txt"));
    }

    @Test
    public void testGetFilename(){
        assertEquals("temp.txt", PathUtil.getFilename("/temp.txt"));
        assertEquals("temp.txt", PathUtil.getFilename("depth/temp.txt"));
        assertEquals("temp.txt", PathUtil.getFilename("/depth/temp.txt"));
        assertEquals("temp.txt", PathUtil.getFilename("depth1/depth2/temp.txt"));
    }
    @Test
    public void testGetDirectoryPath(){
        assertEquals("", PathUtil.getDirectoryPath("temp.txt"));
        assertEquals("", PathUtil.getDirectoryPath("/temp.txt"));
        assertEquals("/depth", PathUtil.getDirectoryPath("/depth/"));
        assertEquals("/depth", PathUtil.getDirectoryPath("/depth/temp.txt"));
        assertEquals("/depth/depth2", PathUtil.getDirectoryPath("/depth/depth2/temp.txt"));
    }
    @Test
    public void testEquals(){
        assertTrue(PathUtil.equals("/depth", "/depth/"));
        assertTrue(PathUtil.equals("/depth/depth", "/depth/depth"));
        assertTrue(PathUtil.equals("/depth/depth", "/depth/depth/"));

        assertFalse(PathUtil.equals("/depth", "depth/"));
        assertFalse(PathUtil.equals("/depth/depth", "depth/depth"));
    }
    @Test
    public void testGetFirstDepthPath(){
        assertEquals("temp.txt" , PathUtil.getFirstDepthPath("temp.txt"));
        assertEquals("temp.txt" , PathUtil.getFirstDepthPath("/temp.txt"));
        assertEquals("testRoot"  , PathUtil.getFirstDepthPath("testRoot/temp.txt"));
        assertEquals("Root"      , PathUtil.getFirstDepthPath("/Root/temp.txt"));
        assertEquals("Root"      , PathUtil.getFirstDepthPath("/Root/depth/temp.txt"));
    }
    @Test
    public void testRemoveFirstDepthPath(){
        assertEquals(""                  , PathUtil.removeFirstDepthPath("temp.txt"));
        assertEquals(""                  , PathUtil.removeFirstDepthPath("/temp.txt"));
        assertEquals("/temp.txt"        , PathUtil.removeFirstDepthPath("testRoot/temp.txt"));
        assertEquals("/temp.txt"        , PathUtil.removeFirstDepthPath("/Root/temp.txt"));
        assertEquals("/depth/temp.txt"  , PathUtil.removeFirstDepthPath("/Root/depth/temp.txt"));
    }

    @Test
    public void testRelativePath() throws Exception {
        final String defaultPath = PathUtil.getPathOfTempDirectory();

        FileUtil.mkdirsThrows(new File(PathUtil.combine(defaultPath , "/Temp/depth1/depth2")), true);
        FileUtil.mkdirsThrows(new File(PathUtil.combine(defaultPath , "/Temp/depthFolder")), true);
        FileUtil.mkdirsThrows(new File(PathUtil.combine(defaultPath , "/tempFolder/")), true);

        assertEquals("temp.txt"                     , PathUtil.relativePath(new File(PathUtil.combine(defaultPath , "/"))	                   , new File(PathUtil.combine(defaultPath , "/temp.txt"))));
        assertEquals("depth2/temp.txt"              , PathUtil.relativePath(new File(PathUtil.combine(defaultPath , "/Temp/depth1"))          , new File(PathUtil.combine(defaultPath , "/Temp/depth1/depth2/temp.txt"))));

        assertEquals("../../../temp.txt"            , PathUtil.relativePath(new File(PathUtil.combine(defaultPath , "/Temp/depth1/depth2/"))  , new File(PathUtil.combine(defaultPath , "/temp.txt"))));
        assertEquals("../../../temp.txt"            , PathUtil.relativePath(new File(PathUtil.combine(defaultPath , "/Temp/depth1/depth2"))   , new File(PathUtil.combine(defaultPath , "/temp.txt"))));

        assertEquals("../../temp.txt"               , PathUtil.relativePath(new File(PathUtil.combine(defaultPath , "/Temp/depth1/depth2"))   , new File(PathUtil.combine(defaultPath , "/Temp/temp.txt"))));
        assertEquals("../../depthFolder/temp.txt"   , PathUtil.relativePath(new File(PathUtil.combine(defaultPath , "/Temp/depth1/depth2"))   , new File(PathUtil.combine(defaultPath , "/Temp/depthFolder/temp.txt"))));
        assertEquals("../../../tempFolder/temp.txt" , PathUtil.relativePath(new File(PathUtil.combine(defaultPath , "/Temp/depth1/depth2"))   , new File(PathUtil.combine(defaultPath , "/tempFolder/temp.txt"))));

        assertEquals("../../temp.txt"               , PathUtil.relativePath(new File(PathUtil.combine(defaultPath , "/Temp/depth1/test.txt")), new File(PathUtil.combine(defaultPath , "/temp.txt"))));
        assertEquals("../temp.txt"                  , PathUtil.relativePath(new File(PathUtil.combine(defaultPath , "/Temp/depth1/test.txt")), new File(PathUtil.combine(defaultPath , "/Temp/temp.txt"))));
        assertEquals("../depthFolder/temp.txt"      , PathUtil.relativePath(new File(PathUtil.combine(defaultPath , "/Temp/depth1/test.txt")), new File(PathUtil.combine(defaultPath , "/Temp/depthFolder/temp.txt"))));
        assertEquals("../../tempFolder/temp.txt"    , PathUtil.relativePath(new File(PathUtil.combine(defaultPath , "/Temp/depth1/test.txt")), new File(PathUtil.combine(defaultPath , "/tempFolder/temp.txt"))));

        FileUtil.deleteRecursiveThrows(new File(PathUtil.combine(defaultPath , "/Temp/"))       , true);
        FileUtil.deleteRecursiveThrows(new File(PathUtil.combine(defaultPath , "/tempFolder/")) , true);
    }

}

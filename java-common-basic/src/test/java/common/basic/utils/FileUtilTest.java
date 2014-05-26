package common.basic.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileUtilTest extends Assert {

    File tempDirReadWrite;
    File tempDirReadOnly;
    @Before
    public void setUp() throws Exception {
        tempDirReadWrite = FileUtil.createSystemTempDir();
        tempDirReadOnly = FileUtil.createSystemTempDir();
        // TODO: assertTrue(tempDirReadOnly.setWritable(false, false));
    }

    @After
    public void tearDown() throws Exception {
        // TODO: assertTrue(tempDirReadOnly.setWritable(true, true));
        FileUtil.deleteThrows(tempDirReadOnly);
        FileUtil.deleteThrows(tempDirReadWrite);
    }

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new FileUtil();
    }

    @Test
    public void testGetExtensionNameWithoutDot() throws Exception {
        assertEquals("", FileUtil.getExtensionNameWithoutDot("test"));
        assertEquals("jpg", FileUtil.getExtensionNameWithoutDot("test.jpg"));
        assertEquals("jpg", FileUtil.getExtensionNameWithoutDot(new File("test.jpg")));
    }

    @Test
    public void testCreateTempDir() throws Exception {
        final File tempDir = FileUtil.createSystemTempDir();
        assertNotNull(tempDir);
        FileUtil.deleteRecursive(tempDir);
    }

    @Test
    public void testGetFileNameWithoutExt() {
        assertEquals("test", FileUtil.getFileNameWithoutExt("test.png"));
        assertEquals("testpng", FileUtil.getFileNameWithoutExt("testpng"));
    }

    @Test
    public void testDeleteRecursiveThrowsReadWrite() throws Exception {

    }

    @Test // TODO: (expected = IOException.class)
    public void testDeleteRecursiveThrowsReadOnly() throws Exception {
        // TODO: FileUtil.deleteRecursiveThrows(tempDirReadOnly);
    }

    @Test(expected = IOException.class)
    public void testCreateDuplicateFile() throws Exception {
        final File tempDir = FileUtil.createSystemTempDir();
        FileUtil.mkdirsThrows(tempDir);
        FileUtil.mkdirsThrows(tempDir,true);

        FileUtil.deleteRecursiveThrows(tempDir);
    }

    @Test //(expected = IOException.class)
    public void testMkdirsThrows() throws Exception {
        //Todo 파일 생성 실패하도록 테스트 코드 만들기
//        final String baseDirectory = PathUtil.getPathOfTempDirectory();
//
//        final File file = new File(PathUtil.combine(baseDirectory, "./:"));
//        FileUtil.mkdirsThrows(file);
//        FileUtil.deleteRecursiveThrows(file);
    }

    @Test(expected = IOException.class)
    public void testDeleteThrows() throws Exception {
        final String baseDirectory = PathUtil.getPathOfTempDirectory();

        final File file = new File(PathUtil.combine(baseDirectory, "/DeleteThrows/test.txt"));
        FileUtil.mkdirsThrows(file);

        FileUtil.deleteThrows(file);
        FileUtil.deleteThrows(file, true);
        FileUtil.deleteThrows(file, false);
    }

    @Test
    public void testDeleteRecursiveThrows() throws Exception {
        final String baseDirectory = PathUtil.getPathOfTempDirectory();

        final File file = new File(PathUtil.combine(baseDirectory, "/DeleteRecursiveThrows/test.txt"));
        FileUtil.mkdirsThrows(file);

        FileUtil.deleteRecursiveThrows(file);
    }

    @Test
    public void testGetFileNotExists() throws Exception{
        final File tempFile = FileUtil.createSystemTempDir();

        String absolutePath = tempFile.getAbsolutePath();
        String directoryPath = PathUtil.getDirectoryPath(absolutePath);

        File fileTarget = new File(absolutePath);
        fileTarget = FileUtil.getFileNotExists(directoryPath, fileTarget);

        assertFalse(fileTarget.exists());
    }

}

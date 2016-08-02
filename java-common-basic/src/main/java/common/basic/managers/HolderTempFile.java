package common.basic.managers;

import common.basic.utils.FileUtil;
import common.basic.utils.PathUtil;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class HolderTempFile implements Closeable {
    final Map<String, File> map;
    private final File fileTempRoot;

    public HolderTempFile() throws IOException {
        map = new HashMap<String, File>();
        fileTempRoot = FileUtil.createSystemTempDir();
    }

    @Override
    public void close() throws IOException {
        clear();
    }

    public void put(final String relativePath, final InputStream inputStream) throws IOException {
        final String absolutePath = PathUtil.combine(getFileTempRoot().getAbsolutePath(), relativePath);
        final String directoryPath = PathUtil.directoryPath(absolutePath);
        final File fileDirectory = new File(directoryPath);
        FileUtil.mkdirsThrows(fileDirectory, true);

        final File file = new File(absolutePath);
        FileUtil.write(file, inputStream);

        map.put(relativePath, file);
    }

    public File get(String relativePath)
    {
        return map.get(relativePath);
    }

    public void clear() throws IOException {
        for (String relativePath : map.keySet())
        {
            final File file = map.get(relativePath);
            FileUtil.deleteThrows(file);
        }

        map.clear();
        FileUtil.deleteRecursiveThrows(getFileTempRoot());
    }

    public File getFileTempRoot() {
        return fileTempRoot;
    }
}

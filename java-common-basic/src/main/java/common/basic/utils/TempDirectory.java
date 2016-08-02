package common.basic.utils;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

public class TempDirectory implements Closeable {
    final File file;

    public TempDirectory() throws IOException {
        file = FileUtil.createSystemTempDir();
    }

    @Override
    public void close() throws IOException {
    }

    public String combine(String path) {
        return PathUtil.combine(file.getAbsolutePath(), path);
    }

}

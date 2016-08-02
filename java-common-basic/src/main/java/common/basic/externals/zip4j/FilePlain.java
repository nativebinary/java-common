package common.basic.externals.zip4j;

import common.basic.utils.CloseableUtil;
import common.basic.utils.FileUtil;
import common.basic.utils.StreamUtil;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FilePlain extends FileAbstract {
    final File file;
    public FilePlain(File file) {
        this.file = file;
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public FileAbstract getSubDir(String name) {
        return new FilePlain(FileUtil.createDir(file, name));
    }

    @Override
    public InputStream getInputStream(String name) throws FileNotFoundException {
        return new FileInputStream(new File(file, name));
    }

    @Override
    public void setInputStream(String name, InputStream inputStream) throws FileNotFoundException, ZipException {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(file, name));
            StreamUtil.copy(inputStream, fileOutputStream);
        }
        finally {
            CloseableUtil.close(fileOutputStream);
        }
    }

    @Override
    public boolean exists(String name) {
        return new File(file, name).exists();
    }

    @Override
    public long getLastModified() {
        return file.lastModified();
    }
}

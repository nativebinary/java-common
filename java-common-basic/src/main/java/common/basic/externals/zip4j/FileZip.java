package common.basic.externals.zip4j;

import common.basic.utils.StringUtil;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.InputStream;

public class FileZip extends FileAbstract {
    final ZipFile zipFile;
    final String pathInner;

    public FileZip(ZipFile zipFile) {
        this(zipFile, "");
    }

    public FileZip(ZipFile zipFile, String pathInner) {
        this.zipFile = zipFile;
        this.pathInner = pathInner;
    }

    @Override
    public String getName() {
        return zipFile.getFile().getName();
    }

    @Override
    public FileAbstract getSubDir(String name) {
        return new FileZip(zipFile, getSubPath(name));
    }

    private String getSubPath(String name) {
        if(StringUtil.isNullOrEmpty(pathInner))
            return new File(name).getPath();

        return new File(pathInner, name).getPath();
    }

    @Override
    public InputStream getInputStream(String name) throws ZipException {
        return ZipFileUtil.getZipInputStream(zipFile, getSubPath(name));
    }

    @Override
    public void setInputStream(String name, InputStream inputStream) throws ZipException {
        ZipFileUtil.write(zipFile, getSubPath(name), inputStream);
    }

    @Override
    public boolean exists(String name) {
        return ZipFileUtil.exists(zipFile, getSubPath(name));
    }

    @Override
    public long getLastModified() {
        return zipFile.getFile().lastModified();
    }
}

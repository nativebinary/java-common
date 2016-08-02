package models;

import common.basic.utils.HumanReadable;
import common.basic.utils.MimeType;

import java.io.File;
import java.io.IOException;

public abstract class Attachment extends ModelAutoIncrement {

    @siena.NotNull
    public String fileName;

    @siena.NotNull
    public long fileLength;

    @siena.NotNull
    public long countDownload;

    public void setFile(File file) {
        fileName = file.getName();
        fileLength = file.length();
    }

    public abstract FileStorage getFileStorage();

    public String getUrl() {
        return getFileStorage().getUrl(id);
    }

    public String getPath() {
        return getFileStorage().getPath(id);
    }

    public File getFile() {
        return getFileStorage().getFile(id);
    }

    public boolean isImage()
    {
        return getMimeType().isImage();
    }

    public MimeType getMimeType() {
        return MimeType.detect(fileName);
    }

    public String getFileSizePretty() {
        return HumanReadable.capacity(fileLength);
    }

    public void overwrite(File file) throws IOException {
        getFileStorage().overwrite(this.id, file);
    }
}

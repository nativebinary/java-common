package models;

import common.basic.logs.Logger;
import common.basic.utils.FileUtil;
import common.basic.utils.PathUtil;
import common.basic.utils.StringUtil;
import logics.ApplicationConf;

import java.io.File;
import java.io.IOException;

public enum FileStorage {
    AttachmentAccountProfile,
    ;

    static  {
        for (FileStorage type : FileStorage.values())
        {
            File file = new File(type.getBasePath());
            if (file.exists())
                continue;

            try {
                FileUtil.mkdirsThrows(file);
            }
            catch (IOException e) {
                Logger.e(e);
            }
        }
    }


    private String getSimpleLowerCaseName() {
        final String concreteName = this.toString().substring("Attachment".length());
        return StringUtil.replaceFirstCharacterToLower(concreteName);
    }


    protected String getBasePath() {
        String basePath = ApplicationConf.fileStorage_path();
        return PathUtil.combine(basePath, getSimpleLowerCaseName());
    }


    public String getPath(long id)
    {
        return PathUtil.combine(getBasePath(), String.valueOf(id));
    }

    public String getUrl(long id) {
        //TODO: return Router.reverse(Download.class.getSimpleName() + "." + getSimpleLowerCaseName(), new HashMapStringObject("id", id)).url;
        return "";
    }





    public File getFile(long fileId)
    {
        return new File(this.getPath(fileId));
    }

    void overwrite(long id, File file) throws IOException {
        FileUtil.copyOverwriteThrows(file, getFile(id));
    }

}

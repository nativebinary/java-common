package common.basic.managers;

import common.basic.logs.Logger;
import common.basic.utils.FileUtil;
import common.basic.utils.PathUtil;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HolderResource implements Closeable {

    final String pathBase;
    final Map<String, File> mapFileResource;


    public HolderResource(String pathBase) {
        this.pathBase = pathBase;
        this.mapFileResource = new HashMap<String, File>();
    }

    @Override
    public void close() throws IOException {
        clear();
    }

    public synchronized void clear()
    {
        for (File file : mapFileResource.values())
        {
            if (!file.exists())
            {
                Logger.e(file);
                continue;
            }

            if (!file.delete())
                Logger.e(file);

        }

        mapFileResource.clear();
    }

    public File add(String relativePath, File file)
    {
        String absolutePath = PathUtil.combine(pathBase, relativePath);
        String directoryPath = PathUtil.getDirectoryPath(absolutePath);

        File fileTarget = new File(absolutePath);

        fileTarget = FileUtil.getFileNotExists(directoryPath, fileTarget);

        final File fileDirectory = new File(directoryPath);
        if (!fileDirectory.exists())
            fileDirectory.mkdirs();

        FileUtil.copy(file, fileTarget);

        mapFileResource.put(relativePath, fileTarget);

        return fileTarget;
    }


    public Map<String, File> getMapFileResource() {
        return mapFileResource;
    }

    public File get(String relativePath) {
        return mapFileResource.get(relativePath);
    }
}

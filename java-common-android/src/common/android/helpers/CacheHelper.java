package common.android.helpers;

import android.content.Context;
import common.basic.logs.Logger;
import common.basic.utils.DateUtil;
import common.basic.utils.FileUtil;
import common.basic.utils.PathUtil;
import common.basic.utils.StreamUtil;
import common.basic.utils.URLUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class CacheHelper {
    public abstract static class Provider<T> {
        public abstract String getKey();
        public abstract boolean isExpired(long lastModified);

        public T create(Context context) throws Exception {
            return null;
        }
    }


    private static File getFileCacheDir(Context context) {
        try {
            return new File(PathUtil.combineAndCreateDir(context.getCacheDir().getPath(), "cache"));
        }
        catch (IOException e) {
            Logger.e(e);
            return null;
        }
    }


    public static byte[] get(Context context, Provider<byte[]> provider) throws Exception {
        final File fileCacheDir = getFileCacheDir(context);
        final File file = new File(fileCacheDir, provider.getKey());
        if (file.exists() && !provider.isExpired(file.lastModified()))
            return FileUtil.readArrayByte(file);

        byte[] arrayByte = provider.create(context);
        if(null != arrayByte)
            FileUtil.write(file, arrayByte);

        return arrayByte;
    }

    public static void set(Context context, String cacheKey, byte[] arrayByte) throws IOException {
        final File fileCacheDir = getFileCacheDir(context);
        final File file = new File(fileCacheDir, cacheKey);
        FileUtil.write(file, arrayByte);
    }

    public static void deleteExpiredCache(Context context, long milliSecExpirePeriod) {
        final File fileCacheDir = getFileCacheDir(context);
        final long expire = new Date().getTime() - milliSecExpirePeriod;
        Logger.i(milliSecExpirePeriod, expire);

        for (File file : FileUtil.getArrayFile(fileCacheDir)) {
            final long lastModified = file.lastModified();
            if (lastModified >= expire)
                continue;

            Logger.d(file.getPath(), expire - lastModified);
            try {
                FileUtil.deleteThrows(file);
            }
            catch (IOException e) {
                Logger.e(e);
            }
        }
    }

    public static byte[] cachedDownload(Context context, final String url) throws Exception {
        return CacheHelper.get(context, new CacheHelper.Provider<byte[]>() {
            @Override
            public String getKey() {
                return URLUtil.toFilename(url);
            }

            @Override
            public boolean isExpired(long lastModified) {
                return lastModified + DateUtil.Day * 7 < new Date().getTime();
            }

            @Override
            public byte[] create(Context context) throws Exception {
                final URLConnection urlConnection = new URL(url).openConnection();
                final InputStream inputStream = urlConnection.getInputStream();
                return StreamUtil.getArrayByteAll(inputStream);
            }
        });
    }
}

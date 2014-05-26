package common.basic.utils;

import java.io.File;

public class PathUtilNew {

    PathUtilNew() throws InstantiationException {
        throw new InstantiationException();
    }

    public static String appendSlashSafe(String path) {
        if (StringUtil.isNullOrEmpty(path))
            return File.separator;

        if (File.separator.equals(String.valueOf(path.charAt(path.length()-1))))
            return path;

        return path + File.separator;
    }

    public static String combine(String path, String fileName) {
        return appendSlashSafe(path) + removeLeadingSlash(fileName);
    }

    public static String removeLeadingSlash(String path) {
        if(!path.startsWith(File.separator))
            return path;

        return path.substring(1);
    }
}

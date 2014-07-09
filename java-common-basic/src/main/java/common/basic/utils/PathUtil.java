package common.basic.utils;

import java.io.File;
import java.io.IOException;

public class PathUtil {

    public PathUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static String appendSlashSafe(String path) {
        if (StringUtil.isNullOrEmpty(path))
            return "/";

        if ('/' == path.charAt(path.length()-1))
            return path;

        return path + "/";
    }

    public static String directoryPath(String filePath) {
        return filePath.substring(0, filePath.lastIndexOf("/") + 1);
    }

    public static String combine(String path, String fileName) {
        return appendSlashSafe(path) + removeLeadingSlash(fileName);
    }

    public static String combineAndCreateDir(String path, String subPath) throws IOException {
        final String result = combine(path, subPath);
        FileUtil.mkdirsThrows(new File(result), true);
        return result;
    }


    public static String removeLeadingSlash(String path) {
        if(!path.startsWith("/"))
            return path;

        return path.substring(1);
    }

    public static String removeTrailingSlash(String path) {
        if(!path.endsWith("/"))
            return path;

        return path.substring(0, path.length() - 1);
    }

    public static int calculateDepth(String path) {
        return StringUtil.count(PathUtil.removeTrailingSlash(path), '/');
    }

    public static String getFilename(String path) {
        final File file = new File(path);
        final String parent = file.getParent();
        return file.getPath().replace(appendSlashSafe(parent), "");
    }

    public static String getDirectoryPath(String path) {
        final int found = path.lastIndexOf("/");
        if(-1 != found)
            return path.substring(0, found);

        return "";
    }

    public static boolean equals(String path1, String path2) {
        return StringUtil.equals(removeTrailingSlash(path1), removeTrailingSlash(path2));
    }

    public static String getFirstDepthPath(String path) {
        String[] arrayPath = removeLeadingSlash(path).split("/");
        return arrayPath[0];
    }

    public static String removeFirstDepthPath(String path) {
        String s = removeLeadingSlash(path);
        int pos = s.indexOf("/");

        if (pos < 0)
            return "";

        return s.substring(pos);
    }

    public static String relativePath(File fileBaseDirectory, File file) {

        final String baseAbsolutePath = fileBaseDirectory.getAbsolutePath();
        final String fileAbsolutePath = file.getAbsolutePath();

        final String[] basePathSplitArray = removeLeadingSlash(baseAbsolutePath).split("/");
        final String[] filePathSplitArray = removeLeadingSlash(fileAbsolutePath).split("/");

        int matchingDepthCount = 0;
        for(String str: basePathSplitArray)
        {
            if(str.equals(filePathSplitArray[matchingDepthCount]))
                matchingDepthCount++;
            else
                break;
        }
        final int depth = calculateDepth(baseAbsolutePath);
        String result = "";
        int upDepthCount;

        if(fileBaseDirectory.isDirectory()){
            upDepthCount = depth - matchingDepthCount;
        }else{
            upDepthCount = (depth - 1) - matchingDepthCount;
        }

        for(int i = 0; i < upDepthCount; i++)
            result += "../";

        for(int i = matchingDepthCount; i < filePathSplitArray.length; i++)
            result += filePathSplitArray[i] + "/";

        result = removeTrailingSlash(result);

        return result;

    }

    public static String getPathOfTempDirectory(){
        return System.getProperty("java.io.tmpdir");
    }

    public static String getExtension(String s) {
        if(StringUtil.isNullOrEmpty(s))
            return StringUtil.empty;

        final int i = s.lastIndexOf('.');
        if(i < 0)
            return StringUtil.empty;

        return s.substring(i);
    }

    public static String getFilenameWithoutExtension(String name) {
        final int i = name.lastIndexOf(".");
        if(i < 0)
            return name;

        return name.substring(0, i);
    }
}

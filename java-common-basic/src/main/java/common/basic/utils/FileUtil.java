package common.basic.utils;

import common.basic.logs.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.comparator.NameFileComparator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FileUtil {

    public static boolean isNullOrNotExist(File fileImage) {
        return null == fileImage || !fileImage.exists();
    }

    public FileUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static String getExtensionNameWithoutDot(File file) {
        final String absolutePath = file.getAbsolutePath();
        return getExtensionNameWithoutDot(absolutePath);
    }

    public static String getExtensionNameWithoutDot(String absolutePath) {
        final int pos = absolutePath.lastIndexOf('.');

        if (0 > pos)
            return "";

        return absolutePath.substring(pos + 1);
    }


    public static String getFileNameWithoutExt(String filePath) {

        int posSlash = filePath.lastIndexOf('/');

        if (posSlash < 0)
            posSlash = 0;

        final int posDot = filePath.lastIndexOf('.');

        if (0 > posDot)
            return filePath.substring(posSlash);

        return filePath.substring(0, posDot);
    }

    public static File createSystemTempDir() throws IOException {
        final File sysTempDir = new File(System.getProperty("java.io.tmpdir"));
        File newTempDir;
        do
        {
            String dirName = UUID.randomUUID().toString();
            newTempDir = new File(sysTempDir, dirName);
        }
        while(newTempDir.exists());

        if (!newTempDir.mkdirs())
            throw new IOException("Failed to create temp dir named " + newTempDir.getAbsolutePath());

        return newTempDir;
    }

    public static boolean deleteRecursive(File fileOrDir) {

        if (fileOrDir.isDirectory())
        {
            for(File file : getListFile(fileOrDir))
            {
                deleteRecursive(file);
            }
        }

        return fileOrDir.delete();
    }

    public static boolean copy(File fileSource, File fileTarget) {

        if (fileTarget.exists())
            return false;

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannelInput = null;
        FileChannel fileChannelOutput = null;

        try {
            fileInputStream = new FileInputStream(fileSource);
            fileOutputStream = new FileOutputStream(fileTarget);

            fileChannelInput = fileInputStream.getChannel();
            fileChannelOutput = fileOutputStream.getChannel();

            long size = fileChannelInput.size();
            fileChannelInput.transferTo(0, size, fileChannelOutput);

        } catch (Exception e) {
            Logger.e(e);
        } finally {
            CloseableUtil.close(fileChannelOutput);
            CloseableUtil.close(fileChannelInput);
            CloseableUtil.close(fileOutputStream);
            CloseableUtil.close(fileInputStream);
        }
        return true;
    }





    public static void overwriteDirThrows(File fileSrc, File fileDst) throws IOException {
        FileUtil.mkdirsThrows(fileDst, true);
        if(fileSrc.exists())
        {
            deleteRecursiveThrows(fileDst, true);
            copyRecursiveThrows(fileSrc, fileDst);
        }
    }


    //<editor-fold desc="void mkdirsThrows();">
    public static void mkdirsThrows(File fileDirectory) throws IOException {
        mkdirsThrows(fileDirectory, false);
    }

    public static void mkdirsThrows(File fileDirectory, boolean ignoreWhenExist) throws IOException {
        if (fileDirectory.exists())
        {
            if(ignoreWhenExist)
                return;

            throw new IOException(fileDirectory.toString() + " is already exist.");
        }

        final boolean result = fileDirectory.mkdirs();
        if(result)
            return;

        throw new IOException("failed to make dir " + fileDirectory.toString());
    }
    //</editor-fold>

    //<editor-fold desc="void delete...();">
    public static void deleteThrows(File file) throws IOException {
        deleteThrows(file, false);
    }

    public static void deleteThrows(File file, boolean ignoreWhenNotExist) throws IOException {
        if(!file.exists())
        {
            if(ignoreWhenNotExist)
                return;

            throw new IOException(file.toString() + " is not exist.");
        }

        final boolean result = file.delete();
        if(result)
            return;

        throw new IOException("failed to delete " + file.toString());
    }

    public static void deleteRecursiveThrows(File file) throws IOException {
        deleteRecursiveThrows(file, false);
    }

    public static void deleteRecursiveThrows(File file, boolean ignoreWhenNotExist) throws IOException {
        if (file.isDirectory()) {
            for (File fileChild : getListFile(file)) {
                deleteRecursiveThrows(fileChild, ignoreWhenNotExist);
            }
        }

        deleteThrows(file, ignoreWhenNotExist);
    }
    //</editor-fold>

    private static List<File> getListFile(File file) {
        final File[] arrayFile = file.listFiles();
        if(null == arrayFile)
            return new ArrayList<File>();

        final ArrayList<File> listFile = new ArrayList<File>();
        listFile.addAll(Arrays.asList(arrayFile));
        return listFile;
    }

    public static File getFileNotExists(String directoryPath, File file) {
        if (!file.exists())
            return file;

        int index = 0;

        String fileName = file.getName();

        String fileNameOnly = getFileNameWithoutExt(fileName);
        String extensionNameWithoutDot = getExtensionNameWithoutDot(fileName);

        do{
            final String fileNameNew = String.format("%s_%04d.%s", fileNameOnly, ++index, extensionNameWithoutDot);
            file = new File(PathUtil.combine(directoryPath, fileNameNew));
        } while (file.exists());
        return file;
    }

    //<editor-fold desc="void read write();">
    public static byte[] readArrayByte(File file) throws IOException {
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            return StreamUtil.getArrayByteAll(fileInputStream);
        }
        finally {
            CloseableUtil.close(fileInputStream);
        }
    }

    public static String readString(File file) throws IOException {FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            return StreamUtil.getString(fileInputStream);
        }
        finally {
            CloseableUtil.close(fileInputStream);
        }
    }

    public static List<String> readListString(File file) throws IOException {
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            return StreamUtil.getListStringAll(fileInputStream);
        }
        finally {
            CloseableUtil.close(fileInputStream);
        }
    }

    public static void write(File file, byte[] arrayByte) throws IOException {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(arrayByte);
        }
        finally {
            CloseableUtil.close(fileOutputStream);
        }
    }

    public static void write(File file, String content) throws IOException {
        write(file, content.getBytes("UTF-8"));
    }

    public static void write(File file, InputStream inputStream) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            StreamUtil.copy(inputStream, outputStream);
        }
        finally {
            CloseableUtil.close(outputStream);
        }
    }

    //</editor-fold>


// TODO

    public interface ITraverseListener {

        boolean onDirectory(File directory);
        void onFile(File file);
    }

    public static void traversePath(File file, ITraverseListener listener) {
        if (!file.exists())
            return;

        if (file.isDirectory()) {
            if (!listener.onDirectory(file))
                return;
        } else {
            listener.onFile(file);
        }

        File[] files = file.listFiles();

        if (null == files)
            return;

        for (File childFile : files) {
            traversePath(childFile, listener);
        }
    }

    public static boolean equalsAllBytes(File lf, File rf) throws IOException {
        if (null == lf || null == rf)
            return false;

        if (lf.length() != rf.length())
            return false;

        InputStreamReader inputStreamReaderLf = new InputStreamReader(new FileInputStream(lf));
        InputStreamReader inputStreamReaderRf = new InputStreamReader(new FileInputStream(rf));

        while (inputStreamReaderLf.ready() && inputStreamReaderRf.ready())
        {
            try {
                if (inputStreamReaderLf.read() != inputStreamReaderRf.read()){
                    inputStreamReaderRf.close();
                    inputStreamReaderLf.close();
                    return false;
                }
            } catch (IOException e) {
                inputStreamReaderRf.close();
                inputStreamReaderLf.close();
                return false;
            }

        }

        inputStreamReaderRf.close();
        inputStreamReaderLf.close();

        return true;
    }
	

    public static File mkdirs(String path) {
        File folder = new File(path);
        return mkdirs(folder);
    }

    public static File mkdirs(File folder) {
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }


    public static File mkdirsPermission777(String folderPath) {
        File folder = mkdirs(folderPath);
        setPermission777(folder);
        return folder;
    }

    public static File mkdirsPermission777(File file) {
        File folder = mkdirs(file);
        setPermission777(folder);
        return folder;
    }

    public static void setPermission666(File file) {
        file.setReadable(true, false);
        file.setWritable(true, false);
        file.setExecutable(false, false);

    }

    public static void setPermission777(File file) {
        file.setReadable(true, false);
        file.setWritable(true, false);
        file.setExecutable(true, false);

    }

    public static boolean exists(String path) {
        File file = new File(path);

        return file.exists();
    }

    public static void deleteSafe(String filePath) {
        File file = new File(filePath);

        if (!file.exists())
            return;

        file.delete();
    }

    public static String getDotExtension(String filename) {
        if(StringUtil.isNullOrEmpty(filename))
            return "";


        final int i = filename.lastIndexOf(".");
        if (i < 0)
            return "";

        return filename.substring(i);
    }

    public static File[] getArrayFile(File file) {
        final File[] arrayFile = file.listFiles();
        return ArrayUtil.isNull(arrayFile, new File[]{});
    }

    public static File[] getArrayFileOrderByLastModifiedDesc(File file) {
        final File[] arrayFile = getArrayFile(file);
        Arrays.sort(arrayFile, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
        return arrayFile;
    }

    public static File[] getArrayFileOrderByNameDesc(File file) {
        final File[] arrayFile = getArrayFile(file);
        Arrays.sort(arrayFile, NameFileComparator.NAME_REVERSE);
        return arrayFile;
    }


    public static void copyRecursiveThrows(File fileSrc, File fileDest) throws IOException {
        FileUtils.copyDirectory(fileSrc, fileDest);
    }

    public static boolean isAncestorOf(File ancestor, File descendant) {
        if (descendant == null)
            return false;

        if (descendant.equals(ancestor))
            return true;

        return isAncestorOf(ancestor, descendant.getParentFile());
    }

    public static String relativize(File base, File target) {
        return base.toURI().relativize(target.toURI()).getPath();
    }

    public static File createDir(File file, String dirName) {
        try {
            final File fileNew = new File(file, dirName);
            FileUtil.mkdirsThrows(fileNew, true);
            return fileNew;
        }
        catch (IOException e) {
            Logger.e(e);
            return null;
        }
    }

    public static void moveThrows(File fileSrc, File fileDst) throws IOException {
        FileUtils.moveFile(fileSrc, fileDst);
    }

    public static void moveOverwriteThrows(File fileSrc, File fileDst) throws IOException {
        if(fileDst.exists())
            deleteThrows(fileDst, true);

        FileUtils.moveFile(fileSrc, fileDst);
    }

    public static void copyOverwriteThrows(File fileSrc, File fileDst) throws IOException {
        if(fileDst.exists())
            deleteThrows(fileDst, true);

        FileUtils.copyFile(fileSrc, fileDst);
    }

    public static void touchThrows(File file) throws IOException {
        touchThrows(file, new Date().getTime());
    }

    public static void touchThrows(File file, long timestamp) throws IOException {
        if (!file.exists())
            new FileOutputStream(file).close();

        if(!file.setLastModified(timestamp))
            throw new IOException("failed to touch " + file.toString());
    }

    public static File getFile(String... array) {
        File file = new File(array[0]);
        for (int i = 1; i < array.length; i++) {
            file = new File(file, array[i]);
        }
        return file;
    }
}

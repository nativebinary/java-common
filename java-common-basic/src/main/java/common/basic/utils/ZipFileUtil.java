package common.basic.utils;

//import net.lingala.zip4j.core.ZipFile;
//import net.lingala.zip4j.exception.ZipException;
//import net.lingala.zip4j.io.ZipInputStream;
//import net.lingala.zip4j.model.FileHeader;
//import net.lingala.zip4j.model.ZipParameters;

public class ZipFileUtil {
//    public static ZipInputStream getZipInputStream(ZipFile zipFile, String file) throws ZipException {
//        final FileHeader fileHeader = getFileHeader(zipFile, file);
//        return zipFile.getInputStream(fileHeader);
//    }
//
//
//    public static void write(ZipFile zipFile, String subPath, InputStream inputStream) throws ZipException {
//        if(exists(zipFile, subPath))
//            zipFile.removeFile(subPath);
//
//        ZipFileUtil.add(zipFile, subPath, inputStream);
//    }
//
//    public static FileHeader getFileHeader(ZipFile zipFile, String file) throws ZipException {
//        return zipFile.getFileHeader(file);
//    }
//
//    public static byte[] getArrayByteAll(ZipFile zipFile, String path) throws ZipException, IOException {
//        ZipInputStream zipInputStream = null;
//        try {
//            zipInputStream = getZipInputStream(zipFile, path);
//            return StreamUtil.getArrayByteAll(zipInputStream);
//        }
//        finally {
//            CloseableUtil.close(zipInputStream);
//        }
//    }
//
//    public static boolean exists(ZipFile zipFile, String path) {
//        try {
//            final FileHeader fileHeader = getFileHeader(zipFile, path);
//            if(fileHeader == null)
//                return false;
//
//            return true;
//        } catch (ZipException e) {
//            Logger.e(e);
//            return false;
//        }
//
//    }
//
//    public static void addStore(ZipFile zipFile, String fileNameInZip, InputStream inputStream) throws ZipException {
//        ZipParameters zipParameters = ZipParametersUtil.createStore();
//        zipParameters.setFileNameInZip(fileNameInZip);
//        zipParameters.setSourceExternalStream(true);
//        zipFile.addStream(inputStream, zipParameters);
//    }
//
//    public static void add(ZipFile zipFile, String fileNameInZip, InputStream inputStream) throws ZipException {
//        ZipParameters zipParameters = ZipParametersUtil.createDeflateUltra();
//        zipParameters.setFileNameInZip(fileNameInZip);
//        zipParameters.setSourceExternalStream(true);
//        zipFile.addStream(inputStream, zipParameters);
//    }
//
//    public static void add(ZipFile zipFile, File fileToCompress) throws ZipException {
//        final ZipParameters zipParameters = ZipParametersUtil.createDeflateUltra();
//        if (fileToCompress.isDirectory()) {
//            zipFile.addFolder(fileToCompress, zipParameters);
//            return;
//        }
//
//        zipFile.addFile(fileToCompress, zipParameters);
//    }


}

package common.basic.zips;

public abstract class FileAbstract {

//    public static FileAbstract create(File file) {
//        if(file.isDirectory())
//            return new FilePlain(file);
//
//        try {
//            return new FileZip(new ZipFile(file));
//        }
//        catch (ZipException e) {
//            Logger.e(e);
//            return null;
//        }
//    }
//
//    public abstract String getName();
//
//    public abstract FileAbstract getSubDir(String name);
//    public abstract InputStream getInputStream(String name) throws FileNotFoundException, ZipException;
//    public abstract void setInputStream(String name, InputStream inputStream) throws FileNotFoundException, ZipException;
//
//    public abstract boolean exists(String name);
//
//    public abstract long getLastModified();
}


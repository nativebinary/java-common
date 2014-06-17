package common.basic.genModels;

import common.basic.interfaces.RunnableThrowsException;
import common.basic.logs.Logger;
import common.basic.utils.CloseableUtil;
import common.basic.utils.FileUtil;
import common.basic.utils.PathUtil;
import common.basic.utils.StreamUtil;
import common.basic.utils.StringUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

public class GenerateSelectorXmls implements RunnableThrowsException {
    final File fileDrawable;

    public GenerateSelectorXmls(File fileDrawable) {
        this.fileDrawable = fileDrawable;
    }

    @Override
    public void run() {
        for (File file : FileUtil.getArrayFile(fileDrawable)) {

            final String path = file.getPath();
            if(!StringUtil.equals(PathUtil.getExtension(path), ".png"))
                continue;

            Logger.i(file);
            if(file.getName().endsWith(".9.png"))
            {
                ProcessPress9(file);
            }
            else
            {
                ProcessPress(file);
            }

        }


    }

    public void ProcessPress9(File file) {
        final String filename = file.getName();
        final String filenameWithoutExtension = PathUtil.getFilenameWithoutExtension(PathUtil.getFilenameWithoutExtension(filename));

        String filenamePress = filenameWithoutExtension + "_press.9.png";
        final File filePress = new File(fileDrawable, filenamePress);
        if (!filePress.exists())
            return;

        final File fileSelector = new File(fileDrawable, filenameWithoutExtension + "_selector.xml");
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = (BufferedInputStream) Main.class.getResource("selector.xml").getContent();
            final String content = StreamUtil.getString(bufferedInputStream);
            final String contentReplaced = content.replaceAll("\\{filenameWithoutExtension\\}", filenameWithoutExtension);
            FileUtil.write(new File(fileSelector.getPath()), contentReplaced);
        }
        catch (IOException e) {
            Logger.e(e);
        }
        finally {
            CloseableUtil.close(bufferedInputStream);
        }
    }

    public void ProcessPress(File file) {
        final String filename = file.getName();
        final String filenameWithoutExtension = PathUtil.getFilenameWithoutExtension(filename);

        String filenamePress = filenameWithoutExtension + "_press.png";
        final File filePress = new File(fileDrawable, filenamePress);

        if (!filePress.exists())
            return;

        final File fileSelector = new File(fileDrawable, filenameWithoutExtension + "_selector.xml");
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = (BufferedInputStream) Main.class.getResource("selector.xml").getContent();
            final String content = StreamUtil.getString(bufferedInputStream);
            final String contentReplaced = content.replaceAll("\\{filenameWithoutExtension\\}", filenameWithoutExtension);
            FileUtil.write(new File(fileSelector.getPath()), contentReplaced);
        }
        catch (IOException e) {
            Logger.e(e);
        }
        finally {
            CloseableUtil.close(bufferedInputStream);
        }
    }
}

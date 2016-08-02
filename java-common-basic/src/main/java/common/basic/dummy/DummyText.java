package common.basic.dummy;

import common.basic.logs.Logger;
import common.basic.utils.CloseableUtil;
import common.basic.utils.ListUtil;
import common.basic.utils.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DummyText {
    static List<String> list;
    static {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            list = new ArrayList<String>();

            inputStream = DummyText.class.getResource("Dummy.txt").openStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(StringUtil.isNullOrWhitespace(line))
                    continue;

                if(line.startsWith("#"))
                    continue;

                list.add(line);
            }
        }
        catch (IOException e) {
            Logger.e(e);
        }
        finally {
            CloseableUtil.close(bufferedReader);
            CloseableUtil.close(inputStreamReader);
            CloseableUtil.close(bufferedReader);
        }

    }

    public DummyText() throws InstantiationException {
        throw new InstantiationException();
    }

    public static String get(){
        return ListUtil.getRandomValue(list);
    }

    public static String getAtLeast(int length){
        StringBuilder stringBuilder = new StringBuilder();
        while(stringBuilder.length() < length)
        {
            stringBuilder.append(get());
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

}

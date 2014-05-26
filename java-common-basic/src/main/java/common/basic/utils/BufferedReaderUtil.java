package common.basic.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BufferedReaderUtil {

    public static List<String> getListStringAll(BufferedReader bufferedReader) throws IOException {
        List<String> list = new ArrayList<String>();
        String s;
        while((s = bufferedReader.readLine()) != null)
        {
            list.add(s);
        }

        return list;
    }

    public static String getStringAll(BufferedReader bufferedReader) throws IOException {
        char[] buffer = new char[8192];

        StringBuilder builder = new StringBuilder();
        int read;
        while ((read = bufferedReader.read(buffer, 0, buffer.length)) > 0) {
            builder.append(buffer, 0, read);
        }

        return builder.toString();
    }
}

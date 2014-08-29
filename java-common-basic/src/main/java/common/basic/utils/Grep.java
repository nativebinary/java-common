package common.basic.utils;

import java.util.ArrayList;
import java.util.List;

public class Grep {
    public Grep() throws InstantiationException {
        throw new InstantiationException();
    }

    public static String execute(String s, String toFind) {
        return execute(s, toFind, false);
    }

    public static String executeInvert(String s, String toFind) {
        return execute(s, toFind, true);
    }

    private static String execute(String s, String toFind, boolean invert) {
        final String[] arrayLine = s.split("\n");

        List<String> listFiltered = new ArrayList<String>(arrayLine.length);
        if(!invert)
        {
            for (String line : arrayLine) {
                if (line.contains(toFind))
                    listFiltered.add(line);
            }
        }
        else
        {
            for (String line : arrayLine) {
                if (!line.contains(toFind))
                    listFiltered.add(line);
            }
        }

        return StringUtil.join("\n", listFiltered);
    }
}

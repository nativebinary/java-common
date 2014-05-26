package common.basic.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpUtil {
    static Pattern patternExtractDomain = Pattern.compile("https?://([^/]+).*");

    public static String getExtractDomain(String url) {
        if(StringUtil.isNullOrWhitespace(url))
            return "";

        Matcher matcher = patternExtractDomain.matcher(url);


        if (matcher.matches())
            return matcher.group(1);

        return "";
    }

    public static String replaceUntilNoChange(Pattern pattern, String s, String replace) {
        String s2;
        while (true) {
            s2 = pattern.matcher(s).replaceAll(replace);
            if (StringUtil.equals(s, s2))
                break;

            s = s2;
        }

        return s2;
    }


    static Pattern patternRemoveRepeatedNewLine = Pattern.compile("\n{2,}");
    public static String removeRepeatedNewLine(String s) {
        return patternRemoveRepeatedNewLine.matcher(s).replaceAll("\n\n");
    }
}

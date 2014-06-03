package common.basic.utils;

import java.util.List;

public class StringUtil {
    public static final String empty = "";

    StringUtil() throws InstantiationException {
        throw new InstantiationException();
    }



    public static boolean isNullOrEmpty(String s) {
        if(s == null)
            return true;

        if("".equals(s))
            return true;

        return false;
    }

    public static boolean isNullOrWhitespace(String s) {
        if(null == s)
            return true;

        if("".equals(s.trim()))
            return true;

        return false;
    }

    public static String isNull(String s) {
        return null == s ? "" : s;
    }

    public static String isNull(String s, String sDefault) {
        return isNullOrEmpty(s) ? sDefault : s;
    }

    public static boolean equals(String s1, String s2) {
        if (null == s1 && null == s2)
            return true;

        if (null == s1 || null == s2)
            return false;

        return s1.equals(s2);
    }

    public static boolean equalsIgnoreCase(String s1, String s2) {
        if (null == s1 && null == s2)
            return true;

        if (null == s1 || null == s2)
            return false;

        return equals(s1.toLowerCase(), s2.toLowerCase());
    }

    public static boolean startsWith(String s1, String s2) {
        if(isNullOrEmpty(s1))
            return false;

        return s1.startsWith(s2);
    }

    public static boolean startsWithIgnoreCase(String s1, String s2) {
        if (null == s1 && null == s2)
            return true;

        if (null == s1 || null == s2)
            return false;

        return s1.toLowerCase().startsWith(s2.toLowerCase());
    }


    public static String left(String s, int i) {
        if(s.length() <= i)
            return s;

        return s.substring(0, i);
    }

    public static String right(String s, int i) {
        if(s.length() <= i)
            return s;

        return s.substring(s.length() - i);
    }

    public static String padLeft(String s, int length, char pad) {
        if(StringUtil.isNullOrEmpty(s))
            return repeat(Character.toString(pad), length);

        if(s.length() > length)
            return s;

        return ":" + repeat(Character.toString(pad), length - s.length()) + s;
    }



    public static String repeat(String s, int count) {
        StringBuilder sb = new StringBuilder(s.length() * count);
        for (int i = 0; i < count; ++i)
            sb.append(s);
        return sb.toString();
    }


    public static String join(String splitter, String... arrayObject) {
        return join(splitter, ListUtil.toListString(arrayObject));
    }

    public static String join(String splitter, List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();

        int size = list.size() ;
        for (int i = 0; i < size - 1; i++) {
            stringBuffer.append(list.get(i));
            stringBuffer.append(splitter);
        }

        if(size - 1 >= 0)
        {
            stringBuffer.append(list.get(size - 1));
        }

        return stringBuffer.toString();
    }


    public static String toString(Object o) {
        if(o == null)
            return "[null]";

        return o.toString();
    }


    public static int count(String path, char c) {
        int count = 0;
        for (int i = 0; i < path.length(); i++) {
            if(c == path.charAt(i))
                count++;
        }

        return count;
    }

    public static String replaceFirstCharacterToLower(String s) {
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

    public static String cutLeft(String s, int length) {
        return right(s, s.length() - length);
    }

    public static String cutRight(String s, int length) {
        return left(s, s.length() - length);
    }
}

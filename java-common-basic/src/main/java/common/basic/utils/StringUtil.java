package common.basic.utils;

import org.apache.commons.codec.binary.Base64;

import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public class StringUtil {
    public static final String empty = "";

    public StringUtil() throws InstantiationException {
        throw new InstantiationException();
    }



    public static boolean isNullOrEmpty(String s) {
        if(s == null)
            return true;

        //noinspection RedundantIfStatement
        if("".equals(s))
            return true;

        return false;
    }

    public static boolean isNullOrWhitespace(String s) {
        if(null == s)
            return true;

        //noinspection RedundantIfStatement
        if("".equals(s.trim()))
            return true;

        return false;
    }

    // TODO: rename. boolean?
    public static String isNull(String s) {
        return null == s ? "" : s;
    }

    public static String isNull(String s, String sDefault) {
        return isNullOrEmpty(s) ? sDefault : s;
    }

    public static boolean equals(String s1, String s2) {
        if (null == s1 && null == s2)
            return true;

        //noinspection SimplifiableIfStatement
        if (null == s1 || null == s2)
            return false;

        return s1.equals(s2);
    }

    public static boolean equalsIgnoreCase(String s1, String s2) {
        if (null == s1 && null == s2)
            return true;

        //noinspection SimplifiableIfStatement
        if (null == s1 || null == s2)
            return false;

        return equals(s1.toLowerCase(), s2.toLowerCase());
    }

    public static boolean equalsLimit(String s1, String s2, int limit) {
        if (null == s1 && null == s2)
            return false;

        //noinspection SimplifiableIfStatement
        if (null == s1 || null == s2)
            return false;

        if (s1.length() < limit || s2.length() < limit)
            return false;

        return s1.startsWith(s2.substring(0, limit));
    }

    public static boolean startsWith(String str, String prefix) {
        //noinspection SimplifiableIfStatement
        if(isNullOrEmpty(str) || isNullOrEmpty(prefix)) {
            return false;
        }

        return str.startsWith(prefix);
    }

    public static boolean startsWith(String str, String prefix, int toOffset) {
        //noinspection SimplifiableIfStatement
        if(isNullOrEmpty(str) || isNullOrEmpty(prefix)) {
            return false;
        }

        return str.startsWith(prefix, toOffset);
    }

    public static boolean startsWithIgnoreCase(String s1, String s2) {
        if (null == s1 && null == s2)
            return true;

        //noinspection SimplifiableIfStatement
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

        return repeat(Character.toString(pad), length - s.length()) + s;
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
        StringBuilder sb = new StringBuilder();

        int size = list.size() ;
        for (int i = 0; i < size - 1; i++) {
            sb.append(list.get(i));
            sb.append(splitter);
        }

        if(size - 1 >= 0)
        {
            sb.append(list.get(size - 1));
        }

        return sb.toString();
    }

    public static <T> String joinWithToString(String splitter, List<T> list) {
        StringBuilder sb = new StringBuilder();

        int size = list.size() ;
        for (int i = 0; i < size - 1; i++) {
            sb.append(list.get(i).toString());
            sb.append(splitter);
        }

        if(size - 1 >= 0)
        {
            sb.append(list.get(size - 1).toString());
        }

        return sb.toString();
    }


    public static String toString(Object o) {
        if(o == null)
            return "" + null;

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

    public static int getSumIntString(String... arrayObject) {

        int sum = 0;
        for (String anArrayObject : arrayObject) {
            sum += IntUtil.parseInt(anArrayObject, 0);
        }
        return sum;
    }

    public static String removeButNumber(String value) {
        if (null == value)
            return "";

        return value.replaceAll("\\D", "");
    }

    public static String removeTail(String path, char symbol) {

        if (isNullOrEmpty(path))
            return "";

        String trimPath = path.trim();

        if (trimPath.charAt(trimPath.length() - 1) == symbol)
            return trimPath.substring(0, trimPath.length() - 1);

        return trimPath;
    }

    public static String removeHead(String path, char symbol) {

        if (isNullOrEmpty(path))
            return "";

        String trimPath = path.trim();

        if (trimPath.charAt(0) == symbol)
            return trimPath.substring(1);

        return trimPath;
    }

    public static boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }

}

package common.basic.utils;

import java.util.Vector;

@Deprecated
public class VectorUtil {
    public static Vector<String> generifyToString(Vector vector) {
        Vector<String> vectorString = new Vector<String>();
        for (final Object object : vector) {
            vectorString.add(StringUtil.toString(object));
        }
        return vectorString;
    }
}

package common.basic.utils;

import java.util.Collection;

public class CollectionUtil {
    public static boolean isSubsetOf(Collection<String> subset, Collection<String> superSet) {
        for (String string : subset) {
            if (!superSet.contains(string)) {
                return false;
            }
        }
        return true;
    }
}

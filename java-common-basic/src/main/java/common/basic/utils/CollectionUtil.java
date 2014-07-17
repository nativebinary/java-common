package common.basic.utils;

import common.basic.interfaces.IPredicator;

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


    public static <T> boolean has(Collection<T> collection, IPredicator<T> predicator) {
        for (T t : collection) {
            if(predicator.predicate(t))
                return true;
        }

        return false;
    }

    public static <T> T find(Collection<T> collection, IPredicator<T> predicator) {
        for (T t : collection) {
            if(predicator.predicate(t))
                return t;
        }

        return null;
    }
}

package common.basic.utils;

import common.basic.interfaces.IPredicator;

import java.util.Collection;

public class CollectionUtil {
    public CollectionUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static <T> boolean isSubsetOf(Collection<T> subset, Collection<T> superSet) {
        for (T t : subset) {
            if (!superSet.contains(t)) {
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

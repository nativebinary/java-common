package common.basic.utils;

import java.util.ArrayList;
import java.util.List;

public class IterableUtil {

    public IterableUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static <T> List<T> toList(final Iterable<T> iterable) {
        final ArrayList<T> list = new ArrayList<T>();

        for (T t : iterable) {
            list.add(t);
        }
        return list;
    }
}

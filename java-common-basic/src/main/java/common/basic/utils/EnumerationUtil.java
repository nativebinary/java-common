package common.basic.utils;

import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationUtil {

    public EnumerationUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static <E> Iterable<E> toIterable(final Enumeration<E> enumeration) {
        return new Iterable<E>() {
            public Iterator<E> iterator() {
                return new Iterator<E>() {
                    public boolean hasNext() {
                        return enumeration.hasMoreElements();
                    }
                    public E next() {
                        return enumeration.nextElement();
                    }
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}

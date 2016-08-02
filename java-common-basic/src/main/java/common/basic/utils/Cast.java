package common.basic.utils;

public class Cast {

    public Cast() throws InstantiationException {
        throw new InstantiationException();
    }

    @SuppressWarnings("unchecked")
    public static <T> T as(Object o, Class<T> clazz){
        return (clazz.isInstance(o)) ? (T) o : null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T as(Class<T> clazz, Object o){
        return (clazz.isInstance(o)) ? (T) o : null;
    }

    public static <T> boolean is(Object o, Class<T> clazz) {
        return clazz.isInstance(o);
    }

    public static <T> boolean is(Class<T> clazz, Object o) {
        return clazz.isInstance(o);
    }
}


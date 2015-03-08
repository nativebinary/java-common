package common.basic.interfaces;

public interface IPredicatorThrowable<T> {
    boolean predicate(T t) throws Exception;
}

package common.basic.utils;

import common.basic.interfaces.ICallbackTransform;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SetUtil {

    public static <TIn, TOut> Set<TOut> transform(Collection<TIn> collection, ICallbackTransform<TIn, TOut> callbackTransform) {
        Set<TOut> SetOut = new HashSet<TOut>(collection.size());
        for (TIn in : collection) {
            SetOut.add(callbackTransform.transform(in));
        }

        return SetOut;
    }

    public static <TIn, TOut> Set<TOut> transform(TIn[] array, ICallbackTransform<TIn, TOut> callbackTransform) {
        Set<TOut> SetOut = new HashSet<TOut>(array.length);
        for (TIn in : array) {
            SetOut.add(callbackTransform.transform(in));
        }

        return SetOut;
    }
}

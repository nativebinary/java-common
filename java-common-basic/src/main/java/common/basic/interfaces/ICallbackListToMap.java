package common.basic.interfaces;

public interface ICallbackListToMap<TKey, TValue> {
     TKey getKey(TValue value);
}

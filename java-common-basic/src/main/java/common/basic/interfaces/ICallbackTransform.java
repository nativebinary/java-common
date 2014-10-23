package common.basic.interfaces;

public interface ICallbackTransform<TIn, TOut> {
    TOut transform(TIn in);
}

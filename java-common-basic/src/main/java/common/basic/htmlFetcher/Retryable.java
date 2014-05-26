package common.basic.htmlFetcher;

import common.basic.logs.Logger;

public abstract class Retryable<T>
{
    public abstract T action() throws Exception;

    public T run(int count, int sleep){
        for(int i = 0; i < count; ++i) {
            try {
                T t = action();
                if (null != t)
                    return t;

                Thread.sleep(sleep);
            } catch (Exception e) {
                Logger.e(e);
            }
        }
        return null;
    }
    public T run(){
        return run(10, 1000);
    }
}

package common.basic.collections;

import java.util.ArrayList;

public class ListInfiniteIndex<T> extends ArrayList<T> {
    @Override
    public T get(int i) {
        final int size = size();

        i = i % size;
        if(i < 0)
            i += size;

        return super.get(i);
    }
}

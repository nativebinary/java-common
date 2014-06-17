package common.android.extensions;

import android.widget.BaseAdapter;
import common.android.utils.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class AdapterList<T> extends BaseAdapter {
    List<T> list;

    protected AdapterList() {
        this.list = new ArrayList<T>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void add(T t) {
        list.add(t);
    }

    public void add(List<T> listToAdd) {
        list.addAll(listToAdd);
    }

    public void add(int index, T t) {
        list.add(index, t);
    }

    public void addFirst(T t) {
        list.add(0, t);
    }

    public void clear() {
        list.clear();
    }

    public T get(int i) {
        return list.get(i);
    }

    public void remove(int i) {
        list.remove(i);
    }

    public boolean contain(T t) {
        return list.contains(t);
    }

    @Override
    public void notifyDataSetChanged() {
        ThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AdapterList.super.notifyDataSetChanged();
            }
        });
    }
}

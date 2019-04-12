package common.basic.utils;

import common.basic.interfaces.*;
import common.basic.logs.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListUtil extends CollectionUtil {


    public ListUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static <T> List<T> isNull(List<T> list) {
        if(list == null)
            return new ArrayList<T>();

        return list;
    }

    public static <T> boolean equals(List<T> listT1, List<T> listT2) {
        if (listT1.size() != listT2.size())
            return false;

        for (int i = 0; i < listT1.size(); ++i)
        {
            final T t1 = listT1.get(i);
            final T t2 = listT2.get(i);
            if (!t1.equals(t2))
                return false;
        }

        return true;
    }

    public static List<String> toListString(Object[] arrayObject) {
        List<String> list = new ArrayList<String>();

        for (Object o : arrayObject)
        {
            if(o == null)
            {
                list.add("" + null);
                continue;
            }

            final Throwable throwable = Cast.as(o, Throwable.class);
            if (throwable != null)
            {
                list.add(ThrowableUtil.getStackTrace(throwable));
                continue;
            }

            list.add(StringUtil.toString(o));
        }
        return list;
    }

    public static <TKey, TValue> Map<TKey, TValue> toMap(List<TValue> list, ICallbackListToMap<TKey, TValue> callback) {
        Map<TKey, TValue> map = new HashMap<TKey, TValue>();
        for (TValue value : list) {
            map.put(callback.getKey(value), value);
        }

        return map;
    }

    public static <T> List<T> create(T... array) {
        return Arrays.asList(array);
    }

    public static <T> List<T> create(Set<T> set) {
        List<T> list = new ArrayList<T>(set.size());
        list.addAll(set);
        return list;
    }


    public static <T> boolean isValidIndex(List<T> listMemoItem, int i) {
        if (listMemoItem.size() <= i) {
            Logger.e();
            return false;
        }

        if (i < 0) {
            Logger.e();
            return false;
        }

        return true;
    }

    public static <T> boolean canConvert(Map<Integer, T> map) {
        for (int i = 0; i < map.size(); i++) {
            if(!map.containsKey(i))
                return false;
        }

        return true;
    }


    public static <T> List<T> convert(Map<Integer, T> map) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < map.size(); i++) {
            final T t = map.get(i);
            if (null == t) {
                Logger.e();
                return null;
            }

            list.add(t);
        }

        return list;
    }

    public static int getIndexNext(int size, int i) {
        return getIndexByInfiniteIndexWithOffset(size, i, 1);
    }

    public static int getIndexPrev(int size, int i) {
        return getIndexByInfiniteIndexWithOffset(size, i, -1);
    }

    public static int getIndexByInfiniteIndex(int size, int i) {
        i = i % size;
        if(i < 0)
            i += size;

        return i;
    }

    public static int getIndexByInfiniteIndexWithOffset(int size, int i, int offset) {
        return getIndexByInfiniteIndex(size, i + offset);
    }

    public static <T> int getIndexByInfiniteIndex(List<T> list, int i) {
        return getIndexByInfiniteIndex(list.size(), i);
    }


    public static <T> T getByInfiniteIndex(List<T> list, int i) {
        return list.get(getIndexByInfiniteIndex(list.size(), i));
    }

    public static <T> T getByInfiniteIndexWithOffset(List<T> list, int i, int offset) {
        return list.get(getIndexByInfiniteIndexWithOffset(list.size(), i, offset));
    }

    public static <T> T getFirst(List<T> listStatus) {
        final int size = listStatus.size();
        if(size == 0)
            return null;

        return listStatus.get(0);
    }

    public static <T> T getLast(List<T> listStatus) {
        final int size = listStatus.size();
        if(size == 0)
            return null;

        return listStatus.get(size - 1);
    }

	public static <T> T getRandomValue(List<T> list) {
        return RandomUtil.get(list);
	}

    public static <T> boolean hasMore(List<T> list, int limit) {
        boolean hasMore = list.size() > limit;

        if (hasMore)
            list.remove(list.get(list.size() - 1));

        return hasMore;
    }




    public static <T> List<T> findAll(List<T> list, IPredicator<T> predicator) {
        List<T> listResult = new ArrayList<T>();
        for (T t : list) {
            if(predicator.predicate(t))
                listResult.add(t);
        }

        return listResult;
    }


    public static <T> int count(List<T> list, IPredicator<T> predicator) {
        int count = 0;

        for (T t : list) {
            if(predicator.predicate(t))
                ++count;
        }

        return count;
    }

    public static <TIn, TOut> List<TOut> transform(Collection<TIn> list, ICallbackTransform<TIn, TOut> callbackTransform) {
        List<TOut> listOut = new ArrayList<TOut>(list.size());
        for (TIn in : list) {
            listOut.add(callbackTransform.transform(in));
        }

        return listOut;
    }

    public static <TIn, TOut> List<TOut> transform(TIn[] array, ICallbackTransform<TIn, TOut> callbackTransform) {
        List<TOut> listOut = new ArrayList<TOut>(array.length);
        for (TIn in : array) {
            listOut.add(callbackTransform.transform(in));
        }

        return listOut;
    }


    public static <T> void remove(List<T> list, IPredicator<T> predicator) {
        List<T> listToRemove = new ArrayList<T>();
        for (T t : list) {
            if(predicator.predicate(t))
                listToRemove.add(t);
        }

        remove(list, listToRemove);
    }

    public static <T> void remove(List<T> list, List<T> listToRemove) {
        for (T t : listToRemove) {
            list.remove(t);
        }
    }

    public static <T> void removeNull(List<T> list) {
        while(list.remove(null));
    }

    public static <T> void removeFirstInPlace(List<T> list) {
        if(list.size() == 0) {
            Logger.e("list.size() == 0");
            return;
        }

        list.remove(0);
    }

    public static <T> void removeLastInPlace(List<T> list) {
        if(list.size() == 0) {
            Logger.e("list.size() == 0");
            return;
        }

        list.remove(list.size() - 1);
    }


    public static <T> void replace(List<T> list, T tToRemove, T tToInsert) {
        final int i = list.indexOf(tToRemove);
        list.remove(i);
        list.add(i, tToInsert);
    }

    public static <T> void replace(List<T> list, List<Pair<T, T>> listPairToReplace) {
        for (Pair<T, T> pairEntityEntity : listPairToReplace) {
            replace(list, pairEntityEntity.getKey(), pairEntityEntity.getValue());
        }
    }

    public static <T> void replace(List<T> list, int indexLeft, int indexRight) {
        final T temporarily = list.get(indexLeft);
        list.remove(indexLeft);
        list.add(indexRight, temporarily);
    }

    public static <T> void exchange(List<T> list, int indexLeft, int indexRight) {
        if(indexLeft == indexRight)
            return;

        if(!isValidIndex(list, indexLeft))
        {
            Logger.e("!isValidIndex(list, indexLeft", indexLeft);
            return;
        }

        if(!isValidIndex(list, indexRight))
        {
            Logger.e("!isValidIndex(list, indexRight", indexRight);
            return;
        }

        final int indexSmall = Math.min(indexLeft, indexRight);
        final int indexBig = Math.max(indexLeft, indexRight);

        final T tSmall = list.get(indexSmall);
        final T tBig = list.get(indexBig);

        list.remove(tSmall);
        list.remove(tBig);

        list.add(indexSmall, tBig);
        list.add(indexBig, tSmall);
    }

    public static List<Integer> sort(List<Integer> list) {
        List<Integer> listSort = new ArrayList<Integer>();
        listSort.addAll(list);
        Collections.sort(listSort);
        return listSort;
    }

    public static <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    public static <T> void swap(List<T> list, int index1, int index2) {
        Collections.swap(list, index1, index2);
    }

    public static <T> List<T> concatenate(List<T> lhs, List<T> rhs) {
        List<T> listConcatenated = new ArrayList<T>();
        listConcatenated.addAll(lhs);
        listConcatenated.addAll(rhs);
        return listConcatenated;
    }

    public static <T> List<T> combine(List<T> top, List<T> bottom) {
        int start = (top.size() <= bottom.size() ? 0 : top.size() - bottom.size());

        for (int i = start; i < top.size(); i++) {
            List<T> bottomOfTop = top.subList(i, top.size());
            List<T> topOfBottom = bottom.subList(0, top.size() - i);
            if (equals(bottomOfTop, topOfBottom)) {
                List<T> topOfTop = top.subList(0, i);
                return concatenate(topOfTop, bottom);
            }
        }

        return concatenate(top, bottom);
    }

    public static <T> int sum(List<T> list, ICallbackInt<T> callback) {
        int sum = 0;
        for (T t : list) {
            sum += callback.getValue(t);
        }
        return sum;
    }

    public static <T> double sum(List<T> list, ICallbackDouble<T> callback) {
        double sum = 0;
        for (T t : list) {
            sum += callback.getValue(t);
        }
        return sum;
    }

    public static <T> BigDecimal sum(List<T> list, ICallbackBigDecimal<T> callback) {
        BigDecimal sum = BigDecimal.ZERO;
        for (T t : list) {
            sum = sum.add(callback.getValue(t));
        }
        return sum;
    }


    public static <T> T find(List<T> list, ICallbackBoolean<T> callback) {
        int i = 0;
        for (T t : list) {
            if (callback.callback(t, i++, list))
                return t;
        }
        return null;
    }

    public static <T> List<T> filter(List<T> list, ICallbackBoolean<T> callback) {
        List<T> listT = new ArrayList<T>();
        int i = 0;
        for (T t : list) {
            if (callback.callback(t, i++, list))
                listT.add(t);
        }
        return listT;
    }

    public static <T> void forEach(List<T> list, ICallbackForEach<T> callback) {
        int i = 0;
        for (T t : list) {
            callback.callback(t, i++, list);
        }
    }

    public static <T,F> List<F> map(List<T> list, ICallbackMap<T, F> callback) {
        List<F> listF = new ArrayList<F>(list.size());
        int i = 0;
        for (T t : list) {
            listF.add(callback.callback(t, i++, list));
        }
        return listF;
    }


    public static <T,F> F reduce(List<T> list, ICallbackReduce<T, F> callback, F init) {
        int i = 0;
        for (T t : list) {
            init = callback.callback(init, t, i++, list);
        }
        return init;
    }


    public static <T> boolean some(List<T> list, ICallbackBoolean<T> callback) {
        int i = 0;
        for (T t : list) {
            if (callback.callback(t, i++, list))
                return true;
        }
        return false;
    }


    public static <T> boolean every(List<T> list, ICallbackBoolean<T> callback) {
        int i = 0;
        for (T t : list) {
            if (!callback.callback(t, i++, list))
                return false;
        }
        return true;
    }




}

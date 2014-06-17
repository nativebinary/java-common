package common.basic.utils;

import common.basic.interfaces.IPredicator;
import common.basic.logs.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListUtil {

    ListUtil() throws InstantiationException {
        throw new InstantiationException();
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

    public static <T> T getLast(List<T> listStatus) {
        final int size = listStatus.size();
        if(size == 0)
            return null;

        return listStatus.get(size - 1);
    }
	
	
	public static <T> T getRandomValue(List<T> list) {
		if(list == null || 0 == list.size())
			return null;

		return list.get(RandomUtil.nextInt(0, list.size()));
	}

    public static <T> boolean hasMore(List<T> list, int limit) {
        boolean hasMore = list.size() > limit;

        if (hasMore)
            list.remove(list.get(list.size() - 1));

        return hasMore;
    }

    public static <T> T find(List<T> list, IPredicator<T> predicator) {
        for (T t : list) {
            if(predicator.predicate(t))
                return t;
        }

        return null;
    }

    public static <T> List<T> findAll(List<T> list, IPredicator<T> predicator) {
        List<T> listResult = new ArrayList<T>();
        for (T t : list) {
            if(predicator.predicate(t))
                listResult.add(t);
        }

        return listResult;
    }

    public static interface ICallbackTransform<TIn, TOut> {
        TOut transform(TIn in);
    }

    public static <TIn, TOut> List<TOut> transform(List<TIn> list, ICallbackTransform<TIn, TOut> callbackTransform) {
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
}

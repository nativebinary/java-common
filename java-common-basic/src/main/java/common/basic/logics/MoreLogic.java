package common.basic.logics;

import common.basic.utils.ListUtil;

import java.util.List;

public class MoreLogic {
    public static <T> boolean hasMore(List<T> list, int limit) {
        if (limit < list.size()) {
            ListUtil.removeLastInPlace(list);
            return true;
        }
        return false;
    }
}

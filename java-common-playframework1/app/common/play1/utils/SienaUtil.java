package common.play1.utils;

import common.basic.logs.Logger;
import common.basic.utils.ReflectionUtil;
import play.modules.siena.EnhancedModel;
import play.modules.siena.QueryWrapper;
import siena.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SienaUtil {

    public static String getAnnotatedFieldNameFirst(Class clazz)  {
        return ReflectionUtil.getAnnotatedFieldNameFirst(clazz, Id.class);
    }

    public static Object getAnnotatedFieldValueFirst(Object object)  {
        return ReflectionUtil.getAnnotatedFieldValueFirst(object, Id.class);
    }

    public static <T extends EnhancedModel> void fillMember(List<T> list, String fieldName) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(list.size() == 0) {
            Logger.e("list.size() == 0");
            return;
        }

        final Class<?> clazz = list.get(0).getClass();
        final String fieldNameTargetId = getAnnotatedFieldNameFirst(clazz);
        final Field fieldTarget = ReflectionUtil.getFieldDeclaredRecursive(clazz, fieldName);
        final Set<Object> set = new HashSet<Object>();
        for (T t : list) {
            set.add(getAnnotatedFieldValueFirst(ReflectionUtil.getValue(t, fieldTarget)));
        }

        final QueryWrapper all = (QueryWrapper) fieldTarget.getType().getMethod("all").invoke(null);
        final List<Object> fetch = all.filter(fieldNameTargetId + " IN", set).fetch();

        ReflectionUtil.assignAnnotatedMemberVariable(list, fetch, Id.class);
    }
}

package common.basic.utils;

import java.util.List;

public class EnumUtil {
	public static <E extends Enum<E>> String getFullName(Enum<E> e)
	{
		if(e == null)
			return "";
		
		return e.getDeclaringClass().getCanonicalName() + "." + e.name();
	}

    public static <E extends Enum<E>> E parse(Class<E> clazz, String s) {
        if(StringUtil.isNullOrEmpty(s))
            return null;

        try {
            return Enum.valueOf(clazz, s);
        }
        catch(IllegalArgumentException exception){
            return null;
        }
    }

   public static <E extends Enum<E>> boolean contains(List<E> list, E other) {
        for (E e : list) {
            if (e.equals(other))
                return true;
        }

        return false;
    }
}

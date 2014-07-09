package common.basic.logs;

import common.basic.utils.Cast;
import common.basic.utils.DateUtil;
import common.basic.jsons.JsonUtil;
import common.basic.utils.ReflectionUtil;
import common.basic.utils.StringUtil;
import common.basic.utils.ThrowableUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogUtil {
    LogUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    static int maxSignatureLength = 0;

    public static String makeMessage(Object[] arrayObject, Level level, int stackRewindCount) {
        return makeMessage(arrayObject, level, stackRewindCount + 1, false);
    }

    public static String makeMessage(Object[] arrayObject, Level level, int stackRewindCount, boolean includeDateTime) {
        List<String> list = toListStringForLog(arrayObject);

        final StackTraceElement[] arrayStackTraceElement = Thread.currentThread().getStackTrace();
        final StackTraceElement stackTraceElement = arrayStackTraceElement[stackRewindCount];
        final String fileName = stackTraceElement.getFileName();
        final String className = stackTraceElement.getClassName();
        final String methodName = stackTraceElement.getMethodName();
        final int lineNumber = stackTraceElement.getLineNumber();

        final String signature = String.format("at %s.%s(%s:%d)", className, methodName, fileName, lineNumber);
        maxSignatureLength = Math.max(maxSignatureLength, signature.length());
        String argument = StringUtil.join("\t", list);
        if(StringUtil.isNullOrEmpty(argument))
        {
            argument = className.substring(className.lastIndexOf(".") + 1) + "." + stackTraceElement.getMethodName() + "(...)";
        }

        final String signaturePart = StringUtil.padLeft(signature, maxSignatureLength + 10 - level.getLength(), ' ');

        if(includeDateTime)
        {
            final String dateTimePart = DateUtil.yyyyMMddHHmmssSSS(new Date());
            return dateTimePart + signaturePart + " : " + argument;
        }
        else
        {
            return signaturePart + " : " + argument;
        }
    }

    public static List<String> toListStringForLog(Object[] arrayObject) {
        List<String> list = new ArrayList<String>();

        for (Object o : arrayObject)
        {
            list.add(toStringForLog(o));
        }

        return list;
    }

    private static String toStringForLog(Object o) {
        if(o == null)
            return "" + null;

        final Throwable throwable = Cast.as(o, Throwable.class);
        if (throwable != null)
            return ThrowableUtil.getStackTrace(throwable);

        try {
            if(o.getClass().getMethod("toString").getDeclaringClass() != Object.class)
                return o.toString();
        }
        catch (NoSuchMethodException e) {
            Logger.e(e);
        }

        return JsonUtil.toJson(ReflectionUtil.toMap(o));
    }

}

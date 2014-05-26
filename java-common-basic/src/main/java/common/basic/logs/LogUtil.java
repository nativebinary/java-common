package common.basic.logs;

import common.basic.utils.ListUtil;
import common.basic.utils.StringUtil;

import java.util.List;

public class LogUtil {
    LogUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static String makeMessage(Object[] arrayObject, Level level, int stackRewindCount) {
        List<String> list = ListUtil.toListString(arrayObject);

        final StackTraceElement[] arrayStackTraceElement = Thread.currentThread().getStackTrace();
        final StackTraceElement stackTraceElement = arrayStackTraceElement[stackRewindCount];
        final String fileName = stackTraceElement.getFileName();
        final String className = stackTraceElement.getClassName();
        final String methodName = stackTraceElement.getMethodName();
        final int lineNumber = stackTraceElement.getLineNumber();

        final String signature = String.format("at %s.%s(%s:%d)", className, methodName, fileName, lineNumber);
        String argument = StringUtil.join("\t", list);
        if(StringUtil.isNullOrEmpty(argument))
        {
            argument = className.substring(className.lastIndexOf(".") + 1) + "." + stackTraceElement.getMethodName() + "(...)";
        }

        return StringUtil.padLeft(signature, 150 - level.getLength(), ' ') + " : " + argument;
    }
}

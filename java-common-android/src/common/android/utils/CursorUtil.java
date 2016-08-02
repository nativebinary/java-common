package common.android.utils;

import android.database.Cursor;
import common.basic.logs.Logger;

public class CursorUtil {
    public static String get(Cursor cursor, int columnIndex, String defaultValue) {
        if (cursor.getColumnCount() <= columnIndex)
            return defaultValue;

        String value = cursor.getString(columnIndex);
        if (null == value)
            return defaultValue;

        return value;
    }

    public static String get(Cursor cursor, int columnIndex) {
        return get(cursor, columnIndex, "");
    }

    public static int get(Cursor cursor, int columnIndex, int defaultValue) {
        if (cursor.getColumnCount() <= columnIndex)
            return defaultValue;

        return cursor.getInt(columnIndex);
    }

    public static long get(Cursor cursor, int columnIndex, long defaultValue) {
        if (cursor.getColumnCount() <= columnIndex)
            return defaultValue;

        return cursor.getLong(columnIndex);
    }

    public static boolean get(Cursor cursor, int columnIndex, boolean defaultValue) {
        if (cursor.getColumnCount() <= columnIndex)
            return defaultValue;

        return 0 < cursor.getInt(columnIndex);
    }


    public static int fetchCount(Cursor cursor) {
        if(!cursor.moveToNext())
            return -1;

        return get(cursor, 0, -1);
    }

    public static int fetchScalarInt(Cursor cursor) {
        try {
            if (cursor == null)
                return 0;

            if (!cursor.moveToNext())
                return 0;

            return cursor.getInt(0);
        }
        catch (Exception e) {
            Logger.e(e);
        }
        finally {
            if (cursor != null /*&& !cursor.isClosed()*/) {
                cursor.close();
            }
        }

        return 0;
    }
}

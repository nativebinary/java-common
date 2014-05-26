package common.android.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {
    public static String get(ContextWrapper contextWrapper, String name, String key, String defaultValue) {
        final SharedPreferences insSharedPref = contextWrapper.getSharedPreferences(name, Context.MODE_PRIVATE);
        return insSharedPref.getString(key, defaultValue);
    }

    public static boolean put(ContextWrapper contextWrapper, String name, String key, String value) {
        final SharedPreferences sharedPreferences = contextWrapper.getSharedPreferences(name, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static boolean remove(ContextWrapper contextWrapper, String name, String key) {
        final SharedPreferences sharedPreferences = contextWrapper.getSharedPreferences(name, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        return editor.commit();
    }


}

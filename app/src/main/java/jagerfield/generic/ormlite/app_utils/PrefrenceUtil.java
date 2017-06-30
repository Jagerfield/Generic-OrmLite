package jagerfield.generic.ormlite.app_utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefrenceUtil
{
    public static void setString(Context context, String key, String val) {
        SharedPreferences.Editor e = context.getSharedPreferences(C.APP_PREFERENCES, context.MODE_PRIVATE).edit();
        e.putString(key, val);
        e.commit();
    }

    public static void setInt(Context context, String key, int val) {
        SharedPreferences.Editor e = context.getSharedPreferences(C.APP_PREFERENCES, context.MODE_PRIVATE).edit();
        e.putInt(key, val);
        e.commit();
    }

    public static void setLong(Context context, String key, Long val) {
        SharedPreferences.Editor e = context.getSharedPreferences(C.APP_PREFERENCES, context.MODE_PRIVATE).edit();
        e.putLong(key, val);
        e.commit();
    }

    public static void setBoolean(Context context, String key, Boolean val)
    {
        SharedPreferences.Editor e = context.getSharedPreferences(C.APP_PREFERENCES, context.MODE_PRIVATE).edit();
        e.putBoolean(key, val);
        e.commit();
    }

    public static boolean getBoolean(Context context, String key, Boolean onNull)
    {
        SharedPreferences e = context.getSharedPreferences(C.APP_PREFERENCES, context.MODE_PRIVATE);
        return e.getBoolean(key, onNull);
    }

    public static int getInt(Context context, String key, int onNull)
    {
        SharedPreferences e = context.getSharedPreferences(C.APP_PREFERENCES, context.MODE_PRIVATE);
        return e.getInt(key, onNull);
    }

    public static Long getLong(Context context, String key, Long onNull)
    {
        SharedPreferences e = context.getSharedPreferences(C.APP_PREFERENCES, context.MODE_PRIVATE);
        return e.getLong(key, onNull);
    }

    public static String getString(Context context, String key, String onNull)
    {
        SharedPreferences e = context.getSharedPreferences(C.APP_PREFERENCES, context.MODE_PRIVATE);
        return e.getString(key, onNull);
    }
}

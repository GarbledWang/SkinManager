package com.jonah.skinmanager.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ringo on 15/11/16.
 */


public class PrefsUtil {
    public static final String PREFS_NAME = "jonah_prefs";

    public static boolean saveInt(Context context, String key, int value){
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.edit().putInt(key,value).commit();
    }

    public static int getInt(Context context,String key){
        return getInt(context, key, -1);
    }

    public static int getInt(Context context, String key, int defValue){
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(key, defValue);
    }

    public static boolean saveString(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.edit().putString(key,value).commit();
    }

    public static String getString(Context context, String key) {
        return getString(context, key, "");
    }

    public static String getString(Context context,String key,String defValue){
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getString(key, defValue);
    }
}

package com.jonah.skinmanager.config;

import android.content.Context;

import com.jonah.skinmanager.util.PrefsUtil;

/**
 * @author JonahWang
 *         Created by JonahWang on 2016/7/19.
 */
public class SkinConfig {

    public static final String CUSTOMER_SKIN_PATH = "jonah_skin";

    public static final String NAME_SPACE = "http://schemas.android.com/android/skin";

    public 	static final String ATTR_SKIN_ENABLE = "enable";

    public static void saveSkinPath(Context context, String skinPath) {
        PrefsUtil.saveString(context, CUSTOMER_SKIN_PATH, skinPath);
    }

    public static String getCustomSkinPath(Context context) {
        return PrefsUtil.getString(context, CUSTOMER_SKIN_PATH);
    }
}

package com.jonah.appsample;

import android.app.Application;

import com.jonah.skinmanager.manager.SkinManager;

/**
 * @author JonahWang
 *         Created by JonahWang on 2016/7/19.
 */
public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstanse().init(this);
        SkinManager.getInstanse().load();
    }
}

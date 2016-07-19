package com.jonah.skinmanager.manager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.jonah.skinmanager.config.SkinConfig;
import com.jonah.skinmanager.listener.SkinLoadListener;
import com.jonah.skinmanager.listener.SkinUpdate;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JonahWang
 *         Created by JonahWang on 2016/7/19.
 */
public class SkinManager {
    public boolean isDefaultSkin;

    private Context mContext;

    private String mSkinPackageName;

    private String mSkinPkgPath;

    private static SkinManager mSkinManager;

    private Resources mResources;

    private List<SkinUpdate> mObserves;

    private SkinManager() {
    }

    public static SkinManager getInstanse() {
        if (mSkinManager == null) {
            synchronized (SkinManager.class) {
                if (mSkinManager == null) {
                    mSkinManager = new SkinManager();
                }
            }
        }
        return mSkinManager;
    }

    public void attach(SkinUpdate observer) {
        if (mObserves == null) {
            mObserves = new ArrayList<>();
        }
        if (!mObserves.contains(observer)) {
            mObserves.add(observer);
        }
    }

    public void restoreDefaultTheme() {
        //重置为默认的主题
        mResources = mContext.getResources();
        isDefaultSkin = true;
        notifySkinUpdate();
    }

    public void init(Context mContext) {
        this.mContext = mContext.getApplicationContext();
    }

    public void load() {
        String skin = SkinConfig.getCustomSkinPath(mContext);
        load(skin, null);
    }

    public void load(final String skinPackagePath, final SkinLoadListener listener) {
        new AsyncTask<String, Void, Resources>() {
            @Override
            protected void onPreExecute() {
                if (listener != null) {
                    listener.onStart();
                }
            }

            @Override
            protected Resources doInBackground(String... strings) {
                try {
                    if (strings.length > 0) {
                        String skinPath = strings[0];
                        File file = new File(skinPath);
                        if (!file.exists()) {
                            return null;
                        }
                        PackageManager pm = mContext.getPackageManager();
                        PackageInfo archiveInfo = pm.getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES);
                        mSkinPackageName = archiveInfo.packageName;

                        AssetManager assetManager = AssetManager.class.newInstance();
                        Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
                        addAssetPath.invoke(assetManager, skinPath);

                        Resources superRes = mContext.getResources();
                        Resources skinRes = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
                        SkinConfig.saveSkinPath(mContext, skinPath);
                        mSkinPkgPath = skinPath;
                        isDefaultSkin = false;
                        return skinRes;
                    }
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Resources resources) {
                mResources = resources;
                if (mResources != null) {
                    if (listener != null) {
                        listener.onSuccess();
                    }
                    isDefaultSkin = false;
                    notifySkinUpdate();
                } else {
                    isDefaultSkin = true;
                    if (listener != null) {
                        listener.onError();
                    }
                }
            }
        }.execute(skinPackagePath);
    }

    private void notifySkinUpdate() {
        if (mObserves != null) {
            for (SkinUpdate obs : mObserves) {
                obs.onThemeUpdate();
            }
        }
    }

    public boolean isExternalSkin() {
        return !isDefaultSkin && mResources != null;
    }

    public int getColor(int attrResId) {
        int originColor = mContext.getResources().getColor(attrResId);
        if (mResources == null || isDefaultSkin) {
            return originColor;
        }
        String resName = mContext.getResources().getResourceEntryName(attrResId);
        int trueResId = mResources.getIdentifier(resName, "color", mSkinPackageName);
        int trueColor = 0;
        try {
            trueColor = mResources.getColor(trueResId);
        } catch (Exception e) {
            e.printStackTrace();
            trueColor = originColor;
        }
        return trueColor;
    }

    public Drawable getDrawable(int attrResId) {
        Drawable originDrawable = mContext.getResources().getDrawable(attrResId);
        if (mResources != null || isDefaultSkin) {
            return originDrawable;
        }

        String resName = mContext.getResources().getResourceEntryName(attrResId);
        int trueResId = mResources.getIdentifier(resName, "drawable", mSkinPackageName);
        Drawable trueDrawable = null;
        try {
            if (android.os.Build.VERSION.SDK_INT < 22) {
                trueDrawable = mResources.getDrawable(trueResId);
            } else {
                trueDrawable = mResources.getDrawable(trueResId, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            trueDrawable = originDrawable;
        }
        return trueDrawable;
    }

    public float getSize(int attrResId) {
        float originSize = mContext.getResources().getDimension(attrResId);
        if (mResources == null || isDefaultSkin) {
            return originSize / mContext.getResources().getDisplayMetrics().scaledDensity;
        }
        String resName = mContext.getResources().getResourceEntryName(attrResId);
        int trueResId = mResources.getIdentifier(resName, "dimen", mSkinPackageName);
        float trueSize = 0;
        try{
            trueSize = mResources.getDimension(trueResId)/ mContext.getResources().getDisplayMetrics().scaledDensity;
        }catch (Exception e){
            e.printStackTrace();
            trueSize = originSize;
        }

        return trueSize;
    }
}

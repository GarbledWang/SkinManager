package com.jonah.skinmanager;

import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.jonah.skinmanager.listener.SkinUpdate;
import com.jonah.skinmanager.manager.SkinLayoutFactory;
import com.jonah.skinmanager.manager.SkinManager;

public class SkinBaseActivity extends AppCompatActivity implements SkinUpdate{
    private SkinLayoutFactory mSkinLayoutFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSkinLayoutFactory = new SkinLayoutFactory();
        LayoutInflaterCompat.setFactory(getLayoutInflater(),mSkinLayoutFactory);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SkinManager.getInstanse().attach(this);
    }

    @Override
    public void onThemeUpdate() {
        mSkinLayoutFactory.apply();
    }
}

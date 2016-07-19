package com.jonah.appsample;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.jonah.skinmanager.SkinBaseActivity;
import com.jonah.skinmanager.listener.SkinLoadListener;
import com.jonah.skinmanager.manager.SkinManager;

import java.io.File;

public class MainActivity extends SkinBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeTheme(View v){
        SkinManager.getInstanse().load(new File(Environment.getExternalStorageDirectory(),"aa.apk").getAbsolutePath(), new SkinLoadListener() {
            @Override
            public void onStart() {
                Log.e("TAG", "onStart");
            }

            @Override
            public void onSuccess() {
                Log.e("TAG", "onSuccess");
            }

            @Override
            public void onError() {
                Log.e("TAG", "onError");
            }
        });
    }

    public void restoreDefault(View v){
        SkinManager.getInstanse().restoreDefaultTheme();
    }
}

package com.jonah.skinmanager.entity;

import android.view.View;

import com.jonah.skinmanager.manager.SkinManager;

/**
 * @author JonahWang
 *         Created by JonahWang on 2016/7/19.
 */
public class BackgroundAttr extends SkinAttr {

    @Override
    public void apply(View view) {
        if (RES_TYPE_NAME_COLOR.equals(typeName)) {
            view.setBackgroundColor(SkinManager.getInstanse().getColor(id));
        } else if (RES_TYPE_NAME_DRAWABLE.equals(typeName)) {
            view.setBackground(SkinManager.getInstanse().getDrawable(id));
        }
    }
}

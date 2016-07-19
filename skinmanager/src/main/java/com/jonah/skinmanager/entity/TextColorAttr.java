package com.jonah.skinmanager.entity;

import android.view.View;
import android.widget.TextView;

import com.jonah.skinmanager.manager.SkinManager;

/**
 * @author JonahWang
 *         Created by JonahWang on 2016/7/19.
 */
public class TextColorAttr extends SkinAttr {
    @Override
    public void apply(View view) {
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            if (RES_TYPE_NAME_COLOR.equals(typeName)) {
                tv.setTextColor(SkinManager.getInstanse().getColor(id));
            }
        }
    }
}

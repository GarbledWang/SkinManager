package com.jonah.skinmanager.entity;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.jonah.skinmanager.manager.SkinManager;

/**
 * @author JonahWang
 *         Created by JonahWang on 2016/7/19.
 */
public class TextSizeAttr extends SkinAttr {

    @Override
    public void apply(View view) {
        if (view instanceof TextView) {
            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_SP, SkinManager.getInstanse().getSize(id));
        }
    }
}

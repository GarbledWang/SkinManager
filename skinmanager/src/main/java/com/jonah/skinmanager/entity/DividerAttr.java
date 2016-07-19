package com.jonah.skinmanager.entity;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListView;

import com.jonah.skinmanager.manager.SkinManager;

/**
 * @author JonahWang
 *         Created by JonahWang on 2016/7/19.
 */
public class DividerAttr extends SkinAttr {
    @Override
    public void apply(View view) {
        if (view instanceof ListView) {
            ListView lv = (ListView) view;
            if (RES_TYPE_NAME_COLOR.equals(typeName)) {
                int color = SkinManager.getInstanse().getColor(id);
                ColorDrawable cd = new ColorDrawable(color);
                lv.setDivider(cd);
            } else if (RES_TYPE_NAME_DRAWABLE.equals(typeName)) {
                lv.setDivider(SkinManager.getInstanse().getDrawable(id));
            }
        }
    }
}

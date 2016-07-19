package com.jonah.skinmanager.entity;

import android.view.View;
import android.widget.AbsListView;

import com.jonah.skinmanager.manager.SkinManager;

/**
 * @author JonahWang
 *         Created by JonahWang on 2016/7/19.
 */
public class ListSelectorAttr extends SkinAttr {
    @Override
    public void apply(View view) {
        if (view instanceof AbsListView) {
            AbsListView tv = (AbsListView)view;
            if(RES_TYPE_NAME_COLOR.equals(typeName)){
                tv.setSelector(SkinManager.getInstanse().getColor(id));
            }else if(RES_TYPE_NAME_DRAWABLE.equals(typeName)){
                tv.setSelector(SkinManager.getInstanse().getDrawable(id));
            }
        }
    }
}

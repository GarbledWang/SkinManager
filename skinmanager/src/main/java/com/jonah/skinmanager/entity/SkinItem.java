package com.jonah.skinmanager.entity;

import android.view.View;

import java.util.List;

/**
 * @author JonahWang
 *         Created by JonahWang on 2016/7/19.
 */
public class SkinItem {
    private View view;
    private List<SkinAttr> skinAttrs;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<SkinAttr> getSkinAttrs() {
        return skinAttrs;
    }

    public void setSkinAttrs(List<SkinAttr> skinAttrs) {
        this.skinAttrs = skinAttrs;
    }

    public void apply(){
        for (SkinAttr attr :
                skinAttrs) {
            attr.apply(view);
        }
    }
}

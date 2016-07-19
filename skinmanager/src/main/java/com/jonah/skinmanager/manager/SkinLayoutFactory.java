package com.jonah.skinmanager.manager;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.jonah.skinmanager.config.SkinConfig;
import com.jonah.skinmanager.entity.SkinAttr;
import com.jonah.skinmanager.entity.SkinItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JonahWang
 *         Created by JonahWang on 2016/7/19.
 */
public class SkinLayoutFactory implements LayoutInflaterFactory {

    private List<SkinItem> mSkinItems = new ArrayList<>();

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

        boolean isSkinEnable = attrs.getAttributeBooleanValue(SkinConfig.NAME_SPACE, SkinConfig.ATTR_SKIN_ENABLE, false);
        if (!isSkinEnable) {
            return null;
        }

        View view = createView(context,name,attrs);

        if (view == null) {
            return null;
        }

        parseSkinAttr(context, attrs, view);

        return view;
    }

    private void parseSkinAttr(Context context, AttributeSet attrs, View view) {
        List<SkinAttr> viewAttr = new ArrayList<>();

        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);

            if (!AttrFactory.isSupportAttr(attrName)) {
                continue;
            }
            if (attrValue.startsWith("@")) {
                try {
                    int id = Integer.parseInt(attrValue.substring(1));
                    String entryName = context.getResources().getResourceEntryName(id);
                    String typeName = context.getResources().getResourceTypeName(id);

                    SkinAttr skinAttr = AttrFactory.get(attrName, id, entryName, typeName);

                    if (skinAttr != null) {
                        viewAttr.add(skinAttr);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        if (viewAttr.size() > 0) {
            SkinItem item = new SkinItem();
            item.setSkinAttrs(viewAttr);
            item.setView(view);
            mSkinItems.add(item);
            if (SkinManager.getInstanse().isExternalSkin()){
                item.apply();
            }
        }
    }

    private View createView(Context context, String name, AttributeSet attrs) {
        View view = null;
        try {
            if (-1 == name.indexOf('.')) {
                if ("View".equals(name)) {
                    view = LayoutInflater.from(context).createView(name, "android.view.", attrs);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.widget.", attrs);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.webkit.", attrs);
                }else {
                    view = LayoutInflater.from(context).createView(name, null, attrs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return view;
    }

    public void apply() {
        if (mSkinItems != null && mSkinItems.size() > 0) {
            for (SkinItem item :
                    mSkinItems) {
                item.apply();
            }
        }
    }
}

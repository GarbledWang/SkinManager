package com.jonah.skinmanager.manager;

import com.jonah.skinmanager.entity.BackgroundAttr;
import com.jonah.skinmanager.entity.DividerAttr;
import com.jonah.skinmanager.entity.ListSelectorAttr;
import com.jonah.skinmanager.entity.SkinAttr;
import com.jonah.skinmanager.entity.TextColorAttr;
import com.jonah.skinmanager.entity.TextSizeAttr;

/**
 * @author JonahWang
 *         Created by JonahWang on 2016/7/19.
 */
public class AttrFactory {
    public static final String BACKGROUND = "background";
    public static final String TEXT_COLOR = "textColor";
    public static final String LIST_SELECTOR = "listSelector";
    public static final String DIVIDER = "divider";
    public static final String TEXT_SIZE = "textSize";


    public static boolean isSupportAttr(String name) {
        boolean isSupport = false;
        switch (name){
            case BACKGROUND:
            case TEXT_COLOR:
            case LIST_SELECTOR:
            case DIVIDER:
            case TEXT_SIZE:
                isSupport = true;
        }

        return isSupport;
    }

    public static SkinAttr get(String attrName, int id, String entryName, String typeName) {
        SkinAttr skinAttr = null;
        switch (attrName){
            case BACKGROUND:
                skinAttr = new BackgroundAttr();
                break;
            case TEXT_COLOR:
                skinAttr = new TextColorAttr();
                break;
            case LIST_SELECTOR:
                skinAttr = new ListSelectorAttr();
                break;
            case DIVIDER:
                skinAttr = new DividerAttr();
                break;
            case TEXT_SIZE:
                skinAttr = new TextSizeAttr();
                break;
        }

        if (skinAttr != null) {
            skinAttr.setId(id);
            skinAttr.setAttrName(attrName);
            skinAttr.setEntryName(entryName);
            skinAttr.setTypeName(typeName);
        }

        return skinAttr;
    }
}

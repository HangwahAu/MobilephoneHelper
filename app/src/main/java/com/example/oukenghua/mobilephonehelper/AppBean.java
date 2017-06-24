package com.example.oukenghua.mobilephonehelper;

import android.graphics.drawable.Drawable;

/**
 * Created by oukenghua on 2017/6/24.
 */

public class AppBean {

    private CharSequence name;
    private String packageName;
    private Drawable icon;

    public CharSequence getName() {
        return name;
    }

    public void setName(CharSequence name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}

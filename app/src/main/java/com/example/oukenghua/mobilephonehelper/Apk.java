package com.example.oukenghua.mobilephonehelper;

import android.graphics.drawable.Drawable;

/**
 * Created by oukenghua on 2017/6/26.
 */

public class Apk {

    private CharSequence name;
    private String versionName;
    private Drawable icon;
    private String packageName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public CharSequence getName() {
        return name;
    }

    public void setName(CharSequence name) {
        this.name = name;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

}

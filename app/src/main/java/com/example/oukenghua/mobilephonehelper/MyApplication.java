package com.example.oukenghua.mobilephonehelper;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

/**
 * Created by oukenghua on 2017/6/23.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate(){
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }

}

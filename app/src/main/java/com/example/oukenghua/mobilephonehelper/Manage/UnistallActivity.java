package com.example.oukenghua.mobilephonehelper.Manage;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.oukenghua.mobilephonehelper.AppBean;
import com.example.oukenghua.mobilephonehelper.R;

import java.util.ArrayList;
import java.util.List;

public class UnistallActivity extends AppCompatActivity {

    private List<AppBean> mList;
    PackageManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unistall);

        initData();
    }

    private void initData(){
        mList = new ArrayList<>();
        manager = getPackageManager();

    }

}

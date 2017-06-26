package com.example.oukenghua.mobilephonehelper.Manage;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.oukenghua.mobilephonehelper.Apk;
import com.example.oukenghua.mobilephonehelper.AppBean;
import com.example.oukenghua.mobilephonehelper.DownloadApk;
import com.example.oukenghua.mobilephonehelper.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DownloadActivity extends AppCompatActivity {

    private List<Apk> mList;
    RecyclerView mainRcv;
    String path = "/sdcard/storage/emulated/0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        mainRcv = (RecyclerView)findViewById(R.id.recycler_view);

        initData();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //DownloadAdapter adapter = new DownloadAdapter(downloadList);
        //recyclerView.setAdapter(adapter);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }

    private void initData(){

        File file = new File(path);
        mList = new ArrayList<>();
        for(File apk : file.listFiles()){
            if(apk.getName().endsWith(".apk")){
                PackageManager manager = getPackageManager();
                String apkPath = path+"/"+apk.getName().toString();
                PackageInfo info = manager.getPackageArchiveInfo(apkPath,PackageManager.GET_ACTIVITIES);
                if(info != null){
                    Apk apk1 = new Apk();
                    ApplicationInfo appInfo = info.applicationInfo;
                    appInfo.sourceDir = apkPath;
                    appInfo.publicSourceDir = apkPath;
                    apk1.setIcon(manager.getApplicationIcon(appInfo));
                    String newPath = "/sdcard/storage/emulated/0/"+manager.getApplicationLabel(appInfo).toString()+".apk";
                    apk.renameTo(new File(newPath));

                    apk1.setName(manager.getApplicationLabel(appInfo).toString());
                    apk1.setVersionName(info.versionName);
                    apk1.setPackageName(info.packageName);
                    mList.add(apk1);
                }
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainRcv.setLayoutManager(layoutManager);
        mainRcv.setHasFixedSize(true);
        DownloadAdapter adapter = new DownloadAdapter(this,mList);
        mainRcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}

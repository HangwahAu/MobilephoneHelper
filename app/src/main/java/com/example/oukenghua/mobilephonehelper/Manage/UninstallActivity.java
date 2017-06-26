package com.example.oukenghua.mobilephonehelper.Manage;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.oukenghua.mobilephonehelper.AppBean;
import com.example.oukenghua.mobilephonehelper.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UninstallActivity extends AppCompatActivity {

    //@BindView(R.id.main_rcv)
    //RecyclerView mainRcv;
    private List<AppBean> mList;
    RecyclerView mainRcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uninstall);
        //ButterKnife.bind(this); //父类bind绑定后，子类不需要再bind
        mainRcv = (RecyclerView)findViewById(R.id.main_rcv);

        initData();
    }

    private void initData(){
        mList = new ArrayList<>();
        PackageManager manager = getPackageManager();
        List<PackageInfo> list = getPackageManager().getInstalledPackages(0); //获取已安装的全部应用
        for(PackageInfo info:list){
            if((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0){
                AppBean bean = new AppBean();
                bean.setName(info.applicationInfo.loadLabel(manager));
                bean.setPackageName(info.packageName);
                bean.setIcon(info.applicationInfo.loadIcon(manager));
                mList.add(bean); //只添加非系统应用
            }
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainRcv.setLayoutManager(layoutManager);
        mainRcv.setHasFixedSize(true);
        AppListAdapter adapter = new AppListAdapter(this,mList);
        mainRcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}

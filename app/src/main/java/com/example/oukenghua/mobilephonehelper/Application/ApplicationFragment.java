package com.example.oukenghua.mobilephonehelper.Application;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oukenghua.mobilephonehelper.Manage.MyAppFragmentAdapter;
import com.example.oukenghua.mobilephonehelper.R;

import java.util.ArrayList;
import java.util.List;

public class ApplicationFragment extends Fragment {

    private TabLayout tab;
    private ViewPager pager;
    private List<String> list;

    public static ApplicationFragment newInstance(String param1){
        ApplicationFragment fragment = new ApplicationFragment();
        Bundle args = new Bundle();
        args.putString("args1",param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ApplicationFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_application_fragment,container,false);
        Bundle bundle = getArguments();
        String args1 = bundle.getString("args1");
        pager = (ViewPager)view.findViewById(R.id.viewPager);
        tab = (TabLayout)view.findViewById(R.id.tabLayout);
        initData();
        MyAppFragmentAdapter adapter = new MyAppFragmentAdapter(getChildFragmentManager(),list);
        pager.setAdapter(adapter);
        tab.setupWithViewPager(pager);
        return view;
    }

    private void initData(){

        list = new ArrayList<>();
        list.add("娱乐游戏");
        list.add("视频音乐");
        list.add("通讯社交");
        list.add("摄影美图");
        list.add("新闻阅读");
        list.add("实用工具");
        list.add("旅游出行");

    }

}

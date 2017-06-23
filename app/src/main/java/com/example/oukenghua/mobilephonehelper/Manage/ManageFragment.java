package com.example.oukenghua.mobilephonehelper.Manage;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oukenghua.mobilephonehelper.R;

import java.util.ArrayList;
import java.util.List;

public class ManageFragment extends Fragment {

    private TabLayout tab;
    private ViewPager pager;
    private List<String> list;

    public static ManageFragment newInstance(String param1){
        ManageFragment fragment = new ManageFragment();
        Bundle args = new Bundle();
        args.putString("args1",param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ManageFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_manage_fragment,container,false);
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
        list.add("下载");
        list.add("手机");

    }



}

package com.example.oukenghua.mobilephonehelper.Manage;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oukenghua.mobilephonehelper.Application.AppAdapter;
import com.example.oukenghua.mobilephonehelper.Item;
import com.example.oukenghua.mobilephonehelper.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ManageFragment extends Fragment {

    private List<Item> itemList = new ArrayList<>();

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
        //CircleImageView circleImageView = (CircleImageView)view.findViewById(R.id.icon_image);
        //circleImageView.setImageResource(R.drawable.acfun);
        //Bundle bundle = getArguments();
        //String args1 = bundle.getString("args1");
        //pager = (ViewPager)view.findViewById(R.id.viewPager);
        //tab = (TabLayout)view.findViewById(R.id.tabLayout);
        //initData();
        //MyAppFragmentAdapter adapter = new MyAppFragmentAdapter(getChildFragmentManager(),list);
        //pager.setAdapter(adapter);
        //tab.setupWithViewPager(pager);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        itemList.clear();
        initData();
        MyAppFragmentAdapter Adapter = new MyAppFragmentAdapter(itemList);
        recyclerView.setAdapter(Adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        return view;
    }

    public void initData(){
        for(int i=0;i<6;i++){
            itemList.add(Item.items[i]);
        }
    }
}

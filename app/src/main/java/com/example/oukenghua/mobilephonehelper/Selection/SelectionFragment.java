package com.example.oukenghua.mobilephonehelper.Selection;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oukenghua.mobilephonehelper.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import com.example.oukenghua.mobilephonehelper.Selection.Select;


public class SelectionFragment extends Fragment {


    private List<Select> selectList = new ArrayList<>();
    private SelectAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    private View rootView = null;//缓存Fragment view

    public static SelectionFragment newInstance(String param1){
        SelectionFragment fragment = new SelectionFragment();
        Bundle args = new Bundle();
        args.putString("args1",param1);
        fragment.setArguments(args);
        return fragment;
    }

    public SelectionFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        if(rootView == null){
            rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_selection,null);
            Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            initSelects();
            RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new SelectAdapter(selectList);
            recyclerView.setAdapter(adapter);
        }

        //View view = inflater.inflate(R.layout.fragment_selection,container,false);


        ViewGroup parent = (ViewGroup)rootView.getParent();
        if(parent != null){
            parent.removeView(rootView);
        }

        swipeRefresh = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshSelects();
            }
        });

        setHasOptionsMenu(true);

        return rootView;
    }

    private void refreshSelects(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                ((Activity)getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        selectList.clear();
                        initSelects();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initSelects(){
        int num[] = randomCommon(0,35,12);
        for(int i=0;i<12;i++){
            selectList.add(Select.selects[num[i]]);
        }
    }

    public static int[] randomCommon(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main,menu);
        //SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
        //MenuItem menuItem = menu.findItem(R.id.search);
        //SearchView searchView =
                //(SearchView) menu.findItem(R.id.search).getActionView();
        //searchView.setSearchableInfo(
                //searchManager.getSearchableInfo(getActivity().getComponentName()));


        //SearchView searchView = (SearchView)MenuItemCompat.getActionView(menuItem);
        //searchView.setSubmitButtonEnabled(true);//设置是否显示搜索按钮
        //searchView.setQueryHint("输入应用名查找");//设置提示信息
        //searchView.setIconifiedByDefault(true);//设置搜索默认为图标
    }
}

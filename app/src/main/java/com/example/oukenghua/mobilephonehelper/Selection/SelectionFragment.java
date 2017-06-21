package com.example.oukenghua.mobilephonehelper.Selection;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oukenghua.mobilephonehelper.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


public class SelectionFragment extends Fragment {

    private Select[] selects = {new Select("王者荣耀",R.drawable.wzry),
            new Select("美图秀秀",R.drawable.mtxx),new Select("百度贴吧",R.drawable.bdtb),new Select("优酷",R.drawable.yk),
            new Select("QQ",R.drawable.qq),new Select("去哪儿",R.drawable.qne),new Select("捕鱼达人",R.drawable.bydr),
            new Select("UC浏览器",R.drawable.uc),new Select("天天P图",R.drawable.ttpt),new Select("爱奇艺",R.drawable.aqy),
            new Select("微信",R.drawable.wx),new Select("AcFun",R.drawable.az),new Select("小影",R.drawable.xy),new Select("QQ浏览器",R.drawable.qqllq),
            new Select("虎扑",R.drawable.hp),new Select("简拼",R.drawable.jp),new Select("消消乐",R.drawable.xxl),
            new Select("欢乐斗地主",R.drawable.ddz),new Select("旺信",R.drawable.wangx),new Select("美颜相机",R.drawable.myxj),
            new Select("12306",R.drawable.tl),new Select("央视新闻",R.drawable.ys),new Select("人人网",R.drawable.rrw),
            new Select("迅雷",R.drawable.xl),new Select("艺龙旅行",R.drawable.yl),new Select("猎豹浏览器",R.drawable.lb),
            new Select("QQ音乐",R.drawable.qqyy),new Select("新浪微博",R.drawable.xlwb),new Select("人民日报",R.drawable.rmrb),
            new Select("携程",R.drawable.xc),new Select("我叫MT",R.drawable.wjmt),new Select("OFO共享单车",R.drawable.ofo),
            new Select("网易云音乐",R.drawable.wyy),new Select("ES文件浏览器",R.drawable.es),new Select("凤凰新闻",R.drawable.fhxw)};
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
        int num[] = randomCommon(0,34,12);
        for(int i=0;i<12;i++){
            selectList.add(selects[num[i]]);
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
}

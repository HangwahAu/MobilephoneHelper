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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oukenghua.mobilephonehelper.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import com.example.oukenghua.mobilephonehelper.Selection.Select;


public class SelectionFragment extends Fragment implements SearchView.OnQueryTextListener{


    private List<Select> selectList = new ArrayList<>();
    private SelectAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    SearchView searchView;
    ListView listView;
    private final String[] appName={"王者荣耀","捕鱼达人","斗地主","消消乐","我叫MT",
        "凤凰新闻","虎扑体育","人民日报","新浪微博","央视新闻",
        "爱奇艺","ACfun","QQ音乐","网易云","优酷",
        "ES文件浏览器","猎豹浏览器","QQ浏览器","UC浏览器","迅雷",
        "百度贴吧","QQ","人人网","微信","旺信",
        "简拼","美图秀秀","美颜自拍","天天P图","小影",
        "ofo共享单车","去哪儿旅行","铁路","携程旅行","艺龙旅行"};

    /*private ListView listView;
    ArrayAdapter<String> mAdapter;
    ArrayList<String> list;*/

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

        //init();
        listView = (ListView)rootView.findViewById(R.id.listView);

        listView.setTextFilterEnabled(true);//可以被过滤
        searchView = (SearchView)rootView.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);
        return rootView;
    }

    /*private void init(){
        listView = (ListView)rootView.findViewById(R.id.listView);
        list = new ArrayList<String>();
        list.add("王者荣耀");list.add("捕鱼达人");list.add("斗地主");list.add("消消乐");list.add("我叫MT");
        list.add("凤凰新闻");list.add("虎扑体育");list.add("人民日报");list.add("新浪微博");list.add("央视新闻");
        list.add("爱奇艺");list.add("ACfun");list.add("QQ音乐");list.add("网易云");list.add("优酷");
        list.add("ES文件浏览器");list.add("猎豹浏览器");list.add("QQ浏览器");list.add("UC浏览器");list.add("迅雷");
        list.add("百度贴吧");list.add("QQ");list.add("人人网");list.add("微信");list.add("旺信");
        list.add("简拼");list.add("美图秀秀");list.add("美颜自拍");list.add("天天P图");list.add("小影");
        list.add("ofo共享单车");list.add("去哪儿旅行");list.add("铁路");list.add("携程旅行");list.add("艺龙旅行");

        SearchView searchView = (SearchView)rootView.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);
    }*/

    @Override
    public boolean onQueryTextSubmit(String query) {
        //Toast.makeText(rootView.getContext(),"您选择的是:"+query,Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        /*listView.bringToFront();
        listView.setVisibility(View.VISIBLE);
        if(TextUtils.isEmpty(newText)){
            listView.clearTextFilter(); //清除过滤
        }else{
            listView.bringToFront();
            listView.setFilterText(newText);
        }*/
        //listView.setVisibility(View.VISIBLE);
        listView.bringToFront();
        listView.setVisibility(View.VISIBLE);
        if(TextUtils.isEmpty(newText)){
            //listView.clearTextFilter(); //清除过滤
            listView.setAdapter(null);
        }else {
            ArrayList<String> showList = new ArrayList<String>();
            for (int i=0;i<appName.length;i++){
                if(appName[i].startsWith(newText))
                    showList.add(appName[i]);
            }
            listView.setAdapter(new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_list_item_1,showList));
        }

        return true;
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

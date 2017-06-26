package com.example.oukenghua.mobilephonehelper.Application;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oukenghua.mobilephonehelper.Manage.AppManageFragment;
import com.example.oukenghua.mobilephonehelper.R;
import com.example.oukenghua.mobilephonehelper.Selection.Select;

import java.util.ArrayList;
import java.util.List;

public class Games extends Fragment {

    public static final String ARGS_PAGE = "args_page";
    private int mPage;
    private List<Select> selectList = new ArrayList<>();
    private View rootView = null;//缓存Fragment view

    public static Games newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE,page);
        Games games = new Games();
        games.setArguments(args);
        return games;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_games_application,container,false);
        //TextView textView = (TextView)view.findViewById(R.id.textView1);
        //textView.setText("第"+mPage+"页");
        selectList.clear();
        if(mPage==1)
            initApp1();
        else if (mPage == 2)
            initApp2();
        else if (mPage == 3)
            initApp3();
        else if (mPage == 4)
            initApp4();
        else if (mPage == 5)
            initApp5();
        else if (mPage == 6)
            initApp6();
        else if (mPage == 7)
            initApp7();
        /*ViewGroup parent = (ViewGroup)rootView.getParent();
        if(parent != null){
            parent.removeView(rootView);
        }*/
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        AppAdapter appAdapter = new AppAdapter(selectList);
        recyclerView.setAdapter(appAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        return view;

    }

    private void initApp1(){
        for (int i=0;i<3;i++){
            selectList.add(Select.selects[0]);
            selectList.add(Select.selects[6]);
            selectList.add(Select.selects[16]);
            selectList.add(Select.selects[17]);
            selectList.add(Select.selects[30]);
        }
    }

    private void initApp2(){
        for (int i=0;i<3;i++){
            selectList.add(Select.selects[3]);
            selectList.add(Select.selects[9]);
            selectList.add(Select.selects[11]);
            selectList.add(Select.selects[26]);
            selectList.add(Select.selects[32]);
        }
    }

    private void initApp3(){
        for (int i=0;i<3;i++){
            selectList.add(Select.selects[2]);
            selectList.add(Select.selects[4]);
            selectList.add(Select.selects[10]);
            selectList.add(Select.selects[18]);
            selectList.add(Select.selects[22]);
        }
    }

    private void initApp4(){
        for (int i=0;i<3;i++){
            selectList.add(Select.selects[1]);
            selectList.add(Select.selects[8]);
            selectList.add(Select.selects[12]);
            selectList.add(Select.selects[15]);
            selectList.add(Select.selects[19]);
        }
    }

    private void initApp5(){
        for (int i=0;i<3;i++){
            selectList.add(Select.selects[14]);
            selectList.add(Select.selects[21]);
            selectList.add(Select.selects[27]);
            selectList.add(Select.selects[28]);
            selectList.add(Select.selects[34]);
        }
    }

    private void initApp6(){
        for (int i=0;i<3;i++){
            selectList.add(Select.selects[7]);
            selectList.add(Select.selects[13]);
            selectList.add(Select.selects[23]);
            selectList.add(Select.selects[25]);
            selectList.add(Select.selects[33]);
        }
    }

    private void initApp7(){
        for (int i=0;i<3;i++){
            selectList.add(Select.selects[5]);
            selectList.add(Select.selects[20]);
            selectList.add(Select.selects[24]);
            selectList.add(Select.selects[29]);
            selectList.add(Select.selects[31]);
        }
    }
}

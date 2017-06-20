package com.example.oukenghua.mobilephonehelper.Manage;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oukenghua.mobilephonehelper.R;

public class AppManageFragment extends Fragment {

    public static final String ARGS_PAGE = "args_page";
    private int mPage;

    public static AppManageFragment newInstance(int page){

        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE,page);
        AppManageFragment appManageFragment = new AppManageFragment();
        appManageFragment.setArguments(args);
        return appManageFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_app_manage_fragment,container,false);
        TextView textView = (TextView)view.findViewById(R.id.textView1);
        textView.setText("第"+mPage+"页");
        return view;

    }
}

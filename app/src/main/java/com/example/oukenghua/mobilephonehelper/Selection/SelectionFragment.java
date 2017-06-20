package com.example.oukenghua.mobilephonehelper.Selection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oukenghua.mobilephonehelper.R;


public class SelectionFragment extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selection,container,false);
        Bundle bundle = getArguments();
        String args1 = bundle.getString("args1");
        TextView tv = (TextView)view.findViewById(R.id.tv_selection);
        tv.setText(args1);
        return view;
    }
}

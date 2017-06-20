package com.example.oukenghua.mobilephonehelper;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ApplicationFragment extends Fragment {

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
        TextView tv = (TextView)view.findViewById(R.id.tv_application);
        tv.setText(args1);
        return view;
    }

}

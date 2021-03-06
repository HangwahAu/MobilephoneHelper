package com.example.oukenghua.mobilephonehelper.Application;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.oukenghua.mobilephonehelper.Manage.AppManageFragment;

import java.util.List;

/**
 * Created by oukenghua on 2017/6/20.
 */

public class MyGameFragmentAdapter extends FragmentPagerAdapter {

    private List<String> list;

    public MyGameFragmentAdapter(FragmentManager fm, List<String> list){
        super(fm);
        this.list = list;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return Games.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}

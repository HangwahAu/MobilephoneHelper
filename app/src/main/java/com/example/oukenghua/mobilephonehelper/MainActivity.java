package com.example.oukenghua.mobilephonehelper;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    private SelectionFragment mSelecetionFragment;
    private ApplicationFragment mApplicationFragment;
    private ManageFragment mManageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationBar = (BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.selection,"精选").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.application,"应用").setActiveColorResource(R.color.green))
                .addItem(new BottomNavigationItem(R.drawable.manage,"管理").setActiveColorResource(R.color.blue))

                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();

    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment(){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mSelecetionFragment = SelectionFragment.newInstance("精选");
        transaction.replace(R.id.tb,mSelecetionFragment);
        transaction.commit();

    }



    @Override
    public void onTabSelected(int position){

        FragmentManager fm = getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position){

            case 0:
                if(mSelecetionFragment == null){
                    mSelecetionFragment = SelectionFragment.newInstance("精选");
                }
                transaction.replace(R.id.tb, mSelecetionFragment);
                break;
            case 1:
                if(mApplicationFragment == null){
                    mApplicationFragment = ApplicationFragment.newInstance("应用");
                }
                transaction.replace(R.id.tb,mApplicationFragment);
                break;
            case 2:
                if(mManageFragment == null){
                    mManageFragment = ManageFragment.newInstance("管理");
                }
                transaction.replace(R.id.tb,mManageFragment);
                break;
            default:
                break;
        }
        //事务提交
        transaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}

package com.example.oukenghua.mobilephonehelper;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.oukenghua.mobilephonehelper.Application.ApplicationFragment;
import com.example.oukenghua.mobilephonehelper.Manage.ManageFragment;
import com.example.oukenghua.mobilephonehelper.Selection.SelectionFragment;

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
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //MenuItem menuItem = menu.findItem(R.id.search);
        //SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        //searchView.setSubmitButtonEnabled(true);//设置是否显示搜索按钮
        //searchView.setQueryHint("输入应用名查找");//设置提示信息
        //searchView.setIconifiedByDefault(true);//设置搜索默认为图标
        return true;
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"拒绝权限无法访问",Toast.LENGTH_SHORT).show();
                    finish();
                }break;
            default:
        }
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

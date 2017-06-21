package com.example.oukenghua.mobilephonehelper;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SelectActivity extends AppCompatActivity {

    public static final String SELECT_NAME = "select_name";
    public static final String SELECT_IMAGE_ID = "select_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Intent intent = getIntent();
        String selectName = intent.getStringExtra(SELECT_NAME);
        int selectImageId = intent.getIntExtra(SELECT_IMAGE_ID,0);
        double selectSize = intent.getDoubleExtra("Size",4);
        String content = intent.getStringExtra("Content");
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ImageView selectImageView = (ImageView)findViewById(R.id.select_image_view);
        TextView selectContentText = (TextView)findViewById(R.id.select_content_text);
        TextView selectSizeText = (TextView)findViewById(R.id.select_size_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(selectName);
        Glide.with(this).load(selectImageId).into(selectImageView);
        selectContentText.setText(content);
        selectSizeText.setText("应用大小:"+String.valueOf(selectSize)+"M");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

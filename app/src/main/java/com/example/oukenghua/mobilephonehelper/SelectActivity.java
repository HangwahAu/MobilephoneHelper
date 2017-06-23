package com.example.oukenghua.mobilephonehelper;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        final String url = intent.getStringExtra("Url");
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ImageView selectImageView = (ImageView)findViewById(R.id.select_image_view);
        final TextView selectContentText = (TextView)findViewById(R.id.select_content_text);
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

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.float_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                        //String fileName = "1.apk";
                        String fileName = url.substring(url.lastIndexOf("="));
                        fileName = fileName.replace("=","");
                        fileName = fileName + ".apk";
                        //String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                        request.setDestinationInExternalPublicDir("/storage/emulated/0",fileName);
                        request.setVisibleInDownloadsUi(true);
                        DownloadManager downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                        downloadManager.enqueue(request);
            }
        });
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

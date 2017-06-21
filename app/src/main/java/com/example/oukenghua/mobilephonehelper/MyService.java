package com.example.oukenghua.mobilephonehelper;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();
    class DownloadBinder extends Binder{

        public void startDownload(){

        }

    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

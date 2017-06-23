package com.example.oukenghua.mobilephonehelper;

import android.app.DownloadManager;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import java.io.File;

public class DownloadReceiver extends BroadcastReceiver {

    private Notification mNotification;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        DownloadManager manager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        if(DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())){
            DownloadManager.Query query = new DownloadManager.Query();
            //在广播中取出下载任务的id
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
            query.setFilterById(id);
            Cursor c = manager.query(query);
            if(c.moveToFirst()) {
                int fileUriIdx = c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);
                String fileUri = c.getString(fileUriIdx);
                String fileName = null;
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    if (fileUri != null) {
                        fileName = Uri.parse(fileUri).getPath();
                    }
                } else {
                    //Android 7.0以上的方式：请求获取写入权限，这一步报错
                    //过时的方式：DownloadManager.COLUMN_LOCAL_FILENAME
                    int fileNameIdx = c.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
                    fileName = c.getString(fileNameIdx);
                }
                for(int i = 0; i < 4; i++){
                    fileName = fileName.substring(fileName.indexOf("/")+1 );
                }
                fileName = "/sdcard/"+fileName;
                //获取文件下载路径
                //String filename = c.getString(c.getColumnIndex(DownloadManager.Contenet));
                //如果文件名不为空，说明已经存在了，拿到文件名想干嘛都好
                if(fileName != null){
                    Toast.makeText(context,"下载完成",Toast.LENGTH_SHORT).show();
                    File file = new File(fileName);
                    if(file.getName().endsWith(".apk")){
                        /*Intent intent1 = new Intent();
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent1.setAction(android.content.Intent.ACTION_VIEW);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
                            Uri apkUri = FileProvider.getUriForFile(context, "com.example.oukenghua.mobilephonehelper.fileprovider", file);
                            intent1.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            intent1.setDataAndType(apkUri, "application/vnd.android.package-archive");
                        } else {
                            intent1.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                        }*/
                        Intent i = new Intent();
                        i.setAction(Intent.ACTION_VIEW);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
                        context.startActivity(i);
                    }
                }
            }
        }else if(DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(intent.getAction())){
            long[] ids = intent.getLongArrayExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS);
            //点击通知栏取消下载
            manager.remove(ids);
            Toast.makeText(context,"已取消下载",Toast.LENGTH_SHORT).show();
        }
    }


}


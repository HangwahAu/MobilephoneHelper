package com.example.oukenghua.mobilephonehelper.Manage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.oukenghua.mobilephonehelper.Apk;
import com.example.oukenghua.mobilephonehelper.Item;
import com.example.oukenghua.mobilephonehelper.R;
import com.example.oukenghua.mobilephonehelper.Selection.Select;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.util.List;

/**
 * Created by oukenghua on 2017/6/24.
 */

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.ViewHolder> {

    private List<Apk> mDownloadList;
    private Context mContext;
    private LayoutInflater inflater;
    private PackageManager manager;

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView downName;
        TextView downContent;
        RoundedImageView roundedImageView;
        View selectView;
        public ViewHolder(View view){
            super(view);
            selectView = view;
            roundedImageView = (RoundedImageView)view.findViewById(R.id.icon_apk);
            downName = (TextView)view.findViewById(R.id.downName);
            downContent = (TextView)view.findViewById(R.id.downContent);
        }

    }

    public DownloadAdapter(Context context,List<Apk> downloadList){

        mDownloadList = downloadList;
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        manager = context.getPackageManager();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.down_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Apk apk = mDownloadList.get(position);
        holder.roundedImageView.setImageDrawable(apk.getIcon());
        holder.downName.setText(apk.getName());
        holder.downContent.setText(apk.getPackageName());

        holder.selectView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(manager.getLaunchIntentForPackage(apk.getPackageName())); //根据包名启动该应用
                //mContext.startActivity(intent);
                String fileName = "/sdcard/storage/emulated/0" + "/" +apk.getName() + ".apk";
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
                mContext.startActivity(i);
            }
        });
        holder.selectView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                dialog.setTitle("警告");
                dialog.setMessage("是否确定删除该安装包");
                dialog.setCancelable(true);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String filePath = "/sdcard/storage/emulated/0" + "/" +apk.getName() + ".apk";
                        File file = new File(filePath);
                        if (file.isFile() && file.exists()) {
                            file.delete();
                            Toast.makeText(mContext,"删除成功",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(mContext,"文件不存在",Toast.LENGTH_SHORT).show();
                        }
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    Thread.sleep(2000);
                                    removeData(position);
                                }catch (InterruptedException e){
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDownloadList.size();
    }

    public void removeData(int position) {
        mDownloadList.remove(position);
        notifyItemRemoved(position);
    }
}

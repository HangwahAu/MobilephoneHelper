package com.example.oukenghua.mobilephonehelper.Manage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oukenghua.mobilephonehelper.AppBean;
import com.example.oukenghua.mobilephonehelper.Application.AppAdapter;
import com.example.oukenghua.mobilephonehelper.R;
import com.example.oukenghua.mobilephonehelper.R2;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oukenghua on 2017/6/26.
 */

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.ViewHolder>{

    private Context mContext;
    private List<AppBean> appList;
    private LayoutInflater inflater;
    private PackageManager manager;

    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R2.id.item_icon_iv)
        RoundedImageView itemIconIv;
        @BindView(R2.id.item_name_tv)
        TextView itemNameTv;
        @BindView(R2.id.item_package_tv)
        TextView itemPackageTv;
        View view;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
            //itemIconIv = (RoundedImageView)view.findViewById(R.id.item_icon_iv);
            //itemNameTv = (TextView)view.findViewById(R.id.item_name_tv);
            //itemPackageTv = (TextView)view.findViewById(R.id.item_package_tv);

            this.view = view;
        }

    }

    public AppListAdapter(Context context,List<AppBean> appList){
        this.mContext = context;
        this.appList = appList;
        inflater = LayoutInflater.from(context);
        manager = context.getPackageManager();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_uninstall,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final AppBean bean = appList.get(position);
        holder.itemIconIv.setImageDrawable(bean.getIcon());//应用图标
        holder.itemNameTv.setText(bean.getName()); //名称
        holder.itemPackageTv.setText(bean.getPackageName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(manager.getLaunchIntentForPackage(bean.getPackageName())); //根据包名启动该应用
                mContext.startActivity(intent);
            }
        });
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                dialog.setTitle("警告");
                dialog.setMessage("是否确定删除该应用程序");
                dialog.setCancelable(true);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent uninstall_intent = new Intent();
                        uninstall_intent.setAction(Intent.ACTION_DELETE);
                        uninstall_intent.setData(Uri.parse("package:"+bean.getPackageName()));
                        mContext.startActivity(uninstall_intent);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    Thread.sleep(3000);
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
        return appList.size();
    }

    public void removeData(int position) {
        appList.remove(position);
        notifyItemRemoved(position);
    }
}

package com.example.oukenghua.mobilephonehelper.Manage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oukenghua.mobilephonehelper.Item;
import com.example.oukenghua.mobilephonehelper.R;
import com.example.oukenghua.mobilephonehelper.Selection.Select;

import java.util.List;

/**
 * Created by oukenghua on 2017/6/24.
 */

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.ViewHolder> {

    private List<Download> mDownloadList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView downName;
        TextView downContent;
        View selectView;
        public ViewHolder(View view){
            super(view);
            selectView = view;
            downName = (TextView)view.findViewById(R.id.downName);
            downContent = (TextView)view.findViewById(R.id.downContent);
        }

    }

    public DownloadAdapter(List<Download> downloadList){
        mDownloadList = downloadList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.down_item,parent,false);
        final DownloadAdapter.ViewHolder holder = new DownloadAdapter.ViewHolder(view);
        if(mContext == null){
            mContext = parent.getContext();
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Download download = mDownloadList.get(position);
        holder.downName.setText(download.getName());
        holder.downContent.setText(download.getTime());
    }

    @Override
    public int getItemCount() {
        return mDownloadList.size();
    }
}

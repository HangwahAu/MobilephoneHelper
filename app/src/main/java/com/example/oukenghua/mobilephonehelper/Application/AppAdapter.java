package com.example.oukenghua.mobilephonehelper.Application;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oukenghua.mobilephonehelper.R;
import com.example.oukenghua.mobilephonehelper.SelectActivity;
import com.example.oukenghua.mobilephonehelper.Selection.Select;

import java.util.List;

/**
 * Created by oukenghua on 2017/6/23.
 */

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {

    private List<Select> mSelectList;
    private Context mContext;
    ViewHolder viewHolder;

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView appImage;
        TextView appName;
        TextView appSize;
        TextView appContent;
        View selectView;
        public ViewHolder(View view){
            super(view);
            selectView = view;
            appImage = (ImageView)view.findViewById(R.id.app_image);
            appName = (TextView)view.findViewById(R.id.app_name);
            appSize = (TextView)view.findViewById(R.id.app_size);
            appContent = (TextView)view.findViewById(R.id.app_content);
        }

    }

    public AppAdapter(List<Select> selectList){
        mSelectList = selectList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        if(mContext == null){
            mContext = parent.getContext();
        }
        holder.selectView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Select select = mSelectList.get(position);
                Intent intent = new Intent(mContext, SelectActivity.class);
                intent.putExtra("Size",select.getSize());
                intent.putExtra(SelectActivity.SELECT_NAME,select.getName());
                intent.putExtra(SelectActivity.SELECT_IMAGE_ID,select.getImageId());
                intent.putExtra("Url",select.getLink());
                intent.putExtra("Content",select.getContent());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Select select = mSelectList.get(position);
        Glide.with(mContext).load(select.getLogoId()).into(holder.appImage);
        holder.appName.setText(select.getName());
        holder.appSize.setText("应用大小:"+String.valueOf(select.getSize())+"M");
        holder.appContent.setText(select.getContent());
    }

    @Override
    public int getItemCount() {
        return mSelectList.size();
    }
}

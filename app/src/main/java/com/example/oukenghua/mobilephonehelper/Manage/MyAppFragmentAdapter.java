package com.example.oukenghua.mobilephonehelper.Manage;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oukenghua.mobilephonehelper.Application.AppAdapter;
import com.example.oukenghua.mobilephonehelper.Application.MyGameFragmentAdapter;
import com.example.oukenghua.mobilephonehelper.Item;
import com.example.oukenghua.mobilephonehelper.Manage.AppManageFragment;
import com.example.oukenghua.mobilephonehelper.R;

import java.util.List;


/**
 * Created by oukenghua on 2017/6/20.
 */

public class MyAppFragmentAdapter extends RecyclerView.Adapter<MyAppFragmentAdapter.ViewHolder>{

    private List<Item> mItemList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView itemName;
        TextView itemContent;
        View selectView;
        public ViewHolder(View view){
            super(view);
            selectView = view;
            itemImage = (ImageView)view.findViewById(R.id.item_image);
            itemName = (TextView)view.findViewById(R.id.item_name);
            itemContent = (TextView)view.findViewById(R.id.item_content);
        }

    }

    public MyAppFragmentAdapter(List<Item> itemList){
        mItemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        final MyAppFragmentAdapter.ViewHolder holder = new MyAppFragmentAdapter.ViewHolder(view);
        if(mContext == null){
            mContext = parent.getContext();
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = mItemList.get(position);
        Glide.with(mContext).load(item.getItemImageId()).into(holder.itemImage);
        holder.itemName.setText(item.getItemName());
        holder.itemContent.setText(item.getItemContent());
        switch (position)
        {
            case 0:
                holder.selectView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext,DownloadActivity.class);
                        mContext.startActivity(intent);
                    }
                });
                break;
            case 2:
                holder.selectView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext,CleanActivity.class);
                        mContext.startActivity(intent);
                    }
                });
                break;
            case 3:
                holder.selectView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext,UninstallActivity.class);
                        mContext.startActivity(intent);
                    }
                });
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}

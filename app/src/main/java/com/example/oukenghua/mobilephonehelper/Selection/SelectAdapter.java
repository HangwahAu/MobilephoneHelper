package com.example.oukenghua.mobilephonehelper.Selection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oukenghua.mobilephonehelper.R;
import com.example.oukenghua.mobilephonehelper.SelectActivity;

import java.util.List;

/**
 * Created by oukenghua on 2017/6/21.
 */

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> {

    private Context mContext;
    private List<Select> mSelectList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView selectImage;
        TextView selectName;
        TextView selectSize;
        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            selectImage = (ImageView)view.findViewById(R.id.select_image);
            selectName = (TextView)view.findViewById(R.id.select_name);
            selectSize = (TextView)view.findViewById(R.id.select_size_text);
        }

    }

    public SelectAdapter(List<Select> selectList){
        mSelectList = selectList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.select_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Select select = mSelectList.get(position);
                Intent intent = new Intent(mContext, SelectActivity.class);
                intent.putExtra("Size",select.getSize());
                intent.putExtra(SelectActivity.SELECT_NAME,select.getName());
                intent.putExtra(SelectActivity.SELECT_IMAGE_ID,select.getImageId());
                intent.putExtra("Content",select.getContent());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Select select = mSelectList.get(position);
        holder.selectName.setText(select.getName());
        Glide.with(mContext).load(select.getImageId()).into(holder.selectImage);
    }

    @Override
    public int getItemCount() {
        return mSelectList.size();
    }
}

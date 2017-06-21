package com.example.oukenghua.mobilephonehelper.Selection;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oukenghua.mobilephonehelper.R;

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
        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            selectImage = (ImageView)view.findViewById(R.id.select_image);
            selectName = (TextView)view.findViewById(R.id.select_name);
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
        return new ViewHolder(view);
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

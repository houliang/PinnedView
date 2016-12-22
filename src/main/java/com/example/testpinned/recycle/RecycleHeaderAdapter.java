package com.example.testpinned.recycle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testpinned.ItemType;
import com.example.testpinned.ItemType.ItemData;
import com.example.testpinned.R;
import com.example.testpinned.log.LogUtility;

import java.util.List;


/**
 * Created by 11002581 on 2016/12/22.
 */

public class RecycleHeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemType.IPinnedAdapter {

    private static final String TAG = "RecycleHeaderAdapter";

    private List<ItemData> mData;

    public void setData(List<ItemData> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemType.TYPE_TITLE) {
            return new PinndHeaderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_header, parent, false));
        } else if (viewType == ItemType.TYPE_CONTENT) {
            return new PinndContentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemData data = mData.get(position);
        switch(data.mItemType) {
        case ItemType.TYPE_TITLE:
            bindHeaderData((PinndHeaderHolder)holder, data);
            break;
        case ItemType.TYPE_CONTENT:
            bindContentData((PinndContentHolder)holder, data);
            break;
        }
    }

    private void bindHeaderData(PinndHeaderHolder header, ItemData data) {
        if (header == null || data == null) {
            return;
        }
        header.mContentView.setText(data.mContent);
    }

    private void bindContentData(PinndContentHolder content, ItemData data) {
        if (content == null || data == null) {
            return;
        }
        content.mContentView.setText(data.mContent);
        content.mIconView.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData == null ? ItemType.TYPE_TITLE : mData.get(position).mItemType;
    }

    @Override
    public View createGroupHeaderView(int pos, ViewGroup parent, int type) {
        if (mData == null || pos >= mData.size()) {
            return null;
        }
        ItemType.ItemData data = null;
        for (int i=pos; i>=0; i--) {
            data = mData.get(i);
            if (data.mItemType == type) {
                RecyclerView.ViewHolder holder = onCreateViewHolder(parent, type);
                holder.itemView.setTag(holder);
                return holder.itemView;
            }
        }
        return null;
    }

    @Override
    public void attachGroupHeaderData(View headerView, int pos, int type) {
        if (headerView == null || mData == null || pos >= mData.size()) {
            return;
        }

        LogUtility.i(TAG, headerView, pos, type);
        ItemData data = null;
        for (int i=pos; i>=0; i--) {
            data = mData.get(i);
            if (data.mItemType == type) {
                onBindViewHolder((RecyclerView.ViewHolder)headerView.getTag(), i);
                break;
            }
        }
    }

    @Override
    public boolean isPinnedType(int pos, int type) {
        if (mData == null || pos >= mData.size()) {
            return false;
        }
        return mData.get(pos).mItemType == type;
    }

    public static class PinndHeaderHolder extends RecyclerView.ViewHolder {

        public TextView mContentView;

        public PinndHeaderHolder(View view) {
            super(view);
            mContentView = (TextView)view;
        }
    }

    public static class PinndContentHolder extends RecyclerView.ViewHolder {

        public TextView mContentView;
        public ImageView mIconView;

        public PinndContentHolder(View view) {
            super(view);
            mIconView = (ImageView)view.findViewById(R.id.item_icon);
            mContentView = (TextView)view.findViewById(R.id.item_description);
        }
    }
}

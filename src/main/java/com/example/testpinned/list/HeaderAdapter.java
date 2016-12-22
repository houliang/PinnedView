package com.example.testpinned.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testpinned.ItemType;
import com.example.testpinned.ItemType.ItemData;
import com.example.testpinned.R;
import com.example.testpinned.log.LogUtility;

import java.util.List;

/**
 * Created by 11002581 on 2016/12/19.
 */

public class HeaderAdapter extends BaseAdapter implements ItemType.IPinnedAdapter {

    private static final String TAG = "HeaderAdapter";

    private static final int ITEM_TYPE_COUNT = 2;

    private List<ItemData> mData;

    @Override
    public View createGroupHeaderView(int pos, ViewGroup parent, int type) {
        if (mData == null || pos >= mData.size()) {
            return null;
        }
        ItemType.ItemData data = null;
        for (int i=pos; i>=0; i--) {
            data = mData.get(i);
            if (data.mItemType == type) {
                return createViewType(i, parent);
            }
        }
        return null;
    }

    @Override
    public void attachGroupHeaderData(View headerView, int pos, int type) {
        if (headerView == null || mData == null || pos >= mData.size()) {
            return;
        }
        ItemData data = null;
        for (int i=pos; i>=0; i--) {
            data = mData.get(i);
            if (data.mItemType == type) {
                bindData(i, headerView);
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

    public void setData(List<ItemData> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {

        return ((ItemData) getItem(position)).mItemType;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LogUtility.i(TAG, "position:", position);

        if (convertView == null) {
            convertView = createViewType(position, parent);
        } else {
            bindData(position, convertView);
        }
        return convertView;
    }

    private void bindData(int pos, View convertView) {
        ItemData data = mData.get(pos);
        int type = data.mItemType;
        if (type == ItemType.TYPE_TITLE) {
            ((TextView)convertView).setText(data.mContent);
        } else if (type == ItemType.TYPE_CONTENT) {
            Object obj = convertView.getTag();
            if (obj == null || !(obj instanceof ViewHolder)) {
                return;
            }
            ViewHolder holder = (ViewHolder)obj;
            holder.mImageView.setImageResource(data.mImageId);
            holder.mTextView.setText(data.mContent);
        }
    }

    private View createViewType(int pos, ViewGroup parent) {
        int count = mData.size();
        if (pos >= count || pos < 0) {
            return null;
        }
        ItemData data = mData.get(pos);
        int type = data.mItemType;
        View view = null;
        if (type == ItemType.TYPE_TITLE) {
            view = createTitle(parent, data);
        } else if (type == ItemType.TYPE_CONTENT) {
            view = createContentView(parent, data);
        }
        return view;
    }

    private TextView createTitle(ViewGroup parent, ItemData data) {
        TextView textView = (TextView)LayoutInflater.from(parent.getContext()).inflate(R.layout.list_header, parent, false);
        textView.setText(data.mContent);
        return textView;
    }

    private View createContentView(ViewGroup parent, ItemData data) {
        Context context = parent.getContext();
        ViewGroup rootView = (ViewGroup)LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        ImageView image = (ImageView)rootView.findViewById(R.id.item_icon);
        TextView textView = (TextView)rootView.findViewById(R.id.item_description);
        image.setImageResource(data.mImageId);
        textView.setText(data.mContent);

        ViewHolder holder = new ViewHolder();
        holder.mImageView = image;
        holder.mTextView = textView;
        rootView.setTag(holder);

        return rootView;
    }

    public static class ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;
    }
}

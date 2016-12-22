package com.example.testpinned;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 11002581 on 2016/12/22.
 */

public class ItemType {
    public static final int TYPE_TITLE = 0;
    public static final int TYPE_CONTENT = 1;

    public static class ItemData {
        public int mItemType;
        public int mImageId;
        public String mContent;

        public ItemData() {
        }

        public ItemData(int type, String content) {
            mItemType = type;
            mContent = content;
        }

        public ItemData(int type, int imageId, String content) {
            mItemType = type;
            mImageId = imageId;
            mContent = content;
        }
    }

    public interface IPinnedAdapter {
        View createGroupHeaderView(int pos, ViewGroup parent, int type);
        void attachGroupHeaderData(View headerView, int pos, int type);
        boolean isPinnedType(int pos, int type);
    }
}

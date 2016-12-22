package com.example.testpinned.recycle;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.testpinned.ItemType.IPinnedAdapter;
import com.example.testpinned.log.LogUtility;

/**
 * Created by 11002581 on 2016/12/22.
 */

public class PinnedRecycleView extends RecyclerView {

    private static final String TAG = "PinnedRecycleView";

    private int mPinnedType = -1;
    private IPinnedAdapter mAttachedAdapter;
    private View mCurrentHeader;
    private float mHeaderOffset = 0;
    private int mWidthMode;

    private LinearLayoutManager mLayoutManager = null;

    public PinnedRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mLayoutManager = new LinearLayoutManager(context);
        setLayoutManager(mLayoutManager);
    }

    public void setPinnedItemType(int type) {
        mPinnedType = type;
    }

    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null && adapter instanceof IPinnedAdapter) {
            mAttachedAdapter = (IPinnedAdapter) adapter;
            addOnScrollListener(new ScrollListener());
        }
    }

    private void scrolling() {
        int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
        final int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        onScrolling(firstVisibleItem, lastVisibleItem);
    }

    private void onScrolling(int firstVisibleItem, int lastVisibleItem) {
        if (mAttachedAdapter == null || firstVisibleItem < 0) {
            return;
        }

        getHeaderView(firstVisibleItem);
        mAttachedAdapter.attachGroupHeaderData(mCurrentHeader, firstVisibleItem, mPinnedType);

        View nextHeader = getNextHeader(firstVisibleItem, lastVisibleItem);
        if (nextHeader == null) {
            return;
        }
        getHeaderOffset(nextHeader);
    }

    private void getHeaderView(int pos) {
        mHeaderOffset = 0;

        if (mCurrentHeader == null) {
            mCurrentHeader = mAttachedAdapter.createGroupHeaderView(pos, this, mPinnedType);
        }
        forceLayoutHeaderView(mCurrentHeader);
    }

    private View getNextHeader(int firstVisibleItem, int lastVisibleItem) {
        int index = -1;
        for (int i = firstVisibleItem + 1; i < lastVisibleItem; i++) {
            if (!mAttachedAdapter.isPinnedType(i, mPinnedType)) {
                continue;
            }
            index = i;
            break;
        }

        LogUtility.i(TAG, "index:", index, "firstVisibleItem:", firstVisibleItem);
        int count = getChildCount();
        index -= firstVisibleItem;
        if (index < 0 || index >= count) {
            return  null;
        }
        return getChildAt(index);
    }

    private void getHeaderOffset(View header) {
        if (header == null) {
            return;
        }
        float headerTop = header.getTop();
        float pinnedHeaderHeight = mCurrentHeader.getMeasuredHeight();
        if (headerTop > pinnedHeaderHeight) {
            mHeaderOffset = 0;
        } else {
            mHeaderOffset = headerTop - header.getHeight();
        }

        LogUtility.i(TAG, "header:", header, "mHeaderOffset:", mHeaderOffset);
    }

    private void forceLayoutHeaderView(View header) {

        int widthSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), mWidthMode);
        ViewGroup.LayoutParams layoutParams = header.getLayoutParams();
        int heightSpec;
        if (layoutParams != null && layoutParams.height > 0) {
            heightSpec = MeasureSpec.makeMeasureSpec(layoutParams.height, MeasureSpec.EXACTLY);
        } else {
            heightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        header.measure(widthSpec, heightSpec);
        header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        super.dispatchDraw(canvas);

        if (mCurrentHeader == null) {
            return;
        }

        int saveCount = canvas.save();
        canvas.translate(0, mHeaderOffset);
        canvas.clipRect(0, 0, getWidth(), mCurrentHeader.getMeasuredHeight()); // needed
        mCurrentHeader.draw(canvas);
        canvas.restoreToCount(saveCount);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidthMode = MeasureSpec.getMode(widthMeasureSpec);
    }

    private static class ScrollListener extends RecyclerView.OnScrollListener {
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (recyclerView instanceof PinnedRecycleView) {
                ((PinnedRecycleView)recyclerView).scrolling();
            }
        }
    }
}

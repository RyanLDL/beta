package com.example.beta1.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

public class MyLayoutManager extends LinearLayoutManager {
    private PagerSnapHelper pagerSnapHelper;//用于辅助recylerview完成对齐
    private MyListener listener;
    private RecyclerView rv;
    private int mDrift;//位移，用来判断位移方向
    private RecyclerView.OnChildAttachStateChangeListener mlistener=new RecyclerView.OnChildAttachStateChangeListener() {//可以附加到 RecyclerView 的监听器，以便在 ViewHolder 附加到 RecyclerView 或从 RecyclerView 分离时得到通知。
        @Override
        public void onChildViewAttachedToWindow(@NonNull View view) {//当视图附加到 RecyclerView 时调用
            if (listener != null && getChildCount() == 1);//getChildCount():返回当前附加到父 RecyclerView 的子视图数量。这不包括暂时分离和或废弃的子视图。
                listener.onInitComplete();
        }

        @Override
        public void onChildViewDetachedFromWindow(@NonNull View view) {//当视图与 RecyclerView 分离时调用。
            if (mDrift >= 0){
                if (listener != null){
                    listener.onPageRelease(true,getPosition(view));
                } else {
                    if (listener != null){
                        listener.onPageRelease(false,getPosition(view));
                    }
                }
            }

        }
    };
    public MyLayoutManager(Context context) {
        super(context);
    }

    public MyLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        init();
    }

    private void init() {
        pagerSnapHelper=new PagerSnapHelper();
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {//当此 LayoutManager 附加到 RecyclerView 并且 RecyclerView 附加到窗口时调用。
        super.onAttachedToWindow(view);
        pagerSnapHelper.attachToRecyclerView(view);//将 SnapHelper 附加到提供的 RecyclerView。
        this.rv=view;
        rv.addOnChildAttachStateChangeListener(mlistener);
    }

    //当滑动状态改变时
    //缓慢拖拽-> SCROLL_STATE_DRAGGING
    //快速滚动-> SCROLL_STATE_SETTLING
    //空闲状态-> SCROLL_STATE_IDLE
    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        switch (state){
            case RecyclerView.SCROLL_STATE_IDLE:
                View view=pagerSnapHelper.findSnapView(this);
                if (view != null){
                    int position =getPosition(view);
                    if (listener !=null && getChildCount() == 1){
                        listener.onPageSelected(position,position == getItemCount()-1);
                    }
                }
                break;
        }
    }

    //监听监听竖直方向的相对偏移量
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dy;
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    //监听水平方向的相对偏移量
    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dx;
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    //设置监听
    public void setListener(MyListener listener){
        this.listener = listener;
    }
}

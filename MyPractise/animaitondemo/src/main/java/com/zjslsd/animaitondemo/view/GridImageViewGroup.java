package com.zjslsd.animaitondemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.zjslsd.animaitondemo.R;

/**
 * Created by Lex lex on 2017/6/25.
 */

public class GridImageViewGroup extends ViewGroup {

    private int childVerticalSpace = 0;
    private int childHorizontalSpace = 0;
    private int columnNum = 3;
    private int childWidth = 0;
    private int childHeight = 0;

    public GridImageViewGroup(Context context) {
        this(context, null);
    }

    public GridImageViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GridImageViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GridImageViewGroup);

        if(a != null){
            childHorizontalSpace = a.getDimensionPixelSize(R.styleable.GridImageViewGroup_childHorizontalSpace, 0);
            childVerticalSpace = a.getDimensionPixelSize(R.styleable.GridImageViewGroup_childVerticalSpace, 0);
            columnNum = a.getInt(R.styleable.GridImageViewGroup_columnNum, 3);

            //        int count = a.getIndexCount();
//        for(int i = 0; i < count;i++){
//            switch (i){
//                case R.styleable.GridImageViewGroup_childHorizontalSpace:
//                    childHorizontalSpace = a.getIndex(i);
//                    break;
//                case R.styleable.GridImageViewGroup_childVerticalSpace:
//                    childVerticalSpace = a.getIndex(i);
//                    break;
//                case R.styleable.GridImageViewGroup_columnNum:
//                    columnNum = a.getIndex(i);
//                    break;
//            }
//        }

            a.recycle();
        }




    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int childCount = getChildCount();
        if(childCount > 0){
            childWidth = (widthSize - (columnNum - 1) * childHorizontalSpace) / columnNum;
            childHeight = childWidth;

            int vw = widthSize;

            if(childCount < columnNum){
                vw = childCount * (childHeight + childVerticalSpace);
            }

            int rowCount = childCount / columnNum + (childCount % columnNum == 0 ? 0 : 1);

            int vh = childHeight * rowCount + childVerticalSpace * (rowCount > 0 ? rowCount - 1 : 0);

            setMeasuredDimension(vw, vh);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int left = 0;
        int top = 0;
        int count = getChildCount();
        for(int i = 0;i < count;i ++){
            View child = getChildAt(i);
            left = (i % columnNum) * (childWidth + childHorizontalSpace);
            top = (i / columnNum) * (childHeight + childVerticalSpace);
            child.layout(left, top, left + childWidth, top + childHeight);
        }

    }
}

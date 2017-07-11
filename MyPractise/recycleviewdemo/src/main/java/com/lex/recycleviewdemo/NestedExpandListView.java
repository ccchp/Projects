package com.lex.recycleviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by Lex lex on 2017/4/5.
 */
public class NestedExpandListView extends ExpandableListView {
    public NestedExpandListView(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

                MeasureSpec.AT_MOST);

        //
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
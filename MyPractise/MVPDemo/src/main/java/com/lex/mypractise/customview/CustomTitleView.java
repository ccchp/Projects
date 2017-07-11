package com.lex.mypractise.customview;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Lex lex on 2017/6/19.
 */

public class CustomTitleView extends View {

    public String title;
    private int titlecolor;
    private int titlesize;

    private Paint paint;
    private Rect rect;

    public CustomTitleView(Context context) {
        this(context, null);
    }

    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public CustomTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R, defStyleAttr, 0);
    }
}

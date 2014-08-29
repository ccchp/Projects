package com.lex.wechathead.util;

import android.content.Context;

public class Util {

	public static int dip2px(Context context, float paramFloat)
	  {
	    return (int)(0.5F + paramFloat * context.getResources().getDisplayMetrics().density);
	  }
}

package com.lex.accounting.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

import com.lex.accounting.R;
import com.lex.accounting.database.DBHelper;

public abstract class BaseView {
	
	protected DBHelper mDBHelper;
	protected View mContentView;
	protected LayoutInflater mInflater;
	protected Context mContext;
	
	private OnSaveClickListener mOnsaveClickListener;

	public abstract void initView();
	
	public interface OnSaveClickListener {
		void onSavingClickListener();
	}
	
	public BaseView(Context context){
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
//		initView();
	}
	
	public View getView(){
		return mContentView;
	}
	
	public void setSaveListener(OnSaveClickListener listener){
		mOnsaveClickListener = listener;
	}
	
	public void displaySaveBtn(boolean isShow){
		ActionBar bar = ((Activity)mContext).getActionBar();
        View saveView = mInflater.inflate(R.layout.costom_actionbar_save, null);
        Button saveBtn = (Button) saveView.findViewById(R.id.barSaveBtn);
        saveBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mOnsaveClickListener.onSavingClickListener();
			}
		});
        bar.setCustomView(saveView, new ActionBar.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        ActionBar.LayoutParams lp = (ActionBar.LayoutParams) saveView.getLayoutParams();
        lp.gravity = lp.gravity & ~Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK | Gravity.RIGHT;
        bar.setCustomView(saveView, lp);
        
        int flags = bar.getDisplayOptions();
        if(isShow){
        	if(flags != 27){
        		flags = flags ^ ActionBar.DISPLAY_SHOW_CUSTOM ^ ActionBar.DISPLAY_HOME_AS_UP
        				 ^ ActionBar.DISPLAY_SHOW_HOME ^ ActionBar.DISPLAY_SHOW_TITLE ^ ActionBar.DISPLAY_USE_LOGO
        				 ^ ActionBar.NAVIGATION_MODE_LIST ^ ActionBar.NAVIGATION_MODE_STANDARD ^ ActionBar.NAVIGATION_MODE_TABS;
        	}
        }else{
        	if(flags == 27){
        		flags = bar.getDisplayOptions() ^ ActionBar.DISPLAY_SHOW_CUSTOM;
        	}
        }
        bar.setDisplayOptions(flags);
	}
	
}

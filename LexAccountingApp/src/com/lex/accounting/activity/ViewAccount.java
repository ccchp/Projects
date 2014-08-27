package com.lex.accounting.activity;

import android.content.Context;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.lex.accounting.R;
import com.lex.accounting.database.DBHelper;

public class ViewAccount extends BaseView{

	private Context mContext;
	private ListView mAccountList;
	private SimpleCursorAdapter mSimpleCursorAdatper;
	private final static String SQL_SELECT_ACCOUNT = "SELECT * FROM " /*+ DBHelper.*/;
	
	public ViewAccount(Context context){
		super(context);
	}

	@Override
	public void initView() {
		mContentView = mInflater.inflate(R.layout.view_account_layout, null);
		mAccountList = (ListView) mContentView.findViewById(R.id.horizon_listview);
		TextView emptyView = new TextView(mContext);
		emptyView.setText(R.string.notice_accout_null);
		mAccountList.setEmptyView(emptyView);
		
		mDBHelper = new DBHelper(mContext);
	}
	
}

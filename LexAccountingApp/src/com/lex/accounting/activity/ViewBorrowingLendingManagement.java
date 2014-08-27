package com.lex.accounting.activity;

import com.lex.accounting.R;

import android.content.Context;

public class ViewBorrowingLendingManagement extends BaseView{

	public ViewBorrowingLendingManagement(Context context) {
		super(context);
		initView();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		displaySaveBtn(true);
		
		mContentView = mInflater.inflate(R.layout.view_borrowinglending_layout, null);
	}

}

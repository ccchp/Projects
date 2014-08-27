package com.lex.accounting.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Comment;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lex.accounting.R;
import com.lex.accounting.activity.BaseView.OnSaveClickListener;
import com.lex.accounting.util.Common;

public class ViewIncomeExpenditureManagement extends BaseView implements OnSaveClickListener{

	private Button mDataBtn, mUserBtn, mCategoryBtn, mAccountBtn, mSearchBtn, mSearchDatePickerBtn;
	private EditText mCommentsEdit, mMoneyEdit;
	private TextView mPayeeTv;
	private ListView mHorizonList;
	private int mFlag = 0;//支付0 / 收入1  标记

	public ViewIncomeExpenditureManagement(Context context, int postion) {
		super(context);
		mFlag = postion;
		initView();
		
	}

	@Override
	public void initView() {
		mContentView = mInflater.inflate(R.layout.view_incomeexpenditure_layout, null);

		mPayeeTv = (TextView) mContentView.findViewById(R.id.payee_tv);
		if(mFlag == 0){
			mPayeeTv.setText(mContext.getString(R.string.tag_drawee));
		}else{
			mPayeeTv.setText(mContext.getString(R.string.tag_payee));
		}
		
		mDataBtn = (Button) mContentView.findViewById(R.id.data_view);
		mUserBtn = (Button) mContentView.findViewById(R.id.user_view);
		mCategoryBtn = (Button) mContentView.findViewById(R.id.category_view);
		mMoneyEdit = (EditText) mContentView.findViewById(R.id.money_view);
		mAccountBtn = (Button) mContentView.findViewById(R.id.account_view);
		mCommentsEdit = (EditText) mContentView.findViewById(R.id.comments_view);
		mSearchBtn = (Button) mContentView.findViewById(R.id.searchBtn);
		mSearchDatePickerBtn = (Button) mContentView.findViewById(R.id.searchDatePickerBtn);
		
		mDataBtn.setText(Common.getCurrentDate());
		mSearchDatePickerBtn.setText(Common.getCurrentDate());
		
		mHorizonList = (ListView) mContentView.findViewById(R.id.horizon_listview);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(int i = 0;i < 50; i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("number", "" + i);
			map.put("date", "1989-" + i);
			map.put("category", "种类=" + i);
			map.put("payee", "路人=" + i);
			map.put("money", "消费=" + i);
			map.put("account", "账户=" + i);
			map.put("comments", "备注=" + i);
			list.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(mContext, list, R.layout.view_account_layout_item,
				new String[]{"number", "date", "category", "payee", "money", "account", "comments"},
				new int[]{R.id.numberTv, R.id.dateTv, R.id.categoryTv, R.id.payeeTv, R.id.moneyTv, R.id.accountTv, R.id.commentsTv});
		mHorizonList.setAdapter(adapter);
		
		mDataBtn.setOnClickListener(onclickListener);
		mUserBtn.setOnClickListener(onclickListener);
		mCategoryBtn.setOnClickListener(onclickListener);
		mMoneyEdit.setOnClickListener(onclickListener);
		mAccountBtn.setOnClickListener(onclickListener);
		mCommentsEdit.setOnClickListener(onclickListener);
		mSearchBtn.setOnClickListener(onclickListener);
		mSearchDatePickerBtn.setOnClickListener(onclickListener);
		setSaveListener(this);
		
		displaySaveBtn(true);
	}

//	class 
	
	private OnClickListener onclickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.data_view:
				Common.showDataPickerDialog(mContext, (Button) v);
				break;
			case R.id.user_view:
				break;
			case R.id.category_view:
				break;
			case R.id.money_view:
				break;
			case R.id.account_view:
				break;
			case R.id.comments_view:
				break;
			case R.id.searchBtn:
				break;
			case R.id.searchDatePickerBtn:
				Common.showDataPickerDialog(mContext, (Button)v);
				break;
			default:
				break;
			}
		}
	};

	@Override
	public void onSavingClickListener() {
		Toast.makeText(mContext, "保存中。。。", Toast.LENGTH_SHORT).show();
	}

}

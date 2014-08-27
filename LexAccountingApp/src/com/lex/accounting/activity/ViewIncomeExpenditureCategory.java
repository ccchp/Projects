package com.lex.accounting.activity;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lex.accounting.R;
import com.lex.accounting.entity.ParentCategoryEntity;
import com.lex.accounting.entity.SubCategoryEntity;
import com.lex.accounting.xmlparser.CategoryXmlPullParser;

public class ViewIncomeExpenditureCategory extends BaseView{

	private int mFlag = 0;//收入 4 / 支出 5
	private ListView mParentCategoryLv, mSubCategoryLv;
	private List<ParentCategoryEntity> mParentCategoryList;
	private List<SubCategoryEntity> mSubCategoryList;
	private String mCurtParentCategory, mCurSubCategory;
	private int mDefaultSelector = 0;
	
	public ViewIncomeExpenditureCategory(Context context, int position) {
		super(context);
		this.mFlag = position;
		initView();
	}

	@Override
	public void initView() {
		mContentView = mInflater.inflate(R.layout.view_incomeexpenditure_category_layout, null);
		
		mParentCategoryLv = (ListView) mContentView.findViewById(R.id.categoryParentList);
		mSubCategoryLv = (ListView) mContentView.findViewById(R.id.categorySubList);
		mParentCategoryLv.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS); 
		mSubCategoryLv.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS); 
		
		View parentFootView = mInflater.inflate(R.layout.view_category_layout_item, null);
		mParentCategoryLv.addFooterView(parentFootView);
		View subFootView = mInflater.inflate(R.layout.view_category_layout_item, null);
		mSubCategoryLv.addFooterView(subFootView);
		if(mFlag == 4){
			mParentCategoryList = CategoryXmlPullParser.getDefaultIncomeCategoryList(mContext);
		}else{
			mParentCategoryList = CategoryXmlPullParser.getDefaultExpenditureCategoryList(mContext);
		}
		
		final ParentCategoryAdapter parentAdapter = new ParentCategoryAdapter(mParentCategoryList);
		mParentCategoryLv.setAdapter(parentAdapter);
		parentAdapter.setSelectIndex(mDefaultSelector);
		
		mCurtParentCategory = mParentCategoryList.get(mDefaultSelector).getName();
		mSubCategoryList = mParentCategoryList.get(mDefaultSelector).getSubList();
		SubCategoryAdapter subAdapter = new SubCategoryAdapter(mSubCategoryList);
		mSubCategoryLv.setAdapter(subAdapter);
		mParentCategoryLv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(arg2 == mParentCategoryList.size()){
					
				}else if(mParentCategoryLv.getSelectedItemPosition() != arg2){
					mCurtParentCategory = mParentCategoryList.get(arg2).getName();
					mSubCategoryList = mParentCategoryList.get(arg2).getSubList();
					SubCategoryAdapter subAdapter = new SubCategoryAdapter(mSubCategoryList);
					mSubCategoryLv.setAdapter(subAdapter);
					parentAdapter.setSelectIndex(arg2);
					parentAdapter.notifyDataSetChanged();
				}
			}
		});
		mSubCategoryLv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(arg2 == mSubCategoryList.size()){
					
				}else{
					mCurSubCategory = mSubCategoryList.get(arg2).getName();
				}
			}
		});
	}
	
	private void showAddCategoryDialog(){
		
	}
	
	class ParentCategoryAdapter extends BaseAdapter{
		private int mCurIndex = 0;
		private List<ParentCategoryEntity> mList = null;
		
		public ParentCategoryAdapter(List<ParentCategoryEntity> list){
			this.mList = list;
		}
		
		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public Object getItem(int arg0) {
			return mList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}
		
		public void setSelectIndex(int index){
			this.mCurIndex = index;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup group) {
			Holder holder = null;
			if(convertView == null){
				holder = new Holder();
				convertView = mInflater.inflate(R.layout.view_category_layout_item, null);
				holder.title = (TextView) convertView.findViewById(R.id.category_name);
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			ParentCategoryEntity entity = (ParentCategoryEntity) mList.get(position);
			holder.title.setText(entity.getName());
			Log.i("lex", "name = " + entity.getName());
			if(mCurIndex == position){
				convertView.setBackgroundColor(mContext.getResources().getColor(R.color.blue));
			}else {
				convertView.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));
			}
			return convertView;
		}
		
		class Holder{
			TextView title = null;
		}
	}
	class SubCategoryAdapter extends BaseAdapter{
		List<SubCategoryEntity> mList = null;
		
		public SubCategoryAdapter(List<SubCategoryEntity> list){
			this.mList = list;
		}
		
		@Override
		public int getCount() {
			return mList.size();
		}
		
		@Override
		public Object getItem(int arg0) {
			return mList.get(arg0);
		}
		
		@Override
		public long getItemId(int arg0) {
			return 0;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup group) {
			Holder holder = null;
			if(convertView == null){
				holder = new Holder();
				convertView = mInflater.inflate(R.layout.view_category_layout_item, null);
				holder.title = (TextView) convertView.findViewById(R.id.category_name);
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			SubCategoryEntity entity = (SubCategoryEntity) mList.get(position);
			holder.title.setText(entity.getName());
			Log.i("lex", "name = " + entity.getName());
			return convertView;
		}
		
		class Holder{
			TextView title = null;
		}
	}

}

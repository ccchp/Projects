package com.lex.wechathead.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lex.wechathead.factory.AsyncImageLoader;
import com.lex.wechathead.util.Constant;
import com.lex.wechathead.util.Util;

public class MainActivity extends Activity implements OnClickListener {
	
	private TextView mChooseTv;
	private GridView mGridView;
	private ArrayList<Integer> mIdsList;
	private GridviewAdapter mAdapter;
	private static final int totalPreview = 48;
	private float mItemGap = 5.0f;
	private float mItemWidth = 90.0f;
	
	private static final int REQUEST_CHOOSE_BG = 1;
	private static final int REQUEST_CROP_OVER = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initDate();
		initFile();
		initView();
	}

	private void initDate(){
		mIdsList = new ArrayList<Integer>();
		for(int i = 1; i <= totalPreview ;i++){
			mIdsList.add(getRawsrcID("icon_", i));
		}
	}
	
	private void initFile(){
		File file = new File(Constant.PATH);
	    if (file.exists())
	      return;
	    file.mkdirs();
	}
	
	private void initView(){
		mChooseTv = (TextView) findViewById(R.id.choose_head);
		mChooseTv.setOnClickListener(this);
		mGridView = (GridView) findViewById(R.id.girdview);
		mGridView.setSelector(getResources().getDrawable(R.drawable.item_selector));
		mAdapter = new GridviewAdapter();
		mGridView.setAdapter(mAdapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent in = new Intent(MainActivity.this, HandleBitmapActivity.class);
				in.putExtra("src_id", mIdsList.get(arg2));
				startActivity(in);
			}
		});
	}
	
	private int getRawsrcID(String title, int name){
		String pkgName = getPackageName();
		return getResources().getIdentifier(title + name, "raw", pkgName);
	}
	
	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */

	public void startPhotoZoom(Uri uri) {
		/*
		 * 至于下面这个Intent的ACTION是怎么知道的，大家可以看下自己路径下的如下网页
		 * yourself_sdk_path/docs/reference/android/content/Intent.html
		 * 直接在里面Ctrl+F搜：CROP ，之前没仔细看过，其实安卓系统早已经有自带图片裁剪功能, 是直接调本地库的
		 */
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, REQUEST_CROP_OVER);
	}

	/**
	 * 保存裁剪之后的图片数据
	 * 
	 * @param picdata
	 */
	private void setPicToView(Intent picdata) {
		picdata.setClassName("com.lex.wechathead.activity", "com.lex.wechathead.activity.HandleBitmapActivity");
		startActivity(picdata);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		// 如果是直接从相册获取
		case REQUEST_CHOOSE_BG:
			startPhotoZoom(data.getData());
			 break;
			// // 如果是调用相机拍照时
			// case 2:
			// File temp = new File(Environment.getExternalStorageDirectory()
			// + "/superspace.jpg");
			// startPhotoZoom(Uri.fromFile(temp));
			// break;
		// 取得裁剪后的图片
		case REQUEST_CROP_OVER:
			/**
			 * 非空判断大家一定要验证，如果不验证的话， 在剪裁之后如果发现不满意，要重新裁剪，丢弃
			 * 当前功能时，会报NullException，只 在这个地方加下，大家可以根据不同情况在合适的 地方做判断处理类似情况
			 * 
			 */
			if (data != null) {
				setPicToView(data);
			}
			break;
		default:
			break;

		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	class GridviewAdapter extends BaseAdapter{

		private LayoutInflater mInflater;
		private HashMap<Integer, View> viewMap;
		public GridviewAdapter(){
			mInflater = LayoutInflater.from(MainActivity.this);
			viewMap = new HashMap<Integer, View>();
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mIdsList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return mIdsList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			Holder holder = null;
//			if(convertView == null){
//				holder = new Holder();
//				convertView = mInflater.inflate(R.layout.gridview_item, null);
//				convertView.setTag(holder);
//				holder.item = (ImageView) convertView.findViewById(R.id.imageview_item);
//			}else{
//				holder = (Holder) convertView.getTag();
//			}
			
			if(!viewMap.containsKey(position) || viewMap.get(position) == null){
		 		holder = new Holder();
		 		convertView = mInflater.inflate(R.layout.gridview_item, null);
		 		holder.item = (ImageView) convertView.findViewById(R.id.imageview_item);
		 		convertView.setTag(holder);
		 		viewMap.put(position, convertView);
		 	}else{
		 		convertView = viewMap.get(position);
		 		holder = (Holder) convertView.getTag();
		 	}
			
			int gap = Util.dip2px(MainActivity.this, mItemGap);
			int width = Util.dip2px(MainActivity.this, mItemWidth);
			holder.item.setLayoutParams(new LinearLayout.LayoutParams(width, width));
			holder.item.setPadding(gap, gap, gap, gap);
			AsyncImageLoader.getInstance(MainActivity.this);
			AsyncImageLoader.loadBitmapByPath(mIdsList.get(position), holder.item, new AsyncImageLoader.ImageCallback() {
				@Override
				public void imageLoaded(Bitmap imageBitmap, ImageView imageView, int resID) {
					imageView.setImageBitmap(imageBitmap);
				}
			});
//			holder.item.setImageResource(mIdsList.get(position));
			return convertView;
		}
		
		class Holder{
			ImageView item;
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.choose_head:
			Intent intent = new Intent(Intent.ACTION_PICK, null);
			/**
			 * 下面这句话，与其它方式写是一样的效果，如果： intent.setData(MediaStore.Images
			 * .Media.EXTERNAL_CONTENT_URI); intent.setType(""image/*");设置数据类型
			 * 如果朋友们要限制上传到服务器的图片类型时可以直接写如 ："image/jpeg 、 image/png等的类型"
			 */
			intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
					"image/*");
			startActivityForResult(intent, REQUEST_CHOOSE_BG);
			break;
		default:
			break;
		}
	}
}

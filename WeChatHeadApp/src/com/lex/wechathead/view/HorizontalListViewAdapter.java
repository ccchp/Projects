package com.lex.wechathead.view;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lex.wechathead.activity.MainActivity;
import com.lex.wechathead.activity.R;
import com.lex.wechathead.util.Util;

public class HorizontalListViewAdapter extends BaseAdapter{
	private ArrayList<Integer> mIconIDs;
	private Context mContext;
	private LayoutInflater mInflater;
//	private Bitmap iconBitmap;
	private int selectIndex = -1;
	private float mItemGap = 10.0f;
	private float mItemWidth = 95.0f;

	public HorizontalListViewAdapter(Context context, ArrayList<Integer> ids){
		this.mContext = context;
		this.mIconIDs = ids;
		mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//LayoutInflater.from(mContext);
	}
	@Override
	public int getCount() {
		return mIconIDs.size();
	}
	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.gridview_item, null);
			holder.mImage=(ImageView)convertView.findViewById(R.id.imageview_item);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		if(position == selectIndex){
			convertView.setSelected(true);
		}else{
			convertView.setSelected(false);
		}
		
		int gap = Util.dip2px(mContext, mItemGap);
		int width = Util.dip2px(mContext, mItemWidth);
		holder.mImage.setLayoutParams(new LinearLayout.LayoutParams(width, width));
		holder.mImage.setPadding(gap, gap, gap, gap);
		
//		iconBitmap = getPropThumnail(mIconIDs[position]);
		holder.mImage.setImageResource(/*iconBitmap*/mIconIDs.get(position));

		return convertView;
	}

	class ViewHolder {
		private ImageView mImage;
	}
//	private Bitmap getPropThumnail(int id){
//		Drawable d = mContext.getResources().getDrawable(id);
//		Bitmap b = ((BitmapDrawable)d).getBitmap();
////		Bitmap bb = BitmapUtil.getRoundedCornerBitmap(b, 100);
//		int w = mContext.getResources().getDimensionPixelOffset(R.dimen.thumnail_default_width);
//		int h = mContext.getResources().getDimensionPixelSize(R.dimen.thumnail_default_height);
//		
//		Bitmap thumBitmap = ThumbnailUtils.extractThumbnail(b, w, h);
//		
//		return thumBitmap;
//	}
	public void setSelectIndex(int i){
		selectIndex = i;
	}
}
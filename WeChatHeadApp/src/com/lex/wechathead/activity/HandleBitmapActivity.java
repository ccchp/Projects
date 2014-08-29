package com.lex.wechathead.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lex.wechathead.factory.CircleNumPicFactory;
import com.lex.wechathead.util.BitmapRotating;
import com.lex.wechathead.util.BitmaptoCard;
import com.lex.wechathead.util.Constant;
import com.lex.wechathead.view.HorizontalListView;
import com.lex.wechathead.view.HorizontalListViewAdapter;

public class HandleBitmapActivity extends Activity implements OnClickListener {

	private ImageView mPreviewIv;
	private ImageView mCornerIv;
	private TextView mBackView;
	private TextView mSaveView;
	private Button mRotateBtn;
	private int mSrcId = 0;
	private Bitmap mPreviewBitmap;
	private HorizontalListView mHorizonList;
	private ArrayList<Integer> mIdsList;

	private static final int mTotalIcons = 17;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handle_bitmap_layout);
		initDate();
		initView();

	}

	private void initDate() {
		Intent in = getIntent();
		if (in != null) {
			mSrcId = in.getExtras().getInt("src_id");
			if (mSrcId != 0) {
				mPreviewBitmap = BitmapFactory.decodeResource(getResources(),
						mSrcId);
			} else {
				Bundle extras = in.getExtras();
				if (extras != null) {
					mPreviewBitmap = extras.getParcelable("data");
				}
			}
		}
		mIdsList = new ArrayList<Integer>();
		String pkgName = getPackageName();
		String iconName = "s_";
		for (int i = 0; i < mTotalIcons; i++) {
			int id = getResources().getIdentifier(iconName + i, "drawable",
					pkgName);
			mIdsList.add(id);
		}
	}

	private void initView() {
		mHorizonList = (HorizontalListView) findViewById(R.id.listview);
		HorizontalListViewAdapter adapter = new HorizontalListViewAdapter(
				HandleBitmapActivity.this, mIdsList);
		mHorizonList.setAdapter(adapter);
		mHorizonList.setOnItemClickListener(onItemClick);
		mBackView = (TextView) findViewById(R.id.act_back);
		mSaveView = (TextView) findViewById(R.id.saveAndshare);
		mRotateBtn = (Button) findViewById(R.id.rotate);

		mBackView.setOnClickListener(this);
		mSaveView.setOnClickListener(this);
		mRotateBtn.setOnClickListener(this);

		mPreviewIv = (ImageView) findViewById(R.id.preview);
		mCornerIv = (ImageView) findViewById(R.id.msg);

		mPreviewIv.setImageBitmap(mPreviewBitmap);
		mCornerIv.setImageResource(mIdsList.get(1));
	}

	private Bitmap toConformBitmap(Bitmap background, Bitmap foreground) {
		if (background == null) {
			return null;
		}

		int bgWidth = background.getWidth();
		int bgHeight = background.getHeight();
		int foregroundW = foreground.getWidth();
		// int fgWidth = foreground.getWidth();
		// int fgHeight = foreground.getHeight();
		// create the new blank bitmap 创建一个新的和SRC长度宽度一样的位图
		Bitmap newbmp = Bitmap
				.createBitmap(bgWidth, bgHeight, Config.ARGB_8888);
		Canvas cv = new Canvas(newbmp);
		// draw bg into
		cv.drawBitmap(background, 0, 0, null);// 在 0，0坐标开始画入bg
		// draw fg into
		cv.drawBitmap(foreground, bgWidth - foregroundW, 0, null);// 在
																	// 0，0坐标开始画入fg
																	// ，可以从任意位置画入
		// save all clip
		cv.save(Canvas.ALL_SAVE_FLAG);// 保存
		// store
		cv.restore();// 存储
		return newbmp;
	}

	private void showInputDialog(final CallBack callBack) {
		final EditText input = new EditText(HandleBitmapActivity.this);
		input.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {

				int total = 2;
				String str = arg0.toString();
				// if(str.length() > 2){
				// input.setText(str.substring(0, str.length() - 1));
				// input.setSelection(input.getText().toString().length());
				// Toast.makeText(HandleBitmapActivity.this,
				// getString(R.string.input_info), Toast.LENGTH_SHORT).show();
				// return;
				// }else{
				// }
				for (int i = 0; i < str.length(); i++) {
					char c = str.charAt(i);
					if (isChinese(c)) {
						total -= 2;
					} else if (Character.isLetterOrDigit(c)
							|| Character.isSpaceChar(c)) {
						total--;
					}
					if (total < 0) {
						input.setText(str.substring(0, str.length() - 1));
						input.setSelection(input.getText().toString().length());
						Toast.makeText(HandleBitmapActivity.this,
								getString(R.string.input_info),
								Toast.LENGTH_SHORT).show();
						return;
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			@Override
			public void afterTextChanged(Editable arg0) {
			}
		});
		input.setFocusable(true);

		new AlertDialog.Builder(this)
				.setTitle(R.string.input_text)
				.setView(input)
				.setNegativeButton(R.string.cancel, null)
				.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int arg1) {
								if (input.getText().toString().length() == 0) {
									Toast.makeText(HandleBitmapActivity.this,
											R.string.not_null,
											Toast.LENGTH_SHORT).show();
								} else {
									callBack.setMsg(input.getText().toString());
								}
							}
						}).create().show();
	}

	public boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	private OnItemClickListener onItemClick = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			if (position == 0) {
				showInputDialog(new HandleBitmapActivity.CallBack() {
					@Override
					public void setMsg(String str) {
						Bitmap bmp = CircleNumPicFactory
								.getInstance()
								.setBg(BitmapFactory.decodeResource(
										HandleBitmapActivity.this
												.getResources(),
										R.drawable.s_bg)).setText(str)
								.generateBitmap();
						mCornerIv.setImageBitmap(bmp);
					}

				});
			} else {
				mCornerIv.setImageResource(mIdsList.get(position));
			}
		}
	};

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.act_back:
			finish();
			break;
		case R.id.saveAndshare:
			BitmaptoCard.saveBmpToSd(
					Constant.PATH,
					toConformBitmap(mPreviewBitmap, ((BitmapDrawable) mCornerIv
							.getDrawable()).getBitmap()),
					System.currentTimeMillis() + "", 100);
			break;
		case R.id.rotate:
			mPreviewBitmap = BitmapRotating
					.rotaingImageView(90, mPreviewBitmap);
			mPreviewIv.setImageBitmap(mPreviewBitmap);
			break;
		default:
			break;
		}
	}

	private static abstract interface CallBack {
		public abstract void setMsg(String paramString);
	}
}

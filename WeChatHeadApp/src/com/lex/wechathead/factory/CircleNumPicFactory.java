package com.lex.wechathead.factory;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;

public class CircleNumPicFactory/* extends BaseNumPicFactory */{
	private Bitmap bg;
	private String text;
	private static CircleNumPicFactory factory;
	
	public static CircleNumPicFactory getInstance(){
		if(factory == null){
			factory = new CircleNumPicFactory();
		}
		return factory;
	}

	public Bitmap generateBitmap() {
		int width = this.bg.getWidth();
		int height = this.bg.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
//		canvas.drawARGB(0, 0, 0, 0);
		TextPaint localTextPaint = new TextPaint();
		Paint localPaint = new Paint();
		canvas.drawBitmap(this.bg, 0.0F, 0.0F, localPaint);
		localTextPaint.setTextSize(0.7F * width);
		localTextPaint.setColor(Color.WHITE);
		Rect localRect = new Rect();
		localTextPaint.getTextBounds(this.text, 0, this.text.length(),
				localRect);
		int k = 1 + (int) localTextPaint.measureText(this.text);
		int l = localRect.height();
		int i1 = this.text.charAt(0);
		if ((i1 >= 913) && (i1 <= 65509))
			l = (int) (0.8F * l);
		canvas.drawText(this.text, (width - k) / 2,
				height - ((height - l) / 2), localTextPaint);
		return bitmap;
	}

	public CircleNumPicFactory setBg(Bitmap paramBitmap) {
		this.bg = paramBitmap;
		return this;
	}

	public CircleNumPicFactory setText(String paramString) {
		this.text = paramString;
		return this;
	}
}
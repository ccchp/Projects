package com.lex.wechathead.factory;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class AsyncImageLoader {
    //SoftReference是软引用，是为了更好的为了系统回收变量
    private static HashMap<Integer, SoftReference<Bitmap>> imageCache;
    private static Context mContext;
    
    private static AsyncImageLoader mAsyncImageLoader;
    
    private AsyncImageLoader(Context context) {
    	AsyncImageLoader.imageCache = new HashMap<Integer, SoftReference<Bitmap>>();
        AsyncImageLoader.mContext = context;
    }
    
    public static AsyncImageLoader getInstance(Context context){
    	if(mAsyncImageLoader == null){
    		mAsyncImageLoader = new AsyncImageLoader(context);
    	}
    	return mAsyncImageLoader;
    }
    
    public static Bitmap loadBitmapByPath(final int resID, final ImageView imageView, final ImageCallback imageCallback){
    	
    	 final Handler handler = new Handler() {
             public void handleMessage(Message message) {
                 imageCallback.imageLoaded((Bitmap) message.obj, imageView, resID);
             }
         };
    	
    	Bitmap bitmap = null;
        if (imageCache.containsKey(resID)) {
            //从缓存中获取
            SoftReference<Bitmap> softReference = imageCache.get(resID);
            bitmap = softReference.get();
            if (bitmap != null) {
                return bitmap;
            }
        }
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), resID);
        imageCache.put(resID, new SoftReference<Bitmap>(bitmap));
        Message message = handler.obtainMessage(0, bitmap);
        handler.sendMessage(message);
       
        return bitmap;
    }
    //回调接口
    public interface ImageCallback {
        public void imageLoaded(Bitmap imageBitmap,ImageView imageView, int resID);
    }
}
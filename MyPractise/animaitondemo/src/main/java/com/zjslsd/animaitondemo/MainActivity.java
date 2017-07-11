package com.zjslsd.animaitondemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjslsd.animaitondemo.view.GridImageViewGroup;

import static android.R.attr.x;

public class MainActivity extends AppCompatActivity {

    ImageView mImageView;
    ImageView mPlayBtn;
    boolean isPlay = false;
    TextView mCountTV;
    GridImageViewGroup mGroup;
    long x = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i = 1; i <= 50;i++){
            x = x * 2;
        }
        Log.i("lex", "x = " + x);
        initView();
    }

    private void initView() {
        //矢量动画
        mImageView = (ImageView) findViewById(R.id.imageview);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatedVectorDrawable animatedVectorDrawable =
                        (AnimatedVectorDrawable) mImageView.getDrawable();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if(animatedVectorDrawable.isRunning()) {
                        animatedVectorDrawable.stop();
                    } else {
                        animatedVectorDrawable.start();
                    }
                }
            }
        });

        mPlayBtn = (ImageView) findViewById(R.id.play_btn);
        mPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlay){
                    AnimatedVectorDrawable animatedVectorDrawable =
                            (AnimatedVectorDrawable) mImageView.getDrawable();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        if(animatedVectorDrawable.isRunning()) {
                            animatedVectorDrawable.stop();
                        } else {
                            animatedVectorDrawable.start();
                        }
                    }
                    mPlayBtn.setImageResource(R.drawable.circle_to_square_drawable);
                    isPlay = false;
                }else{
                    AnimatedVectorDrawable animatedVectorDrawable =
                            (AnimatedVectorDrawable) mImageView.getDrawable();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        if(animatedVectorDrawable.isRunning()) {
                            animatedVectorDrawable.stop();
                        } else {
                            animatedVectorDrawable.start();
                        }
                    }
                    mPlayBtn.setImageResource(R.drawable.square_to_circle_drawable);
                    isPlay = true;
                }
            }
        });

        mGroup = (GridImageViewGroup) findViewById(R.id.gridimageviewgroup);
        setAnimation();
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.mipmap.ic_launcher_round);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImageView();
            }
        });
        mGroup.addView(iv);

        mCountTV = (TextView) findViewById(R.id.count_tv);
        mCountTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationCount();
            }

        });
    }

    private void setAnimation(){
        LayoutTransition mLayoutTransition = new LayoutTransition();

        //设置每个动画持续的时间
        mLayoutTransition.setStagger(LayoutTransition.CHANGE_APPEARING, 50);
        mLayoutTransition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 50);
        mLayoutTransition.setStagger(LayoutTransition.APPEARING, 50);
        mLayoutTransition.setStagger(LayoutTransition.DISAPPEARING, 50);

        PropertyValuesHolder appearingScaleX = PropertyValuesHolder.ofFloat("scaleX", 0.5f, 1.0f);
        PropertyValuesHolder appearingScaleY = PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1.0f);
        PropertyValuesHolder appearingAlpha = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);
        ObjectAnimator mAnimatorAppearing = ObjectAnimator.ofPropertyValuesHolder(this, appearingAlpha, appearingScaleX, appearingScaleY);
        //为LayoutTransition设置动画及动画类型
        mLayoutTransition.setAnimator(LayoutTransition.APPEARING, mAnimatorAppearing);

        PropertyValuesHolder disappearingAlpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
        PropertyValuesHolder disappearingRotationY = PropertyValuesHolder.ofFloat("rotationY", 0.0f, 90.0f);
        ObjectAnimator mAnimatorDisappearing = ObjectAnimator.ofPropertyValuesHolder(this, disappearingAlpha, disappearingRotationY);
        //为LayoutTransition设置动画及动画类型
        mLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, mAnimatorDisappearing);


        ObjectAnimator mAnimatorChangeDisappearing = ObjectAnimator.ofFloat(null, "alpha", 1f, 0f);
        //为LayoutTransition设置动画及动画类型
        mLayoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, mAnimatorChangeDisappearing);

        ObjectAnimator mAnimatorChangeAppearing = ObjectAnimator.ofFloat(null, "alpha", 1f, 0f);
        //为LayoutTransition设置动画及动画类型
        mLayoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, mAnimatorChangeAppearing);

        //为mImageViewGroup设置mLayoutTransition对象
        mGroup.setLayoutTransition(mLayoutTransition);
    }

    private void addImageView() {
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.mipmap.ic_launcher);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGroup.removeView(v);
//                objectAnimatorAlpha(v);
            }
        });
        mGroup.addView(iv, 0);
//        objectAnimatorRotation(iv);
    }

    private void animationCount(){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1223344.13f);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float money = (float) animation.getAnimatedValue();
                mCountTV.setText(String.format("%.2f", money));
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();
    }

    private void animatorSet(){
        AnimatorSet animatorSet = new AnimatorSet();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1223344.13f);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float money = (float) animation.getAnimatedValue();
                mCountTV.setText(String.format("%.2f", money));
            }
        });

        int startColor = Color.parseColor("#FCA3AB");
        int endColor = Color.parseColor("#FB0435");
        ValueAnimator colorAnimator = ValueAnimator.ofObject(new TextArgbEvaluator(),startColor, endColor);
        colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                Log.e("Interpolator", "color---->" + color);
                mCountTV.setTextColor(color);
            }

        });
        colorAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                objectAnimatorAlpha(mCountTV);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animatorSet.playTogether(valueAnimator,colorAnimator);
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.start();
    }

    private void objectAnimatorAlpha(View view){
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, "alpha", 0f, 360f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setRepeatMode(ValueAnimator.REVERSE);
        alphaAnimation.setStartDelay(200);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation.start();

//        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.alpha_animator);
//        anim.setTarget(mCountTV);
//        anim.start();
    }
    private void objectAnimatorRotation(View view){
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setRepeatMode(ValueAnimator.REVERSE);
        alphaAnimation.setStartDelay(200);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation.start();

//        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.alpha_animator);
//        anim.setTarget(mCountTV);
//        anim.start();
    }
    class TextArgbEvaluator implements TypeEvaluator {

        public Object evaluate(float fraction, Object startValue, Object endValue) {
            int startInt = (Integer) startValue;
            int startA = (startInt >> 24) & 0xff;
            int startR = (startInt >> 16) & 0xff;
            int startG = (startInt >> 8) & 0xff;
            int startB = startInt & 0xff;

            int endInt = (Integer) endValue;
            int endA = (endInt >> 24) & 0xff;
            int endR = (endInt >> 16) & 0xff;
            int endG = (endInt >> 8) & 0xff;
            int endB = endInt & 0xff;

            return (int) ((startA + (int) (fraction * (endA - startA))) << 24) |
                    (int) ((startR + (int) (fraction * (endR - startR))) << 16) |
                    (int) ((startG + (int) (fraction * (endG - startG))) << 8) |
                    (int) ((startB + (int) (fraction * (endB - startB))));
        }
    }
}

package com.lex.rxandroiddemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by Lex lex on 2017/3/27.
 */

public class SimpleActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Subscriber<String> mTextSubscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };

        Subscriber<String> mToastSubscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };

    }
}

package com.lex.executorservicedemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private TextView mTextMessage;

    private Button mFixThreadPool, mCachedThreadPool, mSingleThreadPool, mScheduleThreadPool, mSingleScheduleThreadPool;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        mFixThreadPool = (Button) findViewById(R.id.fixTheadPool);
        mCachedThreadPool = (Button) findViewById(R.id.cacheTheadPool);
        mSingleThreadPool = (Button) findViewById(R.id.singalTheadPool);
        mScheduleThreadPool = (Button) findViewById(R.id.scheduleTheadPool);
        mSingleScheduleThreadPool = (Button) findViewById(R.id.singleScheduleTheadPool);

        mFixThreadPool.setOnClickListener(this);
        mCachedThreadPool.setOnClickListener(this);
        mSingleThreadPool.setOnClickListener(this);
        mScheduleThreadPool.setOnClickListener(this);
        mSingleScheduleThreadPool.setOnClickListener(this);
    }

    int flag = 1;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fixTheadPool:
                ExecutorService mFix = ThreadPollExecutor.getIntance().newFixedThreadPool();
                for (int i = 1; i <= 10; i++) {
                    final int index = i;
                    mFix.execute(new Runnable() {
                        @Override
                        public void run() {
                            String threadName = Thread.currentThread().getName();
                            Log.d(TAG, "run: " + threadName + "   正在执行第 " + index + " 个任务");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                break;
            case R.id.cacheTheadPool:
                ExecutorService mCached = ThreadPollExecutor.getIntance().newCachedThreadPool();
                for (int i = 1; i <= 10; i++) {
                    final int index = i;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mCached.execute(new Runnable() {
                        @Override
                        public void run() {
                            String threadName = Thread.currentThread().getName();
                            Log.d(TAG, "run: " + threadName + "   正在执行第 " + index + " 个任务");
                            try {
                                Thread.sleep(500 * index);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                break;
            case R.id.singalTheadPool:
                ExecutorService mSingle = ThreadPollExecutor.getIntance().newSingleThreadPool();
                for (int i = 1; i <= 10; i++) {
                    final int index = i;
                    mSingle.execute(new Runnable() {
                        @Override
                        public void run() {
                            String threadName = Thread.currentThread().getName();
                            Log.d(TAG, "run: " + threadName + "   正在执行第 " + index + " 个任务");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                break;
            case R.id.scheduleTheadPool:
                ScheduledExecutorService mSchedule = ThreadPollExecutor.getIntance().newScheduleThreadPool();
                mSchedule.schedule(new Runnable() {
                    @Override
                    public void run() {
                        String threadName = Thread.currentThread().getName();
                        Log.d(TAG, "run: " + threadName + "   正在执行第 " + 1 + " 个任务");
                    }
                }, 2, TimeUnit.SECONDS);
                mSchedule.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        String threadName = Thread.currentThread().getName();
                        Log.d(TAG, "run: " + threadName + "   正在执行第 " + flag + " 个任务");
                        flag ++;
                    }
                }, 2, 2, TimeUnit.SECONDS);
                break;
            case R.id.singleScheduleTheadPool:
                ScheduledExecutorService mSingleSchedule = ThreadPollExecutor.getIntance().newScheduleThreadPool();
                mSingleSchedule.schedule(new Runnable() {
                    @Override
                    public void run() {
                        String threadName = Thread.currentThread().getName();
                        Log.d(TAG, "run: " + threadName + "   正在执行第 " + 1 + " 个任务");
                    }
                }, 2, TimeUnit.SECONDS);
                break;
        }
    }
}

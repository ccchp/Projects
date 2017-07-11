package com.lex.mypractise.presenter;

import android.os.Handler;

import com.lex.mypractise.bean.User;
import com.lex.mypractise.biz.UserBiz.IUserBiz;
import com.lex.mypractise.biz.UserBiz.OnLoginListener;
import com.lex.mypractise.biz.UserBiz.UserBiz;
import com.lex.mypractise.view.IUserLoginView;


/**
 * Created by Lex lex on 2017/3/21.
 */

public class UserLoginPresenter {

    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private final Handler mHander = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void login(){
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                mHander.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainAcitivty(user);
                        userLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed() {
                mHander.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });
            }
        });
    }

    public void clear(){
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }

    public void toForgetActivity(){
        userLoginView.toForgetActivity();
    }

}

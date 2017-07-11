package com.lex.mypractise.view;

import com.lex.mypractise.bean.User;

/**
 * Created by Lex lex on 2017/3/21.
 */

public interface IUserLoginView {

    void showLoading();
    void hideLoading();

    void toMainAcitivty(User user);
    void showFailedError();
    void toForgetActivity();

    void clearUserName();
    void clearPassword();

    String getUserName();
    String getPassword();

}

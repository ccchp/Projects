package com.lex.mypractise.biz.UserBiz;

import com.lex.mypractise.bean.User;

/**
 * Created by Lex lex on 2017/3/21.
 */

public interface OnLoginListener {

    void loginSuccess(User user);

    void loginFailed();
}

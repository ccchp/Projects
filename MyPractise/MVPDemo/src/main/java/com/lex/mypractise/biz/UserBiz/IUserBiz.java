package com.lex.mypractise.biz.UserBiz;

/**
 * Created by Lex lex on 2017/3/21.
 */

public interface IUserBiz {

    public void login(String username, String password, OnLoginListener loginListener);
}

package com.lex.mypractise.biz.UserBiz;

import com.lex.mypractise.bean.User;

/**
 * Created by Lex lex on 2017/3/21.
 */

public class UserBiz implements IUserBiz {

    @Override
    public void login(final String username, final String password, final OnLoginListener loginListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if("lex".equals(username) && "123".equals(password)){
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    loginListener.loginSuccess(user);
                }else{
                    loginListener.loginFailed();
                }

            }
        }).start();
    }
}

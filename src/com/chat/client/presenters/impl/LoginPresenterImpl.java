package com.chat.client.presenters.impl;

import com.chat.client.presenters.LoginPresenter;
import com.chat.client.view.LoginView;

/**
 * Created by asus on 10.04.2014.
 */
public class LoginPresenterImpl implements LoginPresenter {


    private final LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        bind();
    }

    public void bind() {
        loginView.setPresenter(this);
    }

}

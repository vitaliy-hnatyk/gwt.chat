package com.chat.client.view.impl;

import com.chat.client.presenters.LoginPresenter;
import com.chat.client.view.LoginView;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by asus on 10.04.2014.
 */

public class LoginViewImpl extends Composite implements LoginView {

    private LoginPresenter presenter;

    public LoginViewImpl() {
    }

    public Widget asWidget() {
        return this;
    }

    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
    }


}

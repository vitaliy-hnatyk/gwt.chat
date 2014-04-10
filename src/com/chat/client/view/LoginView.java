package com.chat.client.view;

import com.chat.client.presenters.LoginPresenter;
import com.chat.client.presenters.MainPresenter;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * Created by asus on 10.04.2014.
 */
public interface LoginView extends IsWidget {
    void setPresenter(LoginPresenter presenter);
}

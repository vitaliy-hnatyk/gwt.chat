package com.chat.client.view;

import com.chat.client.presenters.MainPresenter;
import com.google.gwt.user.client.ui.IsWidget;

public interface MainView extends IsWidget{
	void setPresenter(MainPresenter presenter);
	void showLogin();
	void hideLogin();
	void setMessages(String text, String color);
    void setUser(String username);
    void addRoom(String roomName);
}

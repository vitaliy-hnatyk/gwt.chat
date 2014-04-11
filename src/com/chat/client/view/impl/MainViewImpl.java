package com.chat.client.view.impl;

import com.chat.client.presenters.MainPresenter;
import com.chat.client.ui.ChatWidget;
import com.chat.client.ui.LoginWidget;
import com.chat.client.view.MainView;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class MainViewImpl extends Composite implements MainView {
	private MainPresenter presenter;
	private Button showPhotoList;

	private LoginWidget loginDialog = new LoginWidget();
	private ChatWidget chatPlace = new ChatWidget();

	public MainViewImpl() {
		initWidget(chatPlace);

		bind();
	}

	public Widget asWidget() {
		return this;
	}

	public void bind() {
		loginDialog.getButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!loginDialog.getText().equals("")) {
					presenter.login(loginDialog.getText());
					loginDialog.hide();
				}
			}
		});



		chatPlace.getSendButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent clickEvent) {
				if(!chatPlace.getMessage().equals(""))
					presenter.sendMessage(chatPlace.getMessage());
			}
		});
	}

	@Override
	public void setPresenter(MainPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setMessages(String text, String color) {
		chatPlace.setMessages(text,color);
	}

	@Override
	public void showLogin() {
		loginDialog.show();
	}

	@Override
	public void hideLogin() {
		loginDialog.hide();
	}
}

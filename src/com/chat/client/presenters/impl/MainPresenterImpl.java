package com.chat.client.presenters.impl;

import com.chat.client.Tokens;
import com.chat.client.presenters.MainPresenter;
import com.chat.client.view.MainView;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;


public class MainPresenterImpl implements MainPresenter {

	private final MainView mainView;

	public MainPresenterImpl(MainView mainView) {
		this.mainView = mainView;
		bind();
	}

	public void bind() {
		mainView.setPresenter(this);
	}

	public void go(final HasWidgets container) {
		//container.clear();
		container.add(mainView.asWidget());

	}

	public void onShowMainButtonClicked() {
		History.newItem(Tokens.LIST);
	}
}

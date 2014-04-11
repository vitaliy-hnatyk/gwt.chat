package com.chat.client.presenters;

public interface MainPresenter {
	public void onShowMainButtonClicked();
	void login(final String username);
	void sendMessage(String message);
}

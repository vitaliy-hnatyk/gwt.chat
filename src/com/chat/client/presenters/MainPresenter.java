package com.chat.client.presenters;

public interface MainPresenter {
	void login(final String username);
	void sendMessage(String message);
    void createRoom(String newRoom);
    void connectRoom(String s);
}

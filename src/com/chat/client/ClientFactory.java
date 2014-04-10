package com.chat.client;

import com.chat.client.view.MainView;
import com.chat.shared.ChatServiceAsync;
import com.google.web.bindery.event.shared.EventBus;


public interface ClientFactory {

    public EventBus getEventBus();

    ChatServiceAsync getChatServices();

	MainView getMainView();
}

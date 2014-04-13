package com.chat.client;

import com.chat.client.view.MainView;
import com.google.web.bindery.event.shared.EventBus;


public interface ClientFactory {

    public EventBus getEventBus();

    ChatRoomServiceAsync getChatServices();

	MainView getMainView();
}

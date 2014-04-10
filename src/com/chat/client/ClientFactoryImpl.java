package com.chat.client;

import com.chat.client.ClientFactory;
import com.chat.client.view.MainView;
import com.chat.client.view.impl.MainViewImpl;
import com.chat.shared.ChatService;
import com.chat.shared.ChatServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;



public class ClientFactoryImpl implements ClientFactory {

	private static EventBus eventBus;
	private static ChatServiceAsync rpcService;

	private static MainView mainView;
	 
	
	public EventBus getEventBus() {
		if (eventBus == null) eventBus = new SimpleEventBus();
		return eventBus;
	}

	public ChatServiceAsync getChatServices() {
		if (rpcService == null) rpcService = GWT.create(ChatService.class);
		return rpcService;
	}



	public MainView getMainView() {
		if (mainView == null) mainView = new MainViewImpl();
		return mainView;
	}
}

package com.chat.client;

import com.chat.client.view.MainView;
import com.chat.client.view.impl.MainViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;



public class ClientFactoryImpl implements ClientFactory {

	private static EventBus eventBus;
	private static ChatRoomServiceAsync rpcService;

	private static MainView mainView;
	 
	
	public EventBus getEventBus() {
		if (eventBus == null) eventBus = new SimpleEventBus();
		return eventBus;
	}

	public ChatRoomServiceAsync getChatServices() {
		if (rpcService == null) rpcService = GWT.create(ChatRoomService.class);
		return rpcService;
	}



	public MainView getMainView() {
		if (mainView == null) mainView = new MainViewImpl();
		return mainView;
	}
}

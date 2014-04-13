package com.chat.client;

import com.chat.client.bundels.Resources;
import com.chat.client.presenters.impl.MainPresenterImpl;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Created by asus on 09.04.2014.
 */
public class Chat implements EntryPoint {

	ClientFactory clientFactory;
	SimplePanel container = new SimplePanel();
	private ChatRoomServiceAsync chatService;

	static {
		Resources.IMPL.basic().ensureInjected();
	}

	@Override
	public void onModuleLoad() {

		clientFactory = GWT.create(ClientFactory.class);
		chatService = clientFactory.getChatServices();
		container.setHeight("100%");
		container.setWidth("100%");
		RootPanel.get().add(container, 0, 0);
		doMain();
		bind();


	}

	private void doMain() {
		new MainPresenterImpl(clientFactory.getMainView())
			.go(container);
	}

	private void bind() {

	}
}

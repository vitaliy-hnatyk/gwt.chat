package com.chat.client.presenters.impl;

import com.chat.client.ClientFactory;
import com.chat.client.Tokens;
import com.chat.client.presenters.MainPresenter;
import com.chat.client.view.MainView;
import com.chat.shared.ChatServiceAsync;
import com.chat.shared.Message;
import com.chat.shared.StatusUpdate;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import net.zschech.gwt.comet.client.CometClient;
import net.zschech.gwt.comet.client.CometListener;
import net.zschech.gwt.comet.client.CometSerializer;
import net.zschech.gwt.comet.client.SerialTypes;

import java.io.Serializable;
import java.util.List;


public class MainPresenterImpl implements MainPresenter {
	private ClientFactory clientFactory;

	private ChatServiceAsync chatService;
	private final MainView mainView;
	private CometClient cometClient;

	private String username;

	public MainPresenterImpl(MainView mainView) {
		this.mainView = mainView;
		clientFactory = GWT.create(ClientFactory.class);
		chatService = clientFactory.getChatServices();
		bind();
	}

	public void bind() {
		mainView.setPresenter(this);

		chatService.getUsername(new AsyncCallback<String>() {
			@Override
			public void onSuccess(String username) {
				if (username == null) {
					mainView.showLogin();
				}
				else {
					//loggedOn(username);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				//output(caught.toString(), "red");
				mainView.showLogin();
			}
		});
	}

	@Override
	public void login(final String username) {
		chatService.login(username, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				loggedOn(username);
			}

			@Override
			public void onFailure(Throwable caught) {
				mainView.setMessages(caught.toString(), "red");
			}
		});
	}

	private void loggedOn(String username) {
		this.username = username;
		mainView.setMessages("logged in as " + username, "silver");
		CometSerializer serializer = GWT.create(ChatCometSerializer.class);
		cometClient = new CometClient(GWT.getModuleBaseURL() + "comet", serializer, new CometListener() {
			public void onConnected(int heartbeat) {
				mainView.setMessages("connected " + heartbeat, "silver");
			}

			public void onDisconnected() {
				mainView.setMessages("disconnected", "silver");
			}

			public void onError(Throwable exception, boolean connected) {
				mainView.setMessages("error " + connected + " " + exception, "red");
			}

			public void onHeartbeat() {
				mainView.setMessages("heartbeat", "silver");
			}

			public void onRefresh() {
				mainView.setMessages("refresh", "silver");
			}

			public void onMessage(List<? extends Serializable> messages) {
				for (Serializable message : messages) {
					if (message instanceof Message) {
						Message chatMessage = (Message) message;
						mainView.setMessages(chatMessage.getUserName() + ": " + chatMessage.getMessage(), "black");
					}
					else if (message instanceof StatusUpdate) {
						StatusUpdate statusUpdate = (StatusUpdate) message;
						mainView.setMessages(statusUpdate.getUserName() + ": " + statusUpdate.getStatus(), "green");
					}
					else {
						mainView.setMessages("unrecognised message " + message, "red");
					}
				}
			}
		});
		cometClient.start();
	}

	@Override
	public void sendMessage(String message) {
		chatService.send(message, new AsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
			}

			@Override
			public void onFailure(Throwable caught) {
				mainView.setMessages(caught.toString(), "red");
			}
		});
	}


	public void go(final HasWidgets container) {
		container.clear();
		container.add(mainView.asWidget());

	}

	public void onShowMainButtonClicked() {
		History.newItem(Tokens.LIST);
	}

	@SerialTypes({Message.class, StatusUpdate.class })
	public static abstract class ChatCometSerializer extends CometSerializer {
	}
}

package com.chat.client.presenters.impl;

import com.chat.client.Tokens;
import com.chat.client.core.*;
import com.chat.client.presenters.MainPresenter;
import com.chat.client.view.MainView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

import java.util.ArrayList;


public class MainPresenterImpl implements MainPresenter {

    private final MainView mainView;
    private WebSocketClient ws;

    private String username;
    private String roomName = "All";
    private ArrayList<String> roomList = new ArrayList<String>();

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;

        bind();
    }

    public void bind() {
        mainView.setPresenter(this);

        if(ws == null ||
                ws.getReadyState()==1){
            mainView.showLogin();

        }

    }

    @Override
    public void login(final String username) {
        loggedOn(username);
    }

    private void loggedOn(String username) {
        this.username = username;
        mainView.setMessages("logged in as " + username, "silver");
        mainView.setUser(username);
        ws = WebSocketClient.create(GWT.getModuleBaseURL().replace("http", "ws") + "ws?name=" + username+"&room="+roomName);
        ws.setOnOpen(new OpenHandler() {
            @Override
            public void onOpen(WebSocketClient webSocket) {
                webSocket.send(roomName, "Help");
                mainView.setMessages("connected ", "silver");
            }
        });
        ws.setOnClose(new CloseHandler() {
            @Override
            public void onClose(WebSocketClient webSocket) {
                    mainView.setMessages("disconnected", "silver");
            }
        });
        ws.setOnError(new ErrorHandler() {
            @Override
            public void onError(WebSocketClient webSocket) {
                mainView.setMessages("error " + webSocket.getBufferedAmount(), "red");
            }
        });
        ws.setOnMessage(new MessageHandler() {
            @Override
            public void onMessage(WebSocketClient webSocket, MessageEvent event) {
                JSONValue value = JSONParser.parse(event.getData());
                JSONObject obj = value.isObject();

                String roomName = String.valueOf(obj.get("room").isString());

                if(!roomList.contains(roomName)) {
                    roomList.add(roomName);
                }
                String message = String.valueOf(obj.get("message").isString());
                mainView.setMessages(message, "black");
            }
        });
    }

    @Override
    public void sendMessage(String message) {
        ws.send(roomName, message);
    }


    public void go(final HasWidgets container) {
        container.clear();
        container.add(mainView.asWidget());

    }

    public void onShowMainButtonClicked() {
        History.newItem(Tokens.LIST);
    }


}

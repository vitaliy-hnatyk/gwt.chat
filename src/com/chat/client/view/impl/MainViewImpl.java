package com.chat.client.view.impl;

import com.chat.client.presenters.MainPresenter;
import com.chat.client.ui.ChatWidget;
import com.chat.client.ui.LoginWidget;
import com.chat.client.view.MainView;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class MainViewImpl extends Composite implements MainView {
    private MainPresenter presenter;

    private LoginWidget loginDialog = new LoginWidget();
    private ChatWidget chatPlace = new ChatWidget();

    public MainViewImpl() {
        initWidget(chatPlace);
        chatPlace.setVisible(false);
        bind();
    }

    public Widget asWidget() {
        return this;
    }

    public void bind() {
        loginDialog.getButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (!loginDialog.getText().equals("")) {
                    presenter.login(loginDialog.getText());
                    loginDialog.hide();
                    chatPlace.setVisible(true);
                }
            }
        });

        chatPlace.getAddRoom().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                String newRoom = chatPlace.getInputRoom().getText();
                if (!newRoom.equals("")) {
                    chatPlace.getInputRoom().setText("");
                    presenter.createRoom(newRoom);
                }
            }
        });

        chatPlace.getSendButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                String mess = chatPlace.getMessage();
                if (!mess.equals(""))
                    presenter.sendMessage(mess);
            }
        });
    }

    @Override
    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setMessages(String text, String color) {
        chatPlace.setMessages(text, color);
    }

    @Override
    public void setUser(String username) {
        chatPlace.getUserName().setText(username);
    }

    @Override
    public void addRoom(String roomName) {
        Anchor an = new Anchor(roomName);
        an.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        an.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                presenter.connectRoom(((Anchor) event.getSource()).getText());
            }
        });
        chatPlace.getRoomHtml().add(an);
    }

    @Override
    public void showLogin() {
        loginDialog.show();
    }

    @Override
    public void hideLogin() {
        loginDialog.hide();
    }
}
